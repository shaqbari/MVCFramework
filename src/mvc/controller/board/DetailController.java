package mvc.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.Board;
import board.model.BoardDAO;
import mvc.controller.Controller;

public class DetailController implements Controller {
	BoardDAO boardDAO;
	
	public DetailController() {
		boardDAO=new BoardDAO();
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String board_id=request.getParameter("board_id");
		Board board=boardDAO.select(Integer.parseInt(board_id));
		
		//4단계 결과가 있다면 저장
		request.setAttribute("board", board);
		
		return "/result/board/detail";
	}

	@Override
	public boolean isForward() {
		
		return true;
	}

}
