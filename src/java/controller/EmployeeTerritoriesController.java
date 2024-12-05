/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.EmployeeTerritories;
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
import model.DAOEmployeeTerritories;

/**
 *
 * @author haitr
 */
@WebServlet(name = "EmployeeTerritoriesController", urlPatterns = {"/EmployeeTerritoriesURL"})
public class EmployeeTerritoriesController extends HttpServlet {

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
        DAOEmployeeTerritories dao = new DAOEmployeeTerritories();
        try (PrintWriter out = response.getWriter()) {
            //display all products
            // service ?
            String service = request.getParameter("service");
            if (service == null) {
                service = "listAll";
            }
            if (service.equals("deleteEmployeeTerritories")) {
                dao.deleteEmpTer(Integer.parseInt(request.getParameter("eid")));
                response.sendRedirect("EmployeeTerritoriesURL?service=listAll");
            }
            if (service.equals("insertEmployeeTerritories")) {
                //get data
                String submit = request.getParameter("submit");
                if (submit == null) {//show insert form
                    ResultSet rsEmp = dao.getData("SELECT EmployeeID,LastName  FROM Employees");
                    ResultSet rsTer = dao.getData("SELECT TerritoryID,TerritoryDescription FROM Territories");
                    request.setAttribute("rsEmp", rsEmp);
                    request.setAttribute("rsTer", rsTer);
                    dispath(request, response, "/JSP/insertEmployeeTerritories.jsp");
                } else {
                    //if (submit != null) {
                    int EmployeeID = Integer.parseInt(request.getParameter("EmployeeID"));
                    String TerritoryID = request.getParameter("TerritoryID");
                    
                    

                    EmployeeTerritories et = new EmployeeTerritories(EmployeeID, TerritoryID);
                    int n = dao.addEmpTer(et);
                    response.sendRedirect("EmployeeTerritoriesURL");
                }

            }
            if (service.equals("updateEmployeeTerritories")) {
                //get data
                String submit = request.getParameter("submit");
                if (submit == null) {
                    int eid = Integer.parseInt(request.getParameter("eid"));
                    Vector<EmployeeTerritories> vector = dao.getEmpTer("SELECT * FROM EmployeeTerritories where EmployeeID=" + eid);

                    ResultSet rsEmp = dao.getData("SELECT EmployeeID,LastName  FROM Employees");
                    ResultSet rsTer = dao.getData("SELECT TerritoryID,TerritoryDescription FROM Territories");
                    request.setAttribute("rsEmp", rsEmp);
                    request.setAttribute("rsTer", rsTer);
                    request.setAttribute("vector", vector);
                    dispath(request, response, "/JSP/updateEmployeeTerritories.jsp");
                } else {
                    int EmployeeID = Integer.parseInt(request.getParameter("EmployeeID"));
                    String TerritoryID = request.getParameter("TerritoryID");
                    
                    

                    EmployeeTerritories et = new EmployeeTerritories(EmployeeID, TerritoryID);
                    int n = dao.updateEmpTer(et);
                    response.sendRedirect("EmployeeTerritoriesURL");
                }

            }
            
            if (service.equals("listAll")) {//request, service
                //call model
                String submit = request.getParameter("submit");
                String sql = "";
                if (submit == null) {  //khong phai search, hien toan bo
                    sql = "select * from EmployeeTerritories";
                } else { //search
                    String eid = request.getParameter("eid");
                    sql = "select * from EmployeeTerritories where EmployeeID = " + eid;
                }
                Vector<EmployeeTerritories> vector = dao.getEmpTer(sql);
                //select view: jsp
                RequestDispatcher dispath = request.getRequestDispatcher("JSP/listEmployeeTerritories.jsp");
                //set data for view
                request.setAttribute("dataEmployeeTerritories", vector);
                request.setAttribute("titleTable", "EmployeeTerritories Manage");
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
