package controllers;

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import models.User;
import utils.UrlPaths;
import utils.ViewPaths;

@WebServlet("/user/SignUp")
public class SignUp extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SignUp</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SignUp at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(ViewPaths.SIGNUP).forward(request, response);
    }

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

        String error = null;
// ===== USERNAME =====
        if (username == null || username.trim().isEmpty()) {
            request.setAttribute("error", "Username cannot be empty");
            request.getRequestDispatcher(ViewPaths.SIGNUP).forward(request, response);
            return;
        }

        if (dao.checkUnique("userName", username) != null) {
            request.setAttribute("error", "Username already exists");
            request.getRequestDispatcher(ViewPaths.SIGNUP).forward(request, response);
            return;
        }

// ===== PASSWORD =====
        if (password == null || password.trim().isEmpty()) {
            request.setAttribute("error", "Password cannot be empty");
            request.getRequestDispatcher(ViewPaths.SIGNUP).forward(request, response);
            return;
        }

        if (password.length() < 6) {
            request.setAttribute("error", "Password must be at least 6 characters");
            request.getRequestDispatcher(ViewPaths.SIGNUP).forward(request, response);
            return;
        }

// ===== EMAIL =====
        if (email == null || email.trim().isEmpty()) {
            request.setAttribute("error", "Email cannot be empty");
            request.getRequestDispatcher(ViewPaths.SIGNUP).forward(request, response);
            return;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            request.setAttribute("error", "Invalid email format");
            request.getRequestDispatcher(ViewPaths.SIGNUP).forward(request, response);
            return;
        }

        if (dao.checkUnique("email", email) != null) {
            request.setAttribute("error", "Email already exists");
            request.getRequestDispatcher(ViewPaths.SIGNUP).forward(request, response);
            return;
        }

// ===== CITIZEN ID =====
        if (citizen_id == null || citizen_id.trim().isEmpty()) {
            request.setAttribute("error", "Citizen ID cannot be empty");
            request.getRequestDispatcher(ViewPaths.SIGNUP).forward(request, response);
            return;
        }

        if (!citizen_id.matches("\\d{9}|\\d{12}")) {
            request.setAttribute("error", "Citizen ID must be 9 or 12 digits");
            request.getRequestDispatcher(ViewPaths.SIGNUP).forward(request, response);
            return;
        }

        if (dao.checkUnique("citizen_id", citizen_id) != null) {
            request.setAttribute("error", "Citizen ID already exists");
            request.getRequestDispatcher(ViewPaths.SIGNUP).forward(request, response);
            return;
        }

// ===== PHONE NUMBER =====
        if (phonenumber == null || phonenumber.trim().isEmpty()) {
            request.setAttribute("error", "Phone number cannot be empty");
            request.getRequestDispatcher(ViewPaths.SIGNUP).forward(request, response);
            return;
        }

        if (!phonenumber.matches("\\d{10,11}")) {
            request.setAttribute("error", "Phone number must be 10-11 digits");
            request.getRequestDispatcher(ViewPaths.SIGNUP).forward(request, response);
            return;
        }

        if (dao.checkUnique("phoneNumber", phonenumber) != null) {
            request.setAttribute("error", "Phone number already exists");
            request.getRequestDispatcher(ViewPaths.SIGNUP).forward(request, response);
            return;
        }

        if (firstname == null || firstname.trim().isEmpty()) {
            request.setAttribute("error", "First Name cannot be empty");
            request.getRequestDispatcher(ViewPaths.SIGNUP).forward(request, response);
            return;
        }

        if (lastname == null || lastname.trim().isEmpty()) {
            request.setAttribute("error", "Last Name cannot be empty");
            request.getRequestDispatcher(ViewPaths.SIGNUP).forward(request, response);
            return;
        }

        if (licensenumber == null || licensenumber.trim().isEmpty()) {
            request.setAttribute("error", "License number cannot be empty");
            request.getRequestDispatcher(ViewPaths.SIGNUP).forward(request, response);
            return;
        }

        if (address == null || address.trim().isEmpty()) {
            request.setAttribute("error", "Address cannot be empty");
            request.getRequestDispatcher(ViewPaths.SIGNUP).forward(request, response);
            return;
        }

        if (banknumber == null || banknumber.trim().isEmpty()) {
            request.setAttribute("error", "Bank Number cannot be empty");
            request.getRequestDispatcher(ViewPaths.SIGNUP).forward(request, response);
            return;
        }

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

        user.roleId = 2;

        user.createdAt = new Date();
        user.updatedAt = new Date();

        if (error != null) {

            request.setAttribute("error", error);

            request.setAttribute("username", username);
            request.setAttribute("email", email);
            request.setAttribute("citizen_id", citizen_id);
            request.setAttribute("firstname", firstname);
            request.setAttribute("lastname", lastname);
            request.setAttribute("phonenumber", phonenumber);
            request.setAttribute("licensenumber", licensenumber);
            request.setAttribute("address", address);
            request.setAttribute("banknumber", banknumber);

            request.getRequestDispatcher(ViewPaths.SIGNUP).forward(request, response);
            return;
        }

        dao.createAccount(user);

        response.sendRedirect(UrlPaths.url(request, UrlPaths.LOGIN)
        );

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
