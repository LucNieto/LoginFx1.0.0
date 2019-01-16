package ensemble.samples.language.fxml;

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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

/**
 * Created by noodl on 6/18/2017.
 */
public class AdminController extends AnchorPane implements Initializable {

    private boolean flag;
    @FXML
    private TextField adminlUser;
    @FXML
    private PasswordField adminPass;
    @FXML
    private PasswordField adminPassConfirm;
    @FXML
    private ComboBox adminComboBox;
    @FXML
    private Hyperlink logoutAdmin;
    @FXML
    private Button saveAdmin;
    @FXML
    private Label successAdmin;

    private FXMLLoginDemoApp application;

    public void setApp(FXMLLoginDemoApp application){
        this.application = application;
        User loggedUser = application.getLoggedUser();
       // normalUser.setText(loggedUser.getId());

       // adminPassConfirm.setStyle("-fx-border-width: 3; -fx-border-color: darkgreen");
        // Time tm = new Time();
       // String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
       // normalEmail.setText(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));
        // normalPhone.setText(LocalDateTime.now().toString());
        successAdmin.setOpacity(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void ControlLogout(ActionEvent event) {
        if (application == null){
            return;
        }
        application.controlLogout();
    }

    public void saveAdmin(ActionEvent event) {
        if (application == null){
            animateMessageControl();
            return;
        }
        String n = adminlUser.getText();
        String p = adminPass.getText();
        String c = adminPassConfirm.getText();
        //confirmPass(p,c);
        String r = String.valueOf(adminComboBox.getValue());
        System.out.println(r);
        if (p.equals(c)) {

            if ( n.isEmpty() || p.isEmpty() || c.isEmpty() || r.isEmpty()) {
                animateMessageControl();
                successAdmin.setStyle("-fx-background-color: red");
                successAdmin.setText("Todos los campos son obligatorios");
                //JOptionPane.showMessageDialog(null, "No guardando. ==\"\"");
            } else {
                successAdmin.setText("guardado exitosamente!");
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/biometric","Root","ThisIsMyRealPass_TrustMe");
                    String query = " insert into admin (usuario, password, role)"
                            + " values (?, ?, ?)";

                    PreparedStatement preparedStmt = con.prepareStatement(query);
                    preparedStmt.setString(1,n);
                    preparedStmt.setString (2, p);
                    preparedStmt.setString (3, r);
                    preparedStmt.executeUpdate();
                    con.close();
                    animateMessageControl();
                    appendSuccess(n);
                    successAdmin.setTextFill(Color.rgb(21, 117, 84));
                    //success;
                    //reset();
                    //System.out.println("guardando datos");
                } catch (Exception e){
                    // System.err.println("Got an exception!");
                    //System.err.println(e.getMessage());
                    animateMessageControl();
                    successAdmin.setTextFill(Color.rgb(210, 39, 30));
                    successAdmin.setText(e.getMessage());
                }
            }
            // System.out.println("Coinciden");
            //flag = true;
            //return "fgb";
            //adminPassConfirm.setStyle("-fx-border-width: 1; -fx-border-color: darkgreen");
            //animateMessageControl();
        }else {
           // flag = false;
           // adminPassConfirm.setStyle("-fx-background-color: red");
            successAdmin.setText("La contraseña no coincide");
        }
        //animateMessageControl();
    }


    public void resetAdmin(ActionEvent event){
        if (application == null){
            return;
        }
       // System.out.println("exception!");
        adminlUser.setText("");
        adminPass.setText("");
        adminPassConfirm.setText("");
       // adminComboBox.setPromptText("selecione");
       // adminComboBox.promptTextProperty().set("hgf");
       adminComboBox.getSelectionModel().select(null);
        //adminComboBox = null;
        successAdmin.setOpacity(0.0);

    }

    private void animateMessageControl() {
        FadeTransition ft = new FadeTransition(Duration.millis(1000), successAdmin);
        ft.setFromValue(0.0);
        ft.setToValue(1);
        ft.play();
    }

    public void appendSuccess(String newText) {
        successAdmin.setText(newText+" "+successAdmin.getText());
    }

/*     Query para insertar periodo en el ultimo usuario ingresado
INSERT INTO periodo (fecha_inicio, fecha_fin,total_hrs,iduser)
     VALUES ('2017-06-07','2017-06-30', 8,LAST_INSERT_ID());
     */



}
