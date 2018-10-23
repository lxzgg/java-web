<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<form name="Form2" action="/file" method="post" enctype="multipart/form-data">
    <h1>使用spring mvc提供的类的方法上传文件</h1>
    <input type="file" name="file" multiple>
    <input type="submit" value="upload"/>
</form>
</body>
</html>
