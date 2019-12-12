package com.ryhma10.tilastoohjelma.view;

import com.merakianalytics.orianna.types.common.OriannaException;
import com.merakianalytics.orianna.types.common.Region;
import com.ryhma10.tilastoohjelma.MainApp;
import com.ryhma10.tilastoohjelma.api.RiotApi;
import com.ryhma10.tilastoohjelma.model.Gamedata;
import com.ryhma10.tilastoohjelma.model.ModelAccessObject;
import com.ryhma10.tilastoohjelma.model.SoftwareProfile;
import com.ryhma10.tilastoohjelma.view.utilities.AlertFactory;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

/**
 * a class for handling data between the input window and the model
 * @author Janari
 *
 */
public class InputController  {

	private Gamedata match;
	private SoftwareProfile currentProfile;
	private ModelAccessObject modelAccessObject;
	private MainController mainController;
	private MainApp mainApp;
    private Stage inputStage;
    private AlertFactory alertFactory;
	private ResourceBundle textBundle;
	private SpinnerValueFactory<Integer> spinnerValueFactory;
	private final int defaultNumberOfGames = 1;
	private ArrayList<Long> matchHistoryIdsFromDb = null;
	private ArrayList<Long> matchHistoryIdsFromAPI = null;

    @FXML
	private Menu editMenu, helpMenu;
    @FXML
	private MenuItem settingsMenuItem, helpMenuItem, aboutMenuItem;
    @FXML
	private Label leagueOfLegendsRegionLabel, summonerNameLabel, numberOfGamesLabel, currentRegionLabel;
    @FXML
	private TextField summonerNameTextField;
    @FXML
	private Spinner<Integer> numberOfGamesSpinner;
    @FXML
	private Button cancelButton, fetchGamesButton;
    @FXML
	private ProgressIndicator progressIndicator;

	/**
	 * Initializes the window
	 */
	@FXML
	private void initialize() {
    	numberOfGamesSpinner.setEditable(true);
    	spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, defaultNumberOfGames);
    	numberOfGamesSpinner.setValueFactory(spinnerValueFactory);
    	summonerNameTextField.setText("");
		progressIndicator.setVisible(false);
		Platform.runLater(() -> {
			alertFactory = new AlertFactory(textBundle);
			mainController = mainApp.getMainController();
			currentProfile = mainApp.getProfile();
			currentRegionLabel.setText(currentProfile.getDefaultRegion().replace("_", " "));
		});
	}

	/**
	 * Default constructor
	 */
    public InputController() {
    	//Constructor
    }

	/**
	 * Checks whether user inputs are ok for continuation and fires the prepareFetchingGames-method.
	 * @param actionEvent Interacting with the corresponding button, as defined in Input.fxml.
	 */
	@FXML
	public void handleFetchGames(ActionEvent actionEvent) {
    	try {
    		int numberOfGames = Integer.parseInt(numberOfGamesSpinner.editorProperty().getValue().getText());
    		if (numberOfGames < 1) {
				numberOfGamesSpinner.editorProperty().getValue().setText(String.valueOf(defaultNumberOfGames));
				return;
			}
			if (!summonerNameTextField.getText().equals("")) {
				prepareFetchingGames(summonerNameTextField.getText(), numberOfGames);
			}
			else {
				Alert emptySummonerTextFieldAlert = alertFactory.createAlert("Input:UserInputError");
				emptySummonerTextFieldAlert.show();
			}
		}
    	catch (NumberFormatException nfe) {
			numberOfGamesSpinner.editorProperty().getValue().setText(String.valueOf(defaultNumberOfGames));
		}
	}

	/**
	 * Prepares a list of games to fetch from the API. Fires up the startFetchingGames-method in the MainController-class.
	 * @param summonerName The name of the wanted summoner (player).
	 * @param numberOfGames The number of games to fetch.
	 */
	private void prepareFetchingGames(String summonerName, int numberOfGames) {
    	Platform.runLater(() -> {
    		progressIndicator.setVisible(true);
		});
    	new Thread(() -> {
    		matchHistoryIdsFromAPI = new ArrayList<Long>();
    		matchHistoryIdsFromDb = new ArrayList<Long>();
			System.out.println("Fetching games...\nSummoner: " + summonerName + "\tNumber of games: " + numberOfGames);
			RiotApi riotApi = new RiotApi();
			ModelAccessObject modelAccessObject = new ModelAccessObject();
			ArrayList<Long> finalIdArrayList = new ArrayList<Long>();
			if (currentProfile.getRiotAPIKey() != null) {
				System.out.println("SoftwareProfile: API-key found (" + currentProfile.getRiotAPIKey() + ").");
				riotApi.setKeyFromProfile(currentProfile.getRiotAPIKey());
			}
			else {
				System.out.println("SoftwareProfile: No API-key found. Trying the one in ApiKey-class...");
				riotApi.setKey();
			}
			if (currentProfile.getDefaultRegion() != null) {
				riotApi.setWantedPlayer(summonerName);
				riotApi.setMatchListSize(numberOfGames);
				try {
					riotApi.setCurrentRegion(Region.valueOf(currentProfile.getDefaultRegion()));
					matchHistoryIdsFromAPI = riotApi.getCompleteMatchHistoryOnlyIds();
				} catch (OriannaException oe) {
					System.out.println(oe.getClass().getSimpleName());
				}
				List<Gamedata> matchHistoryFromDb;
				matchHistoryFromDb = modelAccessObject.readSpecificProfilesGames(currentProfile.getProfileName());
				for (Gamedata game : matchHistoryFromDb) {
					if (game.getIngameName().equals(summonerName)) {
						matchHistoryIdsFromDb.add(game.getRiotid());
					}
				}
				if (matchHistoryIdsFromDb == null || matchHistoryIdsFromDb.isEmpty()) {
					matchHistoryIdsFromDb = new ArrayList<>();
				}
				ArrayList<Long> helperArrayList = matchHistoryIdsFromAPI;
				Collection<Long> helperCollection = matchHistoryIdsFromDb;
				helperArrayList.removeAll(helperCollection);
				for (int i = 0; i < numberOfGames; i++) {
					try {
						finalIdArrayList.add(helperArrayList.get(i));
					} catch (IndexOutOfBoundsException ioobe) {
						break;
					}
				}
			}
			else {
				System.out.println("No default region set");
				return;
			}
			System.out.println("List from API: " + matchHistoryIdsFromAPI + "\nAPISize: " + matchHistoryIdsFromAPI.size());
			System.out.println("List from DB: " + matchHistoryIdsFromDb + "\nDBSize: " + matchHistoryIdsFromDb.size());
			System.out.println("Final list after removeAll(): " + finalIdArrayList + "\nFinalSize: " + finalIdArrayList.size());
			Platform.runLater(() -> {
				progressIndicator.setVisible(false);
				inputStage.close();
				mainController.startFetchingGames(finalIdArrayList, summonerName, modelAccessObject);
			});
		}).start();
	}

	/**
	 * Closes the inputStage.
	 * @param actionEvent Interacting with the corresponding button, as defined in Input.fxml.
	 */
	@FXML
	public void handleCancel(ActionEvent actionEvent) {
    	inputStage.close();
	}

	/**
	 * Opens the settings-window and closes the inputStage.
	 * @param actionEvent Interacting with the corresponding button, as defined in Input.fxml.
	 * @throws IOException on failed load of Settings.fxml.
	 */
	@FXML
	public void handleSettings(ActionEvent actionEvent) throws IOException {
    	mainApp.showSettingsWindow();
    	inputStage.close();
	}

	/**
	 * Shows a help-dialog. Not yet implemented.
	 * @param actionEvent Interacting with the corresponding button, as defined in Input.fxml.
	 */
	@FXML
	public void handleHelp(ActionEvent actionEvent) {
    	//TODO
		System.out.println("Not yet implemented");
	}

	/**
	 * Shows an Alert as defined in the AlertFactory-class.
	 * @param actionEvent Interacting with the corresponding button, as defined in Input.fxml.
	 */
	@FXML
	public void handleAbout(ActionEvent actionEvent) {
    	Alert aboutAlert = alertFactory.createAlert("About application");
    	aboutAlert.show();
	}

	/**
	 * Sets the currently in use ResourceBundle.
	 * @param textBundle A ResourceBundle.
	 */
	public void setTextBundle(ResourceBundle textBundle) {
		this.textBundle = textBundle;
	}

	/**
	 * Sets the MainApp.
	 * @param mainApp The MainApp.
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	/**
	 * Sets the Stage.
	 * @param inputStage The Stage to set.
	 */
	public void setInputStage(Stage inputStage) {
		this.inputStage = inputStage;
	}

}
