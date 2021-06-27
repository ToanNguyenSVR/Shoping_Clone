/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toan.tblProduct;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;
import toan.util.DBHelper;

/**
 *
 * @author toann
 */
public class TblProductDAO implements Serializable {

    public static List<TblProductDTO> loadProduct() throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        TblProductDTO dto;
        List<TblProductDTO> list = new ArrayList<>();
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "Select  productID , productTitle , productimg , productDescription ,productAuthor ,categoryID , productPrice , productStatus  ,productQuantity\n"
                        + "From tblProduct \n"
                        + "where productStatus = 'Active'";
                ps = cn.prepareCall(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productTitle = rs.getString("productTitle");
                    String productimg = rs.getString("productimg");
                    String productDescription = rs.getString("productDescription");
                    String productAuthor = rs.getString("productAuthor");
                    String categoryID = rs.getString("categoryID");
                    Float productPrice = rs.getFloat("productPrice");
                    String productStatus = rs.getString("productStatus");
                    int productQuantity = rs.getInt("productQuantity");
                    dto = new TblProductDTO(productID, productTitle, productimg, productDescription, productAuthor, categoryID, productStatus, productPrice, productQuantity);
                    list.add(dto);

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
        return list;
    }

    public static boolean checkDuplicateProduct(String id) throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement ps = null;
        boolean rs = false;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "Select productID "
                        + "From tblProduct "
                        + "where productID = ?";
                ps = cn.prepareCall(sql);
                ps.setString(1, id);
                ResultSet result = ps.executeQuery();
                while (result.next()) {
                    rs = true;
                }
            }
        } finally {

            if (ps != null) {
                ps.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return rs;
    }

    public static List<TblProductDTO> getProductByName(String name) throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        TblProductDTO dto;
        List<TblProductDTO> list = new ArrayList<>();
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "Select productID , productTitle , productimg , productDescription , productAuthor , categoryID , productPrice , productStatus  , productQuantity "
                        + "From tblProduct "
                        + "where productStatus = 'ACTIVE'  and productTitle like ? ";
                ps = cn.prepareCall(sql);
                ps.setString(1, '%' + name + '%');
                rs = ps.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productTitle = rs.getString("productTitle");
                    String productimg = rs.getString("productimg");
                    String productDescription = rs.getString("productDescription");
                    String productAuthor = rs.getString("productAuthor");
                    String categoryID = rs.getString("categoryID");
                    Float productPrice = rs.getFloat("productPrice");
                    String productStatus = rs.getString("productStatus");
                    int productQuantity = rs.getInt("productQuantity");
                    dto = new TblProductDTO(productID, productTitle, productimg, productDescription, productAuthor, categoryID, productStatus, productPrice, productQuantity);
                    list.add(dto);
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
        return list;
    }

    public static List<TblProductDTO> getProductByName(String titile, String category, float priceMin, float priceMax) throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        TblProductDTO dto;
        List<TblProductDTO> list = new ArrayList<>();
        if (titile == null) {
            titile = "";
        }
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "Select productID , productTitle , productimg , productDescription ,productAuthor , categoryID, productPrice , productStatus "
                        + "From tblProduct "
                        + "where productStatus = 'ACTIVE'  and productTitle like ?  and categoryID = ?  and  productPrice > ? and  productPrice < ?   ";
                ps = cn.prepareCall(sql);

                ps.setString(1, '%' + titile + '%');
                ps.setString(2, category);
                ps.setFloat(3, priceMin);
                ps.setFloat(4, priceMax);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productTitle = rs.getString("productTitle");
                    String productimg = rs.getString("productimg");
                    String productDescription = rs.getString("productDescription");
                    String productAuthor = rs.getString("productAuthor");
                    String categoryID = rs.getString("categoryID");
                    Float productPrice = rs.getFloat("productPrice");
                    String productStatus = rs.getString("productStatus");
                    int productQuantity = rs.getInt("productQuantity");
                    dto = new TblProductDTO(productID, productTitle, productimg, productDescription, productAuthor, categoryID, productStatus, productPrice, productQuantity);
                    list.add(dto);
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
        return list;
    }
    public static int getQuantity(String productID) throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int productQuantity = 0 ;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "Select productQuantity "
                        + "From tblProduct "
                        + "where productID = ?  ";
                ps = cn.prepareCall(sql);

                ps.setString(1, productID.trim());
                rs = ps.executeQuery();
                if(rs.next()){
                   productQuantity  = rs.getInt("productQuantity");
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
        return productQuantity;
    }
    public static int UpdateProduct(int quantity , String productID) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement ps = null;
        int rs = -1;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "Update tblProduct "
                        + " set productQuantity = ?  "
                        + " where productID = ? ";
                ps = cn.prepareStatement(sql);
                ps.setInt(1, quantity);
                ps.setString(2, productID.trim());
                rs = ps.executeUpdate();
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (cn != null) {
                cn.close();

            }
        }
        return rs;
    }

    public static int UpdateProduct(TblProductDTO dto) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement ps = null;
        int rs = -1;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "Update tblProduct "
                        + " set productTitle = ? , productDescription = ?  , productAuthor = ? , categoryID = ? , productPrice = ? , productQuantity = ?  "
                        + " where productID = ? ";
                ps = cn.prepareStatement(sql);
                System.out.println(dto.toString());
                ps.setString(1, dto.getProductTitle().trim());
                ps.setString(2, dto.getProductDescription().trim());
                ps.setString(3, dto.getProductAuthor().trim());
                ps.setString(4, dto.getCatergoryID().trim().toUpperCase());
                ps.setFloat(5, dto.getProductPrice());
                ps.setInt(6, dto.getProductQuantity());
                ps.setString(7, dto.getProductID().trim());
                rs = ps.executeUpdate();

            }

        } finally {
            if (ps != null) {
                ps.close();
            }
            if (cn != null) {
                cn.close();

            }
        }
        return rs;
    }

    public static String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String date_now = formatter.format(date);
        return date_now;
    }

    public static int InsertProduct(TblProductDTO dto) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement ps = null;
        int rs = -1;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "insert tblProduct \n"
                        + " values( ? , ? , NULL , ? ,? , ? ,?,'ACTIVE' ,?  , ?)";

                ps = cn.prepareStatement(sql);
                ps.setString(1, dto.getProductID().trim());
                ps.setString(2, dto.getProductTitle().trim());
                ps.setString(3, dto.getProductDescription().trim());
                ps.setString(4, dto.getProductAuthor().trim());
                ps.setString(5, dto.getCatergoryID().trim().toUpperCase());
                ps.setFloat(6, dto.getProductPrice());
                ps.setInt(7, dto.getProductQuantity());
                ps.setString(8, getDate());
                rs = ps.executeUpdate();

            }

        } finally {
            if (ps != null) {
                ps.close();
            }
            if (cn != null) {
                cn.close();

            }
        }
        return rs;
    }

    public static int DeleteProduct(String productID) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement ps = null;
        int rs = -1;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "Update tblProduct"
                        + " set productStatus = 'INACTIVE' "
                        + " where productID = ? ";
                ps = cn.prepareStatement(sql);

                ps.setString(1, productID);
                rs = ps.executeUpdate();
            }

        } finally {
            if (ps != null) {
                ps.close();
            }
            if (cn != null) {
                cn.close();

            }
        }
        return rs;
    }
}
