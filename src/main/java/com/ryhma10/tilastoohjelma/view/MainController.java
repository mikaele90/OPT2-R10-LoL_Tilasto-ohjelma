package com.ryhma10.tilastoohjelma.view;

import com.ryhma10.tilastoohjelma.MainApp;
import com.ryhma10.tilastoohjelma.model.Gamedata;
import com.ryhma10.tilastoohjelma.model.ModelAccessObject;
import com.ryhma10.tilastoohjelma.model.Profile;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainController {

    private MainApp mainApp;
    private Stage mainStage;
    private LoginController loginController;
    private ModelAccessObject modelAccessObject;
    private String profileName;
    

    public Profile getCurrentProfile() {
        return currentProfile;
    }

    public void setCurrentProfile(Profile currentProfile) {
        this.currentProfile = currentProfile;
    }

    private Profile currentProfile;
    @FXML
    private Label profileNameLabel;
    @FXML
    private TableView<Gamedata> tableView;
    @FXML
    private TableColumn<Gamedata, Integer> gameIdColumn;
    @FXML
    private TableColumn<Gamedata, String> championColumn;
    @FXML
    private TableColumn<Gamedata, Integer> killsColumn;
    @FXML
    private TableColumn<Gamedata, Integer> deathsColumn;
    @FXML
    private TableColumn<Gamedata, Integer> assistsColumn;
    @FXML
    private TableColumn<Gamedata, String> winLoseColumn;
    @FXML
    private TableColumn<Gamedata, String> positionColumn;
    @FXML
    private TableColumn<Gamedata, Double> gpmColumn;
    @FXML
    private TableColumn<Gamedata, String> slot1Column;
    @FXML
    private TableColumn<Gamedata, String> slot2Column;
    @FXML
    private TableColumn<Gamedata, String> slot3Column;
    @FXML
    private TableColumn<Gamedata, String> slot4Column;
    @FXML
    private TableColumn<Gamedata, String> slot5Column;
    @FXML
    private TableColumn<Gamedata, String> slot6Column;
    @FXML
    private TableColumn<Gamedata, String> profileNameColumn;

    public MainController() {
        //Constructor
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public void initialize() {
        Platform.runLater(() -> {
            this.currentProfile = mainApp.getProfile();
            profileNameLabel.setText(currentProfile.getName());

            mainApp.setMainController(this);

            modelAccessObject = new ModelAccessObject();
            List<Gamedata> gamedataList = modelAccessObject.readSpesificGames(mainApp.getProfile().getName());
            ObservableList<Gamedata> observableGamedataList = FXCollections.observableArrayList(gamedataList);
            ObservableList<Gamedata> observableGameList = FXCollections.observableArrayList();

            gameIdColumn.setCellValueFactory(new PropertyValueFactory<>("gameid"));
            championColumn.setCellValueFactory(new PropertyValueFactory<>("champion"));
            killsColumn.setCellValueFactory(new PropertyValueFactory<>("kills"));
            deathsColumn.setCellValueFactory(new PropertyValueFactory<>("deaths"));
            assistsColumn.setCellValueFactory(new PropertyValueFactory<>("assits"));
            winLoseColumn.setCellValueFactory(new PropertyValueFactory<>("winlose"));
            positionColumn.setCellValueFactory(new PropertyValueFactory<>("positio"));
            gpmColumn.setCellValueFactory(new PropertyValueFactory<>("gpm"));
            slot1Column.setCellValueFactory(new PropertyValueFactory<>("slot1"));
            slot2Column.setCellValueFactory(new PropertyValueFactory<>("slot2"));
            slot3Column.setCellValueFactory(new PropertyValueFactory<>("slot3"));
            slot4Column.setCellValueFactory(new PropertyValueFactory<>("slot4"));
            slot5Column.setCellValueFactory(new PropertyValueFactory<>("slot5"));
            slot6Column.setCellValueFactory(new PropertyValueFactory<>("slot6"));
            profileNameColumn.setCellValueFactory(new PropertyValueFactory<>("pname"));

            tableView.setItems(observableGamedataList);
        });


    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void setProfileNameLabel(String profileName) {
        profileNameLabel.setText(profileName);
    }

    @FXML
    public void handleAddNewGame(ActionEvent actionEvent) throws IOException, InterruptedException {
        mainApp.showInputWindow();
    }

    @FXML
    public void handleProfileOverview(ActionEvent actionEvent) throws IOException {
        mainApp.showProfileWindow();
    }

    @FXML
    public void handleAbout(ActionEvent actionEvent) {
        Alert aboutApplication = new Alert(Alert.AlertType.INFORMATION);
        aboutApplication.setTitle("About LoL Tilasto-ohjelma");
        aboutApplication.setHeaderText("About application");
        aboutApplication.setContentText("Authors: Ryhmä 10\nGitHub: https://github.com/mikaele90/LoL_Tilasto-ohjelma\nVersion: 0.0.1");
        aboutApplication.showAndWait();
    }

    @FXML
    public void handleExit(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void refreshScene() {
        initialize();
    }

}
