package controllers;

import dal.ReviewDAO;
import dto.ReviewDTO;
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

@WebServlet("/user/AddReview")
public class AddReviewController extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddReviewController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddReviewController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if(user == null) {
            response.sendRedirect(UrlPaths.url(request, UrlPaths.LOGIN));
            return;
        }

        int bikeId = Integer.parseInt(request.getParameter("bikeId"));
        int rating = Integer.parseInt(request.getParameter("rating"));
        String comment = request.getParameter("comment");

        ReviewDTO review = new ReviewDTO(user.getUserID(), bikeId, rating, comment);

        ReviewDAO dao = new ReviewDAO();
        dao.addReview(review);

        response.sendRedirect(UrlPaths.url(request, UrlPaths.Id_MotorbikeDetail) + bikeId);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
