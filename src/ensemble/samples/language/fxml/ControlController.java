package ensemble.samples.language.fxml;

/**
 * Created by noodl on 6/16/2017.
 */
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
public class ControlController extends AnchorPane implements Initializable {

    @FXML
    private TextField normalUser;
    @FXML
    private TextField normalPhone;
    @FXML
    private TextField normalEmail;
    @FXML
    private Hyperlink logoutControl;
    @FXML
    private Button saveControl;

    @FXML
    private Label successControl;

    private FXMLLoginDemoApp application;

    public void setApp(FXMLLoginDemoApp application){
        this.application = application;
        User loggedUser = application.getLoggedUser();
        normalUser.setText(loggedUser.getId());

       // Time tm = new Time();
        //String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        normalEmail.setText(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));
       // normalPhone.setText(LocalDateTime.now().toString());
        successControl.setOpacity(0);
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

    public void saveControl(ActionEvent event) {
        if (application == null){
            animateMessageControl();
            return;
        }
        animateMessageControl();
    }

    public void resetControl(ActionEvent event){
        if (application == null){
            return;
        }
        normalEmail.setText("");
        normalPhone.setText("");
        successControl.setOpacity(0.0);

    }

    private void animateMessageControl() {
        FadeTransition ft = new FadeTransition(Duration.millis(1000), successControl);
        ft.setFromValue(0.0);
        ft.setToValue(1);
        ft.play();
    }

/*     Query para insertar periodo en el ultimo usuario ingresado
INSERT INTO periodo (fecha_inicio, fecha_fin,total_hrs,iduser)
     VALUES ('2017-06-07','2017-06-30', 8,LAST_INSERT_ID());
     */



}
