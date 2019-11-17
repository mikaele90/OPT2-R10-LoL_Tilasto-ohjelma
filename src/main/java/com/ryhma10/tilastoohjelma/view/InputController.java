package com.ryhma10.tilastoohjelma.view;

import java.io.IOException;

import com.ryhma10.tilastoohjelma.MainApp;
import com.ryhma10.tilastoohjelma.model.*;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

public class InputController  {
	
	ObservableList<String> champsList = FXCollections.observableArrayList("Kha'Zix", "Lucian", "Qiyana");
	
	private Gamedata match;
	private SoftwareProfile profile;
	private LoginController login;
	private ModelAccessObject dao;
	private MainController mainController;

	
	@FXML
	private TextField textfield1;
	@FXML
	private TextField textfield2;
	@FXML
	private TextField textfield3;
	//@FXML
	//private TextField textfield4;
	
	@FXML
	private ChoiceBox champsPlayed;
	
	private MainApp mainApp;
    private Stage inputStage;
	
    
    @FXML
    private Label label;
    
    @FXML
	private void initialize() {
		champsPlayed.setValue("Kha'Zix");
		champsPlayed.setItems(champsList);

		Platform.runLater(() -> {
			this.mainController = mainApp.getMainController();
		});

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
    	dao = new ModelAccessObject();
    	profile = mainApp.getProfile();
		match = new Gamedata(champsPlayed.getSelectionModel().getSelectedItem().toString(), Integer.parseInt(textfield1.getText()), Integer.parseInt(textfield2.getText()), Integer.parseInt(textfield3.getText()), "", "", 0, "", "", "", "", "", "", profile.getProfileName());
    	dao.createGamedata(match);
        mainApp.showFeedBackWindow();
        mainController.refreshMainScene();
        inputStage.close();
    }
    
    @FXML
    public void handleClearAll(ActionEvent event) throws IOException {
    	textfield1.setText("");
    	textfield2.setText("");
    	textfield3.setText("");
    	//textfield4.setText("");
    	
    }

	@FXML
	public void handleCancel(ActionEvent actionEvent) {
    	inputStage.close();
	}
}
