package control;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

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
public class SanPham extends HttpServlet {

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
        String type = request.getParameter("type");
        List<products> list;
        if (type == null || type.isEmpty()) {
            list = a.getAll(); // 🔥 mặc định
        } else {
            list = a.getProductBySort(type); // 🔥 có sort
        }
        int page = 1;
        int pageSize = 8;
        String indexPage = request.getParameter("page");
        if (indexPage != null) {
            page = Integer.parseInt(indexPage);
        }
        int totalProduct = list.size();
        int endPage = totalProduct / pageSize;
        if (totalProduct % pageSize != 0) {
            endPage++;
        }
        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, totalProduct);
        int totalProducts ;
        if (type == null || type.isEmpty()) {
            totalProducts = a.getAll().size();
        } else {
            totalProducts = a.getProductBySort(type).size();
        }
        List<products> listPage = list.subList(start, end);
        List<products> listLast = a.getLast();
        List<cate> listc = a.getCategory();
        List<Brand> listb = a.getAllBrand();
        request.setAttribute("listP", listPage);
        request.setAttribute("currentPage", page);
        request.setAttribute("endPage", endPage);
        request.setAttribute("totalProducts", totalProducts);
        request.setAttribute("listc", listc);
        request.setAttribute("listN", listLast);
        request.setAttribute("listb", listb);
        request.setAttribute("sortType", type);
        request.setAttribute("cID", null);
        request.setAttribute("bID", null);
         HttpSession session = request.getSession();
        User user = (User) session.getAttribute("acc");
        gioHang cart = new gioHang();
        if (user != null) {
            gioHang userCart = a.getCartByUserId(user.getId());
            if (userCart != null) {
                cart = userCart;
            }
        }
        request.setAttribute("cart", cart);
        request.getRequestDispatcher("giaodien.jsp").forward(request, response);
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
