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
public class TblVoucherError implements Serializable{

    public TblVoucherError() {
    }
  
    private  String voucherCreateNullError  ;
    private  String voucherDuplicate  ;
    private  String CODEDuplicate  ;

    public void setCODEDuplicate(String CODEDuplicate) {
        this.CODEDuplicate = CODEDuplicate;
    }

    public String getCODEDuplicate() {
        return CODEDuplicate;
    }

    public void setVoucherCreateNullError(String voucherCreateNullError) {
        this.voucherCreateNullError = voucherCreateNullError;
    }

    public void setVoucherDuplicate(String voucherDuplicate) {
        this.voucherDuplicate = voucherDuplicate;
    }

    public String getVoucherCreateNullError() {
        return voucherCreateNullError;
    }

    public String getVoucherDuplicate() {
        return voucherDuplicate;
    }
    
}
