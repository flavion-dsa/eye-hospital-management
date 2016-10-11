/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.crce.wtlabs.dto.Patient;
import org.crce.wtlabs.dto.User;
import org.crce.wtlabs.impl.UserDaoImpl;

/**
 *
 * @author Flav
 */
public class RegisterServlet extends HttpServlet {

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
            
            User u = new User();
            u.setName(request.getParameter("email"));
            u.setPassword(request.getParameter("password"));
            u.setType(1);
            
            Random random = new Random();
            int vcode = 1000+random.nextInt(8999);
            u.setVcode(vcode);
            
            UserDaoImpl uDaoImpl = new UserDaoImpl();
            uDaoImpl.addUser(u);
            
            Patient p = new Patient();
            p.setFirstName(request.getParameter("first-name"));
            p.setLastName(request.getParameter("last-name"));
            p.setContact(request.getParameter("phone"));
            p.setEmail(request.getParameter("email"));
            
            // Recipient's email ID needs to be mentioned.
            String email = request.getParameter("email");
            // Sender's email ID needs to be mentioned
            String from = "org.crce.wtlabs.7371@gmail.com";
            
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");

            Session session = Session.getInstance(properties,
                    new javax.mail.Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(from, "WTproject@7371");
                        }
                    });
            
            try {
                // Create a default MimeMessage object.
                MimeMessage message = new MimeMessage(session);
                // Set From: header field of the header.
                message.setFrom(new InternetAddress(from));
                // Set To: header field of the header.
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
                // Set Subject: header field
                message.setSubject("Hey we just mailed you !");
                // Now set the actual message
                message.setText("and this is crazy\nSo here's your account\nMail us maybe\n\nYour verification code is : " + vcode);
                // Send message
                Transport.send(message);
                System.out.println("Message sent successfully...");
                
            } catch (MessagingException ex) {
                Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getServletContext().setAttribute("vcode", vcode);
            request.getServletContext().setAttribute("user", u);
            request.getServletContext().setAttribute("patient", p);
            RequestDispatcher view = request.getRequestDispatcher("JSP/verify.jsp");
            view.forward(request, response);
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
