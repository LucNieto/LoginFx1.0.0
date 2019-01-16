/* ....Show License.... */
package ensemble.samples.language.fxml;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String pass = "";
    private String name = "";
    private String ape_pat = "";
    private String ape_mat = "";
    private String uni = "";
    private String tut_uni = "";
    private String tut_sat = "";
    private String mat = "";

    private static final Map<String, User> USERS = new HashMap<String, User>();

    public static User of(String id) {
        User user = USERS.get(id);
        if (user == null) {
            user = new User(id);
            USERS.put(id, user);
        }
        return user;
    }

    private User(String id) {
        this.id = id;
    }
    private String id;

    public String getId() {
        return id;
    }


    /**
     */

    public String getApe_pat() {return ape_pat;}

    public String getApe_mat() {return ape_mat;}

    public String getUni() {return uni;}

    public String getTut_uni() {return tut_uni;}

    public String getTut_sat() {return tut_sat;}

    public String getMat() {return mat;}

    public void setApe_pat(String ape_pat) {this.ape_pat = ape_pat;}

    public void setApe_mat(String ape_mat) {this.ape_mat = ape_mat;}

    public void setUni(String uni) {this.uni = uni;}

    public void setTut_uni(String tut_uni) {this.tut_uni = tut_uni;}

    public void setTut_sat(String tut_sat) {this.tut_sat = tut_sat;}

    public void setMat(String mat) {this.mat = mat;}

    public String getPass() {return pass;}

    public void setPass(String pass) {this.pass = pass;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}
}