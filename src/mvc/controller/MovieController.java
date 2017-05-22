package mvc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.model.MovieAdviser;

public class MovieController implements Controller{
	MovieAdviser movieAdviser;
	
	public MovieController() {
		movieAdviser=new MovieAdviser();
	}
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String movie=request.getParameter("movie");
		
		//3�ܰ� ��ü�� �� ��Ű��
		String msg=movieAdviser.getAdvice(movie);
		
		//4�ܰ� �������
		request.setAttribute("data", msg);
		
		//5�ܰ� ��� �����ֱ�
		RequestDispatcher dis=request.getRequestDispatcher("/movie/result.jsp");
		dis.forward(request, response);
		
		
		
	}
}
