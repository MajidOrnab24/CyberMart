/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cybermart;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import static java.lang.Math.random;
import static java.lang.StrictMath.random;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;   
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.event.Event;
import javafx.scene.control.PasswordField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author majid
 */
public class VendorPageController implements Initializable {

    /**
     * Initializes the controller class.
     */
        @FXML
    private JFXButton loginButton;
        @FXML
    private JFXButton loginButtonBack;
    @FXML
    private JFXButton loginB;
    @FXML
    private JFXButton signupPush;
    @FXML
    private AnchorPane loginSlider;

    @FXML
    private AnchorPane productSlider;

    @FXML
    private AnchorPane signSlider;
       @FXML
    private TextField vConfirmpass;

    @FXML
    private TextField vContactno;

    @FXML
    private TextField vEmail;

    @FXML
    private ImageView vImage;

    @FXML
    private TextField vLocation;

    @FXML
    private TextField vName;

    @FXML
    private TextField vPassword;

    @FXML
    private JFXButton vReturn;

    @FXML
    private JFXButton addShoplogo;
    @FXML
    private TextField vNameF;

    @FXML
    private PasswordField vPassF;
     @FXML
    private Label loginMsg;
      @FXML
    private ImageView VPimage;

    @FXML
    private Label VPname;
     @FXML
    private JFXButton addProduct;
      @FXML
    private JFXButton addproductImage;
@FXML
    private JFXButton logout;

    @FXML
    private TextField pr_brand;

    @FXML
    private TextArea pr_description;

    @FXML
    private JFXButton seeAllproducts;
    
    @FXML
    private ImageView pr_image;

    @FXML
    private TextField pr_model;

    @FXML
    private TextField pr_name;

    @FXML
    private TextField pr_price;

    @FXML
    private TextField pr_stock;

    @FXML
    private Label productInfoL;

    @FXML
    private Label VPshopname;

    @FXML
    private TextField vShopName;
    @FXML
    private ChoiceBox<String> pr_category;
    @FXML
    private Label warning;
    @FXML
    private JFXButton signupButton;
    @FXML
    private JFXButton signupButtonBack;
    
     private Stage stage;
    private Scene scene;
    private Parent root;
    
    private String imgLink,product_imgLink="images/product_blank.png";
    private Path to,from;
    private String[] categories={"Apparels","Electronics","Accesories","Kids"};
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        Random random = new Random(); 
        pr_category.getItems().addAll(categories);
        loginSlider.setVisible(false);
        productSlider.setTranslateY(708);
        signSlider.setTranslateX(1107);
        int ven_id0 = 0;
        String ven_name0 = null,cur_vName0=null,curShopName0=null, curImage0=null;
        try{
            Statement statement1 = connectDB.createStatement();
            String queryCID = "select id_vendor,vendor_name FROM current_vendor WHERE v_ID=1;";
            ResultSet queryCR = statement1.executeQuery(queryCID);
            while(queryCR.next())
            {
                 ven_id0 = queryCR.getInt("id_vendor");
                 ven_name0=queryCR.getString("vendor_name");
            }
               
           }catch(SQLException ex){
                ex.getCause();
           }
        if(ven_id0!=0 )//|| !ven_name.isEmpty()
                {
                
            String setV = "select vendor_name,shop_name,vendor_image FROM vendor_table WHERE id_vendor="+ven_id0+";";
            
                    
        try {

            Statement statement2 = connectDB.createStatement();
            ResultSet queryresult = statement2.executeQuery(setV);
            while (queryresult.next()) { 
                cur_vName0=queryresult.getString("vendor_name");
                curShopName0=queryresult.getString("shop_name");
                curImage0=queryresult.getString("vendor_image");
                
            }

        } catch (SQLException ex) {
            ex.getCause();
        }
        VPname.setText(cur_vName0);
        VPshopname.setText(curShopName0);
        Image image = new Image(curImage0,140,130,false,false);
        VPimage.setImage(image);
        VPimage.setFitHeight(130);
        VPimage.setFitWidth(140);
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(productSlider);
            slide.setToY(0);
            slide.play();
            productSlider.setTranslateY(708);
                }

        seeAllproducts.setOnMouseClicked(event->{
            try {
                root = FXMLLoader.load(getClass().getResource("vendorProducts.fxml"));
               stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Vendor product page");
                stage.resizableProperty().setValue(false);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(VendorPageController.class.getName()).log(Level.SEVERE, null, ex);
            }

                });
        
        
        loginButton.setOnMouseClicked(e->{
                loginSlider.setVisible(true);
                loginButtonBack.setVisible(true);
                loginButton.setVisible(false);

                });
        loginButtonBack.setOnMouseClicked(e->{
                loginSlider.setVisible(false);
                loginButtonBack.setVisible(false);
                loginButton.setVisible(true);

                });
//        signupButtonBack.setOnMouseClicked(e->{
//            TranslateTransition slide = new TranslateTransition();
//                slide.setDuration(Duration.seconds(0.4));
//                slide.setNode(signSlider);
//                slide.setToX(0);
//                slide.play();
//                signSlider.setTranslateX(680);
//                slide.setOnFinished((ActionEvent ex)->{
//                signupButton.setVisible(true);
//                signupButtonBack.setVisible(false);
//            }); 
//        });
//        signupButton.setOnMouseClicked(e->{
//            TranslateTransition slide = new TranslateTransition();
//                slide.setDuration(Duration.seconds(0.4));
//                slide.setNode(signSlider);
//                slide.setToX(680);
//                slide.play();
//                signSlider.setTranslateX(0);
//                slide.setOnFinished((ActionEvent ex)->{
//                signupButton.setVisible(false);
//                signupButtonBack.setVisible(true);
//            }); 
//        });
        signupButtonBack.setOnMouseClicked(e->{
            TranslateTransition slide = new TranslateTransition();
                slide.setDuration(Duration.seconds(0.4));
                slide.setNode(signSlider);
                slide.setToX(0);
                slide.play();
                signSlider.setTranslateX(680);
                slide.setOnFinished((ActionEvent ex)->{
                signupButton.setVisible(true);
                signupButtonBack.setVisible(false);
            }); 
        });
        signupButton.setOnMouseClicked(e->{
            TranslateTransition slide = new TranslateTransition();
                slide.setDuration(Duration.seconds(0.4));
                slide.setNode(signSlider);
                slide.setToX(680);
                slide.play();
                signSlider.setTranslateX(0);
                slide.setOnFinished((ActionEvent ex)->{
                signupButton.setVisible(false);
                signupButtonBack.setVisible(true);
            }); 
        });
         logout.setOnMouseClicked(e->{
             DatabaseConnection connectnow = new DatabaseConnection();
        Connection connectDBMS = connectnow.getConnection();

        String queryven = "Update current_vendor SET id_vendor='"+0+"',vendor_name='"+""+"' WHERE v_ID='1';";
        try {

            Statement statementR1 = connectDBMS.createStatement();
            statementR1.executeUpdate(queryven);

        } catch (SQLException ex) {
            ex.getCause();
        }
             
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(productSlider);
            slide.setToY(708);
            slide.play();
            productSlider.setTranslateY(0); 
            vNameF.clear();
            vPassF.clear();
            loginMsg.setText("Logut successful");
             
             
         });
        addShoplogo.setOnMouseClicked(e->{
            int y = random.nextInt(1000); 
            FileChooser filechooser=new FileChooser();
        File file =filechooser.showOpenDialog(addShoplogo.getScene().getWindow());
        if (file != null) {
        try {
            String imageUrl = file.toURI().toURL().toExternalForm();
            Image image = new Image(imageUrl,210,135,false,false);
            vImage.setImage(image);
            vImage.setFitHeight(135);
            vImage.setFitWidth(210);
        } catch (MalformedURLException ex) {
            throw new IllegalStateException(ex);
        }
        if(file != null){
  
            try {
                from = Paths.get(file.toURI());   
                to = Paths.get("E:\\My Class\\4th Semester\\Group Project\\CyberMart\\src\\shopimages\\" +vShopName.getText()+"_"+y+".png");
                Files.copy(from, to);
            } catch (IOException ex) {
                System.out.println("Error"+ex);
            }
            
        }
        } 
        imgLink="/shopimages/"+vShopName.getText()+"_"+y+".png";
             
        });
        addProduct.setOnMouseClicked(e->{
            
            
            Image image =pr_image.getImage();
       if(pr_name.getText().isBlank()==false && pr_brand.getText().isBlank()==false && 
               pr_model.getText().isBlank() == false && pr_price.getText().isBlank() == false && 
               pr_stock.getText().isBlank()==false && pr_description.getText().isBlank() == false&&
               pr_category.getValue()!=null) {
            
                registerProduct();
                
            
        }
        else{
           productInfoL.setTextFill(Color.RED);
           productInfoL.setText("Please fill all details");
        }
               
        });
        
        
        
        
        signupPush.setOnMouseClicked(event->  {
            Image image =vImage.getImage();
       if(vName.getText().isBlank()==false && vShopName.getText().isBlank()==false && 
               vEmail.getText().isBlank() == false && vContactno.getText().isBlank() == false && 
               vLocation.getText().isBlank()==false && vPassword.getText().isBlank() == false&&vConfirmpass.getText().isBlank() == false) {
            if (vPassword.getText().equals(vConfirmpass.getText())) {
                registerUser();
                
            }
            else{
                warning.setTextFill(Color.RED);
                warning.setText("Password doesn't match");
            }
        }
        else{
           warning.setTextFill(Color.RED);
            warning.setText("Please fill all details");
        }

       });
        loginB.setOnMouseClicked(e->{
            
        int ven_id = 0;
        String ven_name = null,cur_vName=null,curShopName=null, curImage=null;
        try{
            Statement statement1 = connectDB.createStatement();
            String queryCID = "select id_vendor,vendor_name FROM current_vendor WHERE v_ID=1;";
            ResultSet queryCR = statement1.executeQuery(queryCID);
            while(queryCR.next())
            {
                 ven_id = queryCR.getInt("id_vendor");
                 ven_name=queryCR.getString("vendor_name");
            }
               
           }catch(SQLException ex){
                ex.getCause();
           }
//        if(ven_id!=0 )//|| !ven_name.isEmpty()
//                {
//                
//            String setV = "select vendor_name,shop_name,vendor_image FROM vendor_table WHERE id_vendor="+ven_id+";";
//            
//                    
//        try {
//
//            Statement statement2 = connectDB.createStatement();
//            ResultSet queryresult = statement2.executeQuery(setV);
//            while (queryresult.next()) { 
//                cur_vName=queryresult.getString("vendor_name");
//                curShopName=queryresult.getString("shop_name");
//                curImage=queryresult.getString("vendor_image");
//                
//            }
//
//        } catch (SQLException ex) {
//            ex.getCause();
//        }
//        VPname.setText(cur_vName);
//        VPshopname.setText(curShopName);
//        Image image = new Image(curImage,140,130,false,false);
//        VPimage.setImage(image);
//        VPimage.setFitHeight(130);
//        VPimage.setFitWidth(140);
//            TranslateTransition slide = new TranslateTransition();
//            slide.setDuration(Duration.seconds(0.4));
//            slide.setNode(productSlider);
//            slide.setToY(0);
//            slide.play();
//            productSlider.setTranslateY(708);
//                }
        
        if(ven_id==0 || ven_name.isEmpty())
        {
            if (vNameF.getText().isBlank() == false && vPassF.getText().isBlank() == false) {

            validateLogin();
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(productSlider);
            slide.setToY(0);
            slide.play();
            productSlider.setTranslateY(708);

        } else {
            loginMsg.setText("Please enter info");
        }
                        
        }
  
        });
        addproductImage.setOnMouseClicked(e->{
            int y = random.nextInt(1000); 
            FileChooser filechooser=new FileChooser();
        File file =filechooser.showOpenDialog(addproductImage.getScene().getWindow());
        if (file != null) {
        try {
            String imageUrl = file.toURI().toURL().toExternalForm();
            Image image = new Image(imageUrl,210,135,false,false);
            pr_image.setImage(image);
            pr_image.setFitHeight(135);
            pr_image.setFitWidth(210);
        } catch (MalformedURLException ex) {
            throw new IllegalStateException(ex);
        }
        if(file != null){
  
            try {
                from = Paths.get(file.toURI());   
                to = Paths.get("E:\\My Class\\4th Semester\\Group Project\\CyberMart\\src\\productimages\\" +pr_name.getText()+"_"+y+".png");
                Files.copy(from, to);
            } catch (IOException ex) {
                System.out.println("Error"+ex);
            }
            
        }
        } 
        product_imgLink="/productimages/"+pr_name.getText()+"_"+y+".png";
            
            
        });
        vReturn.setOnMouseClicked(e->{
            TranslateTransition slide = new TranslateTransition();
                slide.setDuration(Duration.seconds(0.4));
                slide.setNode(signSlider);
                slide.setToX(680);
                slide.play();
                signSlider.setTranslateX(0);
                slide.setOnFinished((ActionEvent ex)->{
                signupButton.setVisible(false);
                signupButtonBack.setVisible(true);
            }); 
            
            
            
            
        });
        
    }    
    
    
    
        public void registerUser(){

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String vendor_name= vName.getText();
        String shop_name = vShopName.getText();
        String vendor_email=vEmail.getText();
        String vendor_contact=vContactno.getText();
        String vendor_location=vLocation.getText();
        String vendor_password=vPassword.getText();
        String vendor_image=imgLink;

        String insertFields="INSERT INTO vendor_table(vendor_name,shop_name,vendor_email,vendor_contact, vendor_location,vendor_password,vendor_image) VALUES ('";
        String insertValues= vendor_name + "','"+ shop_name+ "','" + vendor_email + "','" + vendor_contact+ "','" + vendor_location + "','" +vendor_password+ "','" +vendor_image + "')";
        String insertToSignup= insertFields+insertValues;
        // System.out.println(insertToSignup);

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToSignup);
            warning.setTextFill(Color.BLUE);
           warning.setText("SIGNUP SUCCESFUL");
        }
        catch (SQLException e){
            e.getCause();
        }

    }
        
        public void registerProduct(){
            
            
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String d_name_product= pr_name.getText();
        String d_brandname = pr_brand.getText();
        String d_model=pr_model.getText();
        String d_price=pr_price.getText();
        String d_stock=pr_stock.getText();
        String d_description=pr_description.getText();
        String  d_category= pr_category.getValue();
        String d_image=product_imgLink;
        Integer d_rating_count=0;
        Integer d_vendor_id=0;
        String d_vendor_name=null;
        String detailQuery="select id_vendor,vendor_name from current_vendor where v_ID=1;";
 
       try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput1 = statement.executeQuery(detailQuery);
            while (queryOutput1.next()) { 
                 d_vendor_id=queryOutput1.getInt("id_vendor");
                 d_vendor_name=queryOutput1.getString("vendor_name");
            }}
       
       catch (SQLException e) {
           Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, e);
        }
        

        String insertFields="INSERT INTO product_info (d_name_product, d_brandname, d_model, d_price, d_stock, d_rating, d_image, d_description,d_category, d_rating_count,d_vendor_id,d_vendor_name) VALUES ('";
        String insertValues= d_name_product + "','"+ d_brandname+ "','" + d_model + "','" +d_price+ "','" +d_stock + "','"+ 0+ "','" + d_image+ "','"+ d_description+ "','" +d_category  + "','" +d_rating_count
                                 + "','" +d_vendor_id + "','" +d_vendor_name   + "');";
        String insertToSignup= insertFields+insertValues;
        System.out.println(insertToSignup);

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToSignup);
            productInfoL.setTextFill(Color.BLACK);
           productInfoL.setText("PRODUCT ADDED");
        }
        catch (SQLException e){
            e.getCause();
        }
              
            
        }
        
        
        public void validateLogin(){

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        
        String cur_vName = null,curShopName=null,curImage=null;
        int ven_id = 0;
        String verifyLogin = "select count(1) FROM vendor_table WHERE vendor_name='" + vNameF.getText() + "' and vendor_password='" + vPassF.getText() + "';";
        String queryC="select id_vendor FROM vendor_table WHERE vendor_name='" + vNameF.getText() + "' and vendor_password='" + vPassF.getText() + "';";
        try {

            Statement statement = connectDB.createStatement();
            Statement statement1 = connectDB.createStatement();
            ResultSet queryCR = statement1.executeQuery(queryC);
            while(queryCR.next())
            {
                ven_id=queryCR.getInt(1);
            }
            
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) { 
                if (queryResult.getInt(1) == 1) {
                    loginMsg.setTextFill(Color.BLUE);
                    loginMsg.setText("Successful login");
                    String verifyS = "select vendor_name,shop_name,vendor_image FROM vendor_table WHERE id_vendor="+ven_id+";";
                    String queryRow = "Update current_vendor SET id_vendor='"+ven_id+"',vendor_name='"+vNameF.getText()+"' WHERE v_ID='1';";
                    
        try {

            Statement statement2 = connectDB.createStatement();
            Statement statement3 = connectDB.createStatement();
           // System.out.println(queryRow);
            statement2.executeUpdate(queryRow);
            ResultSet queryresult = statement3.executeQuery(verifyS);
            while (queryresult.next()) { 
                cur_vName=queryresult.getString("vendor_name");
                curShopName=queryresult.getString("shop_name");
                curImage=queryresult.getString("vendor_image");
                
            }

        } catch (SQLException e) {
            e.getCause();
        }
      
                } else {
                    loginMsg.setText("Invalid login Please try again");
                }
            }

        } catch (SQLException e) {
            e.getCause();
        }
        
        
        VPname.setText(cur_vName);
        VPshopname.setText(curShopName);
        Image image = new Image(curImage,140,130,false,false);
        VPimage.setImage(image);
        VPimage.setFitHeight(130);
        VPimage.setFitWidth(140);
        
          
    }
        
        
        
        
        
    
    
    
}
