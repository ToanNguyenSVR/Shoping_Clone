/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toan.tblCatergory;

import java.io.Serializable;

/**
 *
 * @author toann
 */
public class TblCatergoryDTO  implements Serializable{

    private String categoryID;
    private String categoryName;
    private String createDate;

    public TblCatergoryDTO(String categoryID, String categoryName, String createDate) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.createDate = createDate;
    }

    public TblCatergoryDTO() {
    }

    public String getCategoryID() {
        return categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    
}
