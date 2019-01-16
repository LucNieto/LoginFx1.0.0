/* ....Show License.... */
package ensemble.samples.language.fxml;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Authenticator {



    private static final Map<String, String> USERS = new HashMap<String, String>();
    private static final Map<String, String> NORMALUSERS = new HashMap<String, String>();

    private static void populate(){
       // static {
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/biometric","Root","ThisIsMyRealPass_TrustMe");
//voila, biometric es el nombre de la db, Luc es el usuario y 2.7182818284590 la contrasena que yo  cree en mi db
                Statement stmt=con.createStatement();
                ResultSet rs=stmt.executeQuery("select * from admin");
                while(rs.next())
                    //System.out.println(rs.getString(2)+"  "+rs.getString(3));
                    USERS.put(rs.getString(2), rs.getString(3));
                con.close();
            }catch(Exception e){ System.out.println(e);}

            USERS.put("Admin", "password");
            //dbConnection();
       // }
       // static {
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/biometric","Root","ThisIsMyRealPass_TrustMe");
//voila, biometric es el nombre de la db, Luc es el usuario y 2.7182818284590 la contrasena que yo  cree en mi db
                Statement stmt=con.createStatement();
                ResultSet rs=stmt.executeQuery("select * from user");
                while(rs.next())
                    //System.out.println(rs.getString(2)+"  "+rs.getString(3));
                    NORMALUSERS.put(rs.getString(2), rs.getString(3));
                con.close();
            }catch(Exception e){ System.out.println(e);}
            // NORMALUSERS.put("Admin", "password");
            //dbConnection();
    }


    //}
    //<editor-fold desc="Description">

    public static boolean validateNormal(String user, String password){
        populate();
        String validUserPassword = NORMALUSERS.get(user);
        //JOptionPane.showMessageDialog(null,validUserPassword );
        // System.out.println(validUserPassword);
        return validUserPassword != null && validUserPassword.equals(password);
    }


    public static boolean validate(String user, String password){
        populate();
        String validUserPassword = USERS.get(user);
        //JOptionPane.showMessageDialog(null,validUserPassword );
       // System.out.println(validUserPassword);
        return validUserPassword != null && validUserPassword.equals(password);
    }
}
