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
import org.crce.wtlabs.dto.User;
import org.crce.wtlabs.impl.UserDaoImpl;
import org.crce.wtlabs.util.Encrypter;

/**
 *
 * @author Flav
 */
public class LoginServlet extends HttpServlet {

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
            
            response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
            response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
            response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility
            
            System.out.println("..Login Servlet");
            
            String password = request.getParameter("password");
            Encrypter encrypter = new Encrypter();
            
            User user = new User();
            user.setName(request.getParameter("email"));
            user.setPassword(encrypter.encrypt(password));
            
            UserDaoImpl userDaoImpl = new UserDaoImpl();
            
            if(userDaoImpl.isValid(user)) {
                User oldUser = userDaoImpl.getUser(user.getName());
                
                if(userDaoImpl.isVerified(user)) {   

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

                } else {
                    request.getRequestDispatcher("JSP/reverify.jsp").forward(request, response);
                }
                
            }
            else {
                response.sendRedirect("JSP/login.jsp");
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
