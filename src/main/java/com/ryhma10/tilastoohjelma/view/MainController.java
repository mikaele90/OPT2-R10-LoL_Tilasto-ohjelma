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
import java.util.concurrent.atomic.AtomicReference;

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
    private Thread masterThread;
    private Thread slaveThread;

    @FXML
    private Label profileNameLabel, welcomeLabel, fetchGamesStatusLabel;
    @FXML
    private Button addNewGamesButton, profileOverviewButton;
    @FXML
    private Menu actionsMenu, editMenu, helpMenu;
    @FXML
    private MenuItem addGamesMenuItem, profileMenuItem, exitMenuItem, settingsMenuItem, helpMenuItem, aboutMenuItem;
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

    /**
     * Default constructor.
     */
    public MainController() {
        //Default constructor
    }

    /**
     * Initializes the main-window.
     */
    public void initialize() {
        System.out.println("MainController: Initialize");
        progressIndicator.setProgress(1.0);
        progressIndicator.setDisable(true);
        Platform.runLater(() -> {
            fetchGamesStatusLabel.setText(textBundle.getString("label.statusReady"));
            fetchGamesStatusLabel.setDisable(true);
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

    /**
     * Initializes the TableView.
     */
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

    /**
     * Sets the texts on language change.
     */
    public void setTexts() {
        Platform.runLater(() -> {
            welcomeLabel.setText(textBundle.getString("label.welcome"));
            if (fetchGamesStatusLabel.getText().equals(textBundle.getString("label.statusReady"))) {
                fetchGamesStatusLabel.setText(textBundle.getString("label.statusReady"));
            }
            else if (fetchGamesStatusLabel.getText().equals(textBundle.getString("label.statusFetchingGames"))) {
                fetchGamesStatusLabel.setText(textBundle.getString("label.statusFetchingGames"));
            }
            else {
                fetchGamesStatusLabel.setText(textBundle.getString("label.statusStandby"));
            }
            addNewGamesButton.setText(textBundle.getString("button.addGames"));
            profileOverviewButton.setText(textBundle.getString("button.profileOverview"));
            actionsMenu.setText(textBundle.getString("menu.actions"));
            editMenu.setText(textBundle.getString("menu.edit"));
            helpMenu.setText(textBundle.getString("menu.help"));
            aboutMenuItem.setText(textBundle.getString("menuItem.about"));
            helpMenuItem.setText(textBundle.getString("menuItem.help"));
            settingsMenuItem.setText(textBundle.getString("menuItem.settings"));
            addGamesMenuItem.setText(textBundle.getString("menuItem.addGames"));
            profileMenuItem.setText(textBundle.getString("menuItem.profileOverview"));
            exitMenuItem.setText(textBundle.getString("menuItem.exit"));
            column1.setText(textBundle.getString("tableColumn.id"));
            column2.setText(textBundle.getString("tableColumn.riotId"));
            column3.setText(textBundle.getString("tableColumn.date"));
            column4.setText(textBundle.getString("tableColumn.result"));
            column5.setText(textBundle.getString("tableColumn.champion"));
            column6.setText(textBundle.getString("tableColumn.summoner"));
            column7.setText(textBundle.getString("tableColumn.profileId"));
        });
    }

    /**
     * Shows the input-window.
     * @param actionEvent Interacting with the corresponding button, as defined in Main.fxml.
     * @throws IOException On failed load of Input.fxml.
     */
    @FXML
    public void handleAddGames(ActionEvent actionEvent) throws IOException {
        mainApp.showInputWindow();
    }

    /**
     * Shows the profile-window.
     * @param actionEvent Interacting with the corresponding button, as defined in Main.fxml.
     * @throws IOException On failed load of Profile.fxml.
     */
    @FXML
    public void handleProfileOverview(ActionEvent actionEvent) throws IOException {
        mainApp.showProfileWindow();
    }

    /**
     * Shows an Alert as defined in AlertFactory.
     * @param actionEvent Interacting with the corresponding button, as defined in Main.fxml.
     */
    @FXML
    public void handleAbout(ActionEvent actionEvent) {
        Alert aboutAlert = alertFactory.createAlert("About application");
        aboutAlert.showAndWait();
    }

    /**
     * Shows the settings-window.
     * @param actionEvent Interacting with the corresponding button, as defined in Main.fxml.
     * @throws IOException On failed load of Settings.fxml.
     */
    @FXML
    public void handleSettings(ActionEvent actionEvent) throws IOException {
        mainApp.showSettingsWindow();
    }

    /**
     * Exits the application.
     * @param actionEvent Interacting with the corresponding button, as defined in Main.fxml.
     */
    @FXML
    public void handleExit(ActionEvent actionEvent) {
        System.exit(0);
    }

    /**
     * Shows a help-dialog. Not yet implemented.
     * @param actionEvent Interacting with the corresponding button, as defined in Main.fxml.
     */
    @FXML
    public void handleMainHelp(ActionEvent actionEvent) {
        System.out.println("Not yet implemented");
        //TODO
    }

    /**
     * Starts fetching the games from the API and once done, exports them to the database.
     * @param gameIdArrayList The list of games to fetch from the API.
     * @param summonerName The player name to use.
     * @param modelAccessObject A ModelAccessObject.
     */
    public void startFetchingGames(ArrayList<Long> gameIdArrayList, String summonerName, ModelAccessObject modelAccessObject) {
        System.out.println("startFetchingGames(" + gameIdArrayList.toString() + ", " + summonerName + ", " + modelAccessObject.toString() + ")");
        Platform.runLater(() -> {
            progressIndicator.setDisable(false);
            progressIndicator.setProgress(0.0);
            fetchGamesStatusLabel.setText(textBundle.getString("label.statusFetchingGames"));
            fetchGamesStatusLabel.setDisable(false);
            while (progressIndicator.getProgress() < 0.09) {
                progressIndicator.setProgress(progressIndicator.getProgress()+0.01);
            }
        });
        slaveThread = new Thread(() -> {
            slaveThread.setPriority(1);
            AtomicReference<String> statusHelper = new AtomicReference<>(textBundle.getString("label.statusFetchingGames"));
            while (masterThread.isAlive()) {
                long sleepTime = gameIdArrayList.size() * 95;
                Platform.runLater(() -> {
                    if (progressIndicator.getProgress() < 0.98) {
                        progressIndicator.setProgress(progressIndicator.getProgress()+0.01);
                        if (!statusHelper.get().equalsIgnoreCase(fetchGamesStatusLabel.getText())) {
                            System.out.println("updating");
                            fetchGamesStatusLabel.setText(textBundle.getString("label.statusFetchingGames"));
                            statusHelper.set(textBundle.getString("label.statusFetchingGames"));
                        }
                    }
                });
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        masterThread = new Thread(() -> {
            slaveThread.start();
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
                try {
                    if (acquireData.getTeamColor(apiDataArray, i).equalsIgnoreCase("BLUE")) {
                        team = new Team(blue[0], blue[1], blue[2], blue[3], red[0], red[1], red[2], red[3], red[4]);
                    }
                    else {
                        team = new Team(red[0], red[1], red[2], red[3], blue[0], blue[1], blue[2], blue[3], blue[4]);
                    }
                } catch (NullPointerException npe) {
                    break;
                }
                modelAccessObject.createGamedata(currentProfile.getProfileName(), gamedata, item, team, additional);
                secondaryInitialize();
            }
            Platform.runLater(() -> {
                while(progressIndicator.getProgress() < 1.0) {
                    progressIndicator.setProgress(progressIndicator.getProgress()+0.01);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                fetchGamesStatusLabel.setText(textBundle.getString("label.statusReady"));
            });
        });
        masterThread.start();
    }

    /**
     * Detects double left-clicks and selects the column from the table and shows the individualGame-window.
     * @param mouseEvent Interacting with the mouse.
     */
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

    /**
     * A helper-method that prints information about the currently loaded SoftwareProfile.
     */
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

    /**
     * Getter for the current SoftwareProfile.
     * @return The SoftwareProfile.
     */
    public SoftwareProfile getCurrentProfile() {
        return currentProfile;
    }

    /**
     * Setter for the currentProfile-variable.
     * @param currentProfile A SoftwareProfile.
     */
    public void setCurrentProfile(SoftwareProfile currentProfile) {
        this.currentProfile = currentProfile;
    }

    /**
     * Refreshes the TableView.
     */
    public void refreshMainScene() {
        secondaryInitialize();
    }

    /**
     * Setter for the MainApp.
     * @param mainApp The MainApp.
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Setter for the mainStage.
     * @param mainStage The Stage.
     */
    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    /**
     * Getter for the currentProfile's name.
     * @return The name.
     */
    public String getProfileName() {
        return profileName;
    }

    /**
     * Text-setter for the profileNameLabel.
     * @param profileName The name.
     */
    public void setProfileNameLabel(String profileName) {
        profileNameLabel.setText(profileName);
    }

    /**
     * Getter for the current ResourceBundle.
     * @return The ResourceBundle.
     */
    public ResourceBundle getTextBundle() {
        return textBundle;
    }

    /**
     * Setter for the ResourceBundle.
     * @param textBundle A ResourceBundle.
     */
    public void setTextBundle(ResourceBundle textBundle) {
        this.textBundle = textBundle;
    }

    /**
     * Getter for the selectedRiotId-variable.
     * @return The selectedRiotId-variable.
     */
    public long getSelectedRiotId() {
        return selectedRiotId;
    }

    /**
     * Setter for the selectedRiotId-variable.
     * @param selectedRiotId The long to set.
     */
    public void setSelectedRiotId(long selectedRiotId) {
        this.selectedRiotId = selectedRiotId;
    }

}
