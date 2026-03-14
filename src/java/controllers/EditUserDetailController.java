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
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.User;
import utils.UrlPaths;
import utils.ViewPaths;

@WebServlet("/user/EditUserDetail")
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
        String avatar = request.getParameter("avatar");

        UserDetailDTO dto = new UserDetailDTO(userName, null, firstName, lastName, phoneNumber, licenseNumber, address, null, avatar, null);
        UserDAO userDAO = new UserDAO();
        userDAO.EditUserDetail(userId, dto);
        response.sendRedirect(UrlPaths.url(request, UrlPaths.USER_DETAIL));
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
