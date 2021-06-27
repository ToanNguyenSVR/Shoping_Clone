/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toan.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import toan.tblVoucher.TblVoucherDAO;
import toan.tblVoucher.TblVoucherDTO;
import toan.tblVoucher.TblVoucherError;

/**
 *
 * @author toann
 */
@WebServlet(name = "CreateVoucher", urlPatterns = {"/CreateVoucher"})
public class CreateVoucher extends HttpServlet {

    public final String ADMIN_PAGE = "admin.jsp";
    public final String ERROR_PAGE = "Error.html";
    public final String CREATE_PAGE = "CreateProduct.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String txtVoucherID = request.getParameter("txtVoucherID");
        String CODE = request.getParameter("CODE");
        String txtpercent = request.getParameter("txtpercent");
       
        String url = CREATE_PAGE;
        TblVoucherError error = new TblVoucherError();
        try {
            String errorNull = "";
            boolean isError = false;
            if (txtVoucherID.equalsIgnoreCase("")) {
                errorNull = errorNull + " , In " + "Voucher ID";
                isError = true;
            }
            if (txtpercent.equalsIgnoreCase("")) {
                errorNull = errorNull + " In " + "Voucher Discount Percent";
                isError = true;
            }
            if (CODE.equalsIgnoreCase("")) {
                errorNull = errorNull + " In " + "Voucher code";
                isError = true;
            }
            
           
            if (TblVoucherDAO.checkVoucher(CODE) > 0) {
                String s = "The Code :" + CODE + " have in the system !!";
                error.setVoucherDuplicate(s);
                isError = true;
            }
            if (TblVoucherDAO.checkDuplicate(txtVoucherID) > 0) {
                String s = "The Code :" + txtVoucherID + " have in the system !!";
                error.setVoucherDuplicate(s);
                isError = true;
            }
            if (!isError) {
                int DiscountPercent = Integer.parseInt(txtpercent);
                TblVoucherDTO dto = new TblVoucherDTO(txtVoucherID, CODE, DiscountPercent);
                int rs = TblVoucherDAO.InsertVoucher(dto);
                if (rs > 0) {
                    String s  = "Create is Complete";
                    request.setAttribute("SUCCESS_VOUCHER", s);
                }
            } else {
                url = CREATE_PAGE;
                error.setVoucherCreateNullError(errorNull);
                request.setAttribute("ERROR_CREATE_VOUCHER", error);
            }
        } catch (NamingException e) {
            log("CreateVoucher_NamingException" + e.getMessage());
            url = ERROR_PAGE;
        } catch (NumberFormatException e) {
            log("CreateVoucher_NumberFormatException" + e.getMessage());
            url = ERROR_PAGE;
        } catch (SQLException e) {
            log("CreateVoucher_SQLException" + e.getMessage());
            url = ERROR_PAGE;
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
