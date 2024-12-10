package controller;

import entity.Category;
import entity.Item;
import entity.Order;
import entity.Product;
import service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
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
            case "edit":
                editProduct(req, resp);
                break;
            case "create":
                addProduct(req, resp);
                break;

            default:
                showHome(req, resp);
                break;
        }
    }




    private void addProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categoryList = productService.getAllCategory();
        req.setAttribute("categories", categoryList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/View/create.jsp");
        dispatcher.forward(req, resp);
    }


    private void editProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categoryList = productService.getAllCategory();
        req.setAttribute("categories", categoryList);
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productService.findById(id);
        req.setAttribute("product", product);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/View/edit.jsp");
        dispatcher.forward(req, resp);
    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idDelete = Integer.parseInt(req.getParameter("id"));
        productService.delete(idDelete);
        resp.sendRedirect("/home");
    }

    private void showCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idCategory = Integer.parseInt(req.getParameter("id"));
        List<Product> products = productService.getAllCategori(idCategory);
        req.setAttribute("products", products);
        List<Category> categories = productService.getAllCategory();
        req.setAttribute("categories", categories);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/View/home.jsp");
        dispatcher.forward(req, resp);
    }

    private void showHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> list = productService.getAll();
        List<Category> categories = productService.getAllCategory();
        req.setAttribute("categories", categories);
        req.setAttribute("products", list);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/View/home.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
            case "edit":
                editProducts(req, resp);
                break;
            case "create":
                addProducts(req, resp);
                break;
            default:
                showHome(req, resp);
                break;
        }
    }

    private void addProducts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        int id = Integer.parseInt(req.getParameter("id"));
//        System.out.println(id);
        String name = req.getParameter("name");
        System.out.println(name);
        String img = req.getParameter("image");
        double price = Double.parseDouble(req.getParameter("price"));
        String description = req.getParameter("description");
        int categorysId = Integer.parseInt(req.getParameter("categoryMethod"));
        Category category = new Category(categorysId);
        Product product = new Product(name, img, price, description, category);
        productService.add(product);
        resp.sendRedirect("/home");
    }

    private void editProducts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        System.out.println(id);
        String name = req.getParameter("name");
        System.out.println(name);
        String img = req.getParameter("image");
        double price = Double.parseDouble(req.getParameter("price"));
        String description = req.getParameter("description");
        int categorysId = Integer.parseInt(req.getParameter("categoryMethod"));
        Category category = new Category(categorysId);
        Product product = new Product(id, name, img, price, description, category);
        productService.update(id, product);
        resp.sendRedirect("/home");
    }
}
