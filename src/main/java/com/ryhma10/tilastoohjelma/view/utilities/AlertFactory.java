package com.ryhma10.tilastoohjelma.view.utilities;

import javafx.scene.control.Alert;

import java.util.ResourceBundle;

public class AlertFactory {

    private ResourceBundle textBundle;

    /**
     * Constructor.
     * @param textBundle Requires a ResourceBundle.
     */
    public AlertFactory(ResourceBundle textBundle) {
        //Default constructor
        this.textBundle = textBundle;
    }

    /**
     * Creates different types of Alert alerts.
     * @param alertType String that defines which Alert to return.
     * @return Returns an Alert in the language defined by the ResourceBundle passed to the constructor.
     */
    public Alert createAlert(String alertType) {
        Alert alertToReturn = new Alert(null);
        switch (alertType) {
            case "Profile successfully created":
                alertToReturn.setAlertType(Alert.AlertType.INFORMATION);
                alertToReturn.setTitle(textBundle.getString("alert.title.profileCreated"));
                alertToReturn.setHeaderText(textBundle.getString("alert.headerText.profileCreated"));
                alertToReturn.setContentText(textBundle.getString("alert.contentText.profileCreated"));
                break;
            case "Profile already exists":
                alertToReturn.setAlertType(Alert.AlertType.WARNING);
                alertToReturn.setTitle(textBundle.getString("alert.title.profileAlreadyExists"));
                alertToReturn.setHeaderText(textBundle.getString("alert.headerText.profileAlreadyExists"));
                alertToReturn.setContentText(textBundle.getString("alert.contentText.profileAlreadyExists"));
                break;
            case "Database connection error":
                alertToReturn.setAlertType(Alert.AlertType.ERROR);
                alertToReturn.setTitle(textBundle.getString("alert.title.databaseConnectionError"));
                alertToReturn.setHeaderText(textBundle.getString("alert.headerText.databaseConnectionError"));
                alertToReturn.setContentText(textBundle.getString("alert.contentText.databaseConnectionError"));
                break;
            case "CreateProfile:UserInputError":
                alertToReturn.setAlertType(Alert.AlertType.ERROR);
                alertToReturn.setTitle(textBundle.getString("alert.title.profileCreationUserInputError"));
                alertToReturn.setHeaderText(textBundle.getString("alert.headerText.profileCreationUserInputError"));
                alertToReturn.setContentText(textBundle.getString("alert.contentText.profileCreationUserInputError"));
                break;
            case "Profile not found":
                alertToReturn.setAlertType(Alert.AlertType.ERROR);
                alertToReturn.setTitle(textBundle.getString("alert.title.profileNotFound"));
                alertToReturn.setHeaderText(textBundle.getString("alert.headerText.profileNotFound"));
                alertToReturn.setContentText(textBundle.getString("alert.contentText.profileNotFound"));
                break;
            case "Too many records found":
                alertToReturn.setAlertType(Alert.AlertType.WARNING);
                alertToReturn.setTitle(textBundle.getString("alert.title.tooManyRecordsFound"));
                alertToReturn.setHeaderText(textBundle.getString("alert.headerText.tooManyRecordsFound"));
                alertToReturn.setContentText(textBundle.getString("alert.contentText.tooManyRecordsFound"));
                break;
            case "Login:UserInputError":
                alertToReturn.setAlertType(Alert.AlertType.ERROR);
                alertToReturn.setTitle(textBundle.getString("alert.title.loginUserInputError"));
                alertToReturn.setHeaderText(textBundle.getString("alert.headerText.loginUserInputError"));
                alertToReturn.setContentText(textBundle.getString("alert.contentText.loginUserInputError"));
                break;
            case "About application":
                alertToReturn.setAlertType(Alert.AlertType.INFORMATION);
                alertToReturn.setTitle(textBundle.getString("alert.title.aboutApp"));
                alertToReturn.setHeaderText(textBundle.getString("alert.headerText.aboutApp"));
                alertToReturn.setContentText(textBundle.getString("alert.contentText.aboutApp"));
                break;
            case "APIKeyTest:Fail":
                alertToReturn.setAlertType(Alert.AlertType.WARNING);
                alertToReturn.setTitle(textBundle.getString("alert.title.failedAPIKeyTest"));
                alertToReturn.setHeaderText(textBundle.getString("alert.headerText.failedAPIKeyTest"));
                alertToReturn.setContentText(textBundle.getString("alert.contentText.failedAPIKeyTest"));
                break;
            case "APIKeyTest:Pass":
                alertToReturn.setAlertType(Alert.AlertType.INFORMATION);
                alertToReturn.setTitle(textBundle.getString("alert.title.successfulAPIKeyTest"));
                alertToReturn.setHeaderText(textBundle.getString("alert.headerText.successfulAPIKeyTest"));
                alertToReturn.setContentText(textBundle.getString("alert.contentText.successfulAPIKeyTest"));
                break;
            case "Passwords do not match":
                alertToReturn.setAlertType(Alert.AlertType.WARNING);
                alertToReturn.setTitle(textBundle.getString("alert.title.changePasswordNoMatch"));
                alertToReturn.setHeaderText(textBundle.getString("alert.headerText.changePasswordNoMatch"));
                alertToReturn.setContentText(textBundle.getString("alert.contentText.changePasswordNoMatch"));
                break;
            default:
                alertToReturn.setAlertType(Alert.AlertType.ERROR);
                alertToReturn.setTitle(textBundle.getString("alert.title.alertFactoryInternalError"));
                alertToReturn.setHeaderText(textBundle.getString("alert.headerText.alertFactoryInternalError"));
                alertToReturn.setContentText(textBundle.getString("alert.contentText.alertFactoryInternalError") + alertType);
        }
        return alertToReturn;
    }

}
