package com.ryhma10.tilastoohjelma.view;

import com.ryhma10.tilastoohjelma.MainApp;
import com.ryhma10.tilastoohjelma.model.Profile;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController {

    private MainApp mainApp;
    private Stage mainStage;
    private LoginController loginController;
    private String profileName;
    

    public Profile getCurrentProfile() {
        return currentProfile;
    }

    public void setCurrentProfile(Profile currentProfile) {
        this.currentProfile = currentProfile;
    }

    private Profile currentProfile;
    @FXML
    private Label profileNameLabel;

    public MainController() {
        //Constructor
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public void initialize() {
        Platform.runLater(() -> {
            this.currentProfile = mainApp.getProfile();
            profileNameLabel.setText(currentProfile.getName());
        });
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void setProfileNameLabel(String profileName) {
        profileNameLabel.setText(profileName);
    }

    @FXML
    public void handleAddNewGame(ActionEvent actionEvent) throws IOException {
        mainApp.showInputWindow();
    }

    @FXML
    public void handleProfileOverview(ActionEvent actionEvent) throws IOException {
        mainApp.showProfileWindow();
    }

    @FXML
    public void handleAbout(ActionEvent actionEvent) {
        Alert aboutApplication = new Alert(Alert.AlertType.INFORMATION);
        aboutApplication.setTitle("About LoL Tilasto-ohjelma");
        aboutApplication.setHeaderText("About application");
        aboutApplication.setContentText("Authors: Ryhmä 10\nGitHub: https://github.com/mikaele90/LoL_Tilasto-ohjelma\nVersion: 0.0.1");
        aboutApplication.showAndWait();
    }

    @FXML
    public void handleExit(ActionEvent actionEvent) {
        System.exit(0);
    }

}
