/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Territories;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.util.Vector;
import model.DAOTerritories;

/**
 *
 * @author haitr
 */
@WebServlet(name = "TerritoriesController", urlPatterns = {"/TerritoriesURL"})
public class TerritoriesController extends HttpServlet {

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
        DAOTerritories dao = new DAOTerritories();
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service == null) {
                service = "listAll";
            }
            if (service.equals("deleteTerritories")) {
                dao.deleteTerritory(request.getParameter("tid"));
                response.sendRedirect("TerritoriesURL?service=listAll");
            }
            if (service.equals("insertTerritories")) {
                //get data
                String submit = request.getParameter("submit");
                if (submit == null) {//show insert form
                    ResultSet rsRegion = dao.getData("SELECT RegionID,RegionDescription FROM Region");
                    request.setAttribute("rsRegion", rsRegion);
                    dispath(request, response, "/JSP/insertTerritories.jsp");
                } else {
                    //if (submit != null) {
                    String TerritoryDescription = request.getParameter("TerritoryDescription");
                    int RegionID = Integer.parseInt(request.getParameter("RegionID"));
                    

                    Territories territory = new Territories("", 
                            TerritoryDescription,RegionID);

                    int n = dao.addTerritory(territory);
                    response.sendRedirect("TerritoriesURL");
                }

            }

            if (service.equals("listAll")) {//request, service
                //call model
                String submit = request.getParameter("submit");
                String sql = "";
                if (submit == null) {  //khong phai search, hien toan bo
                    sql = "select * from Territories";
                } else { //search
                    String companyName = request.getParameter("companyName");
                    sql = "select * from Territories where CompanyName like '%" + companyName + "%'";
                }
                Vector<Territories> vector = dao.getTerritories(sql);
                //select view: jsp
                RequestDispatcher dispath = request.getRequestDispatcher("JSP/listTerritories.jsp");
                //set data for view
                request.setAttribute("dataTerritories", vector);
                request.setAttribute("titleTable", "Territories Manage");
                //run
                dispath.forward(request, response);

            }

            if (service.equals("updateTerritories")) {
                //get data
                String submit = request.getParameter("submit");
                if (submit == null) {
                    int tid = Integer.parseInt(request.getParameter("tid"));
                    Vector<Territories> vector = dao.getTerritories("SELECT * FROM Territories where TerritoryID=" + tid);

                    ResultSet rsRegion = dao.getData("SELECT RegionID,RegionDescription FROM Region");
                    request.setAttribute("rsRegion", rsRegion);
                    request.setAttribute("vector", vector);
                    dispath(request, response, "/JSP/updateTerritories.jsp");
                } else {
                    //if (submit != null) {
                    String TerritoryID = request.getParameter("TerritoryID");
                    String TerritoryDescription = request.getParameter("TerritoryDescription");
                    int RegionID = Integer.parseInt(request.getParameter("RegionID"));
                    

                    Territories territory = new Territories(TerritoryID, 
                            TerritoryDescription,RegionID);

                    int n = dao.addTerritory(territory);
                    response.sendRedirect("TerritoriesURL");
                }

            }
        }
    }

    protected void dispath(HttpServletRequest request,
            HttpServletResponse response, String page)
            throws ServletException, IOException {
//         RequestDispatcher dis
//                        = request.getRequestDispatcher(page);
//         dis.forward(request, response);
        request.getRequestDispatcher(page).forward(request, response);

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
