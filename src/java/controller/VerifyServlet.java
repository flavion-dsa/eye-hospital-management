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
import org.crce.wtlabs.dto.Patient;
import org.crce.wtlabs.dto.User;
import org.crce.wtlabs.impl.PatientDaoImpl;
import org.crce.wtlabs.impl.UserDaoImpl;

/**
 *
 * @author Flav
 */
public class VerifyServlet extends HttpServlet {

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
            
            User u = (User) request.getSession().getAttribute("user");
            Patient p = (Patient) request.getSession().getAttribute("patient");
            
            UserDaoImpl uDaoImpl = new UserDaoImpl();
            User oldUser = uDaoImpl.getUser(u.getName());
           
            int vcode = oldUser.getVcode();
            int passcode = Integer.parseInt(request.getParameter("passcode"));
            
            PatientDaoImpl pDaoImpl = new PatientDaoImpl();    
            pDaoImpl.addPatient(p);
                
            if(vcode == passcode) {
               
                pDaoImpl.updateUser(u);
                request.getSession().setAttribute("user", null);
                request.getSession().setAttribute("patient", null);
                
                response.sendRedirect("JSP/login.jsp");
                
            } else {
                request.getRequestDispatcher("JSP/verify.jsp").include(request, response);
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
