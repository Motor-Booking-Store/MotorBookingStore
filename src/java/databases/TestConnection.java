package databases;

import context.DBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/test")
public class TestConnection extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DBContext db = new DBContext();

        if (db.getConnection() != null) {
            response.getWriter().println("Connect Successfull");
        } else {
            response.getWriter().println("Connect Fall");
        }
    }
}