package controller;

import entity.*;
import service.AccountService;
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

@WebServlet(name = "UserController", value = "/user")
public class UserController extends HttpServlet {
    private final ProductService productService = new ProductService();
    private final AccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        String path = req.getParameter("path");
        if (path == null) {
            path = "";
        }
        switch (path) {
            case "user":
                userProducts(req, resp);
                break;
            case "login":
                accountlogin(req, resp);
                break;
            case "showCategory":
                showCategoryUser(req, resp);
                break;
            case "detailProduct":
                showDetail(req, resp);
                break;
            case "category":
                showCategory(req, resp);
                break;
            case "search":
                searchProduct(req, resp);
                break;
            case "order":
                orderProduct(req, resp);
                break;
            default:
                showHomeUser(req, resp);
                break;
        }
    }

    private void orderProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int quantity = 1;
        int id;
        if (request.getParameter("id") != null) {
            id = Integer.parseInt(request.getParameter("id"));
            Product product = productService.findById(id);

            if (product != null) {
                if (request.getParameter("quantity") != null) {
                    quantity = Integer.parseInt(request.getParameter("quantity"));
                }

                HttpSession session = request.getSession();

                if (session.getAttribute("cart") == null) {
                    Order order = new Order();
                    List<Item> itemList = new ArrayList<>();
                    Item item = new Item();
                    item.setQuantity(quantity);
                    item.setProduct(product);
                    item.setPrice(product.getPrice());
                    itemList.add(item);
                    order.setItems(itemList);
                    session.setAttribute("cart", order);
                } else {

                    Order order = (Order) session.getAttribute("cart");
                    List<Item> itemList = order.getItems();
                    boolean isProductInCart = false;
                    for (Item item : itemList) {
                        if (item.getProduct().getId() == product.getId()) {
                            item.setQuantity(item.getQuantity() + quantity);
                            isProductInCart = true;
                            break;
                        }
                    }

                    if (!isProductInCart) {
                        Item item = new Item();
                        item.setQuantity(quantity);
                        item.setProduct(product);
                        item.setPrice(product.getPrice());
                        itemList.add(item);
                    }

                    session.setAttribute("cart", order);
                }
                Order cart = (Order) session.getAttribute("cart");
                if (cart != null) {
                    int totalQuantity = cart.getItems().stream()
                            .mapToInt(Item::getQuantity)
                            .sum();
                    session.setAttribute("totalQuantity", totalQuantity);
                }
            }
            response.sendRedirect(request.getContextPath() + "/user");
        } else {
            response.sendRedirect(request.getContextPath() + "/user");
        }
    }

    private void searchProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        String name = req.getParameter("keyword");
        String price = req.getParameter("keyword");
        double pricePrameter = 0;
        try {
            if (price != null && !price.isEmpty()) {
                pricePrameter = Double.parseDouble(price);
            }
        } catch (NumberFormatException e) {
            pricePrameter = 0;
        }
        List<Product> products = productService.findByName(name, pricePrameter);
        List<Category> categories = productService.getAllCategory();
        req.setAttribute("categories", categories);
        req.setAttribute("products", products);
        req.setAttribute("txt", name);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/View/user.jsp");
        dispatcher.forward(req, resp);
    }

    private void showCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idCategory = Integer.parseInt(req.getParameter("id"));
        List<Product> products = productService.getAllCategori(idCategory);
        req.setAttribute("products", products);
        List<Category> categories = productService.getAllCategory();
        req.setAttribute("categories", categories);
        req.setAttribute("tag", idCategory);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/View/user.jsp");
        dispatcher.forward(req, resp);
    }

    private void showDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productService.findById(id);
        List<Category> categories = productService.getAllCategory();
        req.setAttribute("product", product);
        req.setAttribute("categories", categories);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/View/detailProduct.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void accountlogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/View/login.jsp");
        dispatcher.forward(req, resp);
    }

    private void showCategoryUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int idCategory = Integer.parseInt(req.getParameter("id"));
        List<Product> products = productService.getAllCategori(idCategory);
        req.setAttribute("products", products);
        List<Category> categories = productService.getAllCategory();
        req.setAttribute("categories", categories);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/View/user.jsp");
        dispatcher.forward(req, resp);
    }

    private void showHomeUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> list = productService.getAll();
        List<Category> categories = productService.getAllCategory();
        req.setAttribute("categories", categories);
        req.setAttribute("products", list);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/View/user.jsp");
        dispatcher.forward(req, resp);
    }

    private void userProducts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/user.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getParameter("path");
        if (path == null) {
            path = "";
        }
        switch (path) {
            case "login":
                loginAccount(req, resp);
                break;
                default:
                    showError(req,resp);
                    break;
        }
    }

    private void showError(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void loginAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accounts = req.getParameter("user");
        String password = req.getParameter("password");
        System.out.println(accounts);
        System.out.println(password);
        Account account = accountService.login(accounts, password);
        System.out.println(account);
        System.out.println(password);
        if (account == null) {
            req.setAttribute("mess", "Sai Mật Khẩu");
            System.out.println("abc");
            req.getRequestDispatcher("/View/login.jsp").forward(req, resp);
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("account", account);
            session.setMaxInactiveInterval(100);
            resp.sendRedirect(req.getContextPath() + "/user");
        }
    }
}
