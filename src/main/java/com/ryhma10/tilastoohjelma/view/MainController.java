package com.ryhma10.tilastoohjelma.view;

import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Region;
import com.ryhma10.tilastoohjelma.MainApp;
import com.ryhma10.tilastoohjelma.api.AcquireData;
import com.ryhma10.tilastoohjelma.api.ApiData;
import com.ryhma10.tilastoohjelma.model.*;
import com.ryhma10.tilastoohjelma.view.utilities.AlertFactory;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    private long selectedRiotId;

    @FXML
    private Label profileNameLabel, welcomeLabel, fetchGamesStatusLabel;
    @FXML
    private ProgressIndicator progressIndicator;
    @FXML
    private TableView<GamedataWrapper> tableView;
    @FXML
    private TableColumn<GamedataWrapper, Integer> column1;
    @FXML
    private TableColumn<GamedataWrapper, Long> column2;
    @FXML
    private TableColumn<GamedataWrapper, String> column3;
    @FXML
    private TableColumn<GamedataWrapper, String> column4;
    @FXML
    private TableColumn<GamedataWrapper, String> column5;
    @FXML
    private TableColumn<GamedataWrapper, String> column6;
    @FXML
    private TableColumn<GamedataWrapper, Integer> column7;

    public MainController() {
        //Default constructor
    }

    public void initialize() {
        progressIndicator.setProgress(1.0);
        progressIndicator.setDisable(true);
        fetchGamesStatusLabel.setText("Status: Ready");
        fetchGamesStatusLabel.setDisable(true);
        Platform.runLater(() -> {
            System.out.println("MainController: Initialize");
            alertFactory = new AlertFactory(textBundle);
            currentProfile = mainApp.getProfile();
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
            modelAccessObject = new ModelAccessObject();
            secondaryInitialize();
        });
    }

    public void secondaryInitialize() {
        List<Gamedata> gamedataList = modelAccessObject.readSpecificProfilesGames(currentProfile.getProfileName());
        List<GamedataWrapper> gamedataWrapperList = new ArrayList<GamedataWrapper>();
        int i = 0;
        for (Gamedata gamedata : gamedataList) {
            Additional additionalGamedata = modelAccessObject.readAdditionalData(gamedata.getRiotid());
            gamedataWrapperList.add(new GamedataWrapper(gamedata.getGameid(), gamedata.getRiotid(), additionalGamedata.getDate(), gamedata.getIngameName(), gamedata.getChampion(), gamedata.getWinlose(), currentProfile.getProfileId()));
            GamedataWrapper gdw = gamedataWrapperList.get(i);
            System.out.println(gdw.getProfileId());
            i++;
        }
        ObservableList<GamedataWrapper> observableGamedataWrapperList = FXCollections.observableArrayList(gamedataWrapperList);

        column1.setCellValueFactory(new PropertyValueFactory<>("gameId"));
        column2.setCellValueFactory(new PropertyValueFactory<>("riotId"));
        column3.setCellValueFactory(new PropertyValueFactory<>("date"));
        column4.setCellValueFactory(new PropertyValueFactory<>("result"));
        column5.setCellValueFactory(new PropertyValueFactory<>("champion"));
        column6.setCellValueFactory(new PropertyValueFactory<>("summoner"));
        column7.setCellValueFactory(new PropertyValueFactory<>("profileId"));
        tableView.setItems(observableGamedataWrapperList);
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

    public void startFetchingGames(ArrayList<Long> gameIdArrayList, String summonerName, ModelAccessObject modelAccessObject) {
        System.out.println("startFetchingGames()");
        Platform.runLater(() -> {
            progressIndicator.setDisable(false);
            progressIndicator.setProgress(0.0);
            fetchGamesStatusLabel.setText("Status: Fetching games...");
            fetchGamesStatusLabel.setDisable(false);
        });
        AcquireData acquireData = new AcquireData();
        acquireData.setCurrentProfile(currentProfile);
        acquireData.setHistorySize(gameIdArrayList.size());
        acquireData.setPlayerName(summonerName);
        ApiData[][] apiDataArray = acquireData.getData(gameIdArrayList);
        Gamedata gamedata;
        Additional additional;
        Item item;
        Team team;
        for(int i = 0; i < acquireData.getSize(); i++) {
            gamedata = new Gamedata(acquireData.getMatchId(apiDataArray, i), acquireData.getPlayerName(apiDataArray, i),
                    acquireData.getChampionPlayed(apiDataArray, i), acquireData.getMatchKills(apiDataArray, i), acquireData.getMatchDeaths(apiDataArray, i),
                    acquireData.getMatchAssists(apiDataArray, i), acquireData.getMatchResult(apiDataArray, i),
                    acquireData.getPosition(apiDataArray, i), acquireData.getPlayerRank(apiDataArray, i));
            additional = new Additional(acquireData.getDamageDealt(apiDataArray, i), acquireData.getDamageTaken(apiDataArray, i),
                    acquireData.getQueueType(apiDataArray, i), acquireData.getGoldEarned(apiDataArray, i), acquireData.getMatchDuration(apiDataArray, i),
                    acquireData.getMatchDate(apiDataArray, i), acquireData.getWardsPlaced(apiDataArray, i), acquireData.getCreepScore(apiDataArray, i));
            String[] items = acquireData.getItemNames(apiDataArray, i);
            item = new Item(items[0], items[1], items[2], items[3], items[4], items[5], items[6]);
            String[] blue = acquireData.getBlueTeamChampions(apiDataArray, i);
            String[] red = acquireData.getRedTeamChampions(apiDataArray, i);
            if (acquireData.getTeamColor(apiDataArray, i).equalsIgnoreCase("BLUE")) {
                team = new Team(blue[0], blue[1], blue[2], blue[3], red[0], red[1], red[2], red[3], red[4]);
            }
            else {
                team = new Team(red[0], red[1], red[2], red[3], blue[0], blue[1], blue[2], blue[3], blue[4]);
            }
            modelAccessObject.createGamedata(currentProfile.getProfileName(), gamedata, item, team, additional);
            secondaryInitialize();
        }
    }

    public void handleTableViewClicked(MouseEvent mouseEvent) {
        tableView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                    try {
                        selectedRiotId = tableView.getSelectionModel().getSelectedItem().getRiotId();
                        System.out.println(selectedRiotId);
                        mainApp.showIGWindow(selectedRiotId);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
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

    public long getSelectedRiotId() {
        return selectedRiotId;
    }

    public void setSelectedRiotId(long selectedRiotId) {
        this.selectedRiotId = selectedRiotId;
    }

}
