package mvc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.model.BloodAdviser;

/**
 * @author user1
 * 현재 우리의 MVC패턴을 적용한 어플리케이션에서는 모든 요청을 받는 진입점을 하나로 두고,
 * 이 컨트롤러에 의해 모든 요청을 처리하는 방식이었다.
 * 하지만, 하나의 클래스가 모든 요청을 처리하다 보니, 각 요청분석시 if문으로 처리해야 하는 문제가 발생했다.
 * 이게 왜 문제인가? 요청의 종류가 기하 급수적으로 늘어나면 if문 블럭도 같이 늘어나,
 *  유지보수할 때 코드의 일부를 수정해야 하는 불편함이 있다. 즉 유연성이 떨어진다.
 *  해결책??
 *	GOF가 소개한 Command Pattern을 적용해 본다.
 *	Command 패턴이란? Command는 '명령'이란 뜻인데, 여기서는 명령보다는 '요청'으로 생각하자.
 *	즉 요청마다 1:1대응하는 if문으로 처리하지 말고, 객체화 시켜 객체로 처리하자.  
 *
 * 
 */
public class BloodController implements Controller{
	BloodAdviser adviser;
	
	public BloodController() {
		adviser=new BloodAdviser();
	}
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String blood=request.getParameter("blood");

		//3단계: 알맞은 비즈니스 로직 객체에 일 시킨다.
		String msg=adviser.getAdvice(blood);
		
		//4단계: 결과저장
		request.setAttribute("data", msg);
		
		//5단계:
		/*RequestDispatcher dis=null;
		dis=request.getRequestDispatcher("/movie/result.jsp");
		dis.forward(request, response);*/
		
		return "/result/blood";
	}

	@Override
	public boolean isForward() {
		return true;
	}
	
}
