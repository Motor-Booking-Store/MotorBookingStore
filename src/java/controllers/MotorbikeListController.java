package controllers;

import dal.MotorbikeDAO;
import dal.RentalDAO;
import dto.AllMotorbikeDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import models.MotorbikeStatus;
import utils.ViewPaths;

@WebServlet("/user/MotorbikeList")
public class MotorbikeListController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MotorbikeListController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MotorbikeListController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RentalDAO rentalDAO = new RentalDAO();
        rentalDAO.updateExpiredRentalsToCompleted();//for update expired date

        MotorbikeDAO dao = new MotorbikeDAO();
        String keyword = request.getParameter("bikeName"); // từ form tìm kiếm
        ArrayList<AllMotorbikeDTO> list;

        if (keyword == null || keyword.trim().isEmpty()) {
            // Không nhập gì -> load tất cả xe
            list = dao.getAllMotorbikes();
        } else {
            // Tìm kiếm theo tên xe, không phân biệt hoa thường
            list = dao.searchMotorbikesByName(keyword);
        }

        for (AllMotorbikeDTO bike : list) {
            if (bike != null && !MotorbikeStatus.Status.Maintenance.name().equalsIgnoreCase(bike.getStatus())) {
                boolean rentedToday = dao.isBikeRentedToday(bike.getBikeId());
                bike.setStatus(rentedToday
                        ? MotorbikeStatus.Status.Rented.name()
                        : MotorbikeStatus.Status.Available.name());
            }
        }

        request.setAttribute("motorbikeList", list);

        request.setAttribute("STATUS_AVAILABLE", MotorbikeStatus.Status.Available.name());
        request.setAttribute("STATUS_RENTED", MotorbikeStatus.Status.Rented.name());
        request.setAttribute("STATUS_MAINTENANCE", MotorbikeStatus.Status.Maintenance.name());

        request.getRequestDispatcher(ViewPaths.MOTORBIKE_LIST).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
