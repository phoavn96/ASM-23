package controller;

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
@WebServlet(name ="edit",value = "/edit")
public class UpdateFoodServlet extends HttpServlet {
    private CategoryService categoryService = new CategoryService();
    private FoodService foodService = new FoodService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("validateString", "Vui lòng điền đầy đủ thông tin!");
        req.setAttribute("currentFood",foodService.findById(Integer.parseInt(req.getParameter("id"))));
        req.setAttribute("categories", categoryService.getList());
        req.getRequestDispatcher("/admin/edit.jsp").forward(req,resp);
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
        if(foodService.edit(Integer.parseInt(req.getParameter("id")),food)){
            req.setAttribute("count",foodService.countFood());
            req.setAttribute("foods",foodService.getList());
            req.getRequestDispatcher("/admin/list.jsp").forward(req,resp);

        }else{
            req.setAttribute("validateString","Tên món ăn phải lớn hơn 7 ký tự, giá không được để trống");
            req.setAttribute("currentFood",foodService.findById(Integer.parseInt(req.getParameter("id"))));
            req.setAttribute("categories", categoryService.getList());
            req.getRequestDispatcher("/admin/edit.jsp").forward(req,resp);
        }


    }
}
