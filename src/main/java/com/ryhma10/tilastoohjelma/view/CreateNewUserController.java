package com.ryhma10.tilastoohjelma.view;

import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.OriannaException;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.status.ShardStatus;

import com.ryhma10.tilastoohjelma.MainApp;
import com.ryhma10.tilastoohjelma.model.ModelAccessObject;
import com.ryhma10.tilastoohjelma.view.utilities.AlertFactory;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;


public class CreateNewUserController {

    private MainApp mainApp;
    private Stage createNewUserStage;
    private AlertFactory alertFactory;
    private ResourceBundle textBundle;

    @FXML
    private Accordion centerAccordion;
    @FXML
    private TitledPane requiredInformationPane, optionalInformationPane;
    @FXML
    private ProgressIndicator progressIndicator;
    @FXML
    private TextField profileNameTextField, riotAPIKeyTextField;
    @FXML
    private PasswordField profilePasswordField;
    @FXML
    private ChoiceBox<String> riotRegionChoiceBox, languageChoiceBox;
    @FXML
    private Button testAPIKeyButton, helpButton, cancelButton, doneButton;
    @FXML
    private Label createNewProfileLabel, profileNameLabel, profilePasswordLabel, riotAPIKeyLabel, riotRegionLabel, languageLabel;
    @FXML
    private Tooltip testAPIKeyButtonTooltip;

    /**
     * Empty constructor.
     */
    public CreateNewUserController() {
        //Constructor
    }

    /**
     * Initializes the CreateNewUser-window.
     */
    public void initialize() {
        if (progressIndicator.isVisible()) {
            progressIndicator.setVisible(false);
        }
        profileNameTextField.setText("");
        profilePasswordField.setText("");
        riotAPIKeyTextField.setText("");
        centerAccordion.setExpandedPane(requiredInformationPane);
        Platform.runLater(() -> {
            alertFactory = new AlertFactory(mainApp.getTextBundle());
            for (Region region : Region.values()) {
                riotRegionChoiceBox.getItems().add(region.name().replace("_", " "));
            }
            riotRegionChoiceBox.setValue(Region.NORTH_AMERICA.name().replace("_", " "));
            List<String> languageArrayList = mainApp.getLanguageArrayList();
            ObservableList<String> languageObservableList = FXCollections.observableList(languageArrayList);
            languageChoiceBox.getItems().addAll(languageObservableList);
            languageChoiceBox.setValue(textBundle.getString(mainApp.getCurrentLocale().toString()));
        });
    }

    /**
     * Sets the MainApp.
     * @param mainApp The MainApp.
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Sets the Stage.
     * @param createNewUserStage The Stage.
     */
    public void setCreateNewUserStage(Stage createNewUserStage) {
        this.createNewUserStage = createNewUserStage;
    }

    /**
     * Cancels the creation of a new SoftwareProfile.
     * @param actionEvent Interacting with the button, defined in CreateNewUser.fxml.
     */
    @FXML
    public void handleCancel(ActionEvent actionEvent) {
        createNewUserStage.close();
    }

    /**
     * Detects if Enter is pressed.
     * @param keyEvent Any KeyEvent.
     */
    @FXML
    public void onEnter(KeyEvent keyEvent) {
        if(keyEvent.getCode().equals(KeyCode.ENTER)) {
            System.out.println("Enter pressed");
            handleDone(new ActionEvent());
        }
    }

    /**
     * Opens the help-window. Help not yet implemented.
     * @param actionEvent Interacting with the button, defined in CreateNewUser.fxml.
     */
    @FXML
    public void handleCreateNewProfileHelp(ActionEvent actionEvent) {
        System.out.println("Not yet implemented");
        //TODO
    }

    /**
     * Tests the String inputted into the API-Key TextField.
     * @param actionEvent Interacting with the button, defined in CreateNewUser.fxml.
     */
    @FXML
    public void handleTestAPIKey(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            progressIndicator.setVisible(true);
            testAPIKeyButton.setText(textBundle.getString("button.testingAPIKey"));
            testAPIKeyButton.setDisable(true);
        });
        new Thread(() -> {
            Orianna.setRiotAPIKey(riotAPIKeyTextField.getText());
            ShardStatus status;
            try {
                status = ShardStatus.withRegion(Region.NORTH_AMERICA).get();
                if (status.exists()) {
                    Platform.runLater(() -> {
                        Alert apiKeySuccessAlert = alertFactory.createAlert("APIKeyTest:Pass");
                        apiKeySuccessAlert.show();
                    });
                }
                else {
                    System.out.println("Unknown error. Status: " + status.exists());
                }
            } catch (OriannaException oe) {
                System.out.println(oe.getClass().getSimpleName());
                Platform.runLater(() -> {
                    Alert apiKeyFailAlert = alertFactory.createAlert("APIKeyTest:Fail");
                    apiKeyFailAlert.show();
                });
            }
            Platform.runLater(() -> {
                progressIndicator.setVisible(false);
                testAPIKeyButton.setText(textBundle.getString("button.testAPIKey"));
                testAPIKeyButton.setDisable(false);
            });
        }).start();
    }

    /**
     * Exports the given values into the database. Creates a new SoftwareProfile.
     * @param actionEvent Interacting with the button, defined in CreateNewUser.fxml.
     */
    @FXML
    public void handleDone(ActionEvent actionEvent) {
        if(profileNameTextField.getText().length() > 0 && profilePasswordField.getText().length() > 3) {
            new Thread(() -> {
                String riotAPIKey = riotAPIKeyTextField.getText();
                if (riotAPIKey.equals("")) {
                    riotAPIKey = null;
                }
                progressIndicator.setVisible(true);
                ModelAccessObject modelAccessObject = new ModelAccessObject();
                String resultStringFromMethod = modelAccessObject.createProfile(profileNameTextField.getText(), profilePasswordField.getText(),
                        riotRegionChoiceBox.getSelectionModel().getSelectedItem().toString().replace(" ", "_"),
                        getSelectedLanguage(languageChoiceBox.getSelectionModel().getSelectedIndex()), null, riotAPIKey);
                Platform.runLater(() -> {
                    switch (resultStringFromMethod) {
                        case "Profile successfully created":
                            System.out.println(resultStringFromMethod);
                            Alert successAlert = alertFactory.createAlert(resultStringFromMethod);
                            successAlert.show();
                            createNewUserStage.close();
                            break;
                        case "Profile already exists":
                        case "Database connection error":
                            System.out.println(resultStringFromMethod);
                            Alert errorAlert = alertFactory.createAlert(resultStringFromMethod);
                            errorAlert.show();
                            break;
                    }
                    progressIndicator.setVisible(false);
                });
            }).start();
        }
        else {
            Alert userInputErrorAlert = alertFactory.createAlert("CreateProfile:UserInputError");
            userInputErrorAlert.showAndWait();
        }
    }

    /**
     * Selects the language that the user has selected.
     * @param langIndex The index of the language; Corresponds to the index of the file when read from the folder due to
     *                  the way the ChoiceBox lists languages in a similar manner.
     * @return String of language and country, e.g. "en_US" or "fi_FI".
     */
    private String getSelectedLanguage(int langIndex) {
        String selectedLanguage;
        selectedLanguage = languageChoiceBox.getSelectionModel().getSelectedItem().toString();
        String langString = mainApp.getLanguageDirFiles().get(langIndex);
        Properties propertiesHelper = new Properties();
        try {
            propertiesHelper.load(new FileInputStream(mainApp.getLanguageDirPath() + "/" + langString));
        } catch (IOException e) {
            e.printStackTrace();
        }
        selectedLanguage = propertiesHelper.getProperty("localeINFO");
        System.out.println(selectedLanguage);
        return selectedLanguage;
    }

    /**
     * Getter for the ResourceBundle.
     * @return The ResourceBundle
     */
    public ResourceBundle getTextBundle() {
        return textBundle;
    }

    /**
     * Setter for the ResourceBundle.
     * @param textBundle The ResourceBundle to be set.
     */
    public void setTextBundle(ResourceBundle textBundle) {
        this.textBundle = textBundle;
    }


}
