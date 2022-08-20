/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cybermart;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author majid
 */
public class FrontPageController implements Initializable {

    /**
     * Initializes the controller class.
     *
     */
    @FXML
    private HBox recentLayout;
    @FXML
    private HBox ratedLayout;
    
    private List<cardObject> recentlyAdded;
    private List<cardObject> toprated;
    private MyListener myListener;
     private Stage stage;
    private Scene scene;
    private Parent root;
    private void setChosenCard(cardObject cardC) {
         String queryRow = "Update row_table SET r_id='"+cardC.getcID()+"',r_name='"+cardC.getcName()+"' WHERE r_pk ='1';";
                    //System.out.println(queryRow);
         DatabaseConnection connectNow = new DatabaseConnection();
        java.sql.Connection connectDB = connectNow.getConnection();

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
        
        //System.out.println(cardC.getcID());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        // TODO
        recentlyAdded =new ArrayList<>(recentlyAdded());
        toprated =new ArrayList<>(toprated());
        if (toprated.size() > 0&& recentlyAdded.size()>0) {
 
            myListener = new MyListener() {
                @Override
                public void onClickListener(cardObject cardC) {
                    setChosenCard(cardC);
                }
            };
        }
        try{
            for(int i=0;i<recentlyAdded.size();i++)
            {
                FXMLLoader fxmlloader =new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("productCard.fxml"));
                HBox cardBox =fxmlloader.load();
                cardController cardC=fxmlloader.getController();
                cardObject a=new cardObject();
                a=recentlyAdded.get(i);
                cardC.setData(a,myListener);
                recentLayout.getChildren().add(cardBox);

            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
            
        }
        
        
        try{
            for(int i=0;i<toprated.size();i++)
            {
                FXMLLoader fxmlloader =new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("productCard.fxml"));
                HBox cardBox =fxmlloader.load();
                cardController cardC=fxmlloader.getController();
                cardObject a=new cardObject();
                a=toprated.get(i);
                cardC.setData(a,myListener);
                ratedLayout.getChildren().add(cardBox);

            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
            
        }
        
    }
      private List<cardObject > recentlyAdded()
      {
          DatabaseConnection connectNow = new DatabaseConnection();
          Connection connectDB = connectNow.getConnection();
          List<cardObject> rAdd =new ArrayList<>();
          cardObject cObj =new cardObject();
          
          
        String query1 = "SELECT d_id_product,d_name_product, d_price, d_stock, d_rating, d_image FROM product_info order by d_id_product desc limit 5;";
        
        try {

            Statement statement = connectDB.createStatement();
            ResultSet queryresult = statement.executeQuery(query1);
            
            while (queryresult.next()) { 
                //rest_part=queryresult.getString(1);
                
                Double queryRating = queryresult.getDouble("d_rating");
                Integer queryStock = queryresult.getInt("d_stock");
                String queryImage=queryresult.getString("d_image");
                Integer queryPrice = queryresult.getInt("d_price");
                String queryName=queryresult.getString("d_name_product");
                Integer queryId = queryresult.getInt("d_id_product");
                
          cObj =new cardObject();
          cObj.setcName(queryName);
          cObj.setCstock(queryStock+" in stock");
          cObj.setcPrice(queryPrice +" BDT");
          cObj.setcRating(queryRating);
          cObj.setcID(queryId);
          //cObj.setcRating("images/5star.png");
          cObj.setcImage(queryImage);//"Lenovo","5 instock","9000 bdt","/productimages/pocox3.png","/images/5star.png"
           rAdd.add(cObj);
                
 
            }

        } catch (SQLException e) {
            e.getCause();
        }

                  return rAdd;
          
      }
      private List<cardObject > toprated()
      {
          DatabaseConnection connectNow = new DatabaseConnection();
          Connection connectDB = connectNow.getConnection();
          List<cardObject> rAdd =new ArrayList<>();
          cardObject cObj =new cardObject();
          
          
        String query1 = "SELECT d_id_product,d_name_product, d_price, d_stock, d_rating, d_image FROM product_info order by d_rating desc limit 5;";
        
        try {

            Statement statement = connectDB.createStatement();
            ResultSet queryresult = statement.executeQuery(query1);
            
            while (queryresult.next()) { 
                //rest_part=queryresult.getString(1);
                
                Double queryRating = queryresult.getDouble("d_rating");
                Integer queryStock = queryresult.getInt("d_stock");
                String queryImage=queryresult.getString("d_image");
                Integer queryPrice = queryresult.getInt("d_price");
                String queryName=queryresult.getString("d_name_product");
                Integer queryId = queryresult.getInt("d_id_product");
                
          cObj =new cardObject();
          cObj.setcName(queryName);
          cObj.setCstock(queryStock+" in stock");
          cObj.setcPrice(queryPrice +" BDT");
          cObj.setcRating(queryRating);
          cObj.setcID(queryId);
          cObj.setcImage(queryImage);//"Lenovo","5 instock","9000 bdt","/productimages/pocox3.png","/images/5star.png"
           rAdd.add(cObj);
                
 
            }

        } catch (SQLException e) {
            e.getCause();
        }
          
       return rAdd;
      }
            

    
}
