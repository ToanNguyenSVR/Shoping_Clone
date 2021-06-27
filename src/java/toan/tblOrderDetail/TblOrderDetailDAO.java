/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toan.tblOrderDetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import static toan.tblOrder.TblOrderDAO.getDate;
import toan.tblOrder.TblOrderDTO;
import toan.util.DBHelper;

/**
 *
 * @author toann
 */
public class TblOrderDetailDAO implements Serializable {

    public static int InsertOrder(TblOrderDetailDTO dto) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement ps = null;
        int rs = -1;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "insert tblOrderDetail \n"
                        + " values( ? ,?  , ? ,? )";

                ps = cn.prepareStatement(sql);

                ps.setString(1, dto.getProductID().trim());
                ps.setInt(2, dto.getQuantity());

                ps.setFloat(3, dto.getPrice());
                ps.setString(4, dto.getOrderID().trim());
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

    public static List<TblOrderDetailDTO> getOrderHistory(String orderID) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TblOrderDetailDTO> list = new ArrayList<>();
        TblOrderDetailDTO dto;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = " SELECT TOP 1000 \n"
                        + "      [productID]\n"
                        + "      ,[Quantity]\n"
                        + "      ,[price]\n"
                        + "  FROM [Tiki_Clone.vn].[dbo].[tblOrderDetail] "
                        + " Where [orderID] = ? ";
                ps = cn.prepareStatement(sql);
                ps.setString(1, orderID.trim());
                rs = ps.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    int Quantity = rs.getInt("Quantity");
                    Float price = rs.getFloat("price");
                    dto = new TblOrderDetailDTO(productID, Quantity, price, orderID);
                    list.add(dto);
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
        return list;
    }
}
