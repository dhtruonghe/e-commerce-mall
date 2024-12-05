/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Shippers;
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
import model.DAOShippers;

/**
 *
 * @author haitr
 */
@WebServlet(name = "ShippersController", urlPatterns = {"/ShippersURL"})
public class ShippersController extends HttpServlet {

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
        DAOShippers dao = new DAOShippers();
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service == null) {
                service = "listAll";
            }
            if (service.equals("deleteShippers")) {
                dao.deleteShipper(Integer.parseInt(request.getParameter("sid")));
                response.sendRedirect("ShippersURL?service=listAll");
            }
            if (service.equals("insertShippers")) {
                //get data
                String submit = request.getParameter("submit");
                if (submit == null) {
                    dispath(request, response, "/JSP/insertShippers.jsp");
                } else {
                    //if (submit != null) {
                    String CompanyName = request.getParameter("CompanyName");
                    String Phone = request.getParameter("Phone");
                    
                    Shippers shipper = new Shippers(0, CompanyName,
                            Phone);
                    int n = dao.addShipper(shipper);
                    response.sendRedirect("ShippersURL");
                }

            }
            if (service.equals("updateShippers")) {
                //get data
                String submit = request.getParameter("submit");
                if (submit == null) {
                    int sid = Integer.parseInt(request.getParameter("sid"));
                    Vector<Shippers> vector = dao.getShipperses("Select * from Shippers where ShipperID = " +sid);
                    request.setAttribute("vector", vector);
                    dispath(request, response, "/JSP/updateShippers.jsp");
                } else {
                    //if (submit != null) {
                    int ShipperID = Integer.parseInt(request.getParameter("ShipperID"));
                    String CompanyName = request.getParameter("CompanyName");
                    String Phone = request.getParameter("Phone");
                    
                    Shippers shipper = new Shippers(ShipperID, CompanyName,
                            Phone);
                    int n = dao.addShipper(shipper);
                    response.sendRedirect("ShippersURL");
                }

            }
            
            if (service.equals("listAll")) {//request, service
                //call model
                String submit = request.getParameter("submit");
                String sql = "";
                if (submit == null) {  //khong phai search, hien toan bo
                    sql = "select * from Shippers";
                } else { //search
                    String cname = request.getParameter("cname");
                    sql = "select * from Shippers where CompanyName like '%" + cname + "%'";
                }
                Vector<Shippers> vector = dao.getShipperses(sql);
                //select view: jsp
                RequestDispatcher dispath = request.getRequestDispatcher("JSP/listShippers.jsp");
                //set data for view
                request.setAttribute("dataShippers", vector);
                request.setAttribute("titleTable", "Shippers Manage");
                //run
                dispath.forward(request, response);

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
