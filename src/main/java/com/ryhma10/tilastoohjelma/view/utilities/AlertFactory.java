package com.ryhma10.tilastoohjelma.view.utilities;

import javafx.scene.control.Alert;

public class AlertFactory {

    public AlertFactory() {
        //Default constructor
    }

    public Alert createAlert(String alertType) {
        Alert alertToReturn = new Alert(null);
        switch (alertType) {
            case "Profile successfully created":
                alertToReturn.setAlertType(Alert.AlertType.INFORMATION);
                alertToReturn.setTitle("Success");
                alertToReturn.setHeaderText("Profile created");
                alertToReturn.setContentText("Profile successfully created. Feel free to login.");
                break;
            case "Profile already exists":
                alertToReturn.setAlertType(Alert.AlertType.WARNING);
                alertToReturn.setTitle("Warning");
                alertToReturn.setHeaderText("Profile already exists");
                alertToReturn.setContentText("The profile already exists. Try using another name.");
                break;
            case "Database connection error":
                alertToReturn.setAlertType(Alert.AlertType.ERROR);
                alertToReturn.setTitle("Error");
                alertToReturn.setHeaderText("Database error");
                alertToReturn.setContentText("The software was unable to connect to the database. Please check Hibernate config.");
                break;
            case "CreateProfile:UserInputError":
                alertToReturn.setAlertType(Alert.AlertType.ERROR);
                alertToReturn.setTitle("Error");
                alertToReturn.setHeaderText("Profile couldn't be created");
                alertToReturn.setContentText("Profile name has to be at least one character long and the password 4 characters long.");
                break;
            case "Profile not found":
                alertToReturn.setAlertType(Alert.AlertType.ERROR);
                alertToReturn.setTitle("Login Unsuccessful");
                alertToReturn.setHeaderText("Profile name or password incorrect");
                alertToReturn.setContentText("Check profile name and password.");
                break;
            case "Too many records found":
                alertToReturn.setAlertType(Alert.AlertType.WARNING);
                alertToReturn.setTitle("Login Unsuccessful");
                alertToReturn.setHeaderText("Database error");
                alertToReturn.setContentText("Too many records found. Clean up database.");
                break;
            case "Login:UserInputError":
                alertToReturn.setAlertType(Alert.AlertType.ERROR);
                alertToReturn.setTitle("Error");
                alertToReturn.setHeaderText("Login error");
                alertToReturn.setContentText("Profile name has to be at least one character long and the password 4 characters long.");
                break;
            case "About application":
                alertToReturn.setAlertType(Alert.AlertType.INFORMATION);
                alertToReturn.setTitle("About WinStreak.exe");
                alertToReturn.setHeaderText("About application");
                alertToReturn.setContentText("Authors: Ryhmä 10\nGitHub: https://github.com/mikaele90/OPT2-R10-LoL_Tilasto-ohjelma\nVersion: 0.0.2");
                break;
            case "APIKeyTest:Fail":
                alertToReturn.setAlertType(Alert.AlertType.WARNING);
                alertToReturn.setTitle("Warning");
                alertToReturn.setHeaderText("Non-functional API-key");
                alertToReturn.setContentText("Click the 'Help'-button in case you don't have a working\nAPI-key from Riot Entertainment.\nNewly generated keys can take a few minutes to register.");
                break;
            case "APIKeyTest:Pass":
                alertToReturn.setAlertType(Alert.AlertType.INFORMATION);
                alertToReturn.setTitle("Success");
                alertToReturn.setHeaderText("Valid API-key");
                alertToReturn.setContentText("The key was successfully validated.\nJust finish the registration-process and you should be\ngood to go.");
                break;
            case "Passwords do not match":
                alertToReturn.setAlertType(Alert.AlertType.WARNING);
                alertToReturn.setTitle("Password change failed");
                alertToReturn.setHeaderText("Passwords didn't match");
                alertToReturn.setContentText("The passwords that you entered didn't match. If you wish to change the password, please type the same passwords in both fields. If you didn't wish to change password, make sure that the 'Change password'-field is empty.");
                break;
            default:
                alertToReturn.setAlertType(Alert.AlertType.ERROR);
                alertToReturn.setTitle("Internal error");
                alertToReturn.setHeaderText("AlertFactory broke :DDDDD");
                alertToReturn.setContentText("alertType: " + alertType);
        }
        return alertToReturn;
    }

}
