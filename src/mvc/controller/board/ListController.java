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
 *MVC�� Controller������ Ŭ�����̸�,
 *�Խ����� ����Ʈ ��û�� ó���Ѵ�.
 */
public class ListController implements Controller{

	BoardDAO boardDAO;
	
	public ListController() {
		boardDAO=new BoardDAO();
	}
	
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//3�ܰ� �˸´� ���� ��ü�� �� ��Ų��.
		List<Board> list=boardDAO.select();

		//4�ܰ� ����� �ִٸ� ��� ����
		request.setAttribute("list", list);
		
		//5�ܰ� ��� �����ֱ�  �����δ� dispatcher�� �ϵ��� ����. 
		//RequestDispatcher dis=request.getRequestDispatcher("/result/board/list");//������ �ּҵ� ���������� ������ �̸����� ��������!! ControllerMapping+ViewMapping
		//dis.forward(request, response);
		
		return "/result/board/list"; 
			
	}


	@Override
	public boolean isForward() {
		
		return true;
	}
	
}
