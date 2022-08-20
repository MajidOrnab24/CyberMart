/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cybermart;

import java.net.URL;
import javafx.scene.layout.Pane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;



/**
 *
 * @author majid
 */
public class fxmlLoaderHome {
    private Pane view;

    public Pane getPage(String fileName)
    {
        
        try{
            
             URL fileUrl =main.class.getResource(fileName+".fxml");
             if(fileUrl==null){
                 throw new java.io.FileNotFoundException("FXML cannot be loaded");
             }
             view =new FXMLLoader().load(fileUrl);
             
            
        }catch(Exception e){
            System.out.println("No page named "+fileName+".fxml found, please check again");
            
        }
                
        
        return view;
        
    }
    
    
    
}
