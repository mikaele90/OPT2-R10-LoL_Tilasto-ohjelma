package com.ryhma10.tilastoohjelma.view;

import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Region;
import com.ryhma10.tilastoohjelma.MainApp;
import com.ryhma10.tilastoohjelma.model.ModelAccessObject;
import com.ryhma10.tilastoohjelma.model.Gamedata;
import com.ryhma10.tilastoohjelma.model.SoftwareProfile;
import com.ryhma10.tilastoohjelma.view.utilities.AlertFactory;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class MainController {

    private MainApp mainApp;
    private Stage mainStage;
    private LoginController loginController;
    private AlertFactory alertFactory;
    private ModelAccessObject modelAccessObject;
    private String profileName;
    private SoftwareProfile currentProfile;
    private ResourceBundle textBundle;

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
        //Default constructor
    }

    public void initialize() {
        Platform.runLater(() -> {
            System.out.println("MainController: Initialize");
            alertFactory = new AlertFactory();
            this.currentProfile = mainApp.getProfile();
            profileNameLabel.setText(currentProfile.getProfileName());
            try {
                Orianna.loadConfiguration("apiconfig.json");
                System.out.println("API config loaded");
            } catch (Exception e) {
                System.out.println("API config not found");
            }
            if (currentProfile.getRiotAPIKey() != null) {
                Orianna.setRiotAPIKey(currentProfile.getRiotAPIKey());
                System.out.println("Riot API Key found: " + currentProfile.getRiotAPIKey());
            }
            if (currentProfile.getDefaultRegion() != null) {
                Orianna.setDefaultRegion(Region.valueOf(currentProfile.getDefaultRegion()));
                System.out.println("Region set to: " + currentProfile.getDefaultRegion());
            }
            if (currentProfile.getDefaultRiotAccount() != null) {
                System.out.println("Profile default riot account id: " + currentProfile.getDefaultRiotAccount());
                //TODO IF NEEDED
                //Set riot account to profile default
            }
            if (currentProfile.getDefaultLanguage() != null) {
                System.out.println("Profile default language: " + currentProfile.getDefaultLanguage());
                System.out.println("TextRes locale: " + textBundle.getLocale().toString());
            }
            mainApp.setMainController(this);
            secondaryInitialize();
        });
    }

    public void secondaryInitialize() {
        //dataAccessObject = new DataAccessObject();
        //List<Gamedata> gamedataList = modelAccessObject.readSpesificGames(mainApp.getProfile().getName());
        //ObservableList<Gamedata> observableGamedataList = FXCollections.observableArrayList(gamedataList);

        gameIdColumn.setCellValueFactory(new PropertyValueFactory<Gamedata, Integer>("gameid"));
        championColumn.setCellValueFactory(new PropertyValueFactory<Gamedata, String>("champion"));
        killsColumn.setCellValueFactory(new PropertyValueFactory<Gamedata, Integer>("kills"));
        deathsColumn.setCellValueFactory(new PropertyValueFactory<Gamedata, Integer>("deaths"));
        assistsColumn.setCellValueFactory(new PropertyValueFactory<Gamedata, Integer>("assits"));
        winLoseColumn.setCellValueFactory(new PropertyValueFactory<Gamedata, String>("winlose"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<Gamedata, String>("positio"));
        gpmColumn.setCellValueFactory(new PropertyValueFactory<Gamedata, Double>("gpm"));
        slot1Column.setCellValueFactory(new PropertyValueFactory<Gamedata, String>("slot1"));
        slot2Column.setCellValueFactory(new PropertyValueFactory<Gamedata, String>("slot2"));
        slot3Column.setCellValueFactory(new PropertyValueFactory<Gamedata, String>("slot3"));
        slot4Column.setCellValueFactory(new PropertyValueFactory<Gamedata, String>("slot4"));
        slot5Column.setCellValueFactory(new PropertyValueFactory<Gamedata, String>("slot5"));
        slot6Column.setCellValueFactory(new PropertyValueFactory<Gamedata, String>("slot6"));
        profileNameColumn.setCellValueFactory(new PropertyValueFactory<Gamedata, String>("pname"));

        //tableView.setItems(observableGamedataList);
    }

    public void setTexts() {

    }

    @FXML
    public void handleAddGames(ActionEvent actionEvent) throws IOException {
        mainApp.showInputWindow();
    }

    @FXML
    public void handleProfileOverview(ActionEvent actionEvent) throws IOException {
        mainApp.showProfileWindow();
    }

    @FXML
    public void handleAbout(ActionEvent actionEvent) {
        Alert aboutAlert = alertFactory.createAlert("About application");
        aboutAlert.showAndWait();
    }

    @FXML
    public void handleSettings(ActionEvent actionEvent) throws IOException {
        mainApp.showSettingsWindow();
    }

    @FXML
    public void handleExit(ActionEvent actionEvent) {
        System.exit(0);
    }

    @FXML
    public void handleMainHelp(ActionEvent actionEvent) {
        System.out.println("Not yet implemented");
        //TODO
    }

    public void printProfileData() {
        if (currentProfile.getRiotAPIKey() != null) {
            System.out.println("Riot API Key found: " + currentProfile.getRiotAPIKey());
        }
        if (currentProfile.getDefaultRegion() != null) {
            System.out.println("Region set to: " + currentProfile.getDefaultRegion());
        }
        if (currentProfile.getDefaultRiotAccount() != null) {
            System.out.println("Profile default riot account id: " + currentProfile.getDefaultRiotAccount());
        }
        if (currentProfile.getDefaultLanguage() != null) {
            System.out.println("Profile default language: " + currentProfile.getDefaultLanguage());
        }
    }

    public SoftwareProfile getCurrentProfile() {
        return currentProfile;
    }

    public void setCurrentProfile(SoftwareProfile currentProfile) {
        this.currentProfile = currentProfile;
    }

    public void refreshMainScene() {
        secondaryInitialize();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileNameLabel(String profileName) {
        profileNameLabel.setText(profileName);
    }

    public ResourceBundle getTextBundle() {
        return textBundle;
    }

    public void setTextBundle(ResourceBundle textBundle) {
        this.textBundle = textBundle;
    }


}
