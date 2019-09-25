package com.ryhma10.tilastoohjelma;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import com.ryhma10.tilastoohjelma.view.LoginController;
import com.ryhma10.tilastoohjelma.view.CreateNewUserController;
import com.ryhma10.tilastoohjelma.model.Database;
import com.ryhma10.tilastoohjelma.view.InputController;
import com.ryhma10.tilastoohjelma.view.FeedBackController;
import com.ryhma10.tilastoohjelma.view.ProfileController;


public class MainApp extends Application {

    private Stage primaryStage;
    private AnchorPane loginWindow;
    private AnchorPane createNewUserWindow;
    private AnchorPane inputWindow;
    private AnchorPane feedBackWindow;
    private AnchorPane profileWindow;

    public MainApp() {
        //Constructor
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("LoL Tilasto-ohjelma");
        showLoginWindow();
        connect();
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

        primaryStage.show();
    }

    public void showCreateNewUserWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("/fxml/CreateNewUser.fxml"));
        createNewUserWindow = (AnchorPane)loader.load();

        Stage createNewUserStage = new Stage();
        createNewUserStage.setTitle("Create new profile");
        createNewUserStage.initModality(Modality.WINDOW_MODAL);
        createNewUserStage.initOwner(primaryStage);
        Scene createNewUserScene = new Scene(createNewUserWindow);
        createNewUserStage.setScene(createNewUserScene);

        CreateNewUserController createNewUserController = loader.getController();
        createNewUserController.setMainApp(this);
        createNewUserController.setCreateNewUserStage(createNewUserStage);

        createNewUserStage.showAndWait();
    }
    
    
    public void showInputWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("/fxml/Input.fxml"));
        inputWindow = (AnchorPane)loader.load();

        Stage inputStage = new Stage();
        inputStage.setTitle("Input your in-game statistics");
        inputStage.initModality(Modality.WINDOW_MODAL);
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
        feedBackStage.initModality(Modality.WINDOW_MODAL);
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
        profileStage.initModality(Modality.WINDOW_MODAL);
        profileStage.initOwner(primaryStage);
        Scene ProfileScene = new Scene(profileWindow);
        profileStage.setScene(ProfileScene);

        ProfileController profileController = loader.getController();
        profileController.setMainApp(this);
        profileController.setProfileStage(profileStage);

        profileStage.showAndWait();
    }

    public void connect() {
        Database db = new Database();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public MainApp getMainApp() {
        return this;
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
