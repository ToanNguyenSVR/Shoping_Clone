/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toan.tblCatergory;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

import toan.util.DBHelper;

/**
 *
 * @author toann
 */
public class TblCategoryDAO implements Serializable {

    public static List<TblCatergoryDTO> loadCategory() throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        TblCatergoryDTO dto;
        List<TblCatergoryDTO> list = new ArrayList<>();
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "Select categoryID , categoryName  ,createDate "
                        + "From tblCategory ";
                ps = cn.prepareCall(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String categoryID = rs.getString("categoryID");
                    String categoryName = rs.getString("categoryName");
                    String createDate = "" + rs.getDate("createDate");
                    dto = new TblCatergoryDTO(categoryID, categoryName, createDate);
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

}
