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

@WebServlet("/admin/EditMotorbike")
@MultipartConfig
public class EditMotorbikeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int bikeId = Integer.parseInt(request.getParameter("id"));

        MotorbikeDAO dao = new MotorbikeDAO();
        Motorbike bike = dao.getMotorbikeById(bikeId);

        request.setAttribute("bike", bike);
        request.getRequestDispatcher(ViewPaths.EDIT_MOTORBIKE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ===== 1. GET DATA =====
        int bikeId = Integer.parseInt(request.getParameter("bikeId"));
        String bikeName = request.getParameter("bikeName");
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        String licensePlate = request.getParameter("licensePlate");
        String priceStr = request.getParameter("pricePerDay");
        String locationStr = request.getParameter("locationId");
        String description = request.getParameter("description");
        String status = request.getParameter("status");
        String oldImage = request.getParameter("oldImage");

        MotorbikeDAO dao = new MotorbikeDAO();
        String error = null;

        // ===== 2. VALIDATE =====
        if (bikeName == null || bikeName.trim().isEmpty()) {
            error = "Bike name is required";
        }

        double price = 0;
        int locationId = 0;

        try {
            price = Double.parseDouble(priceStr);
        } catch (Exception e) {
            error = "Invalid price";
        }

        try {
            locationId = Integer.parseInt(locationStr);
        } catch (Exception e) {
            error = "Invalid location";
        }

        // ===== 3. HANDLE IMAGE =====
        String image = oldImage;

        Part imagePart = request.getPart("imageUpload");

        if (imagePart != null && imagePart.getSize() > 0) {

            String fileName = System.currentTimeMillis() + "_"
                    + Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();

            String uploadPath = getServletContext().getRealPath("/images/motorbike");

            File dir = new File(uploadPath);
            if (!dir.exists()) dir.mkdirs();

            imagePart.write(uploadPath + File.separator + fileName);

            image = "/images/motorbike/" + fileName;
        }

        // ===== 4. ERROR → BACK =====
        if (error != null) {
            Motorbike bike = new Motorbike();

            bike.setBikeId(bikeId);
            bike.setBikeName(bikeName);
            bike.setBrand(brand);
            bike.setModel(model);
            bike.setLicensePlate(licensePlate);
            bike.setPricePerDay(price);
            bike.setLocationId(locationId);
            bike.setDescription(description);
            bike.setImage(image);
            bike.setStatus(status);

            request.setAttribute("error", error);
            request.setAttribute("bike", bike);

            request.getRequestDispatcher(ViewPaths.EDIT_MOTORBIKE).forward(request, response);
            return;
        }

        // ===== 5. UPDATE DB =====
        Motorbike bike = new Motorbike();

        bike.setBikeId(bikeId);
        bike.setBikeName(bikeName);
        bike.setBrand(brand);
        bike.setModel(model);
        bike.setLicensePlate(licensePlate);
        bike.setPricePerDay(price);
        bike.setLocationId(locationId);
        bike.setDescription(description);
        bike.setImage(image);
        bike.setStatus(status);

        dao.updateMotorbike(bike);

        response.sendRedirect("MotorbikeManagement");
    }
}