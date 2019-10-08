package com.ryhma10.tilastoohjelma.view;

import com.ryhma10.tilastoohjelma.MainApp;
import com.ryhma10.tilastoohjelma.model.Login;
import com.ryhma10.tilastoohjelma.view.MainController;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    private MainApp mainApp;
    private MainController mainController;
    private Stage loginStage;

    public LoginController() {
        //Constructor
    }

    public void initialize() {
        progressIndicator.setVisible(false);
        loginButton.setText("Login");
        profileNameField.setText("");
        profileNameField.setPromptText("Profile name");
        passwordField.setText("");
        passwordField.setPromptText("Password");
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setLoginStage(Stage loginStage) {
        this.loginStage = loginStage;
    }

    @FXML
    private TextField profileNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton, createNewProfileButton;

    @FXML
    private ProgressIndicator progressIndicator;

    @FXML
    public void onEnter(KeyEvent keyEvent) throws IOException {
        if(keyEvent.getCode().equals(KeyCode.ENTER)) {
            System.out.println("Enter pressed");
            handleLogin(new ActionEvent());
        }
    }

    @FXML
    public void handleAboutApplication(ActionEvent actionEvent) {
        Alert aboutApplication = new Alert(Alert.AlertType.INFORMATION);
        aboutApplication.setTitle("About LoL Tilasto-ohjelma");
        aboutApplication.setHeaderText("About application");
        aboutApplication.setContentText("Authors: Ryhmä 10\nGitHub: https://github.com/mikaele90/LoL_Tilasto-ohjelma\nVersion: 0.0.1");
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
        if(profileNameField.getText().length() > 0 && passwordField.getText().length() > 3) {
            Login login = new Login(profileNameField.getText(), passwordField.getText());
            progressIndicator.visibleProperty().bind(login.runningProperty());
            loginButton.setText("Logging in...");
            login.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(WorkerStateEvent workerStateEvent) {
                    System.out.println("login.getValue(): " + login.getValue());
                    loginButton.setText("Login");
                    if (login.getValue() == null) {
                        Alert profileNotFoundAlert = new Alert(Alert.AlertType.ERROR);
                        profileNotFoundAlert.setTitle("Login Unsuccessful");
                        profileNotFoundAlert.setHeaderText("Profile name or password incorrect");
                        profileNotFoundAlert.setContentText("Check profile name and password.");
                        profileNotFoundAlert.showAndWait();
                    }
                    else if (login.getValue().getName().equals(profileNameField.getText())) {
                        login.setProfileName(login.getValue().getName());
                        mainApp.setProfile(login.getValue());
                        loginStage.close();
                        try {
                            mainApp.showMainWindow(login.getValue());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else {

                    }

                }
            });

            login.setOnFailed(new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(WorkerStateEvent workerStateEvent) {
                    loginButton.setText("Login");
                    System.out.println("login.getValue(): " + login.getValue());
                    System.out.println("Shiiiet");
                }
            });
            login.restart();

        }
        else {
            Alert emptyFieldError = new Alert(Alert.AlertType.ERROR);
            emptyFieldError.setTitle("Error");
            emptyFieldError.setHeaderText("Login error");
            emptyFieldError.setContentText("Profile name has to be at least one character long and the password 4 characters long.");
            emptyFieldError.showAndWait();
        }
    }

}
