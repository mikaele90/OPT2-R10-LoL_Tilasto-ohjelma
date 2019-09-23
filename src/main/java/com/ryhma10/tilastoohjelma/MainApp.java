package com.ryhma10.tilastoohjelma;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import com.ryhma10.tilastoohjelma.LoginController;
import com.ryhma10.tilastoohjelma.CreateNewUserController;


public class MainApp extends Application {

    private Stage primaryStage;
    private AnchorPane loginWindow;
    private AnchorPane createNewUserWindow;

    public MainApp() {
        //Constructor
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("LoL Tilasto-ohjelma");
        showLoginWindow();
    }

    public void showLoginWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("/fxml/LoginWindow.fxml"));
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
        loader.setLocation(MainApp.class.getResource("/fxml/CreateNewUserWindow.fxml"));
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
