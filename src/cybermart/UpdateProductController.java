/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cybermart;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author majid
 */
public class UpdateProductController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane productSlider;

    @FXML
    private JFXButton up_addImage;

    @FXML
    private TextField up_brand;

    @FXML
    private ChoiceBox<String> up_category;

    @FXML
    private TextArea up_description;

    @FXML
    private ImageView up_image;

    @FXML
    private TextField up_model;

    @FXML
    private TextField up_name;

    @FXML
    private TextField up_price;

    @FXML
    private JFXButton up_save;

    @FXML
    private TextField up_stock;
    @FXML
    private AnchorPane updatePane;

    @FXML
    private Label warningL;
    private int productID;
    private boolean update;
    private String ImageLink,temp=null;
    private Path to,from;
    Random random = new Random();

    @FXML
    void up_addImageClick(MouseEvent event) {
        int y = random.nextInt(1000); 
            FileChooser filechooser=new FileChooser();
        File file =filechooser.showOpenDialog(up_addImage.getScene().getWindow());
        if (file != null) {
        try {
            String imageUrl = file.toURI().toURL().toExternalForm();
            Image image = new Image(imageUrl,210,135,false,false);
            up_image.setImage(image);
            up_image.setFitHeight(135);
            up_image.setFitWidth(210);
        } catch (MalformedURLException ex) {
            throw new IllegalStateException(ex);
        }
        if(file != null){
  
            try {
                from = Paths.get(file.toURI());   
                to = Paths.get("E:\\My Class\\4th Semester\\Group Project\\CyberMart\\src\\productimages\\" +up_name.getText()+"_"+y+".png");
                Files.copy(from, to);
            } catch (IOException ex) {
                System.out.println("Error"+ex);
            }
            
        }
        } 
        temp=ImageLink;
        ImageLink="/productimages/"+up_name.getText()+"_"+y+".png";
        

    }

    @FXML
    void up_saveClick(MouseEvent event) {
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                             alert1.setHeaderText(null);
                              alert1.setContentText("Are you sure to edit the product details ?");
                            Optional <ButtonType> action=alert1.showAndWait();
                            if(action.get()==ButtonType.OK){
                            
                            if(temp!=null)
        {
        String cat =temp; ;
        cat = cat.replace("/productimages/","");
            String fileName = "E:\\My Class\\4th Semester\\Group Project\\CyberMart\\src\\productimages\\" +cat;
        try {
            Files.delete(Paths.get(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        }
        
            String  newName=up_name.getText();
            String  newBrand=up_brand.getText();
            String  newModel=up_model.getText();
            String  newPrice=up_price.getText();
            String  newStock=up_stock.getText();
            String  newdescription=up_description.getText();
            String  newcategory=up_category.getValue();
            
        
            if(newName.isEmpty()||newBrand.isEmpty()||newModel.isEmpty()||newPrice.isEmpty()||newStock.isEmpty()||newdescription.isEmpty()|| newcategory.isEmpty())            
            {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Please fill all data");
            alert.showAndWait();
            }
            else{
                String query="UPDATE `cybermart`.`product_info` SET `d_name_product` = '"+newName+"', `d_brandname` = '"+newBrand+"', `d_model` = '"+newModel+"', `d_price` = '"+newPrice+"', `d_stock` = '"+newStock+"', `d_description` = '"+newdescription+"', `d_image` = '"+ImageLink+"' WHERE (`d_id_product` = '"+productID+"');";
               // System.out.println(query); 
                DatabaseConnection connectNow = new DatabaseConnection();
              java.sql.Connection connectDB = connectNow.getConnection();
                
                try{
                         Statement statement1 = connectDB.createStatement();
                         statement1.executeUpdate(query);    
                         
                     }
                     catch (SQLException ec){
                         ec.getCause();
                         
                     }
                
                
            }
                    updatePane.getScene().getWindow().hide();             
                            }
           
        
    }
    private String[] categories={"Apparels","Electronics","Accesories","Kids"};
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         up_category.getItems().addAll(categories);
    }
 public void setFieldInfo(int p_id,String p_name,String p_brand,String p_model,int p_price,int p_stock,String p_description,String p_category,String p_imageUrl)
 {
     productID=p_id;
     up_name.setText(p_name);
     up_brand.setText(p_brand);
     up_model.setText(p_model);
     up_price.setText(p_price+"");
     up_stock.setText(p_stock+"");
     up_description.setText(p_description);
     ImageLink=p_imageUrl;
     up_category.setValue(p_category);
     Image image=new Image(this.getClass().getResourceAsStream(p_imageUrl));
     up_image.setImage(image);
 }
 void setUpdate(boolean b) {
        this.update = b;
    }
   
    
}
