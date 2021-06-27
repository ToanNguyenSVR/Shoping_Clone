/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toan.tblUser;

import java.io.Serializable;

/**
 *
 * @author toann
 */
public class TblUserDTO implements Serializable{
    private String userID; 
    private String userPass; 
    private String userName; 
    private String userRole; 
    private boolean status; 

    public TblUserDTO() {
    }

    public TblUserDTO(String userID, String userPass, String userName, String userRole, boolean status) {
        this.userID = userID;
        this.userPass = userPass;
        this.userName = userName;
        this.userRole = userRole;
        this.status = status;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserPass() {
        return userPass;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserRole() {
        return userRole;
    }

    public boolean isStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "TblUserDTO{" + "userID=" + userID + ", userPass=" + userPass + ", userName=" + userName + ", userRole=" + userRole + ", status=" + status + '}';
    }
    
    
}
