/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cybermart;

import com.jfoenix.controls.JFXButton;
import java.awt.Insets;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author majid
 */
public class ShoppingCartController implements Initializable {

    
    ObservableList<shoppingCartModel>shoppingCartModelObservableList = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
     @FXML
    private TableColumn<shoppingCartModel, Integer> cartAmount;

     @FXML
    private Label status;
     @FXML
    private JFXButton amountE;

    @FXML
    private TextField amountF;

    @FXML
    private Label amountL;
    @FXML
    private TableColumn<shoppingCartModel, String> cartModel;
    @FXML
    private TableColumn<shoppingCartModel, String> cartBrand;
    @FXML
    private Label warningAmount;
     
    @FXML
    private TableColumn<shoppingCartModel, String> cartName;
    @FXML
    private TableColumn<shoppingCartModel, String> cartImage;

    @FXML
    private TableColumn<shoppingCartModel, Integer> cartPrice;
     @FXML
    private TableColumn<shoppingCartModel, String> editCart;
    @FXML
    private JFXButton paymentButton;
    @FXML
    private TableView<shoppingCartModel> cartTable;
       @FXML
    private Label customerName;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private shoppingCartModel temp=null;
    @FXML
    private Label userName;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         amountL.setVisible(false);
         amountF.setVisible(false);
         amountE.setVisible(false);
        DatabaseConnection connectNow = new DatabaseConnection();
        java.sql.Connection connectDB = connectNow.getConnection();
         loadTable();
         cartTable.setPlaceholder(new Label("No product in your cart !!!"));
          cartTable.setRowFactory (b->{
            TableRow <shoppingCartModel> row=new TableRow();
            
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
         
         
         
         
         
         
         
         paymentButton.setOnMouseClicked(event->{
             int error=0; 
             for(shoppingCartModel sp : shoppingCartModelObservableList) {
                   int pid,cur_amount,cur_stock=0;
                   String p_name="";
                   cur_amount= sp.getCartpAmount();
                   p_name=sp.getCartpName();
                   pid= sp.getCartPid();
                   
                 try{
                Statement statement2 = connectDB.createStatement();
                String getP= "select d_stock from product_info where d_id_product = "+ pid+" ;  ";
                ResultSet queryCR = statement2.executeQuery(getP);
              while(queryCR.next())
            {
                 cur_stock = queryCR.getInt("d_stock");
                
            }
               
           }catch(SQLException e){
                e.getCause();
           }
                 if(cur_stock<cur_amount)
                 {
                     error++;
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setHeaderText(null);
                            alert.setContentText("ERROR!! Reduce the amount of "+p_name+" less than "+cur_stock);
                            alert.show();
                             
                 }
                
                    
               }
             if( shoppingCartModelObservableList.isEmpty())
               {
                   status.setText("Cart empty!!!");
               } 
             else if(error!=0)
             {
                 System.out.println("error"); 
                 
             }
             else{
                 try {
                root = FXMLLoader.load(getClass().getResource("paymentPage.fxml"));
                stage = new Stage();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Payment Page");
                stage.resizableProperty().setValue(false);
                stage.show();
                
            } catch (IOException ex) {
                Logger.getLogger(HomepageController.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
                 
             }
  
         });
         
         
         amountE.setOnMouseClicked(e->{
              
              String query1="select d_stock,d_price from product_info where d_id_product = "+temp.getCartPid()+";" ;
               int cur_stock=0;
               int price=0;
        
        try {

            Statement statement = connectDB.createStatement();
            ResultSet queryresult = statement.executeQuery(query1);
            while (queryresult.next()) { 
                cur_stock=queryresult.getInt("d_stock");
                price=queryresult.getInt("d_price");
            }

        } catch (SQLException ex) {
            ex.getCause();
        }
        String amountS=amountF.getText();
        int amount = Integer.parseInt(amountS);
        if(cur_stock<amount||amount==0||amount<0){
            
         warningAmount.setText("Please set amount less than "+cur_stock+" or greater than 0");
            
        }
        else{
            warningAmount.setText("");
            String queryUp="UPDATE `cybermart`.`shopping_cart` SET `product_amount` = '"+amount+"', `product_price` = '"+amount*price+"' WHERE (`cart_order_no` = '"+temp.getOrderNo()+"');";
            //System.out.println(queryUp);
            try{
                         Statement statement1 = connectDB.createStatement();
                         statement1.executeUpdate(queryUp);    
                         
                     }
                     catch (SQLException ec){
                         ec.getCause();
                         
                     }
         amountL.setVisible(false);
         amountF.setVisible(false);
         amountE.setVisible(false);
        }
         
             loadTable();
         });
        
    }
     public void refreshTable()
     {
         shoppingCartModelObservableList.clear();
        DatabaseConnection connectNow = new DatabaseConnection();
        java.sql.Connection connectDB = connectNow.getConnection();
        String cusname="select d_name,d_username from customer_info,c_user where  customer_info.customer_id=c_user.customer_ID;";
        try{
            Statement statement2 = connectDB.createStatement();
            ResultSet queryOutput2 = statement2.executeQuery(cusname);
             while (queryOutput2.next()){
                String queryCName = queryOutput2.getString("d_name");
                String queryUName = queryOutput2.getString("d_username");
                customerName.setText(queryCName);
                userName.setText(queryUName);
             }
            
             
        }catch (SQLException ex) {
            Logger.getLogger(ShoppingCartController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String getCart = "select cart_order_no,product_id,product_name,product_model,product_brand,product_amount,product_price FROM shopping_cart WHERE customer_id=(select c_user.customer_ID from c_user) and shipping_status='';";
        try {

            Statement statement = connectDB.createStatement();
            Statement statement1 = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(getCart);
            while (queryOutput.next()) { 
                int queryP_ID = queryOutput.getInt("product_id");
                String imageQ="select d_image from product_info where d_id_product ="+queryP_ID+";";
                //System.out.println(imageQ);
                String queryImage = null;
                ResultSet queryOutput1 = statement1.executeQuery(imageQ);
                while (queryOutput1.next())
                {
                    queryImage=queryOutput1.getString(1);
                }
               // System.out.println(queryImage);
                int order_no=queryOutput.getInt("cart_order_no");
                int queryAmount = queryOutput.getInt("product_amount"); 
                int queryPrice = queryOutput.getInt("product_price");
                String queryName = queryOutput.getString("product_name");
                String querymodelName = queryOutput.getString("product_model");
                String querybrandName = queryOutput.getString("product_brand");
                ImageView qImg= new ImageView(new Image(this.getClass().getResourceAsStream(queryImage)));
                qImg.setFitWidth(250);
                qImg.setFitHeight(200);
                 
               shoppingCartModelObservableList.add(new shoppingCartModel(order_no,queryP_ID,queryName,querymodelName,querybrandName,queryAmount,queryPrice,qImg));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShoppingCartController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
     }
      public void loadTable(){

            DatabaseConnection connectNow = new DatabaseConnection();
            java.sql.Connection connectDB = connectNow.getConnection();
            String shopq="UPDATE `cybermart`.`customer_info` SET `d_cartshop` = '' WHERE (`customer_id` = (select c_user.customer_ID from c_user where c_ID ='1'));";
               refreshTable();
               cartName.setCellValueFactory(new PropertyValueFactory("cartpName"));
               cartModel.setCellValueFactory(new PropertyValueFactory("cartpModel"));
               cartBrand.setCellValueFactory(new PropertyValueFactory("cartpBrand"));
               cartPrice.setCellValueFactory(new PropertyValueFactory("cartpPrice"));
               cartAmount.setCellValueFactory(new PropertyValueFactory("cartpAmount"));
               cartImage.setCellValueFactory(new PropertyValueFactory("cartpImage"));
               //cartTable.setItems(shoppingCartModelObservableList);

        Callback<TableColumn<shoppingCartModel, String>, TableCell<shoppingCartModel, String>> cellFoctory = (TableColumn<shoppingCartModel, String> param) -> {
            final TableCell<shoppingCartModel, String> cell = new TableCell<shoppingCartModel, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        ImageView deleteIcon= new ImageView(new Image(this.getClass().getResourceAsStream("images/trash.png")));
                        deleteIcon.setFitHeight(50);
                        deleteIcon.setFitWidth(50);
                        ImageView editIcon= new ImageView(new Image(this.getClass().getResourceAsStream("images/editing.png")));
                        editIcon.setFitHeight(50);
                        editIcon.setFitWidth(50);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"        
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;" 
                        );
                        
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                             alert.setHeaderText(null);
                             alert.setContentText("Are you sure to delete the cart product");
                            Optional <ButtonType> action=alert.showAndWait();
                            if(action.get()==ButtonType.OK){
                                
                                try {
                                 temp = cartTable.getSelectionModel().getSelectedItem();
                                String query = "DELETE FROM shopping_cart WHERE (cart_order_no = '"+temp.getOrderNo()+"');";
                                Statement statement = connectDB.createStatement();
                                statement.executeUpdate(query);
                                
                                
                            } catch (SQLException ex) {
                               Logger.getLogger(ShoppingCartController.class.getName()).log(Level.SEVERE, null, ex); 
                            }  
                                
                            }
                            
                            refreshTable();
                            if(shoppingCartModelObservableList.isEmpty())
                            
                             try{
                                   Statement statement = connectDB.createStatement();
                                       statement.executeUpdate(shopq); 
                                }
                           catch (SQLException e){
                              e.getCause();
                               }

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            temp = cartTable.getSelectionModel().getSelectedItem();
                              amountL.setVisible(true);
                              amountF.setVisible(true);
                              amountE.setVisible(true);
                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        managebtn.setSpacing(20);
                        managebtn.setAlignment(Pos.BASELINE_CENTER);
                        managebtn.setFillHeight(true);
                        HBox.setHgrow(editIcon, Priority.ALWAYS);
                        HBox.setHgrow(deleteIcon, Priority.ALWAYS);
                        setGraphic(managebtn);
                        setText(null);

                    }
                }

            };

            return cell;
        };
         editCart.setCellFactory(cellFoctory);
         cartTable.setItems(shoppingCartModelObservableList);
         
         
    }
        
        
        
        
        
        
        
        
        
        
          
      }    
    

