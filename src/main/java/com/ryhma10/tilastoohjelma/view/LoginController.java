package com.ryhma10.tilastoohjelma.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.SQLException;

import com.ryhma10.tilastoohjelma.MainApp;

public class LoginController {

    private MainApp mainApp;

    public LoginController() {
        //Constructor
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void handleAboutApplication(ActionEvent actionEvent) {
        Alert aboutApplication = new Alert(Alert.AlertType.INFORMATION);
        aboutApplication.setTitle("About LoL Tilasto-ohjelma");
        aboutApplication.setHeaderText("About application");
        aboutApplication.setContentText("Authors: Ryhmä 10\nGitHub: https://github.com/mikaele90/Tilasto-ohjelma");

        aboutApplication.showAndWait();
    }

    @FXML
    public void handleCreateNewProfile(ActionEvent actionEvent) throws IOException {
        mainApp.showCreateNewUserWindow();
    }

    @FXML
    public void handleExit(ActionEvent actionEvent) {
        System.exit(0);
    }

    @FXML
    public void handleLogin(ActionEvent actionEvent) throws IOException {
    	mainApp.showProfileWindow();
    }
}
