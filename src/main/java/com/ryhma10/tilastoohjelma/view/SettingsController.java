package com.ryhma10.tilastoohjelma.view;

import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Region;
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
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public class SettingsController {

    private MainApp mainApp;
    private MainController mainController;
    private Stage settingsStage;
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
    private Button testAPIKeyButton;
    @FXML
    private ChoiceBox<String> changeRegionChoiceBox, changeLanguageChoiceBox;
    @FXML
    private CheckBox makeRegionDefaultCheckBox, makeLanguageDefaultCheckBox;
    @FXML
    private PasswordField profilePasswordField, confirmProfilePasswordField;
    
    public SettingsController() {
        //Default Constructor
    }

    public void initialize() {
        this.alertFactory = new AlertFactory();
        SingleSelectionModel<Tab> selectionModel = settingsTabPane.getSelectionModel();
        selectionModel.select(apiAndUserInterfaceTab);
        Platform.runLater(() -> {
            this.currentProfile = mainApp.getProfile();
            this.mainController = mainApp.getMainController();
            apiKeyTextField.setPromptText("Your Riot API-key");
            if (currentProfile.getRiotAPIKey() == null) {
                apiKeyTextField.setText("");
            }
            else {
                apiKeyTextField.setText(currentProfile.getRiotAPIKey());
            }
            profilePasswordField.setText("");
            profilePasswordField.setPromptText("Type new password");
            confirmProfilePasswordField.setText("");
            confirmProfilePasswordField.setPromptText("Retype new password");
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
            List<String> languageArrayList = mainApp.getLanguageArrayList();
            ObservableList<String> languageObservableList = FXCollections.observableList(languageArrayList);
            changeLanguageChoiceBox.getItems().addAll(languageObservableList);
            changeLanguageChoiceBox.setValue(textBundle.getString(currentProfile.getDefaultLanguage()));
            makeLanguageDefaultCheckBox.setSelected(false);
        });
    }

    @FXML
    public void handleCancel(ActionEvent actionEvent) {
        settingsStage.close();
    }

    @FXML
    public boolean handleApply(ActionEvent actionEvent) {
        boolean dbUpdateRequired = false;
        boolean dbUpdateRegion = false;
        boolean dbUpdateLanguage = false;
        boolean success = true;
        if ((!apiKeyTextField.getText().equals("")) && (!apiKeyTextField.getText().equals(currentProfile.getRiotAPIKey()))) {
            Orianna.setRiotAPIKey(apiKeyTextField.getText());
            currentProfile.setRiotAPIKey(apiKeyTextField.getText());
            dbUpdateRequired = true;
        }
        if (!changeRegionChoiceBox.getSelectionModel().getSelectedItem().replace(" ", "_").equals(currentProfile.getDefaultRegion()) || makeRegionDefaultCheckBox.isSelected()) {
            currentProfile.setDefaultRegion(changeRegionChoiceBox.getSelectionModel().getSelectedItem().replace(" ", "_"));
            Orianna.setDefaultRegion(Region.valueOf(currentProfile.getDefaultRegion()));
            if (makeRegionDefaultCheckBox.isSelected()) {
                dbUpdateRegion = true;
                dbUpdateRequired = true;
            }
        }
        if (!getSelectedLanguage(changeLanguageChoiceBox.getSelectionModel().getSelectedIndex()).equals(currentProfile.getDefaultLanguage()) || makeLanguageDefaultCheckBox.isSelected()) {
            currentProfile.setDefaultLanguage(getSelectedLanguage(changeLanguageChoiceBox.getSelectionModel().getSelectedIndex()));
            System.out.println(currentProfile.getDefaultLanguage());
            if (makeLanguageDefaultCheckBox.isSelected()) {
                dbUpdateLanguage = true;
                dbUpdateRequired = true;
            }
        }
        if (!profilePasswordField.getText().equals("")) {
            if (profilePasswordField.getText().equals(confirmProfilePasswordField.getText())) {
                currentProfile.setProfilePassword(confirmProfilePasswordField.getText());
                dbUpdateRequired = true;
            }
            else {
                success = false;
                Alert nonMatchingPasswordsAlert = alertFactory.createAlert("Passwords do not match");
                nonMatchingPasswordsAlert.show();
            }
        }
        if (dbUpdateRequired) {
            ModelAccessObject modelAccessObject = new ModelAccessObject();
            modelAccessObject.updateProfile(currentProfile, dbUpdateRegion, dbUpdateLanguage);
        }
        mainController.setCurrentProfile(currentProfile);
        mainApp.setProfile(currentProfile);
        mainController.printProfileData();
        return success;
    }

    @FXML
    public void handleSave(ActionEvent actionEvent) {
        if(handleApply(new ActionEvent())) {
            settingsStage.close();
        }
    }

    @FXML
    public void handleHelp(ActionEvent actionEvent) {
        System.out.println("Not yet implemented");
        //TODO
    }

    private String getSelectedLanguage(int langIndex) {
        String selectedLanguage;
        selectedLanguage = changeLanguageChoiceBox.getSelectionModel().getSelectedItem().toString();
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

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setSettingsStage(Stage settingsStage) {
        this.settingsStage = settingsStage;
    }

    public ResourceBundle getTextBundle() {
        return textBundle;
    }

    public void setTextBundle(ResourceBundle textBundle) {
        this.textBundle = textBundle;
    }

}
