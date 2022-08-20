/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cybermart;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author majid
 */
public class VendorProductsController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private JFXButton returnTomain;
     private Stage stage;
    private Scene scene;
    private Parent root;
     @FXML
    private TableColumn<productSearchModel, String> p_brand;

    @FXML
    private TableColumn<productSearchModel, String> p_description;

    @FXML
    private TableColumn<productSearchModel, String> p_edit_delete;

    @FXML
    private TableColumn<productSearchModel, String> p_image;

    @FXML
    private TableColumn<productSearchModel, String> p_model;

    @FXML
    private TableColumn<productSearchModel, String> p_name;

    @FXML
    private TableColumn<productSearchModel, Integer> p_price;

    @FXML
    private TableColumn<productSearchModel, Integer> p_stock;
     @FXML
    private TableColumn<productSearchModel, String>p_rating;

    @FXML
    private TableView<productSearchModel> p_table;
    @FXML
    private JFXButton refresh;

    
    @FXML
    void refreshClick(MouseEvent event) {
        
        loadTable();

    }
    private productSearchModel temp=null;
    ObservableList<productSearchModel>productsList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DatabaseConnection connectNow = new DatabaseConnection();
        java.sql.Connection connectDB = connectNow.getConnection();
         loadTable();
         p_table.setPlaceholder(new Label("You dont have any deployed products"));
        
        returnTomain.setOnMouseClicked(event->{
            try {
               root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
               scene = new Scene(root);
               stage = (Stage)((Node)event.getSource()).getScene().getWindow();
               stage.setTitle("Main Page");
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
               Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
           }
        });
        p_table.setRowFactory (b->{
            TableRow <productSearchModel> row=new TableRow();
            
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
    }


  public void refreshTable()
  {
      DatabaseConnection connectNow = new DatabaseConnection();
      java.sql.Connection connectDB = connectNow.getConnection();
      productsList.clear();
      String productviewQuery="SELECT d_id_product, d_name_product, d_brandname,  d_model, d_price, d_stock, d_rating, d_image,d_description,d_category FROM product_info where d_vendor_id=(select current_vendor.id_vendor from current_vendor where v_ID ='1') and  d_vendor_name = (select current_vendor.vendor_name from current_vendor where v_ID ='1') ;";
      
      try{ 
          Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(productviewQuery);
      while (queryOutput.next()) { 
                Integer queryP_ID = queryOutput.getInt("d_id_product");
                Double queryRating = queryOutput.getDouble("d_rating");
                Integer queryStock = queryOutput.getInt("d_stock");
                String queryImage=queryOutput.getString("d_image");
                Integer queryPrice = queryOutput.getInt("d_price");
                String queryName=queryOutput.getString("d_name_product");
                String querymodelName=queryOutput.getString("d_model");
                String querybrandName=queryOutput.getString("d_brandname");
                String querydescriptionName=queryOutput.getString("d_description");
                String queryCategory=queryOutput.getString("d_category");
                
                //System.out.println(queryImage);
                //ImageView qImg= new ImageView(new Image(this.getClass().getResourceAsStream("/productimages/iphone13.png")));
                ImageView qImg= new ImageView(new Image(this.getClass().getResourceAsStream(queryImage)));
                final Rating q_rating =new Rating();
                qImg.setFitWidth(200);
                qImg.setFitHeight(200); 
                q_rating.setRating(queryRating);
                q_rating.setMaxSize(176, 32);
                q_rating.setPartialRating(true);
                q_rating.setMax(5);
                q_rating.setMouseTransparent(true);
                
              productsList.add(new productSearchModel(queryP_ID,queryName,querybrandName,querymodelName,queryPrice,queryStock,queryRating,qImg,querydescriptionName,q_rating,queryCategory,queryImage));
            }
      
      
  }catch (SQLException e) {
            e.getCause();
        }
      
      
  }
 public void loadTable(){
       DatabaseConnection connectNow = new DatabaseConnection();
        java.sql.Connection connectDB = connectNow.getConnection();
       refreshTable();
               p_name.setCellValueFactory(new PropertyValueFactory("productName"));
               p_brand.setCellValueFactory(new PropertyValueFactory("brandofProduct"));
               p_model.setCellValueFactory(new PropertyValueFactory("productModel"));
               p_price.setCellValueFactory(new PropertyValueFactory("price"));
               p_stock.setCellValueFactory(new PropertyValueFactory("stock"));
               p_rating.setCellValueFactory(new PropertyValueFactory("productRating"));
               p_image.setPrefWidth(200);
               p_image.setCellValueFactory(new PropertyValueFactory("imageProduct"));
               p_description.setCellValueFactory(new PropertyValueFactory("p_description"));
 
     
               Callback<TableColumn<productSearchModel, String>, TableCell<productSearchModel, String>> cellFoctory = (TableColumn<productSearchModel, String> param) -> {
            final TableCell<productSearchModel, String> cell = new TableCell<productSearchModel, String>() {
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
                              alert.setContentText("Are you sure to delete the product from Cybermart ?");
                            Optional <ButtonType> action=alert.showAndWait();
                            if(action.get()==ButtonType.OK){

                            try {
                                 temp = p_table.getSelectionModel().getSelectedItem();
                                String query = "DELETE FROM product_info WHERE (d_id_product = '"+temp.getProductID()+"');";
                                Statement statement = connectDB.createStatement();
                                statement.executeUpdate(query);
       
                            } catch (SQLException ex) {
                               Logger.getLogger(ShoppingCartController.class.getName()).log(Level.SEVERE, null, ex); 
                            }

                            }
                           
                            refreshTable();
                            
                                 //System.out.println("delte"); 

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            temp = p_table.getSelectionModel().getSelectedItem(); 
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("updateProduct.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(VendorProductsController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            UpdateProductController controller = loader.getController();
                            controller.setUpdate(true);
                           controller.setFieldInfo(temp.getProductID(),temp.getProductName(), temp.getBrandofProduct(), temp.getProductModel(),temp.getPrice(),temp.getStock(),temp.getP_description(),temp.getProductCategory(),temp.getImageUrl());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                           // stage.initStyle(StageStyle.UTILITY);
                           stage.resizableProperty().setValue(false);
                            stage.show();
                            //refreshTable();
                             // System.out.println("edit");
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
         p_edit_delete.setCellFactory(cellFoctory);
      refreshTable();         
    p_table.setItems(productsList); 
 }
    
    
}
