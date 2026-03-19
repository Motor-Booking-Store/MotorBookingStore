package controllers.admin;

import dal.MotorbikeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import models.Motorbike;
import utils.ViewPaths;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@WebServlet("/admin/AddMotorbike")
@MultipartConfig
public class AddMotorbikeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(ViewPaths.ADD_MOTORBIKE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ===== 1. GET DATA =====
        String bikeName = request.getParameter("bikeName");
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        String licensePlate = request.getParameter("licensePlate");
        String priceStr = request.getParameter("pricePerDay");
        String locationStr = request.getParameter("locationId");
        String description = request.getParameter("description");
        String status = request.getParameter("status");

        MotorbikeDAO dao = new MotorbikeDAO();
        String error = null;

        // ===== 2. VALIDATE =====
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
            if (pricePerDay <= 0) error = "Price must be > 0";
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

        // ===== 3. HANDLE IMAGE =====
        String image = null;

        try {
            Part imagePart = request.getPart("imageUpload");

            if (imagePart != null && imagePart.getSize() > 0) {

                String originalFileName = Paths.get(imagePart.getSubmittedFileName())
                        .getFileName().toString();

                String fileName = System.currentTimeMillis() + "_" + originalFileName;

                String uploadPath = getServletContext().getRealPath("/images/motorbike");

                File dir = new File(uploadPath);
                if (!dir.exists()) dir.mkdirs();

                imagePart.write(uploadPath + File.separator + fileName);

                image = "/images/motorbike/" + fileName;
            }

        } catch (Exception e) {
            e.printStackTrace();
            error = "Upload image failed";
        }

        // ===== 4. IF ERROR → BACK TO FORM =====
        if (error != null) {

            request.setAttribute("error", error);

            request.setAttribute("bikeName", bikeName);
            request.setAttribute("brand", brand);
            request.setAttribute("model", model);
            request.setAttribute("licensePlate", licensePlate);
            request.setAttribute("pricePerDay", priceStr);
            request.setAttribute("locationId", locationStr);
            request.setAttribute("description", description);
            request.setAttribute("status", status);
            request.setAttribute("image", image);

            request.getRequestDispatcher(ViewPaths.ADD_MOTORBIKE).forward(request, response);
            return;
        }

        // ===== 5. INSERT DB =====
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

        // ===== 6. REDIRECT =====
        response.sendRedirect("MotorbikeManagement");
    }
}