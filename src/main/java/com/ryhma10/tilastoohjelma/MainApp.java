package com.ryhma10.tilastoohjelma;

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
    private AnchorPane inputWindow;
    private AnchorPane feedBackWindow;
    private AnchorPane profileWindow;
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
    private String settingsWindowTitle;

    public MainApp() {
        //Constructor
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle(loginWindowTitle);
        showLoginWindow(textBundle);
    }

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
            this.loginWindowTitle = textBundle.getString("loginWindowTitle");
            this.createNewUserWindowTitle = textBundle.getString("createNewUserWindowTitle");
            this.settingsWindowTitle = textBundle.getString("settingsWindowTitle");
        }catch (Exception e) {
            System.out.println("TextBundle: file not found");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void showLoginWindow(ResourceBundle textBundle) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("/fxml/Login.fxml"));
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

    public void showCreateNewUserWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("/fxml/CreateNewUser.fxml"));
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

    public void showMainWindow(SoftwareProfile profile) throws IOException {
        System.out.println("user: " + profile.getProfileName());
        //System.out.println("psw: " + profile.getProfilePassword());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("/fxml/Main.fxml"));
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
        if (profile.getDefaultLanguage() != null) {
            currentLocale = Locale.forLanguageTag(profile.getDefaultLanguage().replace("_", "-"));
            currentLanguage = currentLocale.getLanguage();
            currentCountry = currentLocale.getCountry();
            textBundle = ResourceBundle.getBundle("languages/TextResources", currentLocale);
        }
        mainController.setTextBundle(textBundle);
        mainStage.setTitle(textBundle.getString("mainWindowTitle"));
        mainStage.show();
    }


    public void showInputWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("/fxml/Input.fxml"));
        inputWindow = (AnchorPane)loader.load();

        Stage inputStage = new Stage();
        inputStage.setTitle("Input your in-game statistics");
        inputStage.initModality(Modality.APPLICATION_MODAL);
        inputStage.initOwner(primaryStage);
        Scene InputScene = new Scene(inputWindow);
        inputStage.setScene(InputScene);

        InputController inputController = loader.getController();
        inputController.setMainApp(this);
        inputController.setInputStage(inputStage);

        inputStage.showAndWait();
    }

    public void showFeedBackWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("/fxml/FeedBack.fxml"));
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

        feedBackStage.showAndWait();
    }

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

        profileStage.showAndWait();
    }

    public void showSettingsWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("/fxml/Settings.fxml"));
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
        settingsStage.setTitle(textBundle.getString("settingsWindowTitle"));

        settingsStage.show();
    }

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

    public int checkLangDirLength() {
        return (Objects.requireNonNull(new File(languageDirPath).listFiles())).length;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public MainApp getMainApp() {
        return this;
    }

    public void setProfile(SoftwareProfile profile) {
        this.currentProfile = profile;
    }

    public SoftwareProfile getProfile() {
        return this.currentProfile;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public MainController getMainController() {
        return this.mainController;
    }

    public ResourceBundle getTextBundle() {
        return textBundle;
    }

    public void setTextBundle(ResourceBundle textBundle) {
        this.textBundle = textBundle;
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }

    public void setCurrentLocale(Locale currentLocale) {
        this.currentLocale = currentLocale;
    }

    public String getCurrentLanguage() {
        return currentLanguage;
    }

    public void setCurrentLanguage(String currentLanguage) {
        this.currentLanguage = currentLanguage;
    }

    public String getCurrentCountry() {
        return currentCountry;
    }

    public void setCurrentCountry(String currentCountry) {
        this.currentCountry = currentCountry;
    }

    public Properties getDefaultProperties() {
        return defaultProperties;
    }

    public void setDefaultProperties(Properties defaultProperties) {
        this.defaultProperties = defaultProperties;
    }

    public String getApplicationDefaultConfigFilePath() {
        return applicationDefaultConfigFilePath;
    }

    public void setApplicationDefaultConfigFilePath(String applicationDefaultConfigFilePath) {
        this.applicationDefaultConfigFilePath = applicationDefaultConfigFilePath;
    }

    public int getLanguageDirLength() {
        return languageDirLength;
    }

    public void setLanguageDirLength(int languageDirLength) {
        this.languageDirLength = languageDirLength;
    }

    public String getLanguageDirPath() {
        return languageDirPath;
    }

    public void setLanguageDirPath(String languageDirPath) {
        this.languageDirPath = languageDirPath;
    }

    public List<String> getLanguageDirFiles() {
        return languageDirFiles;
    }

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
