package com.ryhma10.tilastoohjelma.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import com.ryhma10.tilastoohjelma.MainApp;
import com.ryhma10.tilastoohjelma.model.Profile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class CreateNewUserController {

    private MainApp mainApp;
    private Stage createNewUserStage;

    public CreateNewUserController() {
        //Constructor
    }

    public void initialize() {
        if (progressIndicator.isVisible()) {
            progressIndicator.setVisible(false);
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setCreateNewUserStage(Stage createNewUserStage) {
        this.createNewUserStage = createNewUserStage;
    }

    @FXML
    private ProgressIndicator progressIndicator;

    @FXML
    private TextField createProfileNameField, createProfilePasswordField;


    @FXML
    public void handleCancel(ActionEvent actionEvent) {
        createNewUserStage.close();
    }

    @FXML
    public void handleDone(ActionEvent actionEvent) {
        SessionFactory factory = null;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try{
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        }catch(Exception e) {
            System.out.println("Istuntotehtaan luonti ei onnistunut");
            StandardServiceRegistryBuilder.destroy(registry);
            e.printStackTrace();
            System.exit(-1);
        }
        Profile newProfile = new Profile(createProfileNameField.getText(), createProfilePasswordField.getText());
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();

            session.saveOrUpdate(newProfile);
            transaction.commit();
            transaction = null;
            Alert newProfileSuccess = new Alert(Alert.AlertType.INFORMATION);
            newProfileSuccess.setTitle("Success");
            newProfileSuccess.setHeaderText("Profile created");
            newProfileSuccess.setContentText("Profile created successfully.");
            factory.close();
            newProfileSuccess.showAndWait();
        }
        catch (Exception e) {
            Alert newProfileProblem = new Alert(Alert.AlertType.ERROR);
            newProfileProblem.setTitle("Error");
            newProfileProblem.setHeaderText("Profile couldn't be created");
            newProfileProblem.setContentText("A problem occured while trying to create the profile.");
            System.out.println("Profiilin luonnissa ongelma.");
            if (transaction != null) {
                transaction.rollback();
            }
            factory.close();
            newProfileProblem.showAndWait();
        }
        if (!factory.isClosed()) {
            factory.close();
        }
        createNewUserStage.close();
    }

}
