package controller;

import entity.Category;
import entity.Product;
import service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductController", value = "/home")
public class ProductController extends HttpServlet {
    private final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getParameter("path");
        if (path == null) {
            path = "";
        }
        switch (path) {
            case "home":
                break;
            case "category":
                showCategory(req, resp);
                break;
            case "delete":
                deleteProduct(req, resp);
                break;
            default:
                showHome(req, resp);
                break;
        }
    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idDelete = Integer.parseInt(req.getParameter("id"));
        productService.delete(idDelete);
        resp.sendRedirect("/home");
    }

    private void showCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categories = productService.getAllCategori();
        req.setAttribute("categories", categories);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/View/home.jsp");
        dispatcher.forward(req, resp);
    }

    private void showHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> list = productService.getAll();
        req.setAttribute("products", list);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/View/home.jsp");
        dispatcher.forward(req, resp);
    }
}
