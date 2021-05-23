package controller;

import entity.Category;
import entity.Food;
import service.CategoryService;
import service.FoodService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

@WebServlet(name = "create",value = "/create")
public class CreateFoodServlet extends HttpServlet {

    private CategoryService categoryService = new CategoryService();
    private FoodService foodService = new FoodService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("validateString", "Vui lòng điền đầy đủ thông tin!");
        req.setAttribute("categories", categoryService.getList());
        req.getRequestDispatcher("/admin/create.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Food food = new Food();
        food.setNameFood(req.getParameter("nameFood"));
        food.setCategoryId(Integer.parseInt(req.getParameter("categoryId")));
        food.setDescription(req.getParameter("description"));
        food.setPrice(Double.valueOf(req.getParameter("price")));
        food.setSellStartAt(Date.valueOf(req.getParameter("sellStartAt")));
        food.setThumbnail(req.getParameter("picture"));
        food.setStatus(Integer.parseInt(req.getParameter("status")));
        if(foodService.create(food)){
            req.setAttribute("foods",foodService.getList());
            req.setAttribute("count",foodService.countFood());
            req.getRequestDispatcher("/admin/list.jsp").forward(req,resp);

        }else{
            req.setAttribute("validateString","Tên món ăn phải lớn hơn 7 ký tự, giá không được để trống");
            req.setAttribute("categories", categoryService.getList());
            req.getRequestDispatcher("/admin/create.jsp").forward(req,resp);
        }


    }
}
