/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.jsu.mcis.lab6;

import edu.jsu.mcis.lab6.dao.DAOFactory;
import edu.jsu.mcis.lab6.dao.SessionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tanto
 */
public class SessionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        
        DAOFactory daoFactory = null;

        ServletContext context = request.getServletContext();

        if (context.getAttribute("daoFactory") == null) {
            System.err.println("*** Creating new DAOFactory ...");
            daoFactory = new DAOFactory();
            context.setAttribute("daoFactory", daoFactory);
        } else {
            daoFactory = (DAOFactory) context.getAttribute("daoFactory");
        }

        response.setContentType("application/json; charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            SessionDAO dao = daoFactory.getSessionDAO();

            //check for id parameter, call sessionFind() if id, if null call sessionList()  
            if (request.getParameterMap().containsKey("id")) {
                int id = Integer.parseInt(request.getParameter("id"));
                out.println(dao.sessionFind(id));
            }

            else{
                
                out.println(dao.sessionList());
            }
        }
        catch (Exception e){
            e.printStackTrace();
            
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        // TODO doPost()
    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        // TODO doPut()
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        // TODO doDelete()
    }

    @Override
    public String getServletInfo() {
        return "Session Servlet";
    }

}
