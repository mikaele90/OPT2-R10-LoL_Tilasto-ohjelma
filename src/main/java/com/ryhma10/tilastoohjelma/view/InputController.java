package com.ryhma10.tilastoohjelma.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.ryhma10.tilastoohjelma.MainApp;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

public class InputController  {
	
	ObservableList<String> champsList = FXCollections.observableArrayList("Twisted fate", "Kassadin", "Malzahar");
	
	
	@FXML
	private TextField textfield1;
	@FXML
	private TextField textfield2;
	@FXML
	private TextField textfield3;
	@FXML
	private TextField textfield4;
	
	@FXML
	private ChoiceBox champsPlayed;
	
	private MainApp mainApp;
    private Stage inputStage;
    
    @FXML
    private Label label;
    
    @FXML
	private void initialize() {
		champsPlayed.setValue("Twisted fate");
		champsPlayed.setItems(champsList);
	}
    
    public InputController() {
    	
    }
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    public void setInputStage(Stage inputStage) {
        this.inputStage = inputStage;
    }
    
    

    
    
    @FXML
    private void handleSubmit(ActionEvent event) throws IOException {
        mainApp.showFeedBackWindow();
    }
    
    public void handleClearAll(ActionEvent event) throws IOException {
    	textfield1.setText("");
    	textfield2.setText("");
    	textfield3.setText("");
    	textfield4.setText("");
    	
    }
    
    


}
