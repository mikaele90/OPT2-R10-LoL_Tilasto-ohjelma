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

public class ProfileController {
	
	private MainApp mainApp;
	private Stage profileStage;
	
	/**
	 * Default constructor for ProfileController
	 */
	public ProfileController() {
		
	}
	
	/**
	 * Set method for the main application
	 * @param mainApp to be used
	 */
	public void setMainApp(MainApp mainApp) {
       this.mainApp = mainApp;
   }
   
	/**
	 * set method for the profilestage
	 * @param profileStage to be used
	 */
   public void setProfileStage(Stage profileStage) {
       this.profileStage = profileStage;
   }
   
   /**
    * Handler for the opening of the input window
    * @throws IOException
    */
   @FXML
   private void handleInputNewMatch() throws IOException {
   	    mainApp.showInputWindow();
   	    profileStage.close();
   } 
   
   /**
    * Handler for the back button
    */
   @FXML
   private void handleBack() {
	   profileStage.close();
   }

}

