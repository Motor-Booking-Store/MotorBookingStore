/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.admin;

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;

/**
 *
 * @author pc
 */
@WebServlet("/admin/adduser")
public class AddUserController extends HttpServlet {

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
            out.println("<title>Servlet AddUserController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddUserController at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("/WEB-INF/views/admin/AddUser.jsp").forward(request, response);
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

        AccountDAO dao = new AccountDAO();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String citizen_id = request.getParameter("citizen_id");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String phonenumber = request.getParameter("phonenumber");
        String licensenumber = request.getParameter("licensenumber");
        String address = request.getParameter("address");
        String banknumber = request.getParameter("banknumber");
        String role = request.getParameter("roleId");

        String error = null;

        // ===== VALIDATION =====
        if (username == null || username.trim().isEmpty()) {
            error = "Username cannot be empty";
        } else if (dao.checkUnique("userName", username) != null) {
            error = "Username already exists";
        } else if (password == null || password.trim().isEmpty()) {
            error = "Password cannot be empty";
        } else if (email == null || email.trim().isEmpty()) {
            error = "Email cannot be empty";
        } else if (dao.checkUnique("email", email) != null) {
            error = "Email already exists";
        }

        if (error != null) {

            request.setAttribute("error", error);
            request.getRequestDispatcher("/WEB-INF/views/admin/AddUser.jsp").forward(request, response);
            return;
        }

        // ===== CREATE USER OBJECT =====
        User user = new User();

        user.userName = username;
        user.password = password;
        user.email = email;
        user.citizen_id = citizen_id;
        user.firstName = firstname;
        user.lastName = lastname;
        user.phoneNumber = phonenumber;
        user.licenseNumber = licensenumber;
        user.address = address;
        user.setBankNumber(banknumber);

        user.roleId = Integer.parseInt(role);

        user.createdAt = new java.util.Date();
        user.updatedAt = new java.util.Date();

        boolean success = dao.createAccount(user);

        if (success) {
            response.sendRedirect("UserManagement"); // quay về danh sách user
        } else {
            request.setAttribute("error", "Add user failed");
            request.getRequestDispatcher("/WEB-INF/views/admin/AddUser.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
