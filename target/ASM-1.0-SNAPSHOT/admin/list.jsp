<%@ page import="entity.Food" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.Category" %>
<%@ page import="service.CategoryService" %><%--
  Created by IntelliJ IDEA.
  User: hoa
  Date: 5/19/2021
  Time: 8:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<Food> foods = (ArrayList<Food>) request.getAttribute("foods");
    Category category = new Category();
%>
<html>
<head>
    <title>List Foods</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style>
        body{
            background: red;
            background: linear-gradient(cornflowerblue, lavenderblush);


        }
    </style>
</head>
<body>
<div class="container">
    <div class="">
        <h4 class="text-center">List Foods</h4>
        <button class = "btn-success"style="border: 2px;"><a href="/create"style="color: aliceblue">Thêm</a></button>
        <table class="table" style="height: 500px;">
            <thead class="table-info">
            <tr  class="text-center">
                <th scope="col">Mã</th>
                <th scope="col">Tên món ăn</th>
                <th scope="col" style="width: 10%">Danh mục</th>
                <th scope="col">Mô tả</th>
                <th scope="col">Thumbnail</th>
                <th scope="col">Giá</th>
                <th scope="col">Ngày bắt đầu bán</th>
                <th scope="col">Ngày cập nhật</th>
                <th scope="col">Trạng thái</th>
                <th scope="col">Chức năng</th>
            </tr>
            </thead>
            <tbody>
            <%
                for (int i = 0; i < foods.size(); i++) {
                    Food food = foods.get(i);
            %>
            <tr class="contentPage">
                <td scope="col" style="text-align: center"><%=food.getId()%>
                </td>
                <td scope="col" style="text-align: center"><%=food.getNameFood()%>
                </td>
                <td scope="col" style="text-align: center"><%=category.getNameFromId(food.getCategoryId()) %>
                </td>
                <td scope="col" style="text-align: center"><%=food.getDescription()%>
                </td>
                <td scope="col" style="text-align: center">
                    <div style="width: 100px; height: 100px; background-repeat: no-repeat; background-position: center; background-size: cover; background-image: url('<%=food.getThumbnail()%>')"></div>
                </td>
                <td scope="col" style="text-align: center"><%=food.getPrice()%> VNĐ
                </td>
                <td scope="col"
                    style="text-align: center"><%= food.getSellStartAt()%>
                </td>
                <td scope="col"
                    style="text-align: center"><%=food.getUpdateAt()%>
                </td>
                <td scope="col" style="text-align: center"><%=food.toStatus(food.getStatus())%>
                </td>
                <% if (food.getStatus() == 0) { %>
                <td scope="col" style="text-align: center">
                    <div class="btn-group">


                    </div>
                </td>
                <% } else { %>
                <td scope="col" style="text-align: center">
                    <div class="btn-group">
                        <a href="/edit?id=<%=food.getId()%>" class="btn btn-warning">Sửa</a>
                        <a href="/delete?id=<%=food.getId()%>" class="btn btn-danger">Xóa</a>
                    </div>
                </td>
                <% } %>

            </tr>
            <%}%>
            </tbody>
        </table>
    </div>
</div>
<ul id="pagination"></ul>
</body>
</html>


<script src="http://1892.yn.lt/blogger/JQuery/Pagging/js/jquery.twbsPagination.js" type="text/javascript"></script>

<script type="text/javascript">
    $(function () {
        var pageSize = 5; // Hiển thị 5 sản phẩm trên 1 trang
        showPage = function (page) {
            $(".contentPage").hide();
            $(".contentPage").each(function (n) {
                if (n >= pageSize * (page - 1) && n < pageSize * page)
                    $(this).show();
            });
        }
        showPage(1);

        var totalRows = <%=request.getAttribute("count")%>; // Tổng số sản phẩm hiển thị
        var btnPage = 5; // Số nút bấm hiển thị di chuyển trang
        var iTotalPages = Math.ceil(totalRows / pageSize);

        var obj = $('#pagination').twbsPagination({
            totalPages: iTotalPages,
            visiblePages: btnPage,
            onPageClick: function (event, page) {
                console.info(page);
                showPage(page);
            }
        });
        console.info(obj.data());
    });
</script>
<style>


    #pagination {
        display: flex;
        display: -webkit-flex; /* Safari 8 */
        flex-wrap: wrap;
        -webkit-flex-wrap: wrap; /* Safari 8 */
        justify-content: center;
        -webkit-justify-content: center;
    }
</style>


