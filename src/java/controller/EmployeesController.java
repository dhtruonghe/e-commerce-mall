/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Employees;
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
import model.DAOEmployee;

/**
 *
 * @author haitr
 */
@WebServlet(name = "EmployeesController", urlPatterns = {"/EmployeesURL"})
public class EmployeesController extends HttpServlet {

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
        DAOEmployee dao = new DAOEmployee();
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service == null) {
                service = "listAll";
            }
            if (service.equals("deleteEmployees")) {
                dao.deleteEmp(Integer.parseInt(request.getParameter("eid")));
                response.sendRedirect("EmployeesURL?service=listAll");
            }
            if (service.equals("insertEmployees")) {
                //get data
                String submit = request.getParameter("submit");
                if (submit == null) {//show insert form
                    
                    dispath(request, response, "/JSP/insertEmployees.jsp");
                } else {
                    //if (submit != null) {
                    String LastName = request.getParameter("LastName");
                    String FirstName = request.getParameter("FirstName");
                    String Title = request.getParameter("Title");
                    String TitleOfCourtesy = request.getParameter("TitleOfCourtesy");
                    String BirthDate = request.getParameter("BirthDate");
                    String HireDate = request.getParameter("HireDate");
                    String Address = request.getParameter("Address");
                    String City = request.getParameter("City");
                    String Region = request.getParameter("Region");
                    String PostalCode = request.getParameter("PostalCode");
                    String Country = request.getParameter("Country");
                    String HomePhone = request.getParameter("HomePhone");
                    String Extension = request.getParameter("Extension");
                    String Photo = request.getParameter("Photo");
                    String Notes = request.getParameter("Notes");
                    int ReportsTo = Integer.parseInt(request.getParameter("ReportsTo"));
                    String PhotoPath = request.getParameter("PhotoPath");
                    
                    Employees employee = new Employees(0, LastName, FirstName, 
                    Title, TitleOfCourtesy, BirthDate, HireDate, Address, City,
                    Region, PostalCode, Country, HomePhone, Extension, Photo, 
                    Notes, ReportsTo, PhotoPath);
                    int n = dao.addEmployee(employee);
                    response.sendRedirect("EmployeesURL");
                }

            }
            if (service.equals("updateEmployees")) {
                //get data
                String submit = request.getParameter("submit");
                if (submit == null) {//show insert form
                    int eid = Integer.parseInt(request.getParameter("eid"));
                    Vector<Employees> vector = dao.getEmployees("select * from Employees where EmployeeID = "+eid);
                    request.setAttribute("vector", vector);
                    dispath(request, response, "/JSP/updateEmployees.jsp");
                } else {
                    int EmployeeID = Integer.parseInt(request.getParameter("EmployeeID"));
                    String LastName = request.getParameter("LastName");
                    String FirstName = request.getParameter("FirstName");
                    String Title = request.getParameter("Title");
                    String TitleOfCourtesy = request.getParameter("TitleOfCourtesy");
                    String BirthDate = request.getParameter("BirthDate");
                    String HireDate = request.getParameter("HireDate");
                    String Address = request.getParameter("Address");
                    String City = request.getParameter("City");
                    String Region = request.getParameter("Region");
                    String PostalCode = request.getParameter("PostalCode");
                    String Country = request.getParameter("Country");
                    String HomePhone = request.getParameter("HomePhone");
                    String Extension = request.getParameter("Extension");
                    String Photo = request.getParameter("Photo");
                    String Notes = request.getParameter("Notes");
                    int ReportsTo = Integer.parseInt(request.getParameter("ReportsTo"));
                    String PhotoPath = request.getParameter("PhotoPath");
                    
                    Employees employee = new Employees(EmployeeID, LastName, FirstName, 
                    Title, TitleOfCourtesy, BirthDate, HireDate, Address, City,
                    Region, PostalCode, Country, HomePhone, Extension, Photo, 
                    Notes, ReportsTo, PhotoPath);
                    int n = dao.addEmployee(employee);
                    response.sendRedirect("EmployeesURL");
                }

            }
            
            if (service.equals("listAll")) {//request, service
                //call model
                String submit = request.getParameter("submit");
                String sql = "";
                if (submit == null) {  //khong phai search, hien toan bo
                    sql = "select * from Employees";
                } else { //search
                    String fname = request.getParameter("fname");
                    sql = "select * from Employees where FirstName like '%" + fname + "%'";
                }
                Vector<Employees> vector = dao.getEmployees(sql);
                //select view: jsp
                RequestDispatcher dispath = request.getRequestDispatcher("JSP/listEmployees.jsp");
                //set data for view
                request.setAttribute("dataEmployees", vector);
                request.setAttribute("titleTable", "Employees Manage");
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
