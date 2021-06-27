/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toan.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import toan.cart.TblCartDAO;
import toan.cart.TblCartDTO;

/**
 *
 * @author toann
 */
@WebServlet(name = "RemoveCarServlet", urlPatterns = {"/RemoveCarServlet"})
public class RemoveCarServlet extends HttpServlet {

    public final String CART_PAGE = "cart.jsp";
    public final String Error_PAGE = "Error.html";

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
        String url = CART_PAGE;
        try {

            HttpSession session = request.getSession(false);
            if (session != null) {
                TblCartDAO cart = (TblCartDAO) session.getAttribute("CART");
                if (cart != null) {
                    List<TblCartDTO> item = cart.getIcons();
                    if (item != null) {
                        String removeItem = request.getParameter("txtproductID");
                        System.out.println(removeItem);
                        if (removeItem != null) {

                            cart.removeBookFromCart(removeItem);

                            session.setAttribute("CART", cart);
                        }
                    }

                }
                System.out.println("hello");
            }
        } catch (Exception e) {
            log("RemoveCarServlet_ERROR" + e.getMessage());
            
            url = CART_PAGE;
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
