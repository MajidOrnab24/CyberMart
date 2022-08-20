/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cybermart;

import javafx.scene.image.ImageView;

/**
 *
 * @author majid
 */
public class shoppingCartModel {
    private Integer cartPid;
    private Integer orderNo;
    private String cartpName;
    private String cartpModel;
    private String cartpBrand;
    private Integer cartpAmount;
    private Integer cartpPrice;
    private ImageView cartpImage;

    public shoppingCartModel(Integer orderNo,Integer cartPid, String cartpName, String cartpModel, String cartpBrand, Integer cartpAmount, Integer cartpPrice, ImageView cartpImage) {
        this.orderNo=orderNo;
        this.cartPid = cartPid;
        this.cartpName = cartpName;
        this.cartpModel = cartpModel;
        this.cartpBrand = cartpBrand;
        this.cartpAmount = cartpAmount;
        this.cartpPrice = cartpPrice;
        this.cartpImage = cartpImage;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }
    
    public Integer getCartPid() {
        return cartPid;
    }

    public void setCartPid(Integer cartPid) {
        this.cartPid = cartPid;
    }

    public String getCartpName() {
        return cartpName;
    }

    public void setCartpName(String cartpName) {
        this.cartpName = cartpName;
    }

    public String getCartpModel() {
        return cartpModel;
    }

    public void setCartpModel(String cartpModel) {
        this.cartpModel = cartpModel;
    }

    public String getCartpBrand() {
        return cartpBrand;
    }

    public void setCartpBrand(String cartpBrand) {
        this.cartpBrand = cartpBrand;
    }

    public Integer getCartpAmount() {
        return cartpAmount;
    }

    public void setCartpAmount(Integer cartpAmount) {
        this.cartpAmount = cartpAmount;
    }

    public Integer getCartpPrice() {
        return cartpPrice;
    }

    public void setCartpPrice(Integer cartpPrice) {
        this.cartpPrice = cartpPrice;
    }

    public ImageView getCartpImage() {
        return cartpImage;
    }

    public void setCartpImage(ImageView cartpImage) {
        this.cartpImage = cartpImage;
    }

    

    




    
    
}
