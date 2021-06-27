/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toan.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author toann
 */
@WebServlet(name = "DispatcherServlet", urlPatterns = {"/DispatcherServlet"})
public class DispatcherServlet extends HttpServlet {

    public final String StatUpServlet = "StatUpServlet";
    public final String SearchProductServlet = "SearchProductServlet";
    public final String LoginServlet = "LogingServlet";
    public final String UpdateProductServlet = "UpdateProductServlet2";
    public final String DeleteProductServlet = "DeleteProductServlet";
    public final String CreateProductServlet = "CreateProductServlet";
    public final String LogoutServlet = "LogoutServlet";
    public final String AddToCart = "AddToCart";
    public final String RemoveCarServlet = "RemoveCarServlet";
    public final String CreateVoucher = "CreateVoucher";
    public final String CheckVoucher = "CheckVoucher";
    public final String CheckOutServlet = "CheckOutServlet";
    public final String LoadHistoryServlet = "LoadHistoryServlet";

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
        String btnAction = request.getParameter("btnAction");
        String url = null ;
        try {
            if (btnAction == null) {
                url = StatUpServlet;
            } else if (btnAction.equals("search")||btnAction.equals("apply")) {
                url = SearchProductServlet;
            } else if (btnAction.equals("Login")) {
                url = LoginServlet;
            } else if (btnAction.equals("Update")) {
                url = UpdateProductServlet;
            } else if (btnAction.equals("deleteProduct")) {
                url = DeleteProductServlet;
            } else if (btnAction.equals("Create")) {
                url = CreateProductServlet;
            } else if (btnAction.equals("Logout")) {
                url = LogoutServlet;
            } else if (btnAction.equals("AddToCart")) {
                url = AddToCart;
            } else if (btnAction.equals("CreateVoucher")) {
                url = CreateVoucher;
            } else if (btnAction.equals("checkVoucher")) {
                url = CheckVoucher;
            }  else if (btnAction.equals("PAY")) {
                url = CheckOutServlet;
            } else if (btnAction.equals("Shopping")) {
                url = StatUpServlet;
            } else if (btnAction.equals("X")) {
                url = RemoveCarServlet;
            } else if (btnAction.equals("loadHistory")) {
                url = LoadHistoryServlet;
            } 
        } catch (Exception e) {
            log("DispatcherServlet_Erxception"+ e.getMessage());
        }finally{
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
