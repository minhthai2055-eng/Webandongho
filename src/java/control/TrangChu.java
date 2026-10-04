/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import DAO.dao1;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest; 
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import sanpham.Brand;
import sanpham.User;
import sanpham.cate;
import sanpham.gioHang;
import sanpham.products;

/**
 *
 * @author ADMIN
 */
public class TrangChu extends HttpServlet {

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
        dao1 a = new dao1();
        List<products> list = a.getAll();
        List<products> listLast = a.getLast();
        List<cate> listc = a.getCategory();
        List<Brand> listb = a.getAllBrand();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("acc");
        gioHang cart = new gioHang();
        if (user != null) {
            gioHang userCart = a.getCartByUserId(user.getId());
            if (userCart != null) {
                cart = userCart;
            }
        }
        String error = (String) session.getAttribute("error");

        if(error != null){
            request.setAttribute("error", error);
            session.removeAttribute("error");
        }
        request.setAttribute("cart", cart);
        request.setAttribute("listP", list);
        request.setAttribute("listc", listc);
        request.setAttribute("listN", listLast);
        request.setAttribute("listb", listb);
        request.setAttribute("listHot", a.getHotProduct());
        request.setAttribute("listSale", a.getSaleProduct());
        request.getRequestDispatcher("trangchu.jsp").forward(request, response);
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
