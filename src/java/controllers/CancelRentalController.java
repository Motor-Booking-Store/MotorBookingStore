/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers;

import dal.MotorbikeDAO;
import dal.RentalDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import models.Rental;
import models.User;

@WebServlet("/user/CancelRental")
public class CancelRentalController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CancelRentalController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CancelRentalController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        
        
        String rentalIdParam = request.getParameter("rentalId");
        if (rentalIdParam == null) {
            response.sendRedirect("rental-history?userId=" + user.getUserID());
            return;
        }

        int rentalId = Integer.parseInt(rentalIdParam);

        RentalDAO rentalDao = new RentalDAO();
        MotorbikeDAO bikeDao = new MotorbikeDAO();

        Rental rental = rentalDao.getRentalById(rentalId);
        if (rental == null || rental.getUserID()!= user.getUserID()) {
            response.sendRedirect("rental-history?userId=" + user.getUserID());
            return;
        }

        Date today = new Date();
        Date endDate = rental.getEndDate();
        if ((rental.getStatus().equals("Pending") || rental.getStatus().equals("Approved")) && today.before(endDate)) {

            rentalDao.updateStatus(rentalId, "Cancelled");

            List<Integer> bikeIds = rentalDao.getBikeIdsByRentalId(rentalId);
            for (int bikeId : bikeIds) {
                bikeDao.updateMotorbikeStatus(bikeId, "Available");
            }
        }

        // Quay về lịch sử luôn, không cần session message
        response.sendRedirect("rental-history?userId=" + user.getUserID());
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
