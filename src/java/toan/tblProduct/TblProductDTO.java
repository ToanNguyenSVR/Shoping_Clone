/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toan.tblProduct;

import java.io.Serializable;

/**
 *
 * @author toann
 */
public class TblProductDTO implements Serializable{
    private String productID;
    private String productTitle;
    private String productImg;
    private String productDescription;
    private String productAuthor;
    private String catergoryID;
    private String productStatus;
    private float productPrice;
    private int productQuantity;

    public TblProductDTO(String productID, String productTitle, String productImg, String productDescription, String productAuthor, String catergoryID, String productStatus, float productPrice, int productQuatity) {
        this.productID = productID;
        this.productTitle = productTitle;
        this.productImg = productImg;
        this.productDescription = productDescription;
        this.productAuthor = productAuthor;
        this.catergoryID = catergoryID;
        this.productStatus = productStatus;
        this.productPrice = productPrice;
        this.productQuantity = productQuatity;
    }

    

    @Override
    public String toString() {
        return "TblProductDTO{" + "productID=" + productID + ", productTitle=" + productTitle + ", productImg=" + productImg + ", productDescription=" + productDescription + ", productAuthor=" + productAuthor + ", catergoryID=" + catergoryID + ", productStatus=" + productStatus + ", productPrice=" + productPrice + '}';
    }


    public TblProductDTO() {
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

   

    public String getProductID() {
        return productID;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public String getProductImg() {
        return productImg;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public String getProductAuthor() {
        return productAuthor;
    }

    public String getCatergoryID() {
        return catergoryID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setProductAuthor(String productAuthor) {
        this.productAuthor = productAuthor;
    }

    public void setCatergoryID(String catergoryID) {
        this.catergoryID = catergoryID;
    }

    public String getProductStatus() {
        return productStatus;
    }

   

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }
   
}
