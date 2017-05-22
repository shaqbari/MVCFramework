<%@page import="mvc.model.BloodAdviser"%>
<%@ page contentType="Text/html;charset=utf-8" %>
<!--MVC에서 컨트롤러의 역할?
MVC는 디자인과 로직을 분리하여 개발해야 유집수성과 재사용성이 높아진다는 이론이다.
이때 로직과 디자인이 분리되려면, 컨트롤러라는 중간자가 필요하게 된다.

컨트롤러의 업무 영역
- 요청을 받는다.
- 요청을 분석한다.!(요청의 유형이 다양하므로)
- 알맞은 로직 객체에게 일 시킨다.
- 결과가 있다면 결과를 볼수 있도록 저장한다.
- 결과를 보여준다.

  -->
<%
	//1. 요청을 받는다.
	//2. 요청을 분석한다.(현재는 blood요청만 들어오므로 분석할 것이 없다.)
	//넘겨받은 파라미터를 분석하여, 알맞은 피드백 보여주기
	String blood=request.getParameter("blood");
	
/*-------------------------------------------------------------------------
	여기서부턴 Model Model을 분리하자. 이외에는 Controller
*/

/* String msg=null;
	if(blood.equals("A")){
		msg="소심+세심하고 꼼꼼하다.";
		
	}else if(blood.equals("B")){
		msg="주관이 뚜렷하고 고집이 세다.";
		
	}else if(blood.equals("AB")){
		msg="예측불가하고 발랄하며 종잡을 수 없다.";
		
	}else if(blood.equals("O")){
		msg="성격 좋고, 쓸데없이 오지랖이 넓다.";
		
	} */

/*-------------------------------------------------------------------------  */		
	//3. 알맞은 객체에 일을 시킨다.
	BloodAdviser adviser=new BloodAdviser();
	String msg=adviser.getAdvice(blood);

	//4.결과가 있다면 저장해 놓는다(왜? 현재 페이지와는 다른 페이지에서 결과를 보여줘야 하기 때문에)
	//요청객체에 원하는 데이터를 심자!
	request.setAttribute("data", msg);
	
	//5.결과를 보여준다.(여기선 알맞은 page로 결과 보낸다.)
	//현재 페이지에서 응답을 하지 말고, 현재 요청을 다른 jsp에게 그대로 전달해보자!!=포워딩
	RequestDispatcher dis=request.getRequestDispatcher("result.jsp");
	dis.forward(request, response);//포워딩 발생시점
		
%>
<%--
나중에 뷰를 변경하려면 로직까지 다 바꿔야 한다.
뷰를 분리하자.

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
</html> --%>
