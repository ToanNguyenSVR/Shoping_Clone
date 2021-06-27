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
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import toan.tblUser.TblUserDAO;
import toan.tblUser.TblUserDTO;

/**
 *
 * @author toann
 */
@WebServlet(name = "LogingServlet", urlPatterns = {"/LogingServlet"})
public class LogingServlet extends HttpServlet {

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
        String username = request.getParameter("txtUserName");
        String password = request.getParameter("txtPassWord");
        TblUserDTO dto;
        String url = null;
        try {
            dto = TblUserDAO.checkLogin(username, password);

            if (dto != null) {

                HttpSession session = request.getSession();

                Cookie userCookie = new Cookie("USERNAME", username);
                userCookie.setMaxAge(60 * 60 * 5);
                Cookie passwordCookie = new Cookie("PASSWORD", password);
                passwordCookie.setMaxAge(60 * 60 * 5);
                response.addCookie(userCookie);
                response.addCookie(passwordCookie);
                if (dto.getUserRole().trim().equalsIgnoreCase("ADMIN")) {
                    session.setAttribute("USERADMIN", dto);
                    url = ADMIN_PAGE;

                }
                if (dto.getUserRole().trim().equalsIgnoreCase("USER")) {
                    session.setAttribute("USER", dto);
                    url = HOME_PAGE;

                }
            } else {
                url = HOME_PAGE;
            }

        } catch (SQLException sq) {
            log("LoginServlet_SQLException " + sq.getMessage());
            url = ERROR_PAGE;
        } catch (NamingException ne) {
            log("LoginServlet_NamingException " + ne.getMessage());
            url = ERROR_PAGE;
        } catch (NoSuchAlgorithmException nse) {
            log("LoginServlet_NoSuchAlgorithmException " + nse.getMessage());
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
