/* ....Show License.... */
package ensemble.samples.language.fxml;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import javax.swing.*;

/**
 * Profile Controller.
 */
public class ProfileController extends AnchorPane implements Initializable {

    @FXML
    private TextField user;
    @FXML
    private TextField name;
    @FXML
    private TextField pass;
    @FXML
    private TextField ape_pat;
    @FXML
    private TextField ape_mat;
    @FXML
    private TextField uni;
    @FXML
    private TextField tut_aca;
    @FXML
    private TextField tut_sat;
    @FXML
    private TextField mat;
    @FXML
    private Label adminLbl;
    @FXML
    private Hyperlink logout;
    @FXML
    private Button save;
    @FXML
    private Button reset;
    @FXML
    private Button createAdmin;
    @FXML
    private Label success;

    public void setUsuario(String usuario) {this.usuario = usuario;}
    // public String us = "";
    public static String usuario;
   // public void setUsuario(String us) {this.us = usuario;}
    public String getUsuario() {return usuario;}

    private FXMLLoginDemoApp application;

    public void setApp(FXMLLoginDemoApp application){
        this.application = application;
        User loggedUser = application.getLoggedUser();
       // System.out.println(loggedUser.getId());
        adminLbl.setText("Admin: "+loggedUser.getId());
        success.setOpacity(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void processLogout(ActionEvent event) {
        if (application == null){
            return;
        }
        application.userLogout();
    }

    public void saveProfile(ActionEvent event) {

        String us = user.getText();
        //setUsuario(us);
        usuario = us;
        String n = name.getText();
        String p =  pass.getText();
        String a_p = ape_pat.getText();
        String a_m = ape_mat.getText();
        String un = uni.getText();
        String t_a = tut_aca.getText();
        String t_s = tut_sat.getText();
        String m = mat.getText();
        //System.out.println("usuario"+us + "nombre" + n + "pass" +p + "ape_pat"+a_p + "ape_mat"+a_m +"uni"+un + "tut_aca"+ t_a + "tut_sat"+t_s + "mat"+m);

        if ( us.isEmpty() || n.isEmpty() || p.isEmpty() || a_p.isEmpty() || a_m.isEmpty() || un.isEmpty() || t_a.isEmpty() || t_s.isEmpty() || m.isEmpty()) {
            //JOptionPane.showMessageDialog(null, "guardando ==\"\"");
            //success.setText("Todos los campos son obligatorios");
            animateMessage();
            success.setText("Todos los campos son obligatorios");
            //JOptionPane.showMessageDialog(null, "No guardando. ==\"\"");
        } else {
            success.setText("guardado exitosamente!");
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/biometric","Root","ThisIsMyRealPass_TrustMe");
                String query = " insert into user (usuario, pass, nombre, apellido_paterno, apellido_materno, universidad, tutor_uni, tutor_sat, matricula)"
                        + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setString(1,us);
                preparedStmt.setString (2, p);
                preparedStmt.setString (3, n);
                preparedStmt.setString(4,a_p);
                preparedStmt.setString (5, a_m);
                preparedStmt.setString (6, un);
                preparedStmt.setString (7, t_a);
                preparedStmt.setString (8, t_s);
                preparedStmt.setString (9, m);
                preparedStmt.executeUpdate();
                con.close();
//                animateMessage();
//                appendSuccess(us);
//                success.setTextFill(Color.rgb(21, 117, 84));
                //success;
                //reset();
                //System.out.println("guardando datos");
            } catch (Exception e){
               // System.err.println("Got an exception!");
                //System.err.println(e.getMessage());
                success.setText(e.getMessage());
            }
        }
        application.gotoPeriodo();
    }

    public void reset(){
        if (application == null){return;}
        user.setText("");
        name.setText("");
        pass.setText("");
        ape_pat.setText("");
        ape_mat.setText("");
        uni.setText("");
        tut_aca.setText("");
        tut_sat.setText("");
        mat.setText("");
        success.setOpacity(0.0);
    }


    public void resetProfile(ActionEvent event){
        reset();
    }

    public void AdminProfile(ActionEvent event){
        application.gotoAdmin();
    }

    public void appendSuccess(String newText) {
        success.setText(newText+" "+success.getText());
    }

    private void animateMessage() {
        FadeTransition ft = new FadeTransition(Duration.millis(1000), success);
        ft.setFromValue(0.0);
        ft.setToValue(1);
        ft.play();
    }
}
