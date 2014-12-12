package fr.sewatech.formation.appserv.web;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class PageFilter implements Filter {
    private static final String LAYOUT_PAGE = "/include/layout.jsp";

    private static final Logger logger = Logger.getLogger(PageFilter.class);

    private FilterConfig config;
    private HttpServletRequest httpRequest;

    public void doFilter(final ServletRequest request,
                         final ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        httpRequest = (HttpServletRequest) request;
        String path = httpRequest.getServletPath();
        try {
            logger.info("Appel de " + path);
            request.setAttribute("include_path", path);

            RequestDispatcher dispatcher = httpRequest.getRequestDispatcher("/include/layout.jsp");
            dispatcher.forward(request, response);
        } catch (Throwable e) {
            logger.error("Erreur dans la page " + path, e);
            request.setAttribute("include_path", httpRequest.getContextPath() + "/include/error.jsp");
            request.setAttribute("error", e);

            RequestDispatcher dispatcher = httpRequest.getRequestDispatcher("/include/layout.jsp");
            dispatcher.forward(request, response);
        }
    }


    public void destroy() {
    }

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }
}