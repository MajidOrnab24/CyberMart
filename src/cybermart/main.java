/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package cybermart;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



/**
 *
 * @author majid
 */
public class main extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
        
        
        DatabaseConnection connectnow = new DatabaseConnection();
        Connection connectDBMS = connectnow.getConnection();

        String queryuser = "Update c_user SET customer_ID='"+0+"',customer_name='"+""+"' WHERE c_ID ='1';";
        String queryven = "Update current_vendor SET id_vendor='"+0+"',vendor_name='"+""+"' WHERE v_ID='1';";

        try {

            Statement statementR = connectDBMS.createStatement();
            Statement statementR1 = connectDBMS.createStatement();
            statementR.executeUpdate(queryuser);
            statementR1.executeUpdate(queryven);

        } catch (SQLException e) {
            e.getCause();
        }
        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("homepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Cyber Mart");
       // stage.setScene(scene);
        stage.setScene(scene);
        //stage.setWidth(1280);
        //stage.setHeight(720);
        stage.resizableProperty().setValue(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
