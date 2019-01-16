/* ....Show License.... */
package ensemble.samples.language.fxml;

import java.awt.Color;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;

/**
 * Login Controller.
 */
public class LoginController extends AnchorPane implements Initializable {

    @FXML
    TextField userId;
    @FXML
    PasswordField password;
    @FXML
    Button login;
    @FXML
    Label errorMessage;
    @FXML
    ImageView adminImg;

    private FXMLLoginDemoApp application;


    public void setApp(FXMLLoginDemoApp application){
        this.application = application;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorMessage.setText("");
        userId.setPromptText("ingrese su usuario");
        password.setPromptText("ingrese su contraseña");
        //adminImg.getHeight();
    }


    public void processLogin(ActionEvent event) {
        if (application == null){
            errorMessage.setText("Hola " + userId.getText());
        } else {
            if (!application.normalUserLogging(userId.getText(), password.getText())){
                errorMessage.setText("El usuario/contraseña no coincide");
            }
        }
    }

    public void processAdmin() {
        if (application == null){
            errorMessage.setText("Hola " + userId.getText());
        } else {
            if (!application.userLogging(userId.getText(), password.getText())){
                errorMessage.setText("Sólo personal autorizado");
            }
        }
    }

}