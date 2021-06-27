/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toan.tblVoucher;

import java.io.Serializable;

/**
 *
 * @author toann
 */
public class TblVoucherDTO implements Serializable{
    private String voucherID ;
    private String CODE ;
    private int DiscountPersent ;

    public TblVoucherDTO(String voucherID, String CODE, int DiscountPersent) {
        this.voucherID = voucherID;
        this.CODE = CODE;
        this.DiscountPersent = DiscountPersent;
    }

    public String getVoucherID() {
        return voucherID;
    }

    public String getCODE() {
        return CODE;
    }

    public int getDiscountPersent() {
        return DiscountPersent;
    }

    public void setVoucherID(String voucherID) {
        this.voucherID = voucherID;
    }

    public void setCODE(String CODE) {
        this.CODE = CODE;
    }

    public void setDiscountPersent(int DiscountPersent) {
        this.DiscountPersent = DiscountPersent;
    }

    @Override
    public String toString() {
        return "TblVoucherDTO{" + "voucherID=" + voucherID + ", CODE=" + CODE + ", DiscountPersent=" + DiscountPersent + '}';
    }
    
    
}
