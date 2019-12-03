package com.ryhma10.tilastoohjelma.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.ryhma10.tilastoohjelma.MainApp;
import com.ryhma10.tilastoohjelma.model.FeedBack;
import com.ryhma10.tilastoohjelma.model.Gamedata;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import java.sql.SQLException;

public class FeedBackController {
	
	
	 private MainApp mainApp;
	 private Stage feedBackStage;
	 Gamedata gd = new Gamedata();
	 FeedBack fb = new FeedBack(gd.getRiotid());
	 private MainController mainController;
	 
	@FXML
	private TextArea kdatext;
	@FXML
	private TextArea wardtext;
	@FXML
	private TextArea cstext;
	@FXML
	private TextArea scoretext;
	
	public FeedBackController() {
		
	}
	
	public void initialize() {
		kdatext.setText(fb.getKDAfeedback());
		wardtext.setText(fb.getWardfeedback());
		cstext.setText(fb.getCSfeedback());
		scoretext.setText(fb.getWinstreakscore());
		Platform.runLater(() -> {
			this.mainController = mainApp.getMainController();
		});
	}
	
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    public void setFeedBackStage(Stage feedBackStage) {
        this.feedBackStage = feedBackStage;
    }
    
    @FXML
    private void handleBack() throws IOException {
	    feedBackStage.close();
    }

    @FXML
    private void handleProfile() throws IOException {
	    mainApp.showProfileWindow();
    }

}
