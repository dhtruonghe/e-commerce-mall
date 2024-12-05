/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Region;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAORegion;

/**
 *
 * @author haitr
 */
@WebServlet(name = "RegionController", urlPatterns = {"/RegionURL"})
public class RegionController extends HttpServlet {

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
        DAORegion dao = new DAORegion();

        String service = request.getParameter("service");
        if (service == null) {
            service = "listAll";
        }
        if (service.equals("deleteRegion")) {
            dao.deleteRegion(Integer.parseInt(request.getParameter("rid")));
            response.sendRedirect("RegionURL?service=listAll");
        }
        if (service.equals("insertRegion")) {
            String submit = request.getParameter("submit");
            if (submit == null) {//show insert form
                dispath(request, response, "/JSP/insertRegion.jsp");

            } else {
                String RegionDescription = request.getParameter("RegionDescription");

                Region region = new Region(0, RegionDescription);
                int n = dao.insertRegion(region);
                response.sendRedirect("RegionURL");
            }
        }
        if (service.equals("listAll")) {

            String submit = request.getParameter("submit");
            String sql = "";
            if (submit == null) {
                sql = "select * from Region";
            } else {
                String rDescrip = request.getParameter("rDescrip");
                sql = "SELECT * from Region where RegionDescription like '%" + rDescrip + "%'";
            }
            Vector<Region> vector = dao.getRegions(sql);
            RequestDispatcher dispath = request.getRequestDispatcher("/JSP/listRegion.jsp");

            request.setAttribute("dataRegion", vector);
            request.setAttribute("titleTable", "Region manage");

            dispath.forward(request, response);
        }
        if (service.equals("updateRegion")) {
            String submit = request.getParameter("submit");
            if (submit == null) {
                int rid = Integer.parseInt(request.getParameter("rid"));
                Vector<Region> vector = dao.getRegions("SELECT * FROM Region where RegionID=" + rid);
                request.setAttribute("vector", vector);
                dispath(request, response, "/JSP/updateRegion.jsp");
            } else {
                int RegionID = Integer.parseInt(request.getParameter("RegionID"));

                String RegionDescription = request.getParameter("RegionDescription");

                Region region = new Region(RegionID, RegionDescription);
                int n = dao.updateRegion(region);
                response.sendRedirect("RegionURL");
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
