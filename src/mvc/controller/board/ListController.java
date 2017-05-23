package mvc.controller.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.Board;
import board.model.BoardDAO;
import mvc.controller.Controller;

/**
 * @author user1
 *MVC중 Controller영역의 클래스이며,
 *게시판의 리스트 요청을 처리한다.
 */
public class ListController implements Controller{

	BoardDAO boardDAO;
	
	public ListController() {
		boardDAO=new BoardDAO();
	}
	
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//3단계 알맞는 로직 객체에 일 시킨다.
		List<Board> list=boardDAO.select();

		//4단계 결과가 있다면 결과 저장
		request.setAttribute("list", list);
		
		//5단계 결과 보여주기  앞으로는 dispatcher가 하도록 하자. 
		//RequestDispatcher dis=request.getRequestDispatcher("/result/board/list");//보내는 주소도 설정파일의 가상의 이름으로 관리하자!! ControllerMapping+ViewMapping
		//dis.forward(request, response);
		
		return "/result/board/list"; 
			
	}


	@Override
	public boolean isForward() {
		
		return true;
	}
	
}
