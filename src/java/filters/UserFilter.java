package filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import java.io.IOException;
import models.User;
import utils.UrlPaths;

@WebFilter("/user/*")
public class UserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        // ❌ chưa login
        if (user == null) {
            res.sendRedirect(UrlPaths.url(req, UrlPaths.LOGIN));
            return;
        }
        
        // neu khong phai user thi ve home cua user
        if (user.roleId == 1) {
            res.sendRedirect(UrlPaths.url(req, UrlPaths.ADMIN_HOME));
            return;
        }

        // ✅ login rồi (user hoặc admin đều vào được)
        chain.doFilter(request, response);
    }
}
