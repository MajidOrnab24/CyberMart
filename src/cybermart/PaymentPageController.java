/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cybermart;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author majid
 */
public class PaymentPageController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private TextField addressF;

    @FXML
    private Label againTry;

    @FXML
    private Pane bkash;

    @FXML
    private Pane cashOnD;

    @FXML
    private Label comingB;

    @FXML
    private Label comingN;

    @FXML
    private Label itemL;

    @FXML
    private Pane nogod;

    @FXML
    private Label shippingL;

    @FXML
    private Label statrtedship;

    @FXML
    private Label taxL;

    @FXML
    private Label totalL;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        DatabaseConnection connectnow = new DatabaseConnection();
       Connection connectDBMS = connectnow.getConnection(); 
                 setInfo();
                 
                 nogod.setOnMouseClicked(e->{
                     comingB.setText("");
                     comingN.setText("Coming soon..");
                     againTry.setText("Please try cash on delivery");
                 });
                 bkash.setOnMouseClicked(e->{
                     comingN.setText("");
                     comingB.setText("Coming soon..");
                     againTry.setText("Please try cash on delivery");
                 });
                 cashOnD.setOnMouseClicked(e->{
                     comingN.setText("");
                     comingB.setText("");
                     againTry.setText("");
                     
                     if(addressF.getText().isEmpty()==true)
                     {
                         againTry.setText("Please add address for delivery");
                     }
                     else
                     {
                         againTry.setText("");
                         statrtedship.setText("Dear Customer, your shipping has started");
                     
                     String shipStat="Update shopping_cart SET shipping_status ='Shipped' WHERE customer_id =(select c_user.customer_ID from c_user where c_ID ='1');";
                         try{
                         Statement statement1 = connectDBMS.createStatement();
                         statement1.executeUpdate(shipStat);
                         
                     }
                     catch (SQLException ec){
                         ec.getCause();
                         
                     }
                    String updateProductInfo="select cart_order_no,product_id,product_amount FROM cybermart.shopping_cart where customer_id = (select c_user.customer_ID from c_user where c_ID ='1') and  payment_status=0 ;";
                     int productID=0,product_amount=0,cartID=0;;
                    try{
                     Statement statement = connectDBMS.createStatement();
                     ResultSet queryOutput = statement.executeQuery(updateProductInfo);
                  while (queryOutput.next()) 
                  {
                      productID=queryOutput.getInt("product_id");
                      product_amount=queryOutput.getInt("product_amount");
                      cartID=queryOutput.getInt("cart_order_no");
                      
                      String updateIT ="Update product_info SET d_stock = d_stock-"+product_amount+" where d_id_product="+productID+";";
                      try{
                         Statement statement1 = connectDBMS.createStatement();
                         statement1.executeUpdate(updateIT);
                         
                     }
                     catch (SQLException ec){
                         ec.getCause();
                         
                     }
                 String insertFields="INSERT INTO dashboard(cart_id,shipping_time,shipping_address) VALUES ('";
                 String insertValues= cartID +"',NOW(),'"+addressF.getText() +"');";
                 String insertdash= insertFields+insertValues;
                // System.out.println(insertdash);
                try{
                        Statement statement2 = connectDBMS.createStatement();
                         statement2.executeUpdate(insertdash);
               
                   } catch (SQLException ex){
                         ex.getCause();
                   }
                      
                 }
                    }catch (SQLException ec){
                         ec.getCause();
                         
                     }
                    
                    String payStat="Update shopping_cart SET payment_status =1 WHERE customer_id =(select c_user.customer_ID from c_user where c_ID ='1');";
                     try{
                         Statement statement2 = connectDBMS.createStatement();
                         statement2.executeUpdate(payStat);
 
                     }
                     catch (SQLException ec){
                         ec.getCause();
                         
                     }
                     String shopq="UPDATE `cybermart`.`customer_info` SET `d_cartshop` = '' WHERE (`customer_id` = (select c_user.customer_ID from c_user where c_ID ='1'));";
                        try{
                                   Statement statement = connectDBMS.createStatement();
                                       statement.executeUpdate(shopq); 
                                }
                           catch (SQLException ex){
                              ex.getCause();
                               }
    
                     }
                        
                 });
                 


                 
                 
  
                     
             
         }

    public void setInfo(){
        
        
        DatabaseConnection connectnow = new DatabaseConnection();
             Connection connectDBMS = connectnow.getConnection(); 
               int itemcost=0;
                int a=0;
        try {
             // TODO
            
             Statement statement = connectDBMS.createStatement();
             String cost="select product_price from shopping_cart where customer_id=(select c_user.customer_ID from c_user where c_ID ='1')and shipping_status  not in ('Shipped');";
             ResultSet queryOutput = statement.executeQuery(cost);
             while (queryOutput.next()) {
                 int queryP_ID = queryOutput.getInt("product_price");
                 itemcost=itemcost+queryP_ID;
                 a++;
             }
                } catch (SQLException ex) {
             Logger.getLogger(PaymentPageController.class.getName()).log(Level.SEVERE, null, ex);
                }
                double taxp=itemcost*.15;
                int supplyp=100*a;
                double totalcost=supplyp+taxp+itemcost;
                //System.out.println(taxp+"  "+totalcost);
                 totalL.setText(totalcost+" BDT");
                 taxL.setText(taxp+" BDT");
                 itemL.setText(itemcost+ " BDT");
                 shippingL.setText(supplyp+" BDT");
    }
   



    
 }
    

