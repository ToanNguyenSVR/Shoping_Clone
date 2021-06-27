/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toan.tblOrder;

import java.io.Serializable;

/**
 *
 * @author toann
 */
public class TblOrderDTO  implements Serializable{
    private String orderID ;

    private String userID ;
    private String dateCreate;
    private float totalPrice;
    private String  voucherID;
    private String  PaymentID;
    private String  address;
    private String  postCode;
    private String  Note;

    public TblOrderDTO(String orderID, String userID, String dateCreate, float totalPrice, String voucherID, String PaymentID, String address, String postCode, String Note) {
        this.orderID = orderID;
       
        this.userID = userID;
        this.dateCreate = dateCreate;
        this.totalPrice = totalPrice;
        this.voucherID = voucherID;
        this.PaymentID = PaymentID;
        this.address = address;
        this.postCode = postCode;
        this.Note = Note;
    }

    public String getOrderID() {
        return orderID;
    }

    

    public String getUserID() {
        return userID;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public String getVoucherID() {
        return voucherID;
    }

    public String getPaymentID() {
        return PaymentID;
    }

    public String getAddress() {
        return address;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getNote() {
        return Note;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setVoucherID(String voucherID) {
        this.voucherID = voucherID;
    }

    public void setPaymentID(String PaymentID) {
        this.PaymentID = PaymentID;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }

   
}
