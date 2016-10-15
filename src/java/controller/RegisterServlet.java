/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.crce.wtlabs.dto.Patient;
import org.crce.wtlabs.dto.User;
import org.crce.wtlabs.impl.UserDaoImpl;
import org.crce.wtlabs.util.Messenger;

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
            String to = request.getParameter("email");
            // Sender's email ID needs to be mentioned
            String sub = "Hey we just mailed you !";
            String text = "and this is crazy\nSo here's your account\nMail us maybe\n\nYour verification code is : " + vcode;
            Messenger.sendMessage(to,sub,text);
            
            request.getSession().setAttribute("user", u);
            request.getSession().setAttribute("patient", p);
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
