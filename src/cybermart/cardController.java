/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cybermart;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Rating;

/**
 *
 * @author majid
 */
public class cardController {
    @FXML
    private ImageView cardImage;

    @FXML
    private Label cardName;

    @FXML
    private Label cardPrice;

    @FXML
    private Rating cardRating;

    @FXML
    private Label cardStock;
    private cardObject cardC;
    private MyListener myListener;
    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(cardC);
    }
    
    
    
    public void setData(cardObject cardC,MyListener myListener)
    {
        this.cardC=cardC;
        this.myListener=myListener;

        Image tImage1= new Image (getClass().getResourceAsStream(cardC.getcImage()),169,163,false,false);
        cardImage.setImage(tImage1);
        cardImage.setFitHeight(169);
         cardImage.setFitWidth(163);
        
        cardRating.setRating(cardC.getcRating());
        cardRating.setMouseTransparent(true);
        cardRating.setPartialRating(true);
        cardRating.setMax(5);
        cardPrice.setText(cardC.getcPrice());
        cardName.setText(cardC.getcName());
        cardStock.setText(cardC.getCstock());

 
    }
    
}
