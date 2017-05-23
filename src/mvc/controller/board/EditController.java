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
		
		//jspå 516page
		//mvc ������ �̷� model2�� javaee�� ������ mvc����
		//model1 mvc�� �߱������� �Ϻ����� ���� vc�� ��������
		//p524 factory����:new�Ȼ���ϰ� ������� ��ü�� �����ߴ�.
		//p532 process��� excute
		//p532 properties ��� json�̿�
		//p536 dd���� json���� ��ġ initparameter�� ����
		//p543 
		
		return "/result/board/edit";
	}

	@Override
	public boolean isForward() {
		return true;
	}

}
