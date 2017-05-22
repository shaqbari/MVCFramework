<%@page import="mvc.model.MovieAdviser"%>
<%@ page contentType="Text/html;charset=utf-8" %>
<%

		request.setCharacterEncoding("utf-8");
	/*
		1. 요청을 받는다.
		2. 요청을 분석한다.(선택)
		3. 알맞은 로직 객체에 일을 시킨다.
		4. 결과가 있다면 결과를 저장(선택)
		5. 결과를 보여준다.
		*/
		
		//1.요청을 받는다.
		String movie=request.getParameter("movie");

		//3.알맞은 로직 객체에 일을 시킨다.
		MovieAdviser adviser=new MovieAdviser();
		String msg=adviser.getAdvice(movie);
		
		//4단계 결과가 있다면 결과를 저장
		request.setAttribute("data", msg);
		
		//5단계 결과보여주기
		RequestDispatcher dis=request.getRequestDispatcher("result.jsp");
		dis.forward(request, response);
		

%>