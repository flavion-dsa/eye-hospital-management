/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.crce.wtlabs.dto.User;
import org.crce.wtlabs.impl.UserDaoImpl;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

/**
 *
 * @author Flav
 */
public class OAuth2CallbackServlet extends HttpServlet {

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
            
            String error = request.getParameter("error");
            
            if((error != null) && (error.trim().equals("access_denied"))) {
                request.getSession().invalidate();
                response.sendRedirect("JSP/login.jsp");
                return;
            }
            
            AsyncContext context = request.startAsync();
            context.start(new GetUserInfo(request, response, context));
            
            synchronized(context) {
                System.out.println("Waiting for login details");
                context.wait();
            }

            // Login
            User user = new User();
            user.setName((String) request.getSession().getAttribute("email"));
            
            UserDaoImpl userDaoImpl = new UserDaoImpl();
            User oldUser = userDaoImpl.getUser(user.getName());
            
            if(oldUser == null) {
                request.getSession().invalidate();
                response.sendRedirect("JSP/register.jsp");
                return;
            }
            
            user.setType(oldUser.getType());
            user.setVcode(oldUser.getVcode());
            
            request.getSession().setAttribute("user", user);
            
            switch (user.getType()) {
                case 0:
                    response.sendRedirect("JSP/admin.jsp");
                    break;
                case 1:
                    response.sendRedirect("JSP/patient.jsp");
                    break;
                case 2:
                    response.sendRedirect("JSP/doctor.jsp");
                    break;
                case 3:
                    response.sendRedirect("JSP/chemist.jsp");
            }
            
        } catch (InterruptedException ex) {
            Logger.getLogger(OAuth2CallbackServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public class GetUserInfo implements Runnable {

        private HttpServletRequest request;
        private HttpServletResponse response;
        private AsyncContext context;

        public GetUserInfo(HttpServletRequest request, HttpServletResponse response, AsyncContext context) {
            this.request = request;
            this.response = response;
            this.context = context;
        }
       
        @Override
        public void run() {
            
            OAuthService service = (OAuthService) request.getSession().getAttribute("oauth2Service");
            
            // Get authorization code
            String code = request.getParameter("code");
            // Construct acess token
            Token token = service.getAccessToken(null, new Verifier(code));
            // Save token for the session
            request.getSession().setAttribute("token", token);
            
            /*try {
                // Perform proxy login
                request.login("WTLabs", "WTProject");
            } catch (ServletException ex) {
                Logger.getLogger(OAuth2CallbackServlet.class.getName()).log(Level.SEVERE, null, ex);
            }*/
            
            OAuthRequest oRequest = new OAuthRequest(Verb.GET, "https://www.googleapis.com/oauth2/v2/userinfo");
            service.signRequest(token, oRequest);
            Response oResponse = oRequest.send();
            
            JsonReader reader = Json.createReader(new ByteArrayInputStream(oResponse.getBody().getBytes()));
            JsonObject profile = reader.readObject();
            
            request.getSession().setAttribute("name", profile.getString("name"));
            request.getSession().setAttribute("email", profile.getString("email"));

            context.complete();
            
            synchronized(context) {
                context.notifyAll();
            }
            
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
