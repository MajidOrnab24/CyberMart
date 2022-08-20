/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cybermart;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author majid
 */
public class DashboardPageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane d_anchorpane;
     @FXML
    private Label cus_email;

    @FXML
    private Label cus_name;

    @FXML
    private Label cus_phone;
    

    @FXML
    private Label cus_username;

    @FXML
    private JFXButton logoutB;

    @FXML
    private TableColumn<dashboardObject, String> p_address;

    @FXML
    private TableColumn<dashboardObject, String> p_name;

    @FXML
    private TableColumn<dashboardObject, Double> p_price;

    @FXML
    private TableColumn<dashboardObject,Integer> p_quantity;

    @FXML
    private TableColumn<dashboardObject,String> p_time;

    @FXML
    private TableView<dashboardObject> transactionTable;
    ObservableList<dashboardObject>dashboardObjectList = FXCollections.observableArrayList();
    private Integer customerID;
    @Override
   
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String query1="select c_user.customer_ID from c_user where c_ID ='1'";
        DatabaseConnection connectNow = new DatabaseConnection();
        java.sql.Connection connectDB = connectNow.getConnection();
        int a=0;
        try {

            Statement statement = connectDB.createStatement();
            ResultSet queryresult = statement.executeQuery(query1);
            while (queryresult.next()) { 
                a=queryresult.getInt(1);
            }

        } catch (SQLException e) {
            e.getCause();
        }
        customerID=a;
        String c_name=null,c_phone=null,c_email=null,c_user=null;
        String query2="select d_name,d_phone,d_email,d_username from customer_info where customer_id = "+customerID+";";
        try {

            Statement statement1 = connectDB.createStatement();
            ResultSet queryresult = statement1.executeQuery(query2);
            while (queryresult.next()) { 
                c_name=queryresult.getString("d_name");
                c_phone=queryresult.getString("d_phone");
                c_email=queryresult.getString("d_email");
                c_user=queryresult.getString("d_username");
                
            }

        } catch (SQLException e) {
            e.getCause();
        }
        cus_name.setText("  "+c_name);
        cus_username.setText("  "+c_user);
        cus_email.setText("  "+c_email);
        cus_phone.setText("  "+c_phone);
        String query3="select cart_order_no,product_id,product_name,product_amount,product_price from shopping_cart where customer_id = "+customerID+" and shipping_status ='Shipped' and payment_status = 1;"; 
       // System.out.println(query3);
        try {

            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(query3);

            while (queryOutput.next()) { 
                Integer querycart_id= queryOutput.getInt("cart_order_no");
                Integer queryp_id=queryOutput.getInt("product_id"); 
                Double queryproduct_price = queryOutput.getDouble("product_price");
                Integer queryproduct_amount = queryOutput.getInt("product_amount");
                String queryproduct_name=queryOutput.getString("product_name");
                
                
                String query4= "select dash_id,shipping_time,shipping_address from dashboard where cart_id = "+querycart_id+" ; ";
                //System.out.println(query4);
                String s_address=null;
                String s_time=null;
                Integer dashID=0;
                
                try {

                     Statement statement2 = connectDB.createStatement();
                     ResultSet queryresult1 = statement2.executeQuery(query4);
                     while (queryresult1.next()) { 
                     s_address=queryresult1.getString("shipping_address");
                     dashID=queryresult1.getInt("dash_id");
                     s_time=queryresult1.getString("shipping_time");
                     }

                 } catch (SQLException e) {
                            e.getCause();
                 }
                

                
               dashboardObjectList.add(new dashboardObject(customerID,queryp_id,dashID,queryproduct_amount,queryproduct_name,s_address,s_time,queryproduct_price));
            }
               p_name.setCellValueFactory(new PropertyValueFactory("d_name"));
               p_quantity.setCellValueFactory(new PropertyValueFactory("d_quantity"));
               p_price.setCellValueFactory(new PropertyValueFactory("d_price"));
               p_time.setCellValueFactory(new PropertyValueFactory("d_time"));
               p_address.setCellValueFactory(new PropertyValueFactory("d_address"));
               transactionTable.setItems(dashboardObjectList);
        
        }catch (SQLException e) {
           Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, e);
        }
        transactionTable.setPlaceholder(new Label("No product in your cart !!!"));
          transactionTable.setRowFactory (b->{
            TableRow <dashboardObject> row=new TableRow();
            
            row.setOnMouseEntered(event->{
                
                if(row.isEmpty()){
        
             row.setStyle("-fx-background-color: #FFFAFA; ");
        
        }else{
                   row.setStyle("-fx-background-color: #9bdce0; "); 
                }
            
        });
        row.setOnMouseExited(event->{
            row.setStyle("-fx-background-color: #FFFAFA; "); 
 
        });

        row.setOnMouseReleased(event->{

                row.setStyle("-fx-background-color: #FFFAFA; ");

        });
        row.setOnMousePressed(event->{

                row.setStyle("-fx-background-color: #00CED1; ");

        });
        
               
                return row;
                
            });
        
        
        
        
        
        
        
        
        
        logoutB.setOnMouseClicked(e->{
        String queryuser = "Update c_user SET customer_ID='"+0+"',customer_name='"+""+"' WHERE c_ID ='1';";
        

        try {

            Statement statementR = connectDB.createStatement();
            statementR.executeUpdate(queryuser);
           

        } catch (SQLException ex) {
            ex.getCause();
        }
            fxmlLoaderHome object = new fxmlLoaderHome();
            Pane view =object.getPage("pleaseLogin");   
            d_anchorpane.getChildren().setAll(view);
        });
        
        
    
    }
    
}
                


