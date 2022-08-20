/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cybermart;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;





/**
 * FXML Controller class
 *
 * @author majid
 */

public class LoginController implements Initializable {
        @FXML
    private JFXButton loginButton;

    @FXML
    private TextField passwordField;

    @FXML
    private JFXButton signupButton;

    @FXML
    private TextField usernameField;
    @FXML
    private AnchorPane LoginPane;
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label LoginMsgLabel;

   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        signupButton.setOnMouseClicked(event->{
            try {
                root = FXMLLoader.load(getClass().getResource("signup.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Signup Page");
                stage.resizableProperty().setValue(false);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        });
        
        loginButton.setOnMouseClicked(event-> {
                
         if (usernameField.getText().isBlank() == false && passwordField.getText().isBlank() == false) {

            validateLogin();

        } else {
            LoginMsgLabel.setText("Please enter Username & Password");
        }
                
                
                });

    }
        public void validateLogin(){


        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        int cus_id = 0;
        String verifyLogin = "select count(1) FROM customer_info WHERE d_username='" + usernameField.getText() + "' and d_password='" + passwordField.getText() + "'";
        String queryC="select customer_id FROM customer_info WHERE d_username='" + usernameField.getText() + "' and d_password='" + passwordField.getText() + "'";
        try {

            Statement statement = connectDB.createStatement();
            Statement statement1 = connectDB.createStatement();
            ResultSet queryCR = statement1.executeQuery(queryC);
            while(queryCR.next())
            {
                cus_id=queryCR.getInt(1);
            }
            
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) { 
                if (queryResult.getInt(1) == 1) {
                    LoginMsgLabel.setText("Welcome to Cyber Mart family");
                    
                    
                    String verifyS = "select customer_name FROM c_user WHERE c_ID=1;";
                    String loggedinName = null;
                    String queryRow = "Update c_user SET customer_ID='"+cus_id+"',customer_name='"+usernameField.getText()+"' WHERE c_ID='1';";
                    
        try {

            Statement statement2 = connectDB.createStatement();
            Statement statement3 = connectDB.createStatement();
           // System.out.println(queryRow);
            statement2.executeUpdate(queryRow);
            ResultSet queryresult = statement3.executeQuery(verifyS);
            while (queryresult.next()) { 
                loggedinName=queryresult.getString(1);
            }

        } catch (SQLException e) {
            e.getCause();
        }
                
                   FXMLLoader fxmlloader =new FXMLLoader(getClass().getResource("homepage.fxml"));
                    try {
                        Parent root =fxmlloader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                  HomepageController hpage=fxmlloader.getController(); 
                  hpage.setLabel(loggedinName);

                      LoginPane.getScene().getWindow().hide();
                } else {
                    LoginMsgLabel.setText("Invalid login Please try again");
                }
            }

        } catch (SQLException e) {
            e.getCause();
        }

    }



}