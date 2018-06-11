package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

public class DomainFilter implements Filter {

    public static Map<String, String> getJsessions() {
        return jsessions;
    }

    private static Map<String, String> jsessions = new HashMap<>();

    @Override
    public void init(FilterConfig config) {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpServletResponse httpResponse = (HttpServletResponse) resp;

        Principal userPrincipal = httpRequest.getUserPrincipal();
        String jsessionId = httpRequest.getSession().getId();
        if (userPrincipal == null) {
            httpResponse.sendRedirect("/client/login.xhtml");
            return;
        }
        String name = userPrincipal.getName();
        String s = jsessions.get(name);
        if (s == null) {
            jsessions.put(name, jsessionId);
            chain.doFilter(req, resp);
        } else if (s.equals(jsessionId)) {
            chain.doFilter(req, resp);
        } else {
            httpRequest.getSession().invalidate();
            httpResponse.sendRedirect(httpRequest.getContextPath()
                    + "/login.xhtml?logged=juz%20jest%20zalogowany");
        }
    }

    @Override
    public void destroy() {
    }
}
