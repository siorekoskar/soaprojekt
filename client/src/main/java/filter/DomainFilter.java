package filter;

import javax.ejb.SessionContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

public class DomainFilter implements Filter {

    private String allowedDomain;

    private SessionContext context;

    private Map<String, String> jsessions = new HashMap<>();

    @Override
    public void init(FilterConfig config) {//
        allowedDomain = config.getInitParameter("allowedDomain");
        InitialContext ic = null;
        try {
            ic = new InitialContext();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        //            SessionContext sctxLookup =
//                    (SessionContext) ic.lookup("java:comp/EJBContext");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws java.io.IOException, ServletException {
        Principal userPrincipal = ((HttpServletRequest) req).getUserPrincipal();
        String jsessionId = ((HttpServletRequest) req).getSession().getId();
        String name = userPrincipal.getName();
        String s = jsessions.get(name);
        if (s == null) {
            jsessions.put(name, jsessionId);
            chain.doFilter(req, resp);
        } else if (s.equals(jsessionId)) {
            chain.doFilter(req, resp);
        } else {
            resp.getWriter().print("ktos juz jest zalogowany");
        }
    }

    @Override
    public void destroy() {
    }

}