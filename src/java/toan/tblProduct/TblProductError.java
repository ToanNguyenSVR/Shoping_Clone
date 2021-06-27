/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toan.tblProduct;

/**
 *
 * @author toann
 */
public class TblProductError {
    private  String productUpdateNullError  ;
    private  String productCreateNullError  ;
    private  String productDuplicate  ;

    public TblProductError() {
    }

    public String getProductUpdateNullError() {
        return productUpdateNullError;
    }

    public void setProductUpdateNullError(String productUpdateNullError) {
        this.productUpdateNullError = productUpdateNullError;
    }

    public String getProductCreateNullError() {
        return productCreateNullError;
    }

    public void setProductCreateNullError(String productCreateNullError) {
        this.productCreateNullError = productCreateNullError;
    }

    public void setProductDuplicate(String productDuplicate) {
        this.productDuplicate = productDuplicate;
    }

    public String getProductDuplicate() {
        return productDuplicate;
    }
    
            
}
