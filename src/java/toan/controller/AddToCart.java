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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import toan.cart.TblCartDAO;
import toan.cart.TblCartDTO;
import toan.tblProduct.TblProductDAO;
import toan.tblProduct.TblProductDTO;

/**
 *
 * @author toann
 */
@WebServlet(name = "AddToCart", urlPatterns = {"/AddToCart"})
public class AddToCart extends HttpServlet {
public  final String CART_PAGE = "cart.jsp";
public  final String Error_PAGE = "Eror.html";
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

        String searchValue = request.getParameter("txtsearch");
        String productID = request.getParameter("txtproductID");
        String productTitle = request.getParameter("txtproductTitle");
        String productPrice = request.getParameter("txtproductPrice");
        String txtQuantity = request.getParameter("txtQuantity");
        String category = request.getParameter("cbCatergory");
        String priceMin = request.getParameter("priceMin");
        String priceMax = request.getParameter("priceMax");
        String url = "DispatcherServlet"
                + "?txtsearch=" + searchValue
                +"&priceMin"+priceMin
                +"&priceMax"+priceMax
                + "&btnAction=search";
        System.out.println("cos vao day ko ");
            try {
                HttpSession session = request.getSession();
                TblCartDAO cart = (TblCartDAO) session.getAttribute("CART");
                if (cart == null) {
                    cart = new TblCartDAO();

                }
                String title = request.getParameter("ValueProduct");
                TblCartDTO dto = new TblCartDTO(productID,productTitle, Integer.parseInt(txtQuantity) , Float.parseFloat(productPrice) , TblProductDAO.getQuantity(productID));
                boolean update = cart.addBookToCart(dto);
                
                session.setAttribute("CART", cart);
                if(update){
                    url = CART_PAGE;
                }
            }catch(NumberFormatException e){
                log("AddToCart_NumberFormatException" + "  "+ e.getMessage());
            }catch(NamingException e){
                log("NamingException_NumberFormatException" + "  "+ e.getMessage());
            }catch(SQLException e){
                log("SQLException_NumberFormatException" + "  "+ e.getMessage());
            } 
            finally {
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
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response)
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
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>

    }
