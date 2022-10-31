/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.jsu.mcis.lab6.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author tanto
 */
public class AttendeeDAO {
    private final DAOFactory daoFactory;
    
    private final String QUERY_SELECT_BY_ID = "SELECT * FROM "
            + "attendee WHERE id = ?";
    
    private final String QUERY_CREATE = "INSERT INTO attendee (firstname, lastname, displayname) VALUES (?,?,?)";
    
    
    private final String QUERY_UPDATE = "UPDATE attendee SET firstname = ?, lastname = ?, displayname = ? WHERE id = ?";
    
    private final String QUERY_REGISTRATION_NUMBER = "SELECT CONCAT(\"R\", LPAD(attendeeid, 6, 0)) AS num FROM registration WHERE attendeeid = ?";
    
    AttendeeDAO(DAOFactory dao) {
        this.daoFactory = dao;
    }
    
    public String find(int attendeeid) {

        JSONObject json = new JSONObject();
        json.put("success", false);

        Connection conn = daoFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            ps = conn.prepareStatement(QUERY_SELECT_BY_ID);
            ps.setInt(1, attendeeid);
            
            boolean hasresults = ps.execute();

            if (hasresults) {

                rs = ps.getResultSet();
                
                if (rs.next()) {
                    
                    json.put("success", hasresults);
                    
                    json.put("firstname", rs.getString("firstname"));
                    json.put("lastname", rs.getString("lastname"));
                    json.put("displayname", rs.getString("displayname"));
                                        
                }

            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {

            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                }
                catch (Exception e) { e.printStackTrace(); }
            }
            if (ps != null) {
                try {
                    ps.close();
                    ps = null;
                }
                catch (Exception e) { e.printStackTrace(); }
            }
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                }
                catch (Exception e) { e.printStackTrace(); }
            }

        }

        json.put("registrationnumber", getRegistrationNum(attendeeid));
        return JSONValue.toJSONString(json);

    }
    
    public String create(String firstname, String lastname, String displayname) {
                JSONObject json = new JSONObject();

                json.put("success", false);
                PreparedStatement ps = null;
                ResultSet rs = null;

                try {
                        Connection conn = daoFactory.getConnection();

                        ps = conn.prepareStatement(QUERY_CREATE, Statement.RETURN_GENERATED_KEYS);

                        ps.setString(1, firstname);
                        ps.setString(2, lastname);
                        ps.setString(3, displayname);
                        int updateCount = ps.executeUpdate();

                        if (updateCount > 0) {
                                json.put("success", true);
                                json.put("rowsAffected", updateCount);
                                json.put("message", "Attendee updated successfully!");

                        }
                        else {
                            json.put("message", "No exception, but update query failed.");
                        }

                } catch (Exception e) {
                        e.printStackTrace();
                }

                finally {

                        if (rs != null) {
                                try {
                                        rs.close();
                                } catch (Exception e) {
                                        e.printStackTrace();
                                }
                        }
                        if (ps != null) {
                                try {
                                        ps.close();
                                } catch (Exception e) {
                                        e.printStackTrace();
                                }
                        }

                }
                return JSONValue.toJSONString(json);
        }
    
    public String update(String firstname, String lastname, String displayname, int id) {
                JSONObject json = new JSONObject();

                json.put("success", false);
                PreparedStatement ps = null;
                ResultSet rs = null;

                try {
                        Connection conn = daoFactory.getConnection();

                        ps = conn.prepareStatement(QUERY_UPDATE);

                        ps.setString(1, firstname);
                        ps.setString(2, lastname);
                        ps.setString(3, displayname);
                        ps.setInt(4, id);
                        int updateCount = ps.executeUpdate();

                        if (updateCount > 0) {
                                json.put("success", true);
                                json.put("rowsAffected", updateCount);

                        }

                } catch (Exception e) {
                        e.printStackTrace();
                }

                finally {

                        if (rs != null) {
                                try {
                                        rs.close();
                                } catch (Exception e) {
                                        e.printStackTrace();
                                }
                        }
                        if (ps != null) {
                                try {
                                        ps.close();
                                } catch (Exception e) {
                                        e.printStackTrace();
                                }
                        }

                }
                return JSONValue.toJSONString(json);
        }
    
    private String getRegistrationNum(int attendeeid) {

                Connection conn = daoFactory.getConnection();
                PreparedStatement ps = null;
                ResultSet rs = null;
                String result = "";
                try {

                        ps = conn.prepareStatement(QUERY_REGISTRATION_NUMBER);
                        ps.setInt(1, attendeeid);

                        boolean hasresults = ps.execute();

                        if (hasresults) {

                                rs = ps.getResultSet();

                                if (rs.next()) {

                                        result = rs.getString("num");

                                }

                        }

                } catch (Exception e) {
                        e.printStackTrace();
                } finally {

                        if (rs != null) {
                                try {
                                        rs.close();
                                        rs = null;
                                } catch (Exception e) {
                                        e.printStackTrace();
                                }
                        }
                        if (ps != null) {
                                try {
                                        ps.close();
                                        ps = null;
                                } catch (Exception e) {
                                        e.printStackTrace();
                                }
                        }
                        if (conn != null) {
                                try {
                                        conn.close();
                                        conn = null;
                                } catch (Exception e) {
                                        e.printStackTrace();
                                }
                        }

                }
                return result;
        }
    
}
