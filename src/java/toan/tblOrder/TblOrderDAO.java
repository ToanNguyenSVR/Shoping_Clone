/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toan.tblOrder;

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
public class TblOrderDAO implements Serializable {

    public static String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String date_now = formatter.format(date);
        return date_now;
    }

    public static List<TblOrderDTO> getOrderHistory(String UerID) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
         List<TblOrderDTO> list = new ArrayList<>() ;
         TblOrderDTO dto ;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = " SELECT TOP 1000 [orderID]\n"
                        + "      ,[userID]\n"
                        + "      ,[dateCreate]\n"
                        + "      ,[totalPrice]\n"
                        + "      ,[voucherID]\n"
                        + "      ,[paymentID]\n"
                        + "      ,[address]\n"
                        + "      ,[postCode]\n"
                        + "      ,[Note]\n"
                        + "  FROM [Tiki_Clone.vn].[dbo].[tblOrder_v1]  "
                        + " Where [userID] = ? ";
                    ps = cn.prepareStatement(sql);
                ps.setString(1, UerID.trim());
                rs= ps.executeQuery();
                while(rs.next()){
                    String orderID = rs.getString("orderID");
                    String userID = rs.getString("userID");
                    String dateCreate = rs.getString("dateCreate");
                    Float totalPrice = rs.getFloat("totalPrice");
                    String voucherID = rs.getString("voucherID");
                    String paymentID = rs.getString("paymentID");
                    String address = rs.getString("address");
                    String postCode = rs.getString("postCode");
                    String Note = rs.getString("Note");
                    dto = new TblOrderDTO(orderID, userID, dateCreate, totalPrice, voucherID, paymentID, address, postCode, Note);
                    list.add(dto);
                }
            }
        }finally {
            if (ps != null) {
                ps.close();
            }
            if (cn != null) {
                cn.close();

            }
        }
        return list; 
    }

    public static int InsertOrder(TblOrderDTO dto) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement ps = null;
        int rs = -1;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "insert tblOrder_v1 \n"
                        + " values(  ? , ? , ? ,? , ? , ? , ? ,?  , ? )";

                ps = cn.prepareStatement(sql);
                ps.setString(1, dto.getOrderID().trim());

                ps.setString(2, dto.getUserID().trim());
                ps.setString(3, getDate());
                ps.setFloat(4, dto.getTotalPrice());
                ps.setString(5, dto.getVoucherID());
                ps.setString(6, dto.getPaymentID());
                ps.setString(7, dto.getAddress());
                ps.setString(8, dto.getPostCode());
                ps.setString(9, dto.getNote());
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
