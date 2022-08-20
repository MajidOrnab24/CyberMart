/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cybermart;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import org.controlsfx.control.Rating;



/**
 * FXML Controller class
 *
 * @author majid
 */
public class ProductDetailsController implements Initializable{

    /**
     * Initializes the controller class.
     */
      @FXML
    private JFXButton addrating;

    @FXML
    private Label pd_brand;

    @FXML
    private Label pd_description;

    @FXML
    private ImageView pd_image;

    @FXML
    private Label pd_model;

    @FXML
    private Label pd_name;

    @FXML
    private Label pd_price;

    @FXML
    private Label pd_stock;
    @FXML
    private Label cantrate;
    @FXML
    private Rating ratingBar;
    @FXML
    private AnchorPane a_pane;
     @FXML
    private JFXButton addtoCart;
     @FXML
     
    private TextField amountField;
     @FXML
    private Label rating_count;
     @FXML
    private Label shopname;

    @FXML
    private Rating shoprating;
    
    private String shopName=null;
    private double shopRating=0;
    private int Id,p_price,p_amount,vendorID;
    private double prevRate;
    @FXML
    private Label overAmount;
    @FXML
    void clicked(MouseEvent event) {
        
        DatabaseConnection connectNow = new DatabaseConnection();
        java.sql.Connection connectDB = connectNow.getConnection();
        int cus_id = 0;
        String cus_name = null;
        String cartshop=null;
        try{
            Statement statement1 = connectDB.createStatement();
            String queryCID = "select customer_ID,customer_name FROM c_user WHERE c_ID=1;";
            ResultSet queryCR = statement1.executeQuery(queryCID);
            while(queryCR.next())
            {
                 cus_id = queryCR.getInt("customer_ID");
                 cus_name=queryCR.getString("customer_name");
            }
               
           }catch(SQLException e){
                e.getCause();
           }
        try{
            Statement statement = connectDB.createStatement();
            String queryC = "select d_cartshop FROM customer_info WHERE customer_id="+cus_id+";";
            ResultSet query = statement.executeQuery(queryC);
            while(query.next())
            {
                 cartshop=query.getString("d_cartshop");
            }
               
           }catch(SQLException e){
                e.getCause();
           }
        //System.out.println(cartshop+"grdrfgdfg");
        if(cus_id==0 || cus_name.isEmpty())
        {
            overAmount.setText("Please login to add product to cart");
        }
        else if(cartshop.equals(shopName)==false &&cartshop.isEmpty()==false)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setHeaderText(null);
                            alert.setContentText("In a cart only the products of same shop. Present cart the shop is: "+cartshop);
                            alert.show();
        }
        else{
            
            if(amountField.getText().isEmpty())
        {
            overAmount.setText("Please set amount");
        }
        else{
            String amountS=amountField.getText();
           int amount = Integer.parseInt(amountS);
           int cartID=0;
           int from_cartAmount=0,from_cartPrice=0;
           try{
            Statement statement2 = connectDB.createStatement();
            String queryCart = "select cart_order_no,product_amount,product_price FROM shopping_cart WHERE customer_id="+cus_id+" and  product_id="+Id+" and payment_status = "+0+" ;";
            ResultSet queryCR = statement2.executeQuery(queryCart);
            while(queryCR.next())
            {
                 cartID = queryCR.getInt("cart_order_no");
                 from_cartAmount= queryCR.getInt("product_amount");
                 from_cartPrice= queryCR.getInt("product_price");
            }
               
           }catch(SQLException e){
                e.getCause();
           }
           
           
            if(amount>p_amount||amount==0||amount<0)
            {
                overAmount.setText("Please set correct amount please watch the stock");
            }
            else if(cartID!=0)
            {
               int newAmount=amount+from_cartAmount;
               int newPrice=from_cartPrice+p_price*amount;
               int setamount=p_amount-from_cartAmount;
               if(newAmount>p_amount){
                   
                 overAmount.setText("Please set correct amount less than "+setamount );  
               }
               else
               {
                   String update="UPDATE `cybermart`.`shopping_cart` SET `product_amount` = '"+newAmount+"', `product_price` = '"+newPrice+"' WHERE (`cart_order_no` = '"+cartID+"');";
                   
                   try{
                         Statement statement2 = connectDB.createStatement();
                         statement2.executeUpdate(update);
 
                     }
                     catch (SQLException ec){
                         ec.getCause();
                         
                     }
            overAmount.setTextFill(Color.web("#0000FF"));
            overAmount.setText("Product added to cart");
               }
                
            }
            else{
                
           String name=pd_name.getText();
           String model=pd_model.getText();
           String brand=pd_brand.getText();
           int p_id=Id;
           
           int price=p_price*amount;
           
           
           
           String cartQuery1="INSERT INTO shopping_cart(customer_id,product_id,product_name,product_model,product_brand,product_amount,product_price,timeAndDate,shipping_status,payment_status,rated_status) VALUES('";
           String cartQuery2=cus_id+"','"+p_id+"','"+name+"','"+model+"','"+brand+"','"+amount+"','"+price+"',NOW(),'',0,0);";
           String cartQuery=cartQuery1+cartQuery2;
           //System.out.println(cartQuery);
           
        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(cartQuery);
            overAmount.setTextFill(Color.web("#0000FF"));
            overAmount.setText("Product added to cart");
            
        }
        catch (SQLException e){
            e.getCause();
        }
        String shopq="UPDATE `cybermart`.`customer_info` SET `d_cartshop` = '"+shopName+"' WHERE (`customer_id` = '"+cus_id+"');";
                //System.out.println(shopq);
        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(shopq); 
        }
        catch (SQLException e){
            e.getCause();
        }
  
        
        }     
        }
              
           // System.out.println("Operation complete");
        }
        
        

    }
    @FXML
    void ratingClicked(MouseEvent event) {
       DatabaseConnection connectnow = new DatabaseConnection();
       Connection connectDBMS = connectnow.getConnection(); 
      int checkerID=0;
      Double stat=0.0;
      String check="select product_id,rated_status FROM cybermart.shopping_cart where customer_id = (select c_user.customer_ID from c_user where c_ID ='1') and shipping_status ='Shipped'and payment_status=1";
        //System.out.println(check);
      try{
                     Statement statement = connectDBMS.createStatement();
                     ResultSet queryOutput = statement.executeQuery(check);
                  while (queryOutput.next()) 
                  {
                      checkerID=queryOutput.getInt("product_id");
                      stat=queryOutput.getDouble("rated_status");
 
                  }
                  
         } catch (SQLException ec){
                         ec.getCause();
                         
                     }
        //System.out.println("checkerid"+checkerID);
        if(Id==checkerID)
        {
       Double rate=ratingBar.getRating();
     
       
       Double ratetemp=0.0;
       ratingBar.setPartialRating(true);
       ratingBar.setMax(5);
       String getrate="select d_rating,d_rating_count from product_info where d_id_product="+Id+";";
            //System.out.println(getrate);
       double rateD=0;
       int rateCount=0;
       try{
                     Statement statement = connectDBMS.createStatement();
                     ResultSet queryOutput = statement.executeQuery(getrate);
                  while (queryOutput.next()) 
                  {
                      rateD=queryOutput.getDouble("d_rating");
                      rateCount=queryOutput.getInt("d_rating_count");
                  }
                 } catch (SQLException ec){
                         ec.getCause();
                         
                     }
           if(stat>0)
           {
               rateD=rateD-stat;
               if(rateCount==0)
               {
                 ratetemp=rate;
                 rateCount++;
               }
               else{
               rateCount=rateCount-1;
               ratetemp=((rateD*rateCount)+rate)/(rateCount+1);
               rateCount=rateCount+1;
               }
               
           }
           else{
              ratetemp=((rateD*rateCount)+rate)/(rateCount+1);
               rateCount=rateCount+1;
           }
           String updateIT ="UPDATE `cybermart`.`product_info` SET `d_rating` = '"+ratetemp+"', `d_rating_count` = '"+rateCount+"' WHERE (`d_id_product` = '"+Id+"');";
            //System.out.println(updateIT);
           String updatecart="Update shopping_cart SET rated_status ="+rate+" where product_id = "+Id+" and customer_id=(select c_user.customer_ID from c_user where c_ID ='1');";
          // System.out.println(updatecart);
           
                      try{
                         Statement statement1 = connectDBMS.createStatement();
                         statement1.executeUpdate(updateIT);    
                         Statement statement2 = connectDBMS.createStatement();
                         statement1.executeUpdate(updatecart);
                     }
                     catch (SQLException ec){
                         ec.getCause();
                         
                     }
           
           prevRate=ratetemp;
           
   
        }else{
            
            ratingBar.setMouseTransparent(true);
            ratingBar.setRating(prevRate);
            cantrate.setText("Not eligible to rate");
       
        }
       
       
      setvendorInfo();
    }

    //@Override
    public void initialize(URL url, ResourceBundle rb) {
        DatabaseConnection connectNow = new DatabaseConnection();
        java.sql.Connection connectDB = connectNow.getConnection();
        
        overAmount.setTextFill(Color.web("RED"));
        String rQuery="Select r_name,r_id from row_table where r_pk =1;";
        int productID = 0;
        String productName = null;
        Double queryRating = null;
        Integer queryStock = null,queryPrice = null,queryID=null,queryCount=null,vendorId=null;
        String queryImage = null,queryName = null,querymodelName = null,querybrandName=null,querydescriptionName = null;
       
        try {

            Statement statement = connectDB.createStatement();
            ResultSet queryresult = statement.executeQuery(rQuery);
            while (queryresult.next()) { 
                productName=queryresult.getString("r_name");
                productID=queryresult.getInt("r_id");
            }

        } catch (SQLException e) {
            e.getCause();
        }
       String detailQuery="select d_id_product,d_name_product, d_price, d_stock, d_rating, d_image,d_brandname,d_model,d_rating_count,d_description,d_vendor_id from product_info where d_id_product="+productID+" and d_name_product='"+productName+"';";

        
       try {

            Statement statement = connectDB.createStatement();
            ResultSet queryOutput1 = statement.executeQuery(detailQuery);
            while (queryOutput1.next()) { 
                 queryRating = queryOutput1.getDouble("d_rating");
                 queryStock = queryOutput1.getInt("d_stock");
                 queryImage=queryOutput1.getString("d_image");
                 queryPrice = queryOutput1.getInt("d_price");
                 queryID=queryOutput1.getInt("d_id_product");
                queryName=queryOutput1.getString("d_name_product");
                querymodelName=queryOutput1.getString("d_model");
                 querybrandName=queryOutput1.getString("d_brandname");
                querydescriptionName=queryOutput1.getString("d_description");
                queryCount=queryOutput1.getInt("d_rating_count");
                vendorId=queryOutput1.getInt("d_vendor_id");
                
                
                
            }}
       
       catch (SQLException e) {
           Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, e);
        }
        //System.out.println(queryRating);
      // System.out.println(querymodelName);
      
      Id=queryID;
      p_price=queryPrice;
      p_amount=queryStock;
      vendorID=vendorId;
      setvendorInfo();
      pd_name.setText(queryName);
      ratingBar.setRating(queryRating);
      pd_stock.setText(queryStock+"");
      pd_price.setText(queryPrice+"");
      pd_brand.setText(querybrandName+"");
      pd_description.setText(querydescriptionName);
      pd_description.setMaxWidth(350);
      pd_description.setWrapText(true);
      pd_brand.setText(querybrandName);
      pd_model.setText(querymodelName);
      Image p_img= new Image (getClass().getResourceAsStream(queryImage),320,330,false,false);
      pd_image.setImage(p_img);
      pd_image.setFitHeight(330);
      pd_image.setFitWidth(320);
      rating_count.setText("("+queryCount+")");
      
       prevRate=queryRating;
      // ratingBar.setMaxSize(176, 32);
       ratingBar.setPartialRating(true);
       ratingBar.setMax(5);
       
        
       


  
    }  
    void setvendorInfo()
    {
        DatabaseConnection connectNow = new DatabaseConnection();
        java.sql.Connection connectDB = connectNow.getConnection();
        String v_info="SELECT AVG(d_rating) AS AverageRate FROM product_info  where d_vendor_id= "+vendorID+" ;";
        //System.out.println(v_info); 
        String shop="SELECT shop_name from vendor_table where id_vendor ="+vendorID +";";
       // System.out.println(shop);
        String s=null;
        
         try {

            Statement statement = connectDB.createStatement();
            ResultSet queryOutput1 = statement.executeQuery(v_info);
            while (queryOutput1.next()) { 
                 shopRating = queryOutput1.getDouble("AverageRate");   
            }
            Statement statement1 = connectDB.createStatement();
            ResultSet queryOutput2 = statement1.executeQuery(shop);
            while (queryOutput2.next()) { 
                 s = queryOutput2.getString(1);   
            }
         
         
         }
         
       
       catch (SQLException e) {
           Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, e);
        }
         shopName=s;
        // System.out.println(shopRating);
         //System.out.println(s);
         shoprating.setRating(shopRating);
         shoprating.setMax(5);
         shoprating.setPartialRating(true);
         shoprating.setMouseTransparent(true);
         shopname.setText(shopName);
      // ratingBar.setRating(queryRating);
      
       
       //setvendorInfo();
        
        
        
        
    }
    
      
    
  
}
