package controllers;

import dal.MotorbikeDAO;
import dto.AllMotorbikeDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import utils.ViewPaths;

@WebServlet("/user/MotorbikeList")
public class MotorbikeListController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MotorbikeListController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MotorbikeListController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        MotorbikeDAO dao = new MotorbikeDAO();
        String keyword = request.getParameter("bikeName"); // từ form tìm kiếm
        ArrayList<AllMotorbikeDTO> list;

        if (keyword == null || keyword.trim().isEmpty()) {
            // Không nhập gì -> load tất cả xe
            list = dao.getAllMotorbikes();
        } else {
            // Tìm kiếm theo tên xe, không phân biệt hoa thường
            list = dao.searchMotorbikesByName(keyword);
        }

        request.setAttribute("motorbikeList", list);
        request.getRequestDispatcher(ViewPaths.MOTORBIKE_LIST).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
