package controllers;

import dal.MotorbikeDAO;
import dal.ReviewDAO;
import dto.MotorbikeDetailDTO;
import dto.ReviewListDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import utils.ViewPaths;

@WebServlet("/user/MotorbikeDetail")
public class MotorbikeDetailController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MotorbikeDetailController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MotorbikeDetailController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        MotorbikeDAO dao = new MotorbikeDAO();
        MotorbikeDetailDTO bike = dao.getMotorbikeDetail(id);

        request.setAttribute("bike", bike);

        ReviewDAO reviewDAO = new ReviewDAO();
        List<ReviewListDTO> reviews = reviewDAO.GetAllReviewsBikeId(id);
        request.setAttribute("reviews", reviews);
        
        request.getRequestDispatcher(ViewPaths.MOTORBIKE_DETAIL).forward(request, response);
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
