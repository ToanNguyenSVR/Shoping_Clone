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

/**
 *
 * @author toann
 */
@WebServlet(name = "SearchProductServlet", urlPatterns = {"/SearchProductServlet"})
public class SearchProductServlet extends HttpServlet {

    public final float MAX_PRICE = 1000000;
    public final float MIN_PRICE = 1 ;
    public final String HOME_PAGE = "home.jsp";

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
        String searchValue = request.getParameter("txtsearch");
        String category = request.getParameter("cbCatergory");
        String priceMin = request.getParameter("priceMin");
        String priceMax = request.getParameter("priceMax");
        List<TblProductDTO> list = null;
        float min;
        float max;
        String url = HOME_PAGE;
        try {
            if (searchValue != null && category == null || (priceMin == null && priceMax == null)) {
                list = TblProductDAO.getProductByName(searchValue);
            }
            
            if (category != null || (priceMin != null && priceMax != null)) {

                if ((priceMin.equals("")) || (priceMax.equals(""))) {

                    max = MAX_PRICE;
                    min = MIN_PRICE;
                } else {
                    min = Float.parseFloat(priceMin);
                    max = Float.parseFloat(priceMax);

                }

                list = TblProductDAO.getProductByName(searchValue, category, min, max);
            }
           
            if (list.size() > 0) {
                HttpSession session = request.getSession();
                session.setAttribute("PRODUCT", list);

            } else {
                url = StatUpServlet;
                String s = "Dont have the book :" + searchValue;
                request.setAttribute("MESSES", s);
            }

        } catch (NamingException e) {
            log("SearchProductServlet_NamingException" + e.getMessage());
        } catch (NumberFormatException e) {
            log("SearchProductServlet_NumberFormatException" + e.getMessage());
        } catch (SQLException e) {
            log("SearchProductServlet_SQLException" + e.getMessage());
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
