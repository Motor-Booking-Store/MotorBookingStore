/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers.admin;

import dal.MotorbikeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Motorbike;
import utils.ViewPaths;

/**
 *
 * @author pc
 */
@WebServlet("/admin/AddMotorbike")
public class AddMotorbikeController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet AddMotorbikeController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddMotorbikeController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.getRequestDispatcher(ViewPaths.ADD_MOTORBIKE).forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String bikeName = request.getParameter("bikeName");
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        String licensePlate = request.getParameter("licensePlate");
        String priceStr = request.getParameter("pricePerDay");
        String locationStr = request.getParameter("locationId");
        String description = request.getParameter("description");
        String image = request.getParameter("image");
        String status = request.getParameter("status");

        MotorbikeDAO dao = new MotorbikeDAO();

        String error = null;

        if (bikeName == null || bikeName.trim().isEmpty()) {
            error = "Bike name is required";
        } else if (brand == null || brand.trim().isEmpty()) {
            error = "Brand is required";
        } else if (licensePlate == null || licensePlate.trim().isEmpty()) {
            error = "License plate is required";
        } else if (priceStr == null || priceStr.trim().isEmpty()) {
            error = "Price is required";
        } else if (locationStr == null || locationStr.trim().isEmpty()) {
            error = "Location is required";
        }

        double pricePerDay = 0;
        int locationId = 0;

        try {
            pricePerDay = Double.parseDouble(priceStr);
        } catch (Exception e) {
            error = "Invalid price";
        }

        try {
            locationId = Integer.parseInt(locationStr);
        } catch (Exception e) {
            error = "Invalid location";
        }

        if (error == null && dao.isLicensePlateExist(licensePlate)) {
            error = "License plate already exists";
        }

        if (error != null) {

            request.setAttribute("error", error);

            request.setAttribute("bikeName", bikeName);
            request.setAttribute("brand", brand);
            request.setAttribute("model", model);
            request.setAttribute("licensePlate", licensePlate);
            request.setAttribute("pricePerDay", priceStr);
            request.setAttribute("locationId", locationStr);
            request.setAttribute("description", description);
            request.setAttribute("image", image);
            request.setAttribute("status", status);

            request.getRequestDispatcher(ViewPaths.ADD_MOTORBIKE).forward(request, response);
            return;
        }

        Motorbike bike = new Motorbike();

        bike.setBikeName(bikeName);
        bike.setBrand(brand);
        bike.setModel(model);
        bike.setLicensePlate(licensePlate);
        bike.setPricePerDay(pricePerDay);
        bike.setLocationId(locationId);
        bike.setDescription(description);
        bike.setImage(image);
        bike.setStatus(status);

        dao.addMotorbike(bike);

        response.sendRedirect("MotorbikeManagement");
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
