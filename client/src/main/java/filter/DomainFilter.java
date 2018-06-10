package filter;

import javax.ejb.SessionContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

public class DomainFilter implements Filter {

    private String allowedDomain;

    private SessionContext context;

    public static Map<String, String> getJsessions() {
        return jsessions;
    }

    private static Map<String, String> jsessions = new HashMap<>();

    @Override
    public void init(FilterConfig config) {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws java.io.IOException, ServletException {
        Principal userPrincipal = ((HttpServletRequest) req).getUserPrincipal();
        String jsessionId = ((HttpServletRequest) req).getSession().getId();
        if(userPrincipal == null){
            ((HttpServletResponse)resp).sendRedirect("/client/login.xhtml");
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
            ((HttpServletRequest)req).getSession().invalidate();
            ((HttpServletResponse)resp).sendRedirect(((HttpServletRequest)req).getContextPath()
                    + "/login.xhtml?logged=juz%20jest%20zalogowany");
        }
    }

    @Override
    public void destroy() {
    }

}
