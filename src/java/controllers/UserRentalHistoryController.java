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
import jakarta.servlet.http.HttpSession;
import java.util.List;
import models.User;
import utils.UrlPaths;
import utils.ViewPaths;

@WebServlet("/user/rental-history")
public class UserRentalHistoryController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserRentalHistoryController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserRentalHistoryController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if(user == null) {
            response.sendRedirect(UrlPaths.url(request, UrlPaths.LOGIN));
            return;
        }
        
        RentalDAO dao = new RentalDAO();

        int userId = Integer.parseInt(request.getParameter("userId"));
        String status = request.getParameter("status");

        if (status == null) {
            status = "all";
        }

        List<PendingRentalDTO> list = dao.getRentalsByUser(userId, status);

        request.setAttribute("pendingList", list);
        request.setAttribute("currentStatus", status);

        request.getRequestDispatcher(ViewPaths.USER_RENTALS_HISTORY)
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
