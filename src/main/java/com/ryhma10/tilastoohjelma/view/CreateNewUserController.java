package com.ryhma10.tilastoohjelma.view;

import com.ryhma10.tilastoohjelma.MainApp;
import com.ryhma10.tilastoohjelma.model.CreateNewUser;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


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
    private TextField createProfileNameField, createProfilePasswordField;


    @FXML
    public void handleCancel(ActionEvent actionEvent) {
        createNewUserStage.close();
    }

    @FXML
    public void handleDone(ActionEvent actionEvent) {
        CreateNewUser createNewUser = new CreateNewUser(createProfileNameField.getText(), createProfilePasswordField.getText());
        progressIndicator.visibleProperty().bind(createNewUser.runningProperty());
        createNewUser.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                Alert newProfileSuccess = new Alert(Alert.AlertType.INFORMATION);
                newProfileSuccess.setTitle("Success");
                newProfileSuccess.setHeaderText("Profile created");
                newProfileSuccess.setContentText("Profile created successfully.");
                newProfileSuccess.showAndWait();
                System.out.println(createNewUser.getValue());
                createNewUserStage.close();
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

}
