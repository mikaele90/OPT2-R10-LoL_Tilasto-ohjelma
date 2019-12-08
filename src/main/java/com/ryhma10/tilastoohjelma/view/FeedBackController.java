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
	 FeedBack fb;
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
	
	/**
	 * Method for initializing the Feedback window when the used opens it
	 */
	public void initialize() {
		Platform.runLater(() -> {
			kdatext.setText(fb.getKDAfeedback());
			kdatext.setWrapText(true);
			wardtext.setText(fb.getWardfeedback());
			wardtext.setWrapText(true);
			cstext.setText(fb.getCSfeedback());
			cstext.setWrapText(true);
			scoretext.setText(fb.getWinstreakscore());
			scoretext.setWrapText(true);
			this.mainController = mainApp.getMainController();
		});
	}
	
	/**
	 * Method for setting the main application
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
	/**
	 * Method for setting the Feedback stage
	 * @param feedBackStage
	 */
    public void setFeedBackStage(Stage feedBackStage) {
        this.feedBackStage = feedBackStage;
    }
    
    /**
     * Method for when the "Back" button is pressed
     * @throws IOException
     */
    @FXML
    private void handleBack() {
	    feedBackStage.close();
    }
    
    /**
     * Method for when the "Profile" button is pressed
     * @throws IOException
     */
    @FXML
    private void handleProfile() throws IOException {
	    mainApp.showProfileWindow();
    }

	public void setFb(FeedBack fb) {
		this.fb = fb;
	}

}
