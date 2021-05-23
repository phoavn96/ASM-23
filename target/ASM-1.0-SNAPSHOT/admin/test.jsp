<%--
  Created by IntelliJ IDEA.
  User: hoa
  Date: 5/23/2021
  Time: 2:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style>
        body{
            background-color: aliceblue;
        }
    </style>
</head>
<body>

<form id="product_form" action="">
    <div class="form-group">
        <button type="button" id="upload_widget" class="btn btn-primary">Thêm ảnh
        </button>
        <input type="text" name="picture" id="picture" value="">
        <div class="thumbnails"></div>
    </div>
</form>
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
</body>
</html>
