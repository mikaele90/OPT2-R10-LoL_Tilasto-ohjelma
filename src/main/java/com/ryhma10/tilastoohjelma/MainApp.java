package com.ryhma10.tilastoohjelma;

import com.ryhma10.tilastoohjelma.model.SoftwareProfile;
import com.ryhma10.tilastoohjelma.view.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;


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

    public MainApp() {
        //Constructor
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("WinStreak.exe");
        showLoginWindow();
    }

    public void showLoginWindow() throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("/fxml/Login.fxml"));
        loginWindow = (AnchorPane)loader.load();

        Scene loginScene = new Scene(loginWindow);
        loginScene.getStylesheets().add("/styles/Styles.css");
        primaryStage.setScene(loginScene);

        LoginController loginController = loader.getController();
        loginController.setMainApp(this);
        loginController.setLoginStage(primaryStage);

        primaryStage.show();
    }

    public void showCreateNewUserWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("/fxml/CreateNewUser.fxml"));
        createNewUserWindow = (BorderPane)loader.load();

        Stage createNewUserStage = new Stage();
        createNewUserStage.setTitle("Create new profile");
        createNewUserStage.initModality(Modality.WINDOW_MODAL);
        createNewUserStage.initOwner(primaryStage);
        Scene createNewUserScene = new Scene(createNewUserWindow);
        createNewUserScene.getStylesheets().add("/styles/Styles.css");
        createNewUserStage.setScene(createNewUserScene);

        CreateNewUserController createNewUserController = loader.getController();
        createNewUserController.setMainApp(this);
        createNewUserController.setCreateNewUserStage(createNewUserStage);

        createNewUserStage.show();
    }

    public void showMainWindow(SoftwareProfile profile) throws IOException {
        System.out.println("user: " + profile.getProfileName());
        //System.out.println("psw: " + profile.getProfilePassword());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("/fxml/Main.fxml"));
        mainWindow = (AnchorPane)loader.load();

        Stage mainStage = new Stage();
        mainStage.setTitle(primaryStage.getTitle());
        mainStage.initOwner(primaryStage);
        Scene mainScene = new Scene(mainWindow);
        mainScene.getStylesheets().add("/styles/Styles.css");
        mainStage.setScene(mainScene);

        MainController mainController = loader.getController();
        mainController.setMainApp(this);
        mainController.setMainStage(mainStage);

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
        settingsStage.setTitle("Settings");
        settingsStage.initModality(Modality.APPLICATION_MODAL);
        settingsStage.initOwner(primaryStage);
        Scene settingsScene = new Scene(settingsWindow);
        settingsStage.setScene(settingsScene);

        SettingsController settingsController = loader.getController();
        settingsController.setMainApp(this);
        settingsController.setSettingsStage(settingsStage);

        settingsStage.show();
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
