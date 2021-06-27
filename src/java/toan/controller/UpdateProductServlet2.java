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
import toan.tblCatergory.TblCategoryDAO;
import toan.tblProduct.TblProductDAO;
import toan.tblProduct.TblProductDTO;
import toan.tblProduct.TblProductError;

/**
 *
 * @author toann
 */
@WebServlet(name = "UpdateProductServlet2", urlPatterns = {"/UpdateProductServlet2"})
public class UpdateProductServlet2 extends HttpServlet {

    public final String ADMIN_PAGE = "admin.jsp";
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
        String txtproductID = request.getParameter("txtproductID");
        
        String txtproductTitle = request.getParameter("txtproductTitle");
        String txtproductDescription = request.getParameter("txtproductDescription");
        String txtproductAuthor = request.getParameter("txtproductAuthor");
        String cbcategory = request.getParameter("cbcategory");
        String quantity = request.getParameter("quantity");
        String txtproductPrice = request.getParameter("txtproductPrice");
        String url = ADMIN_PAGE;
        
        try {
            String errorNull = "";
            boolean isError = false;
            if (txtproductTitle.equalsIgnoreCase("")) {
                errorNull = errorNull +" => "+ "Product Title";
                isError = true;
            }
            if (txtproductDescription.equalsIgnoreCase("")) {
                errorNull = errorNull +" => "+ "Product Description";
                isError = true;
            }
            if (txtproductAuthor.equalsIgnoreCase("")) {
                errorNull = errorNull +" =>  "+ "Product Author";
                isError = true;
            }
            if (txtproductPrice.equalsIgnoreCase("") || Float.parseFloat(txtproductPrice) <= 10  ) {
                errorNull = errorNull +" => "+ "ProductPrice";
                isError = true;
            }
            if (!isError) {
                
                int productQuantity = Integer.parseInt(quantity);
                Float productPrice = Float.parseFloat(txtproductPrice);

                TblProductDTO dto = new TblProductDTO(txtproductID, txtproductTitle, null, txtproductDescription, txtproductAuthor, cbcategory, null , productPrice, productQuantity);

                int rs = TblProductDAO.UpdateProduct(dto);
                if (rs > 0) {
                    HttpSession session = request.getSession(false);
                    List<TblProductDTO> list = TblProductDAO.loadProduct() ;
                    session.setAttribute("PRODUCT",list );
                    String s  = "Create is Complete";
                    request.setAttribute("SUCCESS_UPDATE", s);
                  
                }

            } else {
                
                TblProductError error = new TblProductError();
                error.setProductUpdateNullError(errorNull);
                request.setAttribute("ERROR_UPDATE", error );
            }

        } catch (NamingException e) {
            log("UpdateProductServlet2_NamingException" + e.getMessage());
            
            url = ERROR_PAGE;
        } catch (NumberFormatException e) {
            log("UpdateProductServlet2_NumberFormatException" + e.getMessage());
            url = ERROR_PAGE;
        } catch (SQLException e) {
            log("UpdateProductServlet2_SQLException" + e.getMessage());
            
            url = ERROR_PAGE;
        }finally {
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
