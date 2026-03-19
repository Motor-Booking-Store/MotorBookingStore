package controllers;

import dal.MotorbikeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.User;
import utils.SettingVar;
import utils.UrlPaths;
import dal.RentalDAO;

@WebServlet(name = "CreateRentalController", urlPatterns = {"/user/CreateRental"})
public class CreateRentalController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CreateRentalController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateRentalController at " + request.getContextPath() + "</h1>");
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

        if (user == null) {
            response.sendRedirect(UrlPaths.url(request, UrlPaths.LOGIN));
            return;
        }

        int bikeId = Integer.parseInt(request.getParameter("bikeId"));
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        MotorbikeDAO bikeDAO = new MotorbikeDAO();

        if (!bikeDAO.isBikeAvailable(bikeId)) {
            session.setAttribute("error", "This bike is not available.");
        } else if (bikeDAO.hasConflictingRental(
                bikeId,
                java.sql.Date.valueOf(startDate),
                java.sql.Date.valueOf(endDate))) {
            session.setAttribute("error", "Xe này đã được đặt hoặc đang có yêu cầu chờ trong khoảng thời gian bạn chọn.");
        } else if (bikeDAO.countPendingRentalsByUser(user.getUserID()) >= SettingVar.MAX_RENT_PER_USER) {
            session.setAttribute("error", "Bạn đã có 3 yêu cầu thuê xe đang chờ xử lý.");
        } else if (java.sql.Date.valueOf(endDate).before(java.sql.Date.valueOf(startDate))) {
            session.setAttribute("error", "Ngày trả phải sau hoặc bằng ngày thuê.");
        } else {
//            request.setAttribute("success", "Rental logic check passed. Ready to create rental.");
            java.sql.Date sqlStartDate = java.sql.Date.valueOf(startDate);
            java.sql.Date sqlEndDate = java.sql.Date.valueOf(endDate);

            double pricePerDay = bikeDAO.getMotorbikeDetail(bikeId).getPricePerDay();

            RentalDAO rentalDAO = new RentalDAO();
            boolean created = rentalDAO.createRentalRequest( user.getUserID(), bikeId, sqlStartDate, sqlEndDate, pricePerDay );
            if (created) {
                session.setAttribute("success", "Yêu cầu thuê xe đã được tạo thành công. Vui lòng chờ quản trị viên phê duyệt.");
            } else {
                session.setAttribute("error", "Không thể tạo yêu cầu thuê xe.");
            }
        }

        
        response.sendRedirect(UrlPaths.url(request, UrlPaths.Id_MotorbikeDetail) + bikeId);
    }

}
