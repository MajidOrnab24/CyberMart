/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cybermart;

import javafx.scene.image.ImageView;
import org.controlsfx.control.Rating;

/**
 *
 * @author majid
 */
public class productSearchModel {
    
    private int productID,price,stock;
    private double rating;
    private  String productName,productModel,brandofProduct,p_description;
    private  ImageView imageProduct;
    private Rating productRating;
    private String productCategory;
    private String imageUrl;
    public productSearchModel(int productID,String productName,String brandofProduct,String productModel, int price, int stock, double rating,   ImageView imageProduct,String p_description,Rating productRating,String productCategory,String imageUrl) {
        this.productID = productID;
        this.price = price;
        this.stock = stock;
        this.rating = rating;
        this.productName = productName;
        this.productModel = productModel;
        this.brandofProduct = brandofProduct;
        this.imageProduct=imageProduct;
        this.p_description=p_description;
        this.productRating=productRating;
        this.productCategory=productCategory;
        this.imageUrl= imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
    
    public Rating getProductRating() {
        return productRating;
    }

    public void setProductRating(Rating productRating) {
        this.productRating = productRating;
    }
    
    public int getProductID() {
        return productID;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public double getRating() {
        return rating;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductModel() {
        return productModel;
    }

    public String getBrandofProduct() {
        return brandofProduct;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public void setBrandofProduct(String brandofProduct) {
        this.brandofProduct = brandofProduct;
    }

    public ImageView getImageProduct() {
        return imageProduct;
    }

    public void setImageProduct(ImageView imageProduct) {
        this.imageProduct = imageProduct;
    }

    public String getP_description() {
        return p_description;
    }

    public void setP_description(String p_description) {
        this.p_description = p_description;
    }
    
            
}
