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
    private Button testAPIKeyButton;

    public CreateNewUserController() {
        //Constructor
    }

    public void initialize() {
        alertFactory = new AlertFactory();
        if (progressIndicator.isVisible()) {
            progressIndicator.setVisible(false);
        }
        profileNameTextField.setText("");
        profileNameTextField.setPromptText("New profile name");
        profilePasswordField.setText("");
        profilePasswordField.setPromptText("Password");
        riotAPIKeyTextField.setText("");
        riotAPIKeyTextField.setPromptText("Your Riot API-key");
        centerAccordion.setExpandedPane(requiredInformationPane);
        Platform.runLater(() -> {
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

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setCreateNewUserStage(Stage createNewUserStage) {
        this.createNewUserStage = createNewUserStage;
    }

    @FXML
    public void handleCancel(ActionEvent actionEvent) {
        createNewUserStage.close();
    }

    @FXML
    public void onEnter(KeyEvent keyEvent) throws IOException {
        if(keyEvent.getCode().equals(KeyCode.ENTER)) {
            System.out.println("Enter pressed");
            handleDone(new ActionEvent());
        }
    }

    @FXML
    public void handleCreateNewProfileHelp(ActionEvent actionEvent) {
        System.out.println("Not yet implemented");
        //TODO
    }

    @FXML
    public void handleTestAPIKey(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            progressIndicator.setVisible(true);
            testAPIKeyButton.setText("Testing key...");
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
                testAPIKeyButton.setText("Test API-key");
                testAPIKeyButton.setDisable(false);
            });
        }).start();
    }

    @FXML
    public void handleDone(ActionEvent actionEvent) throws IOException {
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

    public ResourceBundle getTextBundle() {
        return textBundle;
    }

    public void setTextBundle(ResourceBundle textBundle) {
        this.textBundle = textBundle;
    }


}
