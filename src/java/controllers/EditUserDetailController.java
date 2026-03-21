/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.UserDAO;
import dto.UserDetailDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import models.User;
import utils.UrlPaths;
import utils.ViewPaths;

@WebServlet("/user/EditUserDetail")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class EditUserDetailController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditUserDetailController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditUserDetailController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect(UrlPaths.url(request, UrlPaths.LOGIN));
            return;
        }
        
        UserDAO userDAO = new UserDAO();
        // Load FULL user detail from DB using DAO method
        UserDetailDTO editUser = userDAO.GetUserDetailById(user.getUserID());
        if (editUser == null) {
            response.sendRedirect(UrlPaths.url(request, UrlPaths.USER_DETAIL));
            return;
        }
        request.setAttribute("user", editUser);

        request.getRequestDispatcher(ViewPaths.EDIT_USER).forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect(UrlPaths.url(request, UrlPaths.LOGIN));
            return;
        }

        int userId = user.getUserID();

        String userName = request.getParameter("userName");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phoneNumber = request.getParameter("phoneNumber");
        String licenseNumber = request.getParameter("licenseNumber");
        String address = request.getParameter("address");
        String citizenId = request.getParameter("citizen");
        // Keep old avatar if no new file selected
        String avatar = request.getParameter("oldAvatar");

        // Get uploaded file
        Part avatarPart = request.getPart("avatar");

        if (avatarPart != null && avatarPart.getSize() > 0) {
            String originalFileName = Paths.get(avatarPart.getSubmittedFileName()).getFileName().toString();

            // Make unique filename
            String fileName = System.currentTimeMillis() + "_" + originalFileName;

            // 1. Runtime folder (build/web) -> image visible immediately
            String runtimePath = getServletContext().getRealPath("/images/avatar");

            if (runtimePath == null) {
                throw new ServletException("Runtime upload path is null. Cannot save file.");
            }

            File runtimeDir = new File(runtimePath);
            if (!runtimeDir.exists()) {
                runtimeDir.mkdirs();
            }

            // Save to runtime folder first
            String runtimeFilePath = runtimePath + File.separator + fileName;
            avatarPart.write(runtimeFilePath);

            // Save relative URL path into DB
            avatar = "/images/avatar/" + fileName;

            // Debug logs (optional)
            System.out.println("Runtime saved: " + runtimeFilePath);
        }

        UserDetailDTO dto = new UserDetailDTO(
                userName,
                null,
                firstName,
                lastName,
                phoneNumber,
                licenseNumber,
                address,
                null,
                avatar,
                null,
                citizenId
        );

        UserDAO userDAO = new UserDAO();
        userDAO.EditUserDetail(userId, dto);

        // Update session user avatar immediately so navbar/profile shows new image without re-login
        user.setAvatar(avatar);
        user.setUserName(userName); // optional if username can change
        session.setAttribute("user", user);

        response.sendRedirect(UrlPaths.url(request, UrlPaths.USER_DETAIL));
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
