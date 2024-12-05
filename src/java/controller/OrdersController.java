/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Orders;
import entity.Suppliers;
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
import model.DAOOrder;

/**
 *
 * @author haitr
 */
@WebServlet(name = "OrdersController", urlPatterns = {"/OrdersURL"})
public class OrdersController extends HttpServlet {

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
        DAOOrder dao = new DAOOrder();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("service");
            if (service == null) {
                service = "listAll";
            }
            if (service.equals("listAll")) {//request, service
                //call model
                String submit = request.getParameter("submit");
                String sql = "";
                if (submit == null) {  //khong phai search, hien toan bo
                    sql = "select * from Orders";
                } else { //search
                    String orderID = request.getParameter("orderID");
                    sql = "select * from Orders where OrderID =" + orderID;
                }
                Vector<Orders> vector = dao.getOrders(sql);
                //select view: jsp
                RequestDispatcher dispath = request.getRequestDispatcher("JSP/listOrders.jsp");
                //set data for view
                request.setAttribute("dataOrders", vector);
                request.setAttribute("titleTable", "Orders Manage");
                //run
                dispath.forward(request, response);

            }
            if (service.equals("deleteOrders")) {
                dao.deleteOrder(Integer.parseInt(request.getParameter("oid")));
                response.sendRedirect("OrdersURL?service=listAll");
            }
            if (service.equals("insertOrders")) {
                String submit = request.getParameter("submit");
                if (submit == null) {//show insert form
                    ResultSet rsCus = dao.getData("SELECT CustomerID,CompanyName  FROM Customers");
                    ResultSet rsEmploy = dao.getData("SELECT EmployeeID,LastName FROM Employees");
                    ResultSet rsShip = dao.getData("SELECT ShipperID,CompanyName FROM shippers");
                    request.setAttribute("rsCus", rsCus);
                    request.setAttribute("rsEmploy", rsEmploy);
                    request.setAttribute("rsShip", rsShip);
                    dispath(request, response, "/JSP/insertOrders.jsp");
                } else {
                    String CustomerID = request.getParameter("CustomerID");
                    int EmployeeID = Integer.parseInt(request.getParameter("EmployeeID"));
                    String OrderDate = request.getParameter("OrderDate");
                    String RequiredDate = request.getParameter("RequiredDate");
                    String ShippedDate = request.getParameter("ShippedDate");
                    int ShipVia = Integer.parseInt(request.getParameter("ShipVia"));
                    double Freight = Double.parseDouble(request.getParameter("Freight"));
                    String ShipName = request.getParameter("ShipName");
                    String ShipAddress = request.getParameter("ShipAddress");
                    String ShipCity = request.getParameter("ShipCity");
                    String ShipRegion = request.getParameter("ShipRegion");
                    String ShipPostalCode = request.getParameter("ShipPostalCode");
                    String ShipCountry = request.getParameter("ShipCountry");

                    Orders order = new Orders(0, CustomerID, EmployeeID, OrderDate, RequiredDate,
                            ShippedDate, ShipVia, Freight, ShipName, ShipAddress, ShipCity,
                            ShipRegion, ShipPostalCode, ShipCountry);
                    int n = dao.insertOrder(order);
                    response.sendRedirect("OrdersURL");
                }
            }
            if (service.equals("updateOrders")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    int oid = Integer.parseInt(request.getParameter("oid"));
                    Vector<Orders> vector = dao.getOrders("SELECT * FROM Orders where OrderID=" + oid);

                    ResultSet rsCus = dao.getData("SELECT CustomerID,CompanyName  FROM Customers");
                    ResultSet rsEmploy = dao.getData("SELECT EmployeeID,LastName FROM Employees");
                    ResultSet rsShip = dao.getData("SELECT ShipperID,CompanyName FROM Shippers");
                    request.setAttribute("rsCus", rsCus);
                    request.setAttribute("rsEmploy", rsEmploy);
                    request.setAttribute("rsShip", rsShip);
                    request.setAttribute("vector", vector);
                    dispath(request, response, "/JSP/updateOrders.jsp");
                } else {
                    int OrderID = Integer.parseInt(request.getParameter("OrderID"));
                    String CustomerID = request.getParameter("CustomerID");
                    int EmployeeID = Integer.parseInt(request.getParameter("EmployeeID"));
                    String OrderDate = request.getParameter("OrderDate");
                    String RequiredDate = request.getParameter("RequiredDate");
                    String ShippedDate = request.getParameter("ShippedDate");
                    int ShipVia = Integer.parseInt(request.getParameter("ShipVia"));
                    double Freight = Double.parseDouble(request.getParameter("Freight"));
                    String ShipName = request.getParameter("ShipName");
                    String ShipAddress = request.getParameter("ShipAddress");
                    String ShipCity = request.getParameter("ShipCity");
                    String ShipRegion = request.getParameter("ShipRegion");
                    String ShipPostalCode = request.getParameter("ShipPostalCode");
                    String ShipCountry = request.getParameter("ShipCountry");

                    Orders order = new Orders(OrderID, CustomerID, EmployeeID, OrderDate, RequiredDate,
                            ShippedDate, ShipVia, Freight, ShipName, ShipAddress, ShipCity,
                            ShipRegion, ShipPostalCode, ShipCountry);
                    int n = dao.updateOrder(order);
                    response.sendRedirect("OrdersURL");
                }
            }
            if (service.equals("searchCustomerID")) {
                String cid = request.getParameter("cid");
                String sql = "select * from Orders where CustomerID like '%" + cid + "%'";
                Vector<Orders> vector = dao.getOrders(sql);
                RequestDispatcher dispath = request.getRequestDispatcher("JSP/listOrders.jsp");

                request.setAttribute("dataOrders", vector);
                request.setAttribute("titleTable", "Orders Manage");

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
