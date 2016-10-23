/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.crce.wtlabs.util.Google2Api;
import org.scribe.builder.ServiceBuilder;
import org.scribe.oauth.OAuthService;

/**
 *
 * @author Flav
 */
public class GoogleLoginServlet extends HttpServlet {
    
    private static final String CLIENT_ID = "928535412886-n4pqhk06a6daqp0hbqalk32dfdff62a2.apps.googleusercontent.com";
    private static final String CLIENT_SECRET = "feMXIUB6ZwGwhTWZiPQrUqUo";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            ServiceBuilder sBuilder = new ServiceBuilder();
            OAuthService service = (OAuthService) sBuilder.provider(Google2Api.class)
                        .apiKey(CLIENT_ID)
                        .apiSecret(CLIENT_SECRET)
                        .callback("http://localhost:8080/EyeHospitalManagement/oauth2callback")
                        .scope("openid profile email "+
                                "https://www.googleapis.com/auth/plus.login "+
                                "https://www.googleapis.com/auth/plus.me")
                        .debug()
                        .build();
            
            request.getSession().setAttribute("oauth2Service", service);
            response.sendRedirect(service.getAuthorizationUrl(null));
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
