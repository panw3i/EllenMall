<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"  %>
<html>
<body>
<h2>Hello World!</h2>

springmvc上传文件
<form name="form1" action ="/manage/product/upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file">
    <input type="submit" value="springmvc上传文件到服务器">
</form>

富文本的图片上传
<form name="form1" action ="/manage/product/richtext_img_upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file">
    <input type="submit" value="富文本的图片上传到服务器">
</form>
</body>
</html>
