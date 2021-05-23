package controller;

import service.FoodService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "list",value = "/list")
public class ListFoodServlet extends HttpServlet {
    private FoodService foodService = new FoodService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        req.setAttribute("count",foodService.countFood());
        req.setAttribute("foods",foodService.getList());
        req.getRequestDispatcher("/admin/list.jsp").forward(req, resp);
    }
}
