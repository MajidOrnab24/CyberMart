/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cybermart;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author majid
 */

public class SignupController implements Initializable {
   @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
        @FXML
    private JFXButton ExitWin;

    @FXML
    private JFXButton ReturnLogin;

    @FXML
    private PasswordField S_C_PasswordField;
    @FXML
    private Label passwordMatch;

    @FXML
    private JFXButton sigupEntry;


    @FXML
    private TextField S_EmailField;

    @FXML
    private TextField S_NameField;

    @FXML
    private PasswordField S_PasswordField;

    @FXML
    private TextField S_PhoneField;

    @FXML
    private TextField S_UsernameField;
    @FXML
    private Label signupmessageLabel;






    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ReturnLogin.setOnMouseClicked(event->{
           try {
               root = FXMLLoader.load(getClass().getResource("login.fxml"));
               scene = new Scene(root);
               stage = (Stage)((Node)event.getSource()).getScene().getWindow();
               stage.setTitle("Login Page");
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
               Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
           }
           
           
      
               }) ;
       ExitWin.setOnMouseClicked(event->   
       {
       Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
       
       });
       sigupEntry.setOnMouseClicked(event->  {
       if(S_NameField.getText().isBlank()==false && S_EmailField.getText().isBlank()==false && 
               S_UsernameField.getText().isBlank() == false && S_PasswordField.getText().isBlank() == false && 
               S_C_PasswordField.getText().isBlank()==false && S_PhoneField.getText().isBlank() == false) {
            if (S_C_PasswordField.getText().equals(S_PasswordField.getText())) {
                registerUser();
            }
            else{
                passwordMatch.setText("Password Doesn't Match");
            }
        }
        else{
            passwordMatch.setText("Please fill all details");
        }
       
       
       
       });
        
        
        
        
        // TODO
    }
    public void registerUser(){

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String d_name= S_NameField.getText();
        String d_phone = S_PhoneField.getText();
        String d_email=S_EmailField.getText();
        String d_username=S_UsernameField.getText();
        String d_password=S_C_PasswordField.getText();
        

        String insertFields="INSERT INTO customer_info(d_name,d_phone,d_email,d_username,d_password,d_cartshop) VALUES ('";
        String insertValues= d_name + "','"+ d_phone+ "','" + d_email + "','" + d_username + "','" + d_password+ "','')";
        String insertToSignup= insertFields+insertValues;


        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToSignup);
            signupmessageLabel.setText("User has been signed up Successfully");
        }
        catch (SQLException e){
            e.getCause();
        }

    }
    
}
