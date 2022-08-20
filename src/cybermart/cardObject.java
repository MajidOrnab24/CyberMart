/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cybermart;

/**
 *
 * @author majid
 */
public class cardObject {
   private String cName,cPrice,cstock ,cImage;
   private double cRating;
   private int cID;

    public int getcID() {
        return cID;
    }

    public void setcID(int cID) {
        this.cID = cID;
    }
   
   
    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcPrice() {
        return cPrice;
    }

    public void setcPrice(String cPrice) {
        this.cPrice = cPrice;
    }

    public String getCstock() {
        return cstock;
    }

    public void setCstock(String cstock) {
        this.cstock = cstock;
    }

    public String getcImage() {
        return cImage;
    }

    public void setcImage(String cImage) {
        this.cImage = cImage;
    }

    public double getcRating() {
        return cRating;
    }

    public void setcRating(Double cRating) {
        this.cRating = cRating;
    }


    
}
