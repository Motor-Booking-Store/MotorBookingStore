/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.User;

public class Login extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("views/Login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // ===== VALIDATE =====
        if (email == null || email.trim().isEmpty()) {
            request.setAttribute("error", "Email cannot be empty");
            request.getRequestDispatcher("views/Login.jsp").forward(request, response);
            return;
        }

        if (password == null || password.trim().isEmpty()) {
            request.setAttribute("error", "Password cannot be empty");
            request.setAttribute("email", email);
            request.getRequestDispatcher("views/Login.jsp").forward(request, response);
            return;
        }

        // ===== LOGIN CHECK =====
        AccountDAO dao = new AccountDAO();
        User user = dao.login(email, password);

        if (user == null) {
            request.setAttribute("error", "Invalid email or password");
            request.setAttribute("email", email);
            request.getRequestDispatcher("views/Login.jsp").forward(request, response);
            return;
        }

        // ===== LOGIN SUCCESS =====
        HttpSession session = request.getSession();
        session.setAttribute("user", user);

 
        if (user.roleId == 1) {
            response.sendRedirect("admin/Home");
        } else {
            response.sendRedirect("Home");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
