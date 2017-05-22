<%@ page contentType="Text/html;charset=utf-8" %>
<!doctype html>
<html>
<head>
	<title>Document</title>
	<meta charset="utf-8">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

</head>
<body bgcolor="yellow">
	<%String msg=(String)request.getAttribute("data"); %>
	<%=msg %>
</body>
</html>
