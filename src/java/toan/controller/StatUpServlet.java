/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toan.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import toan.tblCatergory.TblCategoryDAO;
import toan.tblCatergory.TblCatergoryDTO;
import toan.tblProduct.TblProductDAO;
import toan.tblProduct.TblProductDTO;
import toan.tblUser.TblUserDAO;
import toan.tblUser.TblUserDTO;

/**
 *
 * @author toann
 */
@WebServlet(name = "StatUpServlet", urlPatterns = {"/StatUpServlet"})
public class StatUpServlet extends HttpServlet {

    //public final String HOME_PAGE = "home.jsp";
    public final String ADMIN_PAGE = "admin.jsp";
    public final String HOME_PAGE = "home.jsp";
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
        String url = HOME_PAGE;
        try {
            // get product 
            List<TblProductDTO> list = TblProductDAO.loadProduct();
            HttpSession session = request.getSession();
            if (list != null) {
                session.setAttribute("PRODUCT", list);
            }
            List<TblCatergoryDTO> listCategory = TblCategoryDAO.loadCategory();

            if (listCategory != null) {
                session.setAttribute("CATEGORY", listCategory);
            }

            // do cookies 
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                String username = null;
                String password = null;
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("USERNAME")) {
                        username = cookie.getValue();
                    }
                    if (cookie.getName().equals("PASSWORD")) {
                        password = cookie.getValue();
                    }
                }
                if (username != null || password != null) {
                    TblUserDAO dao = new TblUserDAO();
                    TblUserDTO dto = TblUserDAO.checkLogin(username, password);

                    if (dto != null) {

                        if (dto.getUserRole().equalsIgnoreCase("ADMIN")) {
                            session.setAttribute("USERADMIN", dto);
                            url = ADMIN_PAGE;
                        }
                        if (dto.getUserRole().equalsIgnoreCase("USER")) {
                            session.setAttribute("USER", dto);
                            url = HOME_PAGE;
                        }
                    }
                }
            }
        } catch (NamingException e) {
            log("StatUpServlet_NamingException " + e.getMessage());
        } catch (SQLException e) {
            log("StatUpServlet_SQLException " + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            log("StatUpServlet_NoSuchAlgorithmException " + e.getMessage());
        } catch (IOException e) {
            log("StatUpServlet_IOException " + e.getMessage());
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
