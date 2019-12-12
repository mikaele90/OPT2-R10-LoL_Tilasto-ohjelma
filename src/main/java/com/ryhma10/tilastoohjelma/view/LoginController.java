package com.ryhma10.tilastoohjelma.view;

import com.ryhma10.tilastoohjelma.MainApp;
import com.ryhma10.tilastoohjelma.model.ModelAccessObject;
import com.ryhma10.tilastoohjelma.model.SoftwareProfile;
import com.ryhma10.tilastoohjelma.view.utilities.AlertFactory;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class LoginController {

    private MainApp mainApp;
    private MainController mainController;
    private Stage loginStage;
    private AlertFactory alertFactory;
    private ResourceBundle textBundle;
    private List<CheckMenuItem> languageList;

    @FXML
    private Label labelUseExistingProfile, labelNoProfileYet, labelProfileName, labelProfilePassword;
    @FXML
    private TextField profileNameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton, createNewProfileButton;
    @FXML
    private ProgressIndicator progressIndicator;
    @FXML
    private Menu languageMenu, fileMenu, helpMenu;
    @FXML
    private MenuItem menuItemCreateNewProfile, menuItemExit, menuItemAbout;

    /**
     * Default constructor
     */
    public LoginController() {
        //Constructor
    }

    /**
     * Initializes the window
     */
    public void initialize() {
        if (progressIndicator.isVisible()) {
            progressIndicator.setVisible(false);
        }
        profileNameField.setText("");
        passwordField.setText("");
        Platform.runLater(() -> {
            if (languageList == null) {
                for (int i = 0; i < mainApp.getLanguageDirLength(); i++) {
                    String langString = mainApp.getLanguageDirFiles().get(i);
                    Properties propertiesHelper = new Properties();
                    try {
                        InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream("languages/" + langString);
                        propertiesHelper.load(in);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    CheckMenuItem checkMenuItemHelper = new CheckMenuItem(textBundle.getString(propertiesHelper.getProperty("localeINFO")));
                    checkMenuItemHelper.setId(propertiesHelper.getProperty("localeINFO"));
                    checkMenuItemHelper.setOnAction(this::handleChangeLanguage);
                    languageMenu.getItems().add(checkMenuItemHelper);
                }
                languageList = new ArrayList<>();
                for (MenuItem menuItem : languageMenu.getItems()) {
                    languageList.add((CheckMenuItem)menuItem);
                }
            }
            setSelectedLanguageMenuItem();
            alertFactory = new AlertFactory(textBundle);
        });
    }

    /**
     * Setter for the MainApp.
     * @param mainApp The MainApp.
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Setter for the loginStage-variable.
     * @param loginStage A Stage.
     */
    public void setLoginStage(Stage loginStage) {
        this.loginStage = loginStage;
    }

    /**
     * Detects if Enter-key is pressed.
     * @param keyEvent Any KeyEvent.
     * @throws IOException On exception.
     */
    @FXML
    public void onEnter(KeyEvent keyEvent) throws IOException {
        if(keyEvent.getCode().equals(KeyCode.ENTER)) {
            System.out.println("Detected: Enter pressed");
            handleLogin(new ActionEvent());
        }
    }

    /**
     * Shows an Alert as defined by AlertFactory.
     * @param actionEvent Interacting with the corresponding button, as defined in Login.fxml.
     */
    @FXML
    public void handleAboutApplication(ActionEvent actionEvent) {
        Alert aboutAlert = alertFactory.createAlert("About application");
        aboutAlert.showAndWait();
    }

    /**
     * Shows the createNewUser-window.
     * @param actionEvent Interacting with the corresponding button, as defined in Login.fxml.
     * @throws IOException On failed load of CreateNewUser.fxml.
     */
    @FXML
    public void handleCreateNewProfile(ActionEvent actionEvent) throws IOException {
        mainApp.showCreateNewUserWindow();
    }

    /**
     * Exits the application.
     * @param actionEvent Interacting with the corresponding button, as defined in Login.fxml.
     */
    @FXML
    public void handleExit(ActionEvent actionEvent) {
        System.exit(0);
    }

    /**
     * Attempts to login a SoftwareProfile into the application. On success, opens the main-window.
     * Loads a ResourceBundle based on the SoftwareProfile's defaultLanguage.
     * @param actionEvent Interacting with the corresponding button, as defined in Login.fxml.
     * @throws IOException On failed load of Main.fxml.
     */
    @FXML
    public void handleLogin(ActionEvent actionEvent) throws IOException {
        if(profileNameField.getText().length() > 0 && passwordField.getText().length() > 3) {
            loginButton.setText(textBundle.getString("button.loggingIn"));
            new Thread(() -> {
                progressIndicator.setVisible(true);
                ModelAccessObject modelAccessObject1 = new ModelAccessObject();
                String resultStringFromMethod = modelAccessObject1.loginProfile(profileNameField.getText(), passwordField.getText());
                Platform.runLater(() -> {
                    switch (resultStringFromMethod) {
                        case "Login successful":
                            System.out.println(resultStringFromMethod);
                            ModelAccessObject modelAccessObject2 = new ModelAccessObject();
                            SoftwareProfile currentProfile = modelAccessObject2.setLoggedInProfile(profileNameField.getText(), passwordField.getText());
                            if (currentProfile != null) {
                                System.out.println(currentProfile.getProfileName());
                                mainApp.setProfile(currentProfile);
                                loginStage.close();
                                try {
                                    mainApp.showMainWindow(currentProfile);
                                } catch (IOException e) {
                                    System.out.println("Something went wrong during the login (showMainWindow).");
                                    e.printStackTrace();
                                }
                            }
                            else {
                                System.out.println("Something went wrong during the login (setLoggedInProfile).");
                            }
                            break;
                        case "Profile not found":
                        case "Too many records found":
                        case "Database connection error":
                            Platform.runLater(() -> {
                                System.out.println(resultStringFromMethod);
                                Alert errorAlert = alertFactory.createAlert(resultStringFromMethod);
                                errorAlert.show();
                            });
                            break;
                    }
                    progressIndicator.setVisible(false);
                    loginButton.setText(textBundle.getString("button.login"));
                });
            }).start();
        }
        else {
            Alert userInputErrorAlert = alertFactory.createAlert("Login:UserInputError");
            userInputErrorAlert.showAndWait();
        }

    }

    /**
     * Change the language of the defaultconfig.properties file (unless running from a JAR) and updates the UI. Only affects windows pre-login.
     * @param actionEvent Interacting with the corresponding button, as defined in Login.fxml.
     */
    public void handleChangeLanguage(ActionEvent actionEvent) {
        CheckMenuItem clickedItem = (CheckMenuItem)actionEvent.getTarget();
        if (!mainApp.isJAR()) {
            if ((!mainApp.getDefaultProperties().getProperty("languageINFO").equals(clickedItem.getId().substring(0,2))) && (!mainApp.getDefaultProperties().getProperty("countryINFO").equals(clickedItem.getId().substring(3,5)))) {
                mainApp.getDefaultProperties().setProperty("languageINFO", clickedItem.getId().substring(0,2));
                mainApp.getDefaultProperties().setProperty("countryINFO", clickedItem.getId().substring(3,5));
                try {
                    mainApp.getDefaultProperties().store(new FileOutputStream(mainApp.getApplicationDefaultConfigFilePath()), null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mainApp.init();
                try {
                    mainApp.start(mainApp.getPrimaryStage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> {
                    try {
                        mainApp.showLoginWindow(mainApp.getTextBundle());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
            setSelectedLanguageMenuItem();
        }
        else {
            if ((!mainApp.getDefaultProperties().getProperty("languageINFO").equals(clickedItem.getId().substring(0,2))) && (!mainApp.getDefaultProperties().getProperty("countryINFO").equals(clickedItem.getId().substring(3,5)))) {
                mainApp.getDefaultProperties().setProperty("languageINFO", clickedItem.getId().substring(0,2));
                mainApp.getDefaultProperties().setProperty("countryINFO", clickedItem.getId().substring(3,5));
                mainApp.init();
                try {
                    mainApp.start(mainApp.getPrimaryStage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> {
                    try {
                        mainApp.showLoginWindow(mainApp.getTextBundle());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
            setSelectedLanguageMenuItem();
        }
    }

    /**
     * Sets the check-mark in the language-menu.
     */
    private void setSelectedLanguageMenuItem() {
        for (CheckMenuItem language : languageList) {
            if (language.getId().equals(textBundle.getString("localeINFO"))) {
                language.setSelected(true);
            }
            else {
                language.setSelected(false);
            }
        }
    }

    /**
     * Getter for the ResourceBundle in the textBundle-variable.
     * @return The ResourceBundle.
     */
    public ResourceBundle getTextBundle() {
        return textBundle;
    }

    /**
     * Setter for the textBundle-variable.
     * @param textBundle A ResourceBundle.
     */
    public void setTextBundle(ResourceBundle textBundle) {
        this.textBundle = textBundle;
    }
}
