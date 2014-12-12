package fr.sewatech.formation.appserv.web;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;

/**
 * @author Alexis Hassler
 */
//@WebFilter(urlPatterns = "/*")
public class UserFilter implements Filter {

    public static final String KEY = "username";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            Principal userPrincipal = httpServletRequest.getUserPrincipal();
            if ( (userPrincipal != null)
              && (userPrincipal.getName() != null) ) {
                HttpSession session = httpServletRequest.getSession();
                session.setAttribute("XXX", "rien");
                if (session.getAttribute(KEY) == null) {
                    session.setAttribute(KEY, userPrincipal.getName());
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
