 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toan.tblVoucher;

import java.io.Serializable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.naming.NamingException;

import toan.util.DBHelper;

/**
 *
 * @author toann
 */


public class TblVoucherDAO implements Serializable {

    public static int checkVoucher(String CODE) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int DiscountPersent = 0;

        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "Select DiscountPersent "
                        + "From tblVoucher "
                        + "where CODE = ?  and status = 'ACTIVE' ";
                ps = cn.prepareCall(sql);
                ps.setString(1, CODE);
                
                rs = ps.executeQuery();
                if (rs.next()) {
                    DiscountPersent = rs.getInt("DiscountPersent");
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
        return DiscountPersent;
    }
        public static TblVoucherDTO getVoucher(String CODE) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int DiscountPersent = 0;
        TblVoucherDTO dto = null ;

        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "Select DiscountPersent , voucherID "
                        + "From tblVoucher "
                        + "where CODE = ?  and status = 'ACTIVE' ";
                ps = cn.prepareCall(sql);
                ps.setString(1, CODE);
                
                rs = ps.executeQuery();
                if (rs.next()) {
                    DiscountPersent = rs.getInt("DiscountPersent");
                    String voucherID = rs.getString("voucherID");
                    dto = new TblVoucherDTO(voucherID, CODE, DiscountPersent);
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
        return dto;
    }
    public static int checkDuplicate(String ID) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int DiscountPersent = 0;

        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "Select DiscountPersent "
                        + "From tblVoucher "
                        + "where voucherID = ?  and status = 'ACTIVE' ";
                ps = cn.prepareCall(sql);
                ps.setString(1, ID);
                
                rs = ps.executeQuery();
                if (rs.next()) {
                    DiscountPersent = rs.getInt("DiscountPersent");
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
        return DiscountPersent;
    }

    public static int DeleteVoucher(String voucherID) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement ps = null;
        int rs = -1;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "Update tblVoucher"
                        + " set status = 'INACTIVE' "
                        + " where voucherID = ? ";
                ps = cn.prepareStatement(sql);

                ps.setString(1, voucherID);
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

    public static int InsertVoucher(TblVoucherDTO dto) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement ps = null;
        int rs = -1;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "insert tblVoucher \n"
                        + " values(?,?,?,'ACTIVE',?)";

                ps = cn.prepareStatement(sql);
                ps.setString(1, dto.getVoucherID());
                ps.setString(2, dto.getCODE());
                ps.setInt(3, dto.getDiscountPersent());
                ps.setString(4, getDate());
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
