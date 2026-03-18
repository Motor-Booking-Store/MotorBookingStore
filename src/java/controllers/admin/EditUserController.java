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
import utils.ViewPaths;

/**
 *
 * @author pc
 */
@WebServlet("/admin/EditUser")
public class EditUserController extends HttpServlet {
   
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
            out.println("<title>Servlet EditUserController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditUserController at " + request.getContextPath () + "</h1>");
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

        String idStr = request.getParameter("id");

        if (idStr == null) {
            response.sendRedirect("UserManagement");
            return;
        }

        int userID = Integer.parseInt(idStr);

        AccountDAO dao = new AccountDAO();
        User user = dao.getUserById(userID);

        request.setAttribute("user", user);
        request.getRequestDispatcher(ViewPaths.ADMIN_EDIT_USER).forward(request, response);
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

        int userID = Integer.parseInt(request.getParameter("userID"));

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
        int roleId = Integer.parseInt(request.getParameter("roleId"));

        User user = new User();
        user.setUserID(userID);
        user.setUserName(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setCitizenId(citizen_id);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setPhoneNumber(phonenumber);
        user.setLicenseNumber(licensenumber);
        user.setAddress(address);
        user.setBankNumber(banknumber);
        user.setRoleId(roleId);

        AccountDAO dao = new AccountDAO();
        dao.updateUser(user);

        response.sendRedirect("UserManagement");
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
