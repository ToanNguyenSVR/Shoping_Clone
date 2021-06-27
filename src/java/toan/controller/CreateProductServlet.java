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
import toan.tblProduct.TblProductDAO;
import toan.tblProduct.TblProductDTO;
import toan.tblProduct.TblProductError;

/**
 *
 * @author toann
 */
@WebServlet(name = "CreateProductServlet", urlPatterns = {"/CreateProductServlet"})
public class CreateProductServlet extends HttpServlet {

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
        String txtproductID = request.getParameter("txtproductID");
        String txtproductTitle = request.getParameter("txtproductTitle");
        String txtproductDescription = request.getParameter("txtproductDescription");
        String txtproductAuthor = request.getParameter("txtproductAuthor");
        String cbcategory = request.getParameter("cbcategory");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String txtproductPrice = request.getParameter("txtproductPrice");
        String url = CREATE_PAGE;
        TblProductError error = new TblProductError();
        try {
            String errorNull = "";
            boolean isError = false;
            if (txtproductTitle.equalsIgnoreCase("")) {
                errorNull = errorNull + " , In " + "Product Title";
                isError = true;
            }
            if (txtproductDescription.equalsIgnoreCase("")) {
                errorNull = errorNull + " In " + "Product Description";
                isError = true;
            }
            if (txtproductAuthor.equalsIgnoreCase("")) {
                errorNull = errorNull + " In " + "Product Author";
                isError = true;
            }
            if (txtproductPrice.equalsIgnoreCase("") || Float.parseFloat(txtproductPrice) <= 10) {
                errorNull = errorNull + " In " + "Product Price";
                isError = true;
            }
            if (TblProductDAO.checkDuplicateProduct(txtproductID.trim())) {
                String s = "The product ID :" + txtproductID + " have in the system !!";
                error.setProductDuplicate(s);
                isError = true;
            }
            if (!isError) {
                Float price = Float.parseFloat(txtproductPrice);

                TblProductDTO dto = new TblProductDTO(txtproductID, txtproductTitle, null, txtproductDescription, txtproductAuthor, cbcategory, null, price, quantity);
                int rs = TblProductDAO.InsertProduct(dto);
                if (rs > 0) {
                    HttpSession session = request.getSession(false);
                    List<TblProductDTO> list = TblProductDAO.loadProduct();
                    session.setAttribute("PRODUCT", list);
                    String s = "Create is Complete";
                    request.setAttribute("SUCCESS_PRODUCT", s);
                }
            } else {
                url = CREATE_PAGE;
                error.setProductCreateNullError(errorNull);
                request.setAttribute("ERROR_CREATE", error);
            }
        } catch (NamingException e) {
            log("CreateProductServlet _NamingException" + e.getMessage());
            url = ERROR_PAGE;
        } catch (NumberFormatException e) {
            log("CreateProductServlet _NumberFormatException" + e.getMessage());
            url = ERROR_PAGE;
        } catch (SQLException e) {
            log("CreateProductServlet _SQLException" + e.getMessage());

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
