package ensemble.samples.language.fxml;

import com.mysql.jdbc.ResultSet;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * Created by noodl on 6/20/2017.
 */
public class PeriodoController extends AnchorPane implements Initializable {


    @FXML
    TextField periodoUser;
    @FXML
    DatePicker inicio;
    @FXML
    DatePicker fin;
    @FXML
    TextField totalHoras;
    @FXML
    Button guardarPeriodo;
    @FXML
    private Hyperlink logoutPeriodo;
    @FXML
    Button volver;
    @FXML
    Label errorMessage;
    @FXML
    Label success;

    private FXMLLoginDemoApp application;
    private ProfileController pc = new ProfileController();
    private String us;
    private  int id=0;

    //us = pc.getUs();


    public void setApp(FXMLLoginDemoApp application){
        this.application = application;
        //ProfileController loggedUser = application.getPc();
         us = pc.getUsuario();
        //this.us = us;
        //JOptionPane.showMessageDialog(null, us);
       //System.out.println(us);
       periodoUser.setText(us);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    public void processVolver(ActionEvent event) {
        application.gotoProfile();
    }

    public void processSavePeriodo() {
        String ini = inicio.getValue().toString();
      //  System.out.println(ini);
        String f = fin.getValue().toString();
        //System.out.println(f);
        String hrs =  totalHoras.getText();
        //System.out.println(hrs);
        if ( ini.equals(null) || f.equals(null) || hrs.isEmpty() ) {
            animateMessage();
            success.setStyle("-fx-background-color: red");
            success.setText("Todos los campos son obligatorios");
        } else {
           // success.setText("guardado exitosamente!");
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biometric","Root","ThisIsMyRealPass_TrustMe");
                String query = "SELECT * FROM user";
                // create the java statement
                Statement st = con.createStatement();
                // execute the query, and get a java resultset
                ResultSet rs = (ResultSet) st.executeQuery(query);
                // iterate through the java resultset
                while (rs.next()) {
                   id = rs.getInt("iduser");
                   // System.out.format("%s\n", id);
                }
                String iduser = String.valueOf(id);
                 query = " insert into periodo (fecha_inicio, fecha_fin, total_hrs, iduser)"
                        + " values (?, ?, ?, ?)";
                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setString(1,ini);
                preparedStmt.setString (2, f);
                preparedStmt.setString (3, hrs);
                preparedStmt.setString(4,iduser);
                preparedStmt.executeUpdate();
                st.close();
                animateMessage();
                appendSuccess(us);
                success.setTextFill(Color.rgb(21, 117, 84));
                volver.setDisable(false);
            } catch (Exception e){
                animateMessage();
                success.setTextFill(Color.rgb(210, 39, 30));
                success.setText(e.getMessage());
            }
        }
    }

    public void appendSuccess(String newText) {
        success.setText(newText+" "+success.getText());
    }

    public void ControlLogoutPeriodo(ActionEvent event) {
        if (application == null){return;}
        application.controlLogout();
    }

    private void animateMessage() {
        FadeTransition ft = new FadeTransition(Duration.millis(1000), success);
        ft.setFromValue(0.0);
        ft.setToValue(1);
        ft.play();
    }


}
