package edu.jsu.mcis.lab6;

import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;

import edu.jsu.mcis.lab6.dao.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.HashMap;


public class RegistrationServlet extends HttpServlet {

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
            
            int sessionid = Integer.parseInt(request.getParameter("sessionid"));
            int attendeeid = Integer.parseInt(request.getParameter("attendeeid"));
            
            RegistrationDAO dao = daoFactory.getRegistrationDAO();
            
            out.println(dao.find(sessionid, attendeeid));
            
            
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
            
            AttendeeDAO attendeeDAO = daoFactory.getAttendeeDAO();

            RegistrationDAO dao = daoFactory.getRegistrationDAO();

            // check if id parameter
            if (request.getParameterMap().containsKey("sessionid") && request.getParameterMap().containsKey("sessionid")) {

                int sessionid = Integer.parseInt(request.getParameter("sessionid"));
                int attendeeid = Integer.parseInt(request.getParameter("attendeeid"));
                out.println(dao.create(sessionid, attendeeid));
                
            } else if (request.getParameterMap().containsKey("firstname") && request.getParameterMap().containsKey("lastname") && request.getParameterMap().containsKey("displayname")) {

                // get parameters
                int sessionid = Integer.parseInt(request.getParameter("sessionmenu"));
                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                String displayname = request.getParameter("displayname");

                // get attendee id if attendee is not a new attendee
                if (attendeeDAO.findID(firstname, lastname) != 0) {

                    int attendeeid = attendeeDAO.findID(firstname, lastname);

                    // call create fuction
                    System.err.println("attendee id=" + attendeeid);
                    out.println(dao.create(sessionid, attendeeid));
                    response.sendRedirect("Registration.jsp");
                }
                // create a new attendee
                else {
                    // create new attendee
                    attendeeDAO.create(firstname, lastname, displayname);

                    // getid
                    int attendeeid = attendeeDAO.findID(firstname, lastname);
                    // make registration
                    out.println(dao.create(sessionid, attendeeid));
                    // redirect
                    response.sendRedirect("Registration.jsp");
                }

            } else {

                Exception notvalidException = new Exception("Args not valid");
                throw notvalidException;

            }
            response.sendRedirect("Registration.jsp");
        
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
            
            int oldSessionID = Integer.parseInt(parameters.get("oldSessionID"));
            int oldAttendeeID = Integer.parseInt(parameters.get("oldAttendeeID"));
            int updatedSessionID = Integer.parseInt(parameters.get("updatedSessionID"));
            int updatedAttendeeID = Integer.parseInt(parameters.get("updatedAttendeeID"));
            
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
            
            RegistrationDAO dao = daoFactory.getRegistrationDAO();
            
            //System.err.println("Registering ...");
            
            out.println(dao.update(oldAttendeeID, oldSessionID, updatedAttendeeID, updatedSessionID));
        
        }
        
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        
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
            
            int sessionID = Integer.parseInt(parameters.get("sessionid"));
            int attendeeID = Integer.parseInt(parameters.get("attendeeid"));
            
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
            
            RegistrationDAO dao = daoFactory.getRegistrationDAO();
            
            out.println(dao.delete(attendeeID, sessionID));
        
        }
        
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Registration Servlet";
    }

}