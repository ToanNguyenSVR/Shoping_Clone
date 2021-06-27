/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toan.tblOrderDetail;

import java.io.Serializable;

/**
 *
 * @author toann
 */
public class TblOrderDetailDTO implements Serializable{
   
    private String productID ;
    private int quantity ;
    private float price ;
    private String orderID ;

    public TblOrderDetailDTO( String productID, int quantity, float price, String orderID) {
  
        this.productID = productID;
        this.quantity = quantity;
        this.price = price;
        this.orderID = orderID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    

    

    public String getProductID() {
        return productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }

    

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
}
