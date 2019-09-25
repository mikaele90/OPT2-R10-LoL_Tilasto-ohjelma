package com.ryhma10.tilastoohjelma.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.ryhma10.tilastoohjelma.MainApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import java.sql.SQLException;

public class FeedBackController {
	
	 @FXML
	 private Label label;
	 
	 private MainApp mainApp;
	 private Stage feedBackStage;
	
	public FeedBackController() {
		
	}
	
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    public void setFeedBackStage(Stage feedBackStage) {
        this.feedBackStage = feedBackStage;
    }
    
    @FXML
    private void handleBack() throws IOException, SQLException {
    	mainApp.showLoginWindow();
    } 

}
