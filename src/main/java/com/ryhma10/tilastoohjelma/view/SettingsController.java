package com.ryhma10.tilastoohjelma.view;

import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.OriannaException;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.status.ShardStatus;
import com.ryhma10.tilastoohjelma.MainApp;
import com.ryhma10.tilastoohjelma.model.ModelAccessObject;
import com.ryhma10.tilastoohjelma.model.SoftwareProfile;
import com.ryhma10.tilastoohjelma.view.utilities.AlertFactory;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class SettingsController {

    private MainApp mainApp;
    private MainController mainController;
    private Stage settingsStage;
    private List<Button> buttonList;
    private SingleSelectionModel<Tab> tabSingleSelectionModel;
    private AlertFactory alertFactory;
    private SoftwareProfile currentProfile;
    private ResourceBundle textBundle;

    @FXML
    private TabPane settingsTabPane;
    @FXML
    private Tab apiAndUserInterfaceTab, profileTab;
    @FXML
    private TextField apiKeyTextField;
    @FXML
    private Button testAPIKeyButton, helpButton, cancelButton, applyButton, saveButton, showOrHidePasswordButton, hiddenShowOrHidePasswordButton;
    @FXML
    private Label changeAPIKeyLabel, changeRegionLabel, changeLanguageLabel, changeProfilePasswordLabel, confirmProfilePasswordLabel;
    @FXML
    private ChoiceBox<String> changeRegionChoiceBox, changeLanguageChoiceBox;
    @FXML
    private CheckBox makeRegionDefaultCheckBox, makeLanguageDefaultCheckBox;
    @FXML
    private PasswordField profilePasswordField, confirmProfilePasswordField;

    /**
     * Default constructor
     */
    public SettingsController() {
        //Default Constructor
    }

    /**
     * Initializes the Settings-window.
     */
    public void initialize() {
        tabSingleSelectionModel = settingsTabPane.getSelectionModel();
        tabSingleSelectionModel.select(apiAndUserInterfaceTab);
        profilePasswordField.setText("");
        confirmProfilePasswordField.setText("");
        Platform.runLater(() -> {
            alertFactory = new AlertFactory(textBundle);
            if (buttonList == null) {
                makeButtonList();
            }
            currentProfile = mainApp.getProfile();
            mainController = mainApp.getMainController();
            if (currentProfile.getRiotAPIKey() == null) {
                apiKeyTextField.setText("");
            }
            else {
                apiKeyTextField.setText(currentProfile.getRiotAPIKey());
            }
            changeRegionChoiceBox.getItems().clear();
            for (Region region : Region.values()) {
                changeRegionChoiceBox.getItems().add(region.name().replace("_", " "));
            }
            if (currentProfile.getDefaultRegion() != null) {
                changeRegionChoiceBox.setValue(currentProfile.getDefaultRegion().replace("_", " "));
            }
            else {
                changeRegionChoiceBox.setValue(Region.NORTH_AMERICA.name().replace("_", " "));
            }
            makeRegionDefaultCheckBox.setSelected(false);
            if (!changeLanguageChoiceBox.getItems().isEmpty()) {
                changeLanguageChoiceBox.getSelectionModel().clearSelection();
                changeLanguageChoiceBox.getItems().clear();
            }
            List<String> languageArrayList = mainApp.getLanguageArrayList();
            ObservableList<String> languageObservableList = FXCollections.observableList(languageArrayList);
            changeLanguageChoiceBox.getItems().addAll(languageObservableList);
            changeLanguageChoiceBox.setValue(textBundle.getString(currentProfile.getDefaultLanguage()));
            makeLanguageDefaultCheckBox.setSelected(false);
        });
    }

    /**
     * Updates the settings depending on which have been changed. Updates either the SoftwareProfile of this session or the SoftwareProfile in the
     * database depending on user choices.
     * @return String of the result-variable. Can have three values:
     * "Nothing changed": No settings have been changed.
     * "Settings changed": At least one of the settings have been changed.
     * "Fail": Only happens when trying to change the password and the given passwords does not match.
     */
    public String updateSettings() {
        boolean dbUpdateRequired = false;
        boolean dbUpdateRegion = false;
        boolean dbUpdateLanguage = false;
        String result = "Nothing changed";
        if ((!apiKeyTextField.getText().equals("")) && (!apiKeyTextField.getText().equals(currentProfile.getRiotAPIKey()))) {
            Orianna.setRiotAPIKey(apiKeyTextField.getText());
            currentProfile.setRiotAPIKey(apiKeyTextField.getText());
            result = "Settings changed";
            dbUpdateRequired = true;
        }
        if (!changeRegionChoiceBox.getSelectionModel().getSelectedItem().replace(" ", "_").equals(currentProfile.getDefaultRegion()) || makeRegionDefaultCheckBox.isSelected()) {
            currentProfile.setDefaultRegion(changeRegionChoiceBox.getSelectionModel().getSelectedItem().replace(" ", "_"));
            Orianna.setDefaultRegion(Region.valueOf(currentProfile.getDefaultRegion()));
            result = "Settings changed";
            if (makeRegionDefaultCheckBox.isSelected()) {
                dbUpdateRegion = true;
                dbUpdateRequired = true;
            }
        }
        if (!getSelectedLanguage(changeLanguageChoiceBox.getSelectionModel().getSelectedIndex()).equals(currentProfile.getDefaultLanguage()) || makeLanguageDefaultCheckBox.isSelected()) {
            currentProfile.setDefaultLanguage(getSelectedLanguage(changeLanguageChoiceBox.getSelectionModel().getSelectedIndex()));
            this.textBundle = ResourceBundle.getBundle("languages/TextResources", Locale.forLanguageTag(currentProfile.getDefaultLanguage().replace("_", "-")));
            mainApp.setTextBundle(this.textBundle);
            result = "Settings changed";
            if (makeLanguageDefaultCheckBox.isSelected()) {
                dbUpdateLanguage = true;
                dbUpdateRequired = true;
            }
        }
        if (!profilePasswordField.getText().equals("") && profilePasswordField.getText().length() > 3) {
            if (profilePasswordField.getText().equals(confirmProfilePasswordField.getText())) {
                currentProfile.setProfilePassword(confirmProfilePasswordField.getText());
                result = "Settings changed";
                dbUpdateRequired = true;
            }
            else {
                result = "Fail";
                Platform.runLater(() -> {
                    alertFactory = new AlertFactory(textBundle);
                    Alert nonMatchingPasswordsAlert = alertFactory.createAlert("Passwords do not match");
                    nonMatchingPasswordsAlert.show();
                });
            }
        }
        if (dbUpdateRequired) {
            Platform.runLater(() -> {
                for (Button button : buttonList) {
                    button.setDisable(true);
                }
            });
            boolean finalDbUpdateRegion = dbUpdateRegion;
            boolean finalDbUpdateLanguage = dbUpdateLanguage;
            new Thread(() -> {
                ModelAccessObject modelAccessObject = new ModelAccessObject();
                modelAccessObject.updateProfile(currentProfile, finalDbUpdateRegion, finalDbUpdateLanguage);
                Platform.runLater(() -> {
                    for (Button button : buttonList) {
                        button.setDisable(false);
                    }
                });
            }).start();
        }
        if (result.equals("Settings changed")) {
            mainController.setCurrentProfile(currentProfile);
            mainApp.setProfile(currentProfile);
            mainController.setTextBundle(this.textBundle);
            mainController.printProfileData();
            mainController.setTexts();
        }
        return result;
    }

    /**
     * Cancels any changes done and closes the settingsStage.
     * @param actionEvent Interacting with the button, defined in Settings.fxml.
     */
    @FXML
    public void handleCancel(ActionEvent actionEvent) {
        settingsStage.close();
    }

    /**
     * Applies settings using updateSettings()-method. Does different things depending on return from updateSettings().
     * @param actionEvent Interacting with the button, defined in Settings.fxml.
     */
    @FXML
    public void handleApply(ActionEvent actionEvent) {
        String result = updateSettings();
        switch (result) {
            case "Settings changed":
                Platform.runLater(() -> {
                    initialize();
                    updateTexts();
                });
                break;
            case "Fail":
                Platform.runLater(() -> {
                    String passwordMemory = profilePasswordField.getText();
                    initialize();
                    updateTexts();
                    profilePasswordField.setText(passwordMemory);
                    tabSingleSelectionModel.select(profileTab);
                });
                break;
            case "Nothing changed":
            default:
        }
    }

    /**
     * Runs the updateSettings()-method. If method is successfully ran, will close settingsStage;
     * Otherwise opens the profileTab.
     * @param actionEvent Interacting with the button, defined in Settings.fxml.
     */
    @FXML
    public void handleSave(ActionEvent actionEvent) {
        String result = updateSettings();
        if (!result.equals("Fail")) {
            settingsStage.close();
        }
        else {
            tabSingleSelectionModel.select(profileTab);
        }
    }

    /**
     * Tests the String inputted into the API-Key TextField.
     * @param actionEvent Interacting with the button, defined in Settings.fxml.
     */
    @FXML
    public void handleTestAPIKey(ActionEvent actionEvent) {
        testAPIKeyButton.setText(textBundle.getString("button.testingAPIKey"));
        for (Button button : buttonList) {
            button.setDisable(true);
        }
        new Thread(() -> {
            Orianna.setRiotAPIKey(apiKeyTextField.getText());
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
                testAPIKeyButton.setText(textBundle.getString("button.testAPIKey"));
                for (Button button : buttonList) {
                    button.setDisable(false);
                }
            });
        }).start();
    }

    /**
     * Shows or hides the password. Not yet implemented.
     * @param actionEvent Interacting with the button, defined in Settings.fxml.
     */
    @FXML
    public void handleShowOrHide(ActionEvent actionEvent) {
    }

    /**
     * Opens the program's help. Help not yet implemented.
     * @param actionEvent Interacting with the button, defined in Settings.fxml.
     */
    @FXML
    public void handleHelp(ActionEvent actionEvent) {
        System.out.println("Not yet implemented");
        //TODO
    }

    /**
     * Selects the language that the user has selected.
     * @param langIndex The index of the language; Corresponds to the index of the file when read from the folder due to
     *                  the way the ChoiceBox lists languages in a similar manner.
     * @return String of language and country, e.g. "en_US" or "fi_FI".
     */
    private String getSelectedLanguage(int langIndex) {
        String selectedLanguage;
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
     * Create a list of buttons in the Settings.fxml.
     */
    private void makeButtonList() {
        buttonList = Arrays.asList(testAPIKeyButton, helpButton, cancelButton, applyButton, saveButton, showOrHidePasswordButton, hiddenShowOrHidePasswordButton);
    }

    /**
     * Updates UI texts, meant for updating the language without having to reload the fxml.
     */
    private void updateTexts() {
        settingsStage.setTitle(textBundle.getString("windowTitle.settings"));
        apiAndUserInterfaceTab.setText(textBundle.getString("tab.apiAndUserInterface"));
        profileTab.setText(textBundle.getString("tab.profile"));
        apiKeyTextField.setPromptText(textBundle.getString("promptText.yourRiotAPIKey"));
        profilePasswordField.setPromptText(textBundle.getString("promptText.newPassword"));
        confirmProfilePasswordField.setPromptText(textBundle.getString("promptText.retypeNewPassword"));
        changeAPIKeyLabel.setText(textBundle.getString("label.changeRiotAPIKey"));
        changeRegionLabel.setText(textBundle.getString("label.changeRegion"));
        changeLanguageLabel.setText(textBundle.getString("label.changeLanguage"));
        changeProfilePasswordLabel.setText(textBundle.getString("label.changePassword"));
        confirmProfilePasswordLabel.setText(textBundle.getString("label.confirmNewPassword"));
        makeRegionDefaultCheckBox.setText(textBundle.getString("checkBox.makeDefaultForProfile"));
        makeLanguageDefaultCheckBox.setText(textBundle.getString("checkBox.makeDefaultForProfile"));
        testAPIKeyButton.setText(textBundle.getString("button.testAPIKey"));
        helpButton.setText(textBundle.getString("button.help"));
        cancelButton.setText(textBundle.getString("button.cancel"));
        applyButton.setText(textBundle.getString("button.apply"));
        saveButton.setText(textBundle.getString("button.save"));
        showOrHidePasswordButton.setText(textBundle.getString("button.show"));
        hiddenShowOrHidePasswordButton.setText(showOrHidePasswordButton.getText());
    }

    /**
     * Sets the MainApp.
     * @param mainApp The MainApp to be set.
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Sets the Stage for this class.
     * @param settingsStage The Stage to be set.
     */
    public void setSettingsStage(Stage settingsStage) {
        this.settingsStage = settingsStage;
    }

    /**
     * Getter for the current ResourceBundle in this class.
     * @return The ResourceBundle of this class.
     */
    public ResourceBundle getTextBundle() {
        return textBundle;
    }

    /**
     * Setter for the ResourceBundle
     * @param textBundle The ResourceBundle to be set.
     */
    public void setTextBundle(ResourceBundle textBundle) {
        this.textBundle = textBundle;
    }


}
