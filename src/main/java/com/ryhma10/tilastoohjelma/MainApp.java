package com.ryhma10.tilastoohjelma;

import com.ryhma10.tilastoohjelma.model.FeedBack;
import com.ryhma10.tilastoohjelma.model.SoftwareProfile;
import com.ryhma10.tilastoohjelma.view.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;


public class MainApp extends Application {

    private Stage primaryStage;
    private AnchorPane loginWindow;
    private BorderPane createNewUserWindow;
    private AnchorPane mainWindow;
    private BorderPane inputWindow;
    private AnchorPane feedBackWindow;
    private AnchorPane profileWindow;
    private AnchorPane IGWindow;
    private BorderPane settingsWindow;

    private SoftwareProfile currentProfile;
    private MainController mainController;

    private ResourceBundle textBundle;
    private Properties defaultProperties;
    private String applicationDefaultConfigFilePath;
    private String languageDirPath;
    private Locale currentLocale;
    private String currentLanguage;
    private String currentCountry;
    private int languageDirLength;
    private List<String> languageDirFiles;

    private String loginWindowTitle;
    private String createNewUserWindowTitle;
    private String mainWindowTitle;
    private String inputWindowTitle;
    private String settingsWindowTitle;

    public MainApp() {
        //Constructor
    }

    /**
     * Run when app starts.
     * @param primaryStage the primaryStage Stage.
     * @throws Exception on Exception.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle(loginWindowTitle);
        showLoginWindow(textBundle);
    }

    /**
     * Init for MainApp. Loads language-files among other things.
     */
    public void init() {
        this.applicationDefaultConfigFilePath = "src/main/resources/defaultconfig.properties";
        this.languageDirPath = "src/main/resources/languages";
        this.defaultProperties = new Properties();
        try {
            this.defaultProperties.load(new FileInputStream(applicationDefaultConfigFilePath));
            this.currentLanguage = defaultProperties.getProperty("languageINFO");
            this.currentCountry = defaultProperties.getProperty("countryINFO");
            this.currentLocale = new Locale(currentLanguage, currentCountry);
            System.out.println("Current Locale (mainApp):" + currentLocale.toString());
        } catch (Exception e) {
            System.out.println("Default properties: file not found");
            e.printStackTrace();
            System.exit(-1);
        }
        try {
            this.textBundle = ResourceBundle.getBundle("languages/TextResources", currentLocale);
            this.languageDirLength = checkLangDirLength();
            this.languageDirFiles = Arrays.asList(Objects.requireNonNull(new File(languageDirPath).list()));
            //Set window titles using defaultconfig
            this.loginWindowTitle = textBundle.getString("windowTitle.login");
            this.createNewUserWindowTitle = textBundle.getString("windowTitle.createNewUser");
            this.mainWindowTitle = textBundle.getString("windowTitle.main");
            this.settingsWindowTitle = textBundle.getString("windowTitle.settings");
            this.inputWindowTitle = textBundle.getString("windowTitle.input");
        }catch (Exception e) {
            System.out.println("TextBundle: file not found");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Loads the Login-window.
     * @param textBundle a ResourceBundle.
     * @throws IOException on failed load.
     */
    public void showLoginWindow(ResourceBundle textBundle) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"), textBundle);
        loginWindow = (AnchorPane)loader.load();

        Scene loginScene = new Scene(loginWindow);
        loginScene.getStylesheets().add("/styles/Styles.css");
        primaryStage.setScene(loginScene);

        LoginController loginController = loader.getController();
        loginController.setMainApp(this);
        loginController.setLoginStage(primaryStage);
        loginController.setTextBundle(textBundle);

        primaryStage.show();
    }

    /**
     * Loads the CreateNewUser-window.
     * @throws IOException on failed load.
     */
    public void showCreateNewUserWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CreateNewUser.fxml"), textBundle);
        createNewUserWindow = (BorderPane)loader.load();

        Stage createNewUserStage = new Stage();
        createNewUserStage.setTitle(createNewUserWindowTitle);
        createNewUserStage.initModality(Modality.WINDOW_MODAL);
        createNewUserStage.initOwner(primaryStage);
        Scene createNewUserScene = new Scene(createNewUserWindow);
        createNewUserScene.getStylesheets().add("/styles/Styles.css");
        createNewUserStage.setScene(createNewUserScene);

        CreateNewUserController createNewUserController = loader.getController();
        createNewUserController.setMainApp(this);
        createNewUserController.setCreateNewUserStage(createNewUserStage);
        createNewUserController.setTextBundle(textBundle);

        createNewUserStage.show();
    }

    /**
     * Shows the Main-window.
     * @param profile a SoftwareProfile.
     * @throws IOException on failed load.
     */
    public void showMainWindow(SoftwareProfile profile) throws IOException {
        if (profile.getDefaultLanguage() != null) {
            currentLocale = Locale.forLanguageTag(profile.getDefaultLanguage().replace("_", "-"));
            currentLanguage = currentLocale.getLanguage();
            currentCountry = currentLocale.getCountry();
            textBundle = ResourceBundle.getBundle("languages/TextResources", currentLocale);
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"), textBundle);
        mainWindow = (AnchorPane)loader.load();

        Stage mainStage = new Stage();
        mainStage.setTitle(mainWindowTitle);
        mainStage.initOwner(primaryStage);
        Scene mainScene = new Scene(mainWindow);
        mainScene.getStylesheets().add("/styles/Styles.css");
        mainStage.setScene(mainScene);

        MainController mainController = loader.getController();
        mainController.setMainApp(this);
        mainController.setMainStage(mainStage);

        mainController.setTextBundle(textBundle);
        mainStage.setTitle(textBundle.getString("windowTitle.main"));
        mainStage.show();
    }

    /**
     * Loads the IndividualGame-window.
     * @param riotId a valid id for a game fetched from Riot's API.
     * @throws IOException on failed load.
     */
    public void showIGWindow(long riotId) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/IndividualGame.fxml"), textBundle);
        IGWindow = (AnchorPane)loader.load();

        Stage IGStage = new Stage();
        IGStage.setTitle("Here are your statistics for this specific game");
        IGStage.initModality(Modality.APPLICATION_MODAL);
        IGStage.initOwner(primaryStage);
        Scene IGScene = new Scene(IGWindow);
        IGStage.setScene(IGScene);

        IndividualGameController igcontroller = loader.getController();
        igcontroller.setMainApp(this);
        igcontroller.setIGStage(IGStage);
        igcontroller.setRiotId(riotId);
        igcontroller.setTextBundle(textBundle);
        IGStage.setTitle(textBundle.getString("windowTitle.individualGame"));

        IGStage.show();
    }

    /**
     * Shows the Input-window.
     * @throws IOException on failed load.
     */
    public void showInputWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Input.fxml"), textBundle);
        inputWindow = (BorderPane)loader.load();

        Stage inputStage = new Stage();
        inputStage.setTitle("Input your in-game statistics");
        inputStage.initModality(Modality.APPLICATION_MODAL);
        inputStage.initOwner(primaryStage);
        Scene InputScene = new Scene(inputWindow);
        inputStage.setScene(InputScene);

        InputController inputController = loader.getController();
        inputController.setMainApp(this);
        inputController.setInputStage(inputStage);
        inputController.setTextBundle(textBundle);
        inputStage.setTitle(textBundle.getString("windowTitle.input"));

        inputStage.show();
    }

    /**
     * Loads the Feedback-window.
     * @param newFeedback a Feedback-object.
     * @throws IOException on failed load.
     */
    public void showFeedBackWindow(FeedBack newFeedback) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Feedback.fxml"), textBundle);
        feedBackWindow = (AnchorPane)loader.load();

        Stage feedBackStage = new Stage();
        feedBackStage.setTitle("Check out your match analysis");
        feedBackStage.initModality(Modality.APPLICATION_MODAL);
        feedBackStage.initOwner(primaryStage);
        Scene FeedBackScene = new Scene(feedBackWindow);
        feedBackStage.setScene(FeedBackScene);

        FeedBackController feedBackController = loader.getController();
        feedBackController.setMainApp(this);
        feedBackController.setFeedBackStage(feedBackStage);
        feedBackController.setFb(newFeedback);
        feedBackController.setTextBundle(textBundle);
        feedBackStage.setTitle(textBundle.getString("windowTitle.feedback"));

        feedBackStage.show();
    }

    /**
     * Loads the Profile-window. Under construction.
     * @throws IOException on failed load.
     */
    public void showProfileWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("/fxml/Profile.fxml"));
        profileWindow = (AnchorPane)loader.load();

        Stage profileStage = new Stage();
        profileStage.setTitle("Check out your profile");
        profileStage.initModality(Modality.APPLICATION_MODAL);
        profileStage.initOwner(primaryStage);
        Scene ProfileScene = new Scene(profileWindow);
        profileStage.setScene(ProfileScene);

        ProfileController profileController = loader.getController();
        profileController.setMainApp(this);
        profileController.setProfileStage(profileStage);

        profileStage.show();
    }

    /**
     * Loads the Settings-window.
     * @throws IOException on failed load.
     */
    public void showSettingsWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Settings.fxml"), textBundle);
        settingsWindow = (BorderPane)loader.load();

        Stage settingsStage = new Stage();
        settingsStage.setTitle(settingsWindowTitle);
        settingsStage.initModality(Modality.APPLICATION_MODAL);
        settingsStage.initOwner(primaryStage);
        Scene settingsScene = new Scene(settingsWindow);
        settingsStage.setScene(settingsScene);

        SettingsController settingsController = loader.getController();
        settingsController.setMainApp(this);
        settingsController.setSettingsStage(settingsStage);
        settingsController.setTextBundle(textBundle);
        settingsStage.setTitle(textBundle.getString("windowTitle.settings"));

        settingsStage.show();
    }

    /**
     * Returns a List of languages.
     * @return the List<String>.
     */
    public List<String> getLanguageArrayList() {
        List<String> languageArrayList = new ArrayList<>();
        Properties propertiesHelper = new Properties();
        for (int i = 0; i < getLanguageDirLength(); i++) {
            String langString = getLanguageDirFiles().get(i);
            try {
                propertiesHelper.load(new FileInputStream(getLanguageDirPath() + "/" + langString));
            } catch (IOException e) {
                e.printStackTrace();
            }
            languageArrayList.add(textBundle.getString(propertiesHelper.getProperty("localeINFO")));
        }
        return languageArrayList;
    }

    /**
     * Checks the number of files in the languages-directory.
     * @return the number of files.
     */
    public int checkLangDirLength() {
        return (Objects.requireNonNull(new File(languageDirPath).listFiles())).length;
    }

    /**
     * Return the primaryStage Stage.
     * @return the Stage.
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Return this MainApp.
     * @return the MainApp.
     */
    public MainApp getMainApp() {
        return this;
    }

    /**
     * Sets the settingsWindow-variable.
     * @param settingsWindow a BorderPane.
     */
    public void setSettingsWindow(BorderPane settingsWindow) {
        this.settingsWindow = settingsWindow;
    }

    /**
     * Sets the currentProfile-variable.
     * @param profile a SoftwareProfile.
     */
    public void setProfile(SoftwareProfile profile) {
        this.currentProfile = profile;
    }

    /**
     * Returns the currentProfile-variable.
     * @return a SoftwareProfile.
     */
    public SoftwareProfile getProfile() {
        return this.currentProfile;
    }

    /**
     * Sets the mainController-variable.
     * @param mainController a MainController.
     */
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * Returns the mainController-variable.
     * @return a MainController.
     */
    public MainController getMainController() {
        return this.mainController;
    }

    /**
     * Return the textBundle-variable.
     * @return a ResourceBundle.
     */
    public ResourceBundle getTextBundle() {
        return textBundle;
    }

    /**
     * Sets the textBundle-variable.
     * @param textBundle a ResourceBundle.
     */
    public void setTextBundle(ResourceBundle textBundle) {
        this.textBundle = textBundle;
    }

    /**
     * Returns the currentLocale-variable.
     * @return a Locale.
     */
    public Locale getCurrentLocale() {
        return currentLocale;
    }

    /**
     * Sets the currentLocale-variable.
     * @param currentLocale a Locale.
     */
    public void setCurrentLocale(Locale currentLocale) {
        this.currentLocale = currentLocale;
    }

    /**
     * Returns the currentLanguage-variable.
     * @return a String.
     */
    public String getCurrentLanguage() {
        return currentLanguage;
    }

    /**
     * Sets the currentLanguage-variable.
     * @param currentLanguage a String of format e.g. "en".
     */
    public void setCurrentLanguage(String currentLanguage) {
        this.currentLanguage = currentLanguage;
    }

    /**
     * Return the currentCountry-variable.
     * @return a String.
     */
    public String getCurrentCountry() {
        return currentCountry;
    }

    /**
     * Sets the currentCountry-variable.
     * @param currentCountry a String of format e.g. "US".
     */
    public void setCurrentCountry(String currentCountry) {
        this.currentCountry = currentCountry;
    }

    /**
     * Return the defaultProperties-variable.
     * @return a Properties.
     */
    public Properties getDefaultProperties() {
        return defaultProperties;
    }

    /**
     * Sets the defaultProperties-variable.
     * @param defaultProperties a Properties.
     */
    public void setDefaultProperties(Properties defaultProperties) {
        this.defaultProperties = defaultProperties;
    }

    /**
     * Returns the applicationDefaultConfigFilePath-variable.
     * @return a String.
     */
    public String getApplicationDefaultConfigFilePath() {
        return applicationDefaultConfigFilePath;
    }

    /**
     * Sets the applicationDefaultConfigFilePath-variable.
     * @param applicationDefaultConfigFilePath a String.
     */
    public void setApplicationDefaultConfigFilePath(String applicationDefaultConfigFilePath) {
        this.applicationDefaultConfigFilePath = applicationDefaultConfigFilePath;
    }

    /**
     * Returns the languageDirLength-variable.
     * @return an Integer.
     */
    public int getLanguageDirLength() {
        return languageDirLength;
    }

    /**
     * Sets the languageDirLength-variable.
     * @param languageDirLength an Integer.
     */
    public void setLanguageDirLength(int languageDirLength) {
        this.languageDirLength = languageDirLength;
    }

    /**
     * Returns the languageDirPath-variable.
     * @return a String.
     */
    public String getLanguageDirPath() {
        return languageDirPath;
    }

    /**
     * Sets the languageDirPath-variable.
     * @param languageDirPath a String.
     */
    public void setLanguageDirPath(String languageDirPath) {
        this.languageDirPath = languageDirPath;
    }

    /**
     * Returns the languageDirFiles-List.
     * @return a List<String>.
     */
    public List<String> getLanguageDirFiles() {
        return languageDirFiles;
    }

    /**
     * Sets the languageDirFiles-List.
     * @param languageDirFiles a List<String>.
     */
    public void setLanguageDirFiles(List<String> languageDirFiles) {
        this.languageDirFiles = languageDirFiles;
    }


    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
