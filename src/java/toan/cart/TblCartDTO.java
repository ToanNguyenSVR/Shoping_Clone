/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toan.cart;

import java.io.Serializable;

/**
 *
 * @author toann
 */
public class TblCartDTO implements Serializable{
      private String productID ;
      private String productTitle ;
      private int productQuantity;
      private int realQuantity;
      private float productPrice ;

    public TblCartDTO()  {
    }

    public TblCartDTO(String productID, String productTitle, int productQuantity, float productPrice , int realQuantity) {
        this.productID = productID;
        this.productTitle = productTitle;
        this.productQuantity = productQuantity;
        
        this.productPrice = productPrice;
        this.realQuantity = realQuantity;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductID() {
        return productID;
    }

    public int getRealQuantity() {
        return realQuantity;
    }

    @Override
    public String toString() {
        return "TblCartDTO{" + "productID=" + productID + ", productTitle=" + productTitle + ", productQuantity=" + productQuantity + ", productPrice=" + productPrice + '}';
    }

   

    public String getProductTitle() {
        return productTitle;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setRealQuantity(int realQuantity) {
        this.realQuantity = realQuantity;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }
      
      
}
