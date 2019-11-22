package com.ryhma10.tilastoohjelma.view;

import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Region;
import com.ryhma10.tilastoohjelma.MainApp;
import com.ryhma10.tilastoohjelma.model.ModelAccessObject;
import com.ryhma10.tilastoohjelma.model.SoftwareProfile;
import com.ryhma10.tilastoohjelma.view.utilities.AlertFactory;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class SettingsController {

    private MainApp mainApp;
    private MainController mainController;
    private Stage settingsStage;
    private AlertFactory alertFactory;
    private SoftwareProfile currentProfile;
    @FXML
    private TabPane settingsTabPane;
    @FXML
    private Tab apiAndUserInterfaceTab;
    @FXML
    private TextField apiKeyTextField;
    @FXML
    private Button testAPIKeyButton;
    @FXML
    private ChoiceBox changeRegionChoiceBox;
    @FXML
    private CheckBox makeRegionDefaultCheckBox;
    @FXML
    private ChoiceBox changeLanguageChoiceBox;
    @FXML
    private CheckBox makeLanguageDefaultCheckBox;
    @FXML
    private Tab profileTab;
    @FXML
    private PasswordField profilePasswordField;
    @FXML
    private PasswordField confirmProfilePasswordField;
    
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
        boolean success = true;
        if ((!apiKeyTextField.getText().equals("")) && (!apiKeyTextField.getText().equals(currentProfile.getRiotAPIKey()))) {
            Orianna.setRiotAPIKey(apiKeyTextField.getText());
            currentProfile.setRiotAPIKey(apiKeyTextField.getText());
            dbUpdateRequired = true;
        }
        if (!changeRegionChoiceBox.getSelectionModel().getSelectedItem().toString().replace(" ", "_").equals(currentProfile.getDefaultRegion())) {
            Orianna.setDefaultRegion(Region.valueOf(changeRegionChoiceBox.getSelectionModel().getSelectedItem().toString().replace(" ", "_")));
            if (makeRegionDefaultCheckBox.isSelected()) {
                currentProfile.setDefaultRegion(changeRegionChoiceBox.getSelectionModel().getSelectedItem().toString().replace(" ", "_"));
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
            modelAccessObject.updateProfile(currentProfile);
        }
        mainController.setCurrentProfile(currentProfile);
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

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setSettingsStage(Stage settingsStage) {
        this.settingsStage = settingsStage;
    }
}
