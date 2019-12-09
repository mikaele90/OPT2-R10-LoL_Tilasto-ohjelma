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
	 private ResourceBundle textBundle;
	 
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
			if(fb.getKDAevaluation().equals("poor KDA")) {
				kdatext.setText(textBundle.getString("textArea.kdaFeedback.poor"));
				kdatext.setWrapText(true);
			}
			else if(fb.getKDAevaluation().equals("Average KDA")) {
				kdatext.setText(textBundle.getString("textArea.kdaFeedback.average"));
				kdatext.setWrapText(true);
			}
			else if(fb.getKDAevaluation().equals("Good KDA")) {
				kdatext.setText(textBundle.getString("textArea.kdaFeedback.good"));
				kdatext.setWrapText(true);
			}
			else if(fb.getKDAevaluation().equals("Insane KDA")) {
				kdatext.setText(textBundle.getString("textArea.kdaFeedback.insane"));
				kdatext.setWrapText(true);
			}
			if(fb.getWardEvaluation().equals("poor")) {
				wardtext.setText(textBundle.getString("textArea.wardFeedback.poor"));
				wardtext.setWrapText(true);
			}
			else if(fb.getWardEvaluation().equals("average")) {
				wardtext.setText(textBundle.getString("textArea.wardFeedback.average"));
				wardtext.setWrapText(true);
			}
			else if(fb.getWardEvaluation().equals("good")) {
				wardtext.setText(textBundle.getString("textArea.wardFeedback.good"));
				wardtext.setWrapText(true);
			}
			else if(fb.getWardEvaluation().equals("Too many")) {
				wardtext.setText(textBundle.getString("textArea.wardFeedback.toomany"));
				wardtext.setWrapText(true);
			}
			if(fb.getCSevaluation().equals("poor")) {
				cstext.setText(textBundle.getString("textArea.csFeedback.poor"));
				cstext.setWrapText(true);
			}
			else if(fb.getCSevaluation().equals("good")) {
				cstext.setText(textBundle.getString("textArea.csFeedback.good"));
				cstext.setWrapText(true);
			}
			else if(fb.getCSevaluation().equals("excellent")) {
				cstext.setText(textBundle.getString("textArea.csFeedback.excellent"));
				cstext.setWrapText(true);
			}
			if(fb.getCSevaluation().equals("poor") && fb.getWardEvaluation().equals("poor")) {
				scoretext.setText(textBundle.getString("textArea.score.0"));
				scoretext.setWrapText(true);
			}
			else if(fb.getCSevaluation().equals("good") && fb.getWardEvaluation().equals("poor")) {
				scoretext.setText(textBundle.getString("textArea.score.1"));
				scoretext.setWrapText(true);
			}
			else if(fb.getCSevaluation().equals("excellent") && fb.getWardEvaluation().equals("poor")) {
				scoretext.setText(textBundle.getString("textArea.score.2"));
				scoretext.setWrapText(true);
			}
			else if(fb.getCSevaluation().equals("poor") && fb.getWardEvaluation().equals("average")) {
				scoretext.setText(textBundle.getString("textArea.score.3"));
				scoretext.setWrapText(true);
			}
			else if(fb.getCSevaluation().equals("poor") && fb.getWardEvaluation().equals("good")) {
				scoretext.setText(textBundle.getString("textArea.score.4"));
				scoretext.setWrapText(true);
			}
			else if(fb.getCSevaluation().equals("poor") && fb.getWardEvaluation().equals("Too many")) {
				scoretext.setText(textBundle.getString("textArea.score.5"));
				scoretext.setWrapText(true);
			}
			else if(fb.getCSevaluation().equals("good") && fb.getWardEvaluation().equals("average")) {
				scoretext.setText(textBundle.getString("textArea.score.6"));
				scoretext.setWrapText(true);
			}
			else if(fb.getCSevaluation().equals("good") && fb.getWardEvaluation().equals("good")) {
				scoretext.setText(textBundle.getString("textArea.score.7"));
				scoretext.setWrapText(true);
			}
			else if(fb.getCSevaluation().equals("poor") && fb.getWardEvaluation().equals("Too many")) {
				scoretext.setText(textBundle.getString("textArea.score.8"));
				scoretext.setWrapText(true);
			}
			else if(fb.getCSevaluation().equals("excellent") && fb.getWardEvaluation().equals("average")) {
				scoretext.setText(textBundle.getString("textArea.score.9"));
				scoretext.setWrapText(true);
			}
			else if(fb.getCSevaluation().equals("excellent") && fb.getWardEvaluation().equals("good")) {
				scoretext.setText(textBundle.getString("textArea.score.10"));
				scoretext.setWrapText(true);
			}
			else if(fb.getCSevaluation().equals("excellent") && fb.getWardEvaluation().equals("Too many")) {
				scoretext.setText(textBundle.getString("textArea.score.11"));
				scoretext.setWrapText(true);
			}
			this.mainController = mainApp.getMainController();
		});
	}
	
	/**
	 * Method for setting the main application
	 * @param mainApp defines the MainApp object used
	 */
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
	/**
	 * Method for setting the Feedback stage
	 * @param feedBackStage defined the feedBackStage object used
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
    
    /**
     * Method for setting FeedBack object in this class
     * @param fb defined the FeedBack object
     */
	public void setFb(FeedBack fb) {
		this.fb = fb;
	}

}
