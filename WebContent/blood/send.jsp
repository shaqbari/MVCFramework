<%@ page contentType="Text/html;charset=utf-8" %>
<!doctype html>
<html>
<head>
	<title>Document</title>
	<meta charset="utf-8">
	<script >
		function send(){
			form1.method="get";
			form1.action="/blood.do";
			form1.submit();
			
			//요청타입을 .do앞에 표현하는 것이 좋다. ?requestType=blood이런식으로 쓰면 일일이 확인해야 한다.
			
		}
		
	</script>
	
</head>
<body>
	<form name="form1">
		<select name="blood">
			<option value="A">A형</option>
			<option value="B">B형</option>
			<option value="AB">AB형</option>
			<option value="O">O형</option>
		</select>
		<input type="button" value="전송" onclick="send();"/>
	</form>
</body>
</html>
