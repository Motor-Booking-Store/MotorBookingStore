/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.RentalDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.RentalStatus;
import utils.UrlPaths;

/**
 *
 * @author testu
 */
@WebServlet("/admin/update-rental-status")
public class ApproveRentalController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ApproveRentalController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ApproveRentalController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int rentalId = Integer.parseInt(request.getParameter("id"));
        String action = request.getParameter("action");

        RentalDAO dao = new RentalDAO();

        if ("approve".equals(action)) {
            dao.updateRentalStatus(rentalId, RentalStatus.Approved.name());
        }

        if ("cancel".equals(action)) {
            dao.updateRentalStatus(rentalId, RentalStatus.Cancelled.name());
        }

        response.sendRedirect(UrlPaths.url(request, UrlPaths.PENDING_RENTALS));
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
