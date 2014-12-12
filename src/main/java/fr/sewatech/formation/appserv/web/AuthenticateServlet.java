package fr.sewatech.formation.appserv.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;

@WebServlet(name = "AuthenticateServlet", urlPatterns = {"/login"})
public class AuthenticateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        request.authenticate(response);
        Principal userPrincipal = request.getUserPrincipal();
        out.println("<p>Authenticate Successful as " + userPrincipal.getName() + "</p>");
        printIsInRole(request, response, "sw-webuser");
        printIsInRole(request, response, "sw-ejbuser");
    }

    private void printIsInRole(HttpServletRequest request, HttpServletResponse response, String role) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("<p>Role " + role + " : " + request.isUserInRole(role) + "</p>");
    }
}
