/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Categories;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOCategories;

/**
 *
 * @author haitr
 */
@WebServlet(name = "CategoriesController", urlPatterns = {"/CategoriesURL"})
public class CategoriesController extends HttpServlet {

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
        DAOCategories dao = new DAOCategories();

        String service = request.getParameter("service");
        if (service == null) {
            service = "listAll";
        }
        if (service.equals("deleteCategories")) {
            dao.deleteCategories(Integer.parseInt(request.getParameter("cid")));
            response.sendRedirect("CategoriesURL?service=listAll");
        }
        if (service.equals("insertCategories")) {
            String submit = request.getParameter("submit");
            if (submit == null) {
                
                dispath(request, response, "/JSP/insertCategories.jsp");
            } else {
                String CategoryName = request.getParameter("CategoryName");
                String Description = request.getParameter("Description");
                String Picture = request.getParameter("Picture");

                Categories categories = new Categories(0, CategoryName, Description, Picture);
                int n = dao.insertCategories(categories);
                response.sendRedirect("CategoriesURL");
            }
        }
        if (service.equals("listAll")) {

            String submit = request.getParameter("submit");
            String sql = "";
            if (submit == null) {
                sql = "select * from Categories";
            } else {
                String cname = request.getParameter("cname");
                sql = "SELECT * from Categories where CategoryName like '%" + cname + "%'";
            }
            Vector<Categories> vector = dao.getCategorieses(sql);
            RequestDispatcher dispath = request.getRequestDispatcher("/JSP/listCategory.jsp");

            request.setAttribute("dataCategories", vector);
            request.setAttribute("titleTable", "Categories manage");

            dispath.forward(request, response);
        }
        if (service.equals("updateCategories")) {
            String submit = request.getParameter("submit");
            if (submit == null) {
                int cid = Integer.parseInt(request.getParameter("cid"));
                Vector<Categories> vector = dao.getCategorieses("SELECT * FROM Categories where CategoryID=" + cid);
                request.setAttribute("vector", vector);
                dispath(request, response, "/JSP/updateCategories.jsp");
            } else {
                int CategoryID = Integer.parseInt(request.getParameter("CategoryID"));
                String CategoryName = request.getParameter("CategoryName");
                String Description = request.getParameter("Description");
                String Picture = request.getParameter("Picture");
                Categories categories = new Categories(CategoryID, CategoryName, Description, Picture);
                int n = dao.updateCategories(   categories);
                response.sendRedirect("CategoriesURL");
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
