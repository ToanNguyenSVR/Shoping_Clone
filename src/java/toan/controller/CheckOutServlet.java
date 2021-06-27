/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toan.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Random;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import toan.cart.TblCartDAO;
import toan.tblOrder.TblOrderDAO;
import toan.tblOrder.TblOrderDTO;
import toan.tblOrderDetail.TblOrderDetailDAO;
import toan.tblOrderDetail.TblOrderDetailDTO;
import toan.tblProduct.TblProductDAO;
import toan.tblUser.TblUserDTO;
import toan.tblVoucher.TblVoucherDAO;
import toan.tblVoucher.TblVoucherDTO;

/**
 *
 * @author toann
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {

    public final String ADMIN_PAGE = "admin.jsp";
    public final String HOME_PAGE = "home.jsp";
    public final String ERROR_PAGE = "Error.html";
    public final String StatUpServlet = "StatUpServlet";

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
        String Address = request.getParameter("txtaddress");
        String post_code = request.getParameter("shipping_postcode");
        String order_comments = request.getParameter("order_comments");
        String voucherID = request.getParameter("txtvoucherID");
        String discount = request.getParameter("discount");
        HttpSession session = request.getSession(false);
        String url = StatUpServlet;
        Random rand = new Random();
        int upperbound = 1000;

        int OrderID = rand.nextInt(upperbound);
        float totalPrice = 0;
        try {

            TblCartDAO cart = (TblCartDAO) session.getAttribute("CART");
            TblUserDTO user = (TblUserDTO) session.getAttribute("USER");

            int rs_order = 0;
            int rs_detail = 0;
            for (int i = 0; i < cart.getIcons().size(); i++) {
                totalPrice = totalPrice + cart.getIcons().get(i).getProductQuantity() * cart.getIcons().get(i).getProductPrice();
            }

            if (!discount.equals("")) {
                float discount_value = Integer.parseInt(discount);
                totalPrice = totalPrice - totalPrice * discount_value / 100;
                TblOrderDTO orderdto = new TblOrderDTO("" + OrderID, user.getUserID(), null, totalPrice, voucherID, null, Address, post_code, order_comments);
                rs_order = TblOrderDAO.InsertOrder(orderdto);
                TblVoucherDAO.DeleteVoucher(voucherID);

            } else {
                totalPrice = totalPrice;
                TblOrderDTO orderdto = new TblOrderDTO("" + OrderID, user.getUserID(), null, totalPrice, null, null, Address, post_code, order_comments);
                rs_order = TblOrderDAO.InsertOrder(orderdto);
            }
            for (int i = 0; i < cart.getIcons().size(); i++) {
                TblOrderDetailDTO orderDetaildto = new TblOrderDetailDTO(cart.getIcons().get(i).getProductID(), cart.getIcons().get(i).getProductQuantity(), cart.getIcons().get(i).getProductPrice(), "" + OrderID);
                rs_detail = TblOrderDetailDAO.InsertOrder(orderDetaildto);
                // update quantity of product
                int realQuantity = TblProductDAO.getQuantity(cart.getIcons().get(i).getProductID()) - cart.getIcons().get(i).getProductQuantity();
                TblProductDAO.UpdateProduct(realQuantity, cart.getIcons().get(i).getProductID());
            }
            if (rs_detail > 0 && rs_order > 0) {
                String s = "Your Order is Success ";
                request.setAttribute("SUCCESS_ORDER", s);
                session.setAttribute("CART", null);
                session.setAttribute("DISCOUNT", null);

            }
        } catch (NamingException e) {
            log("CheckOutServlet_NamingException" + e.getMessage());
            url = ERROR_PAGE;
        } catch (SQLException e) {
            e.printStackTrace();
            log("CheckOutServlet_SQLException" + e.getMessage());
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
