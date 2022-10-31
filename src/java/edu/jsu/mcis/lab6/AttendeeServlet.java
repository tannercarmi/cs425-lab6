/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.jsu.mcis.lab6;

import edu.jsu.mcis.lab6.dao.DAOFactory;
import edu.jsu.mcis.lab6.dao.AttendeeDAO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tanto
 */
public class AttendeeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        
        DAOFactory daoFactory = null;

        ServletContext context = request.getServletContext();

        if (context.getAttribute("daoFactory") == null) {
            System.err.println("*** Creating new DAOFactory ...");
            daoFactory = new DAOFactory();
            context.setAttribute("daoFactory", daoFactory);
        }
        else {
            daoFactory = (DAOFactory) context.getAttribute("daoFactory");
        }
        
        response.setContentType("application/json; charset=UTF-8");
        
        try ( PrintWriter out = response.getWriter()) {
            
            int attendeeid = Integer.parseInt(request.getParameter("attendeeid"));
            
            AttendeeDAO dao = daoFactory.getAttendeeDAO();
            
            out.println(dao.find(attendeeid));
            
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        DAOFactory daoFactory = null;
        
        ServletContext context = request.getServletContext();
        
        if (context.getAttribute("daoFactory") == null) {
            System.err.println("*** Creating new DAOFactory ...");
            daoFactory = new DAOFactory();
            context.setAttribute("daoFactory", daoFactory);
        }
        else {
            daoFactory = (DAOFactory) context.getAttribute("daoFactory");
        }
        
        response.setContentType("application/json; charset=UTF-8");
        
        try ( PrintWriter out = response.getWriter()) {
            
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String displayname = request.getParameter("displayname");
            
            AttendeeDAO dao = daoFactory.getAttendeeDAO();
            
            out.println(dao.create(firstname, lastname, displayname));
        
        }
        
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        BufferedReader br = null;
        response.setContentType("application/json; charset=UTF-8");
        
        try ( PrintWriter out = response.getWriter()) {
            
            br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String p = URLDecoder.decode(br.readLine().trim(), Charset.defaultCharset());
            
            HashMap<String, String> parameters = new HashMap<>();
            
            String[] pairs = p.trim().split("&");
            
            for (int i = 0; i < pairs.length; ++i) {
                String[] pair = pairs[i].split("=");
                parameters.put(pair[0], pair[1]);
            }
            
            String firstname = parameters.get("firstname");
            String lastname = parameters.get("lastname");
            String displayname = parameters.get("displayname");
            int id = Integer.parseInt(parameters.get("id"));
            
            //System.err.println("Old: " + oldSessionID + "/" + oldAttendeeID + "; New: " + updatedSessionID + "/" + updatedAttendeeID);
            
            DAOFactory daoFactory = null;
        
            ServletContext context = request.getServletContext();
        
            if (context.getAttribute("daoFactory") == null) {
                System.err.println("*** Creating new DAOFactory ...");
                daoFactory = new DAOFactory();
                context.setAttribute("daoFactory", daoFactory);
            }
            else {
                daoFactory = (DAOFactory) context.getAttribute("daoFactory");
            }
            
            AttendeeDAO dao = daoFactory.getAttendeeDAO();
            
            //System.err.println("Registering ...");
            
            out.println(dao.update(firstname, lastname, displayname, id));
        
        }
        
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        // TODO doDelete method
    }

    @Override
    public String getServletInfo() {
        return "Attendee Servlet";
    }

}
