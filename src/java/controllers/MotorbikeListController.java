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

@WebServlet("/MotorbikeList")
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
        rentalDAO.updateExpiredRentalsToCompleted();

        MotorbikeDAO dao = new MotorbikeDAO();

        String bikeName = request.getParameter("bikeName");
        String brand = request.getParameter("brand");
        String status = request.getParameter("status");

        String minPriceRaw = request.getParameter("minPrice");
        String maxPriceRaw = request.getParameter("maxPrice");

        String priceRange = request.getParameter("priceRange");

        Double minPrice = null;
        Double maxPrice = null;

// If manual min/max from another form
        try {
            if (minPriceRaw != null && !minPriceRaw.trim().isEmpty()) {
                minPrice = Double.valueOf(minPriceRaw.trim());
            }
            if (maxPriceRaw != null && !maxPriceRaw.trim().isEmpty()) {
                maxPrice = Double.valueOf(maxPriceRaw.trim());
            }
        } catch (NumberFormatException e) {
            // ignore invalid input
        }

// If sidebar uses priceRange, override min/max
        if (priceRange != null && !priceRange.trim().isEmpty()) {
            switch (priceRange) {
                case "under150000":
                    maxPrice = 150000.0;
                    break;
                case "150000to200000":
                    minPrice = 150000.0;
                    maxPrice = 200000.0;
                    break;
                case "above200000":
                    minPrice = 200000.0;
                    break;
            }
        }

        try {
            if (minPriceRaw != null && !minPriceRaw.trim().isEmpty()) {
                minPrice = Double.valueOf(minPriceRaw.trim());
            }
            if (maxPriceRaw != null && !maxPriceRaw.trim().isEmpty()) {
                maxPrice = Double.valueOf(maxPriceRaw.trim());
            }
        } catch (NumberFormatException e) {
            // ignore invalid input
            System.out.println(e.getMessage());
        }
        
        request.setAttribute("priceRange", priceRange);

        // DO NOT filter status in SQL because displayed status is dynamic
        ArrayList<AllMotorbikeDTO> list = dao.filterMotorbikes(bikeName, brand, minPrice, maxPrice, null);

        // Update dynamic status
        for (AllMotorbikeDTO bike : list) {
            if (bike != null && !MotorbikeStatus.Status.Maintenance.name().equalsIgnoreCase(bike.getStatus())) {
                boolean rentedToday = dao.isBikeRentedToday(bike.getBikeId());
                bike.setStatus(rentedToday
                        ? MotorbikeStatus.Status.Rented.name()
                        : MotorbikeStatus.Status.Available.name());
            }
        }

        // Filter status AFTER status is recalculated
        if (status != null && !status.trim().isEmpty()) {
            list.removeIf(bike -> !bike.getStatus().equalsIgnoreCase(status.trim()));
        }

        request.setAttribute("brandList", dao.getAllBrands());

        request.setAttribute("bikeName", bikeName);
        request.setAttribute("brand", brand);
        request.setAttribute("status", status);
        request.setAttribute("minPrice", minPriceRaw);
        request.setAttribute("maxPrice", maxPriceRaw);

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
