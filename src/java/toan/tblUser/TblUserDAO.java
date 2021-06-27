/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toan.tblUser;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.naming.NamingException;
import toan.util.DBHelper;

/**
 *
 * @author toann
 */
public class TblUserDAO implements Serializable {

    public static TblUserDTO checkLogin(String ID, String passWord) throws SQLException, NamingException, NoSuchAlgorithmException, IOException {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        TblUserDTO dto = null;
        passWord = tranferPassword(passWord);
        InputStream inputStream = null;

        try {
            if (ID == null || passWord == null) {
                return null;
            }
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "Select userID , userPassword , userName , role , statusUser "
                        + " From tblUser "
                        + " Where userID = ? and userPassword = ? ";
                ps = cn.prepareStatement(sql);
                ps.setString(1, ID);
                ps.setString(2, passWord);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String userID = rs.getString("userID");
                    String userPassword = rs.getString("userPassword");
                    String fullname = rs.getString("userName");
                    String role = rs.getString("role");
                    boolean statusUser = rs.getBoolean("statusUser");
//                    Blob blob = rs.getBlob("img_data");

                    

//                    inputStream = blob.getBinaryStream();
//                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//                    byte[] buffer = new byte[4096];
//                    int bytesRead = -1;
//
//                    while ((bytesRead = inputStream.read(buffer)) != -1) {
//                        outputStream.write(buffer, 0, bytesRead);
//                    }
//
//                    byte[] imageBytes = outputStream.toByteArray();
//                    String image_Name = Base64.getEncoder().encodeToString(imageBytes);

                    dto = new TblUserDTO(userID, userPassword, fullname, role, statusUser);            
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (cn != null) {
                cn.close();
            }

        }
        return dto;
    }

    private static String tranferPassword(String password) throws NoSuchAlgorithmException {
        String resultPass = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            resultPass = Base64.getEncoder().encodeToString(hash);
        } finally {
            return resultPass;
        }
    }
    


}
