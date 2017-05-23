package mvc.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.Board;
import board.model.BoardDAO;
import mvc.controller.Controller;

public class EditController implements Controller {
	BoardDAO boardDAO;
	
	public EditController() {
		boardDAO=new BoardDAO();
	}
	
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Board board=new Board();
		board.setBoard_id(Integer.parseInt(request.getParameter("board_id")));
		board.setTitle(request.getParameter("title"));
		board.setWriter(request.getParameter("writer"));
		board.setContent(request.getParameter("content"));
				
		int result=boardDAO.update(board);
		
		request.setAttribute("board", board);
		
		//jsp책 516page
		//mvc 패턴은 이론 model2란 javaee로 구현한 mvc패턴
		//model1 mvc를 추구했으나 완벽하지 않음 vc가 섞여있음
		//p524 factory패턴:new안사용하고 명단으로 객체를 생성했다.
		//p532 process대신 excute
		//p532 properties 대신 json이용
		//p536 dd에서 json파일 위치 initparameter로 받음
		//p543 
		
		return "/result/board/edit";
	}

	@Override
	public boolean isForward() {
		return true;
	}

}
