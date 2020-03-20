/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import db.DBAccess;
import db.DBManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sun.security.pkcs11.Secmod;

/**
 * 
 * @author timon_kaufmann
 */
@WebServlet(name = "BookstoreDispather", urlPatterns = {"/BookstoreDispather"})
public class BookstoreDispather extends HttpServlet {
    private BookstoreModel model = BookstoreModel.getInstance();
    private DBManager dbm = DBManager.getInstance();
    private DBAccess dba = DBAccess.getInstance();

    @Override
    public void init(ServletConfig config)
            throws ServletException {
        super.init(config);
        try {
            config.getServletContext().setAttribute("publisherList", model.getAllPublisher());
            config.getServletContext().setAttribute("offset", "0");
            List<Book> books = new ArrayList<>();
            config.getServletContext().setAttribute("books", books);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("jsp/bookstoreView.jsp").forward(request, response);
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
        
        try {
            String cmd = request.getParameter("cmd");
            System.out.println("Hansii");
            switch(cmd){
                case "Next":
                    System.out.println("Willkommen");
                    doFindNext(request,response);
                    break;
                case "Previous":
                    doFindPrevious(request,response);
                    break;
                case "findboock":
                    doFindBook(request,response);
                    break;
                default:
                    request.setAttribute("errorMsg", "Kommand falsch --> " + cmd);
                    processRequest(request, response);
                    return;
            }
        } catch (Exception e) {
            request.setAttribute("errorMsg", e.getMessage());
            processRequest(request, response);
        }
        
        processRequest(request, response);
    }
    private void doFindNext(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String idstr = getServletContext().getAttribute("offset").toString(); 
        Integer id = Integer.parseInt(idstr);
        id = id+3; 
        getServletContext().setAttribute("offset", id+"");  
    }
    
     private void doFindPrevious(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String idstr = getServletContext().getAttribute("offset").toString(); 
        Integer id = Integer.parseInt(idstr);
        id = id-3; 
         System.out.println("HI");
        getServletContext().setAttribute("offset", id+""); 
    }
     
    private void doFindBook(HttpServletRequest request, HttpServletResponse response) throws Exception {
          
        String idname =request.getParameter("ausgabe"); 
        System.out.println("Welcome: " + idname);
        String[] arraysplit = idname.split("#");
        int index = Integer.parseInt(arraysplit[0]);
        List<Book> books = model.getAllBooks(index);
        getServletContext().setAttribute("books", books);
        System.out.println(arraysplit[1]);
        getServletContext().setAttribute("publishername", arraysplit[1]);
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
