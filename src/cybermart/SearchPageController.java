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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author majid
 */
public class SearchPageController implements Initializable {
    @FXML
    private TableColumn<productSearchModel,String> brandnameCol;

    @FXML
    private TableColumn<productSearchModel, String> descriptionCol;

    @FXML
    private ImageView exit;

    @FXML
    private TableColumn<productSearchModel, String> imageCol;

    @FXML
    private JFXButton login_sign;
    
    @FXML
    private TextField keywordField;

    @FXML
    private TableColumn<productSearchModel, String> modelCol;

    @FXML
    private TableColumn<productSearchModel, String> nameCol;

    @FXML
    private TableColumn<productSearchModel, Integer> priceCol;

    @FXML
    private TableView<productSearchModel> productTable;

    @FXML
    private TableColumn<productSearchModel, String> ratingCol;

    @FXML
    private AnchorPane slider;

    @FXML
    private TableColumn<productSearchModel, Integer> stockCol;
    @FXML
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    ObservableList<productSearchModel>productSearchModelObservableList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DatabaseConnection connectNow = new DatabaseConnection();
        java.sql.Connection connectDB = connectNow.getConnection();
        String verifyS = "select search_query FROM search_info WHERE s_id=1;";
        String rest_part = null;
        
        try {

            Statement statement = connectDB.createStatement();
            ResultSet queryresult = statement.executeQuery(verifyS);
            while (queryresult.next()) { 
                rest_part=queryresult.getString(1);
            }

        } catch (SQLException e) {
            e.getCause();
        }
        String productviewQuery;
        if(rest_part==null||rest_part.length()<1)
        {
             productviewQuery="SELECT d_id_product, d_name_product, d_brandname,  d_model, d_price, d_stock, d_rating, d_image,d_description, d_category FROM product_info;";
            
        }else{
            String temp="SELECT d_id_product, d_name_product, d_brandname,  d_model, d_price, d_stock, d_rating, d_image,d_description, d_category FROM product_info";
             productviewQuery=temp+" where d_category='"+rest_part+"';";
        }
        
        
        
        try {

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
                qImg.setFitWidth(240);
                qImg.setFitHeight(200);
                
               q_rating.setRating(queryRating);
               q_rating.setMaxSize(176, 32);
               q_rating.setPartialRating(true);
               q_rating.setMax(5);
               
               q_rating.setMouseTransparent(true);
                
               productSearchModelObservableList.add(new productSearchModel(queryP_ID,queryName,querybrandName,querymodelName,queryPrice,queryStock,queryRating,qImg,querydescriptionName,q_rating,queryCategory,queryImage));
            }
               nameCol.setCellValueFactory(new PropertyValueFactory("productName"));
               brandnameCol.setCellValueFactory(new PropertyValueFactory("brandofProduct"));
               modelCol.setCellValueFactory(new PropertyValueFactory("productModel"));
               priceCol.setCellValueFactory(new PropertyValueFactory("price"));
               stockCol.setCellValueFactory(new PropertyValueFactory("stock"));
               ratingCol.setCellValueFactory(new PropertyValueFactory("productRating"));
               imageCol.setPrefWidth(250);
               imageCol.setCellValueFactory(new PropertyValueFactory("imageProduct"));
               descriptionCol.setCellValueFactory(new PropertyValueFactory("p_description"));
               productTable.setItems(productSearchModelObservableList);
               FilteredList <productSearchModel> filteredData =new FilteredList<>(productSearchModelObservableList,b->true);
               keywordField.textProperty().addListener((observable,oldValue,newValue)->{
               filteredData.setPredicate(productSearchModel->{
                
                if(newValue.isBlank()||newValue.isBlank())
                { return true;
                }
                String searchKeyword =newValue.toLowerCase();
                if(productSearchModel.getProductName().toLowerCase().indexOf(searchKeyword)> -1 )
                {
                    return true;
                }
                else if(productSearchModel.getBrandofProduct().toLowerCase().indexOf(searchKeyword)> -1 )
                {
                    return true;
                }
                else if(productSearchModel.getP_description().toLowerCase().indexOf(searchKeyword)> -1 )
                {
                    return true;
                }
                else if(productSearchModel.getProductModel().toLowerCase().indexOf(searchKeyword)> -1 )
                {
                    return true;
                }
                
                else
                   return false;
                                
                });
               
              
               
                       });
            SortedList<productSearchModel> sortedData= new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(productTable.comparatorProperty());
            productTable.setItems(sortedData);
            productTable.setPlaceholder(new Label("No specified product found"));
            productTable.setRowFactory(b->{
            TableRow <productSearchModel> row=new TableRow();
            row.setOnMouseClicked(rEvent->{
                if(!row.isEmpty())
                {
                   productSearchModel rowData=row.getItem();
                String queryRow = "Update row_table SET r_id='"+rowData.getProductID()+"',r_name='"+rowData.getProductName()+"' WHERE r_pk ='1';";

        try {

            Statement statementR = connectDB.createStatement();
            statementR.executeUpdate(queryRow);

        } catch (SQLException e) {
            e.getCause();
        }
        
        try {

                root = FXMLLoader.load(getClass().getResource("ProductDetails.fxml"));
                stage = new Stage();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Product details page");
                stage.resizableProperty().setValue(false);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(HomepageController.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        
        
        
                }
            });
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
        
        } catch (SQLException e) {
           Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, e);
        }
    }    
    
}
