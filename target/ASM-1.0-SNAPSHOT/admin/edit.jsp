<%@ page import="entity.Category" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.Food" %><%--
  Created by IntelliJ IDEA.
  User: hoa
  Date: 5/23/2021
  Time: 9:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Food food = (Food) request.getAttribute("currentFood");
    String validateString = (String) request.getAttribute("validateString");
    ArrayList<Category> categories = (ArrayList<Category>) request.getAttribute("categories");
%>
<html>
<head>
    <title>Sửa món ăn</title>
    <!-- Latest compiled and minified CSS -->
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
<h4 class="text-center">Sửa Món Ăn</h4>
<div style="width: 80vw; margin: 0 auto">
    <p class="text-danger"><%=validateString%>
    </p>
    <form method="post" action="/edit">
        <div class="form-group">
            <label for="nameFood">Tên món ăn </label>
            <input type="text" class="form-control" id="nameFood" value="<%=food.getNameFood()%>" placeholder="Nhập tên món ăn" name="nameFood" required>
        </div>
        <div class="form-group">
            <label for="categoryId">Danh mục</label>
            <select class="form-control" id="categoryId" name="categoryId">
                <%for (Category category:categories) { %>
                <option value=<%=category.getId()%>><%=category.getNameCategory()%></option>
                <%}%>
            </select>
        </div>
        <div class="form-group">
            <label for="description">Mô tả </label>
            <textarea class="form-control" id="description" name="description"  placeholder="" rows="3" required><%=food.getDescription()%></textarea>
        </div>
<%--        <div class="form-group">--%>
<%--            <label for="thumbnail">Link ảnh đại diện</label>--%>
<%--            <input type="text" class="form-control" id="thumbnail"  value="<%=food.getThumbnail()%>" placeholder="Nhập link ảnh" name="thumbnail" required>--%>
<%--        </div>--%>

        <div class="form-group">
            <button type="button" id="upload_widget" class="btn btn-primary">Thêm ảnh
            </button>
            <input hidden type="text" name="picture" id="picture" value="<%=food.getThumbnail()%>">
            <div class="thumbnails"></div>
        </div>


        <div class="form-group">
            <label for="price">Giá</label>
            <input type="number" class="form-control" id="price" value="<%=food.getPrice()%>" placeholder="Nhập giá tiền" name="price" required>
        </div>
        <div class="form-group">
            <label>Ngày Bắt Đầu Bán</label>
            <input type="date" class="form-control" name="sellStartAt" value="<%=food.getSellStartAt()%>" placeholder="Nhập ngày bán" required>
        </div>
        <div class="form-group">
            <label for="status">Trạng thái</label>
            <select class="form-control" id="status" name="status">

                <option value="1">Đang Bán</option>
                <option value="2">Dừng Bán</option>

            </select>
        </div>
        <input hidden type="text" name="id" value="<%=food.getId()%>">
        <button type="submit" class="btn btn-primary">Sửa</button>
    </form>
</div>
</body>
</html>

<script src="https://upload-widget.cloudinary.com/global/all.js" type="text/javascript"></script>

<script type="text/javascript">
    var myWidget = cloudinary.createUploadWidget(
        {
            cloudName: 'hoadaica',
            uploadPreset: 'hoadaica',
            multiple: false,
            form: '#product_form',
            fieldName: 'thumbnail',
            thumbnails: '.thumbnails'
        }, function (error, result) {
            if (!error && result && result.event === "success") {
                $('#picture').val(result.info.url);
            }
        }
    );
    document.getElementById("upload_widget").addEventListener("click", function(){
        myWidget.open();
    }, false);
    // xử lý js trên dynamic content.

</script>