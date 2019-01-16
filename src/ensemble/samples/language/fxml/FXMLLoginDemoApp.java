/* ....Show License.... */
package ensemble.samples.language.fxml;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Parent;

/**
 * FXML-based Login screen sample
 */

public class FXMLLoginDemoApp extends Application {

    private Group root = new Group();
    private User loggedUser;
   // private ProfileController pc;
    private final double MINIMUM_WINDOW_WIDTH = 400.0;
    private final double MINIMUM_WINDOW_HEIGHT = 700.0;

    public Parent createContent() {
        gotoLogin();
        return root;
    }

    @Override public void start(Stage primaryStage) throws Exception {
        primaryStage.setResizable(false);
        primaryStage.setTitle("Control");
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    public User getLoggedUser() {
        return loggedUser;
    }
   // public ProfileController getPc(){return pc;}

    public boolean userLogging(String userId, String password){
        if (Authenticator.validate(userId, password)) {
            loggedUser = User.of(userId);
            gotoProfile();
            return true;
        } else {
            return false;
        }
    }

    public boolean normalUserLogging(String userId, String password){
        if (Authenticator.validateNormal(userId, password)) {
            loggedUser = User.of(userId);
            gotoControl();
            return true;
        } else {
            return false;
        }
    }


    void userLogout(){
        loggedUser = null;
        gotoLogin();
    }


    void controlLogout(){
        loggedUser = null;
        gotoLogin();
    }

     void gotoPeriodo() {
        try {
            PeriodoController periodo = (PeriodoController) replaceSceneContent("Periodo.fxml");
            periodo.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(FXMLLoginDemoApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


     void gotoProfile() {
        try {
            ProfileController profile = (ProfileController) replaceSceneContent("Profile.fxml");
            profile.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(FXMLLoginDemoApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void gotoControl() {
        try {
            ControlController control = (ControlController) replaceSceneContent("Control.fxml");
            control.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(FXMLLoginDemoApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void gotoLogin() {
        try {
            LoginController login = (LoginController) replaceSceneContent("Login.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(FXMLLoginDemoApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void gotoAdmin() {
        try {
            AdminController admin = (AdminController) replaceSceneContent("Admin.fxml");
            admin.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(FXMLLoginDemoApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = FXMLLoginDemoApp.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(FXMLLoginDemoApp.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        }
        root.getChildren().removeAll();
        root.getChildren().addAll(page);
        return (Initializable) loader.getController();
    }
}