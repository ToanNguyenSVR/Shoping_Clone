/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toan.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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

/**
 *
 * @author toann
 */
@WebServlet(name = "CheckVoucher", urlPatterns = {"/CheckVoucher"})
public class CheckVoucher extends HttpServlet {
public final  String CART_PAGE = "cart.jsp";
public final String ERROR_PAGE = "Error.html";
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
        String txtCode = request.getParameter("txtCode");
        String url = CART_PAGE;
        try {
            HttpSession session = request.getSession();
            session.setAttribute("DISCOUNT", 0);
           int result =  TblVoucherDAO.checkVoucher(txtCode);
            TblVoucherDTO voucher =  TblVoucherDAO.getVoucher(txtCode);
           if(result > 0){
              
               session.setAttribute("DISCOUNT", result);
               session.setAttribute("VOUCHER", voucher);
               String s = "WOW ! you was discounted " + result + "%" ;
               request.setAttribute("SUCCESS_ADD_VOUCHER", s);
           }else{
                String s = "WOW ! Sory this voucher was used" ;
               request.setAttribute("FAIL_ADD_VOUCHER", s);
           }
        }catch (NamingException e) {
            log("CheckVoucher_NamingException" + e.getMessage());
            url = ERROR_PAGE;
        } catch (NumberFormatException e) {
            log("CheckVoucher_NumberFormatException" + e.getMessage());
            url = ERROR_PAGE;
        } catch (SQLException e) {
            log("CheckVoucher_SQLException" + e.getMessage());

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
