package com.ryhma10.tilastoohjelma.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import com.ryhma10.tilastoohjelma.MainApp;

public class CreateNewUserController {

    private MainApp mainApp;
    private Stage createNewUserStage;

    public CreateNewUserController() {
        //Constructor
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setCreateNewUserStage(Stage createNewUserStage) {
        this.createNewUserStage = createNewUserStage;
    }

    @FXML
    public void handleCancel(ActionEvent actionEvent) {
        createNewUserStage.close();
    }

    @FXML
    public void handleDone(ActionEvent actionEvent) {
    }

}
