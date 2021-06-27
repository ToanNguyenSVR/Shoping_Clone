/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toan.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import toan.tblOrder.TblOrderDAO;
import toan.tblOrder.TblOrderDTO;
import toan.tblOrderDetail.TblOrderDetailDAO;
import toan.tblOrderDetail.TblOrderDetailDTO;

/**
 *
 * @author toann
 */
@WebServlet(name = "LoadHistoryServlet", urlPatterns = {"/LoadHistoryServlet"})
public class LoadHistoryServlet extends HttpServlet {

    public final String HISTORY_PAGE = "history.jsp";
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
        String txtUserId = request.getParameter("txtuserID");
        String url = HISTORY_PAGE;
        List<List<TblOrderDetailDTO>> listp = new ArrayList<>();
        try {
            List<TblOrderDTO> list = TblOrderDAO.getOrderHistory(txtUserId);
            if (list != null) {

                for (TblOrderDTO tblOrderDTO : list) {
                    List<TblOrderDetailDTO> listPro = TblOrderDetailDAO.getOrderHistory(tblOrderDTO.getOrderID());
                    listp.add(listPro);
                }
            }
            if (listp.size() > 0) {
                HttpSession session = request.getSession(false);
                if(session!= null){
                    session.setAttribute("HISTORYDATA", list);
                    session.setAttribute("HISTORYPRODUCT", listp);
                }
                
            }
        } catch (NamingException e) {
            log("LoadHistoryServlet_NamingException" + e.getMessage());
            url = ERROR_PAGE;
        } catch (SQLException e) {
            log("LoadHistoryServlet_SQLException" + e.getMessage());
            url = ERROR_PAGE;
        } finally {
            response.sendRedirect(url);
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
