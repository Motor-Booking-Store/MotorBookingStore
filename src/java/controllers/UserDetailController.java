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

/**
 *
 * @author nguye
 */
@WebServlet("/user/UserDetail")
public class UserDetailController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserDetailController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserDetailController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);

        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect(UrlPaths.url(request, UrlPaths.LOGIN));
            return;
        }
       
        UserDAO userDAO = new UserDAO();
        int userId = user.getUserID();
        
        UserDetailDTO userDetail = userDAO.GetUserDetailById(userId);

        if (userDetail == null) {
            out.print("User khong ton tai");
            return;
        }

        request.setAttribute("userDetail", userDetail);
        request.getRequestDispatcher(ViewPaths.USER_DETAIL).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
