/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.MotorbikeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.User;
import utils.SettingVar;
import utils.UrlPaths;
import utils.ViewPaths;
import dal.RentalDAO;

/**
 *
 * @author testu
 */
@WebServlet(name = "CreateRentalController", urlPatterns = {"/user/CreateRental"})
public class CreateRentalController extends HttpServlet {

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
            out.println("<title>Servlet CreateRentalController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateRentalController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect(UrlPaths.url(request, UrlPaths.LOGIN));
            return;
        }

        int bikeId = Integer.parseInt(request.getParameter("bikeId"));
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        MotorbikeDAO bikeDAO = new MotorbikeDAO();

        if (!bikeDAO.isBikeAvailable(bikeId)) {
            request.setAttribute("error", "This bike is not available.");
        } else if (bikeDAO.hasConflictingRental(
                bikeId,
                java.sql.Date.valueOf(startDate),
                java.sql.Date.valueOf(endDate))) {
            request.setAttribute("error", "This bike is already booked or has a pending request in the selected dates.");
        } else if (bikeDAO.countPendingRentalsByUser(user.getUserID()) >= SettingVar.MAX_RENT_PER_USER) {
            request.setAttribute("error", "You already have 3 pending rental requests.");
        } else if (java.sql.Date.valueOf(endDate).before(java.sql.Date.valueOf(startDate))) {
            request.setAttribute("error", "End date must be after or equal to start date.");
        } else {
//            request.setAttribute("success", "Rental logic check passed. Ready to create rental.");
            java.sql.Date sqlStartDate = java.sql.Date.valueOf(startDate);
            java.sql.Date sqlEndDate = java.sql.Date.valueOf(endDate);

            double pricePerDay = bikeDAO.getMotorbikeDetail(bikeId).getPricePerDay();

            RentalDAO rentalDAO = new RentalDAO();
            boolean created = rentalDAO.createRentalRequest(
                    user.getUserID(),
                    bikeId,
                    sqlStartDate,
                    sqlEndDate,
                    pricePerDay
            );

            if (created) {
                request.setAttribute("success", "Rental request created successfully. Waiting for admin approval.");
            } else {
                request.setAttribute("error", "Failed to create rental request.");
            }
        }

        request.setAttribute("bike", bikeDAO.getMotorbikeDetail(bikeId));
        request.getRequestDispatcher(ViewPaths.MOTORBIKE_DETAIL).forward(request, response);
    }// </editor-fold>

}
