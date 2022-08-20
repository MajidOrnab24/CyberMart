/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cybermart;

/**
 *
 * @author majid
 */
public class dashboardObject {
    private int d_cus_id,d_prdct_id,d_id,d_quantity;
    private String d_name,d_address,d_time;
    private double d_price;

    public dashboardObject(int d_cus_id, int d_prdct_id, int d_id, int d_quantity, String d_name, String d_address, String d_time, double d_price) {
        this.d_cus_id = d_cus_id;
        this.d_prdct_id = d_prdct_id;
        this.d_id = d_id;
        this.d_quantity = d_quantity;
        this.d_name = d_name;
        this.d_address = d_address;
        this.d_time = d_time;
        this.d_price = d_price*.15+d_price;
    }
   
    public int getD_cus_id() {
        return d_cus_id;
    }

    public void setD_cus_id(int d_cus_id) {
        this.d_cus_id = d_cus_id;
    }

    public int getD_prdct_id() {
        return d_prdct_id;
    }

    public void setD_prdct_id(int d_prdct_id) {
        this.d_prdct_id = d_prdct_id;
    }

    public int getD_id() {
        return d_id;
    }

    public void setD_id(int d_id) {
        this.d_id = d_id;
    }

    public int getD_quantity() {
        return d_quantity;
    }

    public void setD_quantity(int d_quantity) {
        this.d_quantity = d_quantity;
    }

    public String getD_name() {
        return d_name;
    }

    public void setD_name(String d_name) {
        this.d_name = d_name;
    }

    public String getD_address() {
        return d_address;
    }

    public void setD_address(String d_address) {
        this.d_address = d_address;
    }

    public String getD_time() {
        return d_time;
    }

    public void setD_time(String d_time) {
        this.d_time = d_time;
    }

    public double getD_price() {
        return d_price;
    }

    public void setD_price(double d_price) {
        this.d_price = d_price;
    }
    
    
    
    
}
