package controller;

import entity.Account;
import entity.Category;
import entity.Product;
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
import java.util.List;

@WebServlet(name = "loginControlller", value = "/login")
public class loginControlller extends HttpServlet {
    private final ProductService productService = new ProductService();
    private final AccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getParameter("path");
        if (path == null) {
            path = "";
        }
        switch (path) {
            case "signup":
                signAccount(req, resp);
                break;
            case "login":
                accountlogin(req, resp);
                break;
            default:
                break;
        }
    }


    private void accountlogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/View/login.jsp");
        dispatcher.forward(req, resp);
    }

    private void signAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/View/signup.jsp");
        dispatcher.forward(req, resp);

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
