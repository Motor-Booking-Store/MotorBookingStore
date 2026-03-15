/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.RentalDAO;
import dto.PendingRentalDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import utils.ViewPaths;

/**
 *
 * @author testu
 */
@WebServlet("/admin/pending-rentals")
public class AdminRentalController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminRentalController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminRentalController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RentalDAO dao = new RentalDAO();

        String status = request.getParameter("status");

        if (status == null) {
            status = "Pending"; // default page
        }

        List<PendingRentalDTO> list = dao.getRentalsByStatus(status);

        request.setAttribute("pendingList", list);
        request.setAttribute("currentStatus", status);

        request.getRequestDispatcher(ViewPaths.PENDING_RENTALS).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
