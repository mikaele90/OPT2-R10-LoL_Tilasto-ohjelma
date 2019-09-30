package com.ryhma10.tilastoohjelma.view;

import com.ryhma10.tilastoohjelma.MainApp;
import com.ryhma10.tilastoohjelma.model.CreateNewUser;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;

import java.io.IOException;


public class CreateNewUserController {

    private MainApp mainApp;
    private Stage createNewUserStage;

    public CreateNewUserController() {
        //Constructor
    }

    public void initialize() {
        if (progressIndicator.isVisible()) {
            progressIndicator.setVisible(false);
        }
        createProfileNameField.setText("");
        createProfileNameField.setPromptText("New profile name");
        createProfilePasswordField.setText("");
        createProfilePasswordField.setPromptText("Password");
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setCreateNewUserStage(Stage createNewUserStage) {
        this.createNewUserStage = createNewUserStage;
    }

    @FXML
    private ProgressIndicator progressIndicator;

    @FXML
    private TextField createProfileNameField;

    @FXML
    private PasswordField createProfilePasswordField;

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
    public void handleDone(ActionEvent actionEvent) throws IOException {
        if(createProfileNameField.getText().length() > 0 && createProfilePasswordField.getText().length() > 3) {
            CreateNewUser createNewUser = new CreateNewUser(createProfileNameField.getText(), createProfilePasswordField.getText());
            progressIndicator.visibleProperty().bind(createNewUser.runningProperty());
            createNewUser.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(WorkerStateEvent workerStateEvent) {
                    if(createNewUser.getValue().equals("Success")) {
                        Alert newProfileSuccess = new Alert(Alert.AlertType.INFORMATION);
                        newProfileSuccess.setTitle("Success");
                        newProfileSuccess.setHeaderText("Profile created");
                        newProfileSuccess.setContentText("Profile created successfully.");
                        newProfileSuccess.showAndWait();
                        System.out.println(createNewUser.getValue());
                        createNewUserStage.close();
                    }
                    else if (createNewUser.getValue().equals("Profile already exists")) {
                        Alert existingProfileError = new Alert(Alert.AlertType.WARNING);
                        existingProfileError.setTitle("Profile creation unsuccessful");
                        existingProfileError.setHeaderText("A profile by that name already exists");
                        existingProfileError.setContentText("Please choose a different profile name.");
                        existingProfileError.showAndWait();
                    }
                }
            });

            createNewUser.setOnFailed(new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(WorkerStateEvent workerStateEvent) {

                    Alert newProfileProblem = new Alert(Alert.AlertType.ERROR);
                    newProfileProblem.setTitle("Error");
                    newProfileProblem.setHeaderText("Profile couldn't be created");
                    newProfileProblem.setContentText("A problem occurred while trying to create the profile.");
                    newProfileProblem.showAndWait();
                    System.out.println(createNewUser.getValue());
                    createNewUserStage.close();
                }
            });
            createNewUser.restart();
        }
        else {
            Alert emptyFieldError = new Alert(Alert.AlertType.ERROR);
            emptyFieldError.setTitle("Error");
            emptyFieldError.setHeaderText("Profile couldn't be created");
            emptyFieldError.setContentText("Profile name has to be at least one character long and the password 4 characters long.");
            emptyFieldError.showAndWait();
        }

    }

}
