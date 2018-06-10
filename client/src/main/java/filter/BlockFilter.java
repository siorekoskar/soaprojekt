package filter;

import javax.servlet.*;
import java.io.IOException;

public class BlockFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        response.getWriter().write("DISABLED SITE!");
    }

    @Override
    public void destroy() {

    }
}