package mvc.controller.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.Board;
import board.model.BoardDAO;
import mvc.controller.Controller;

/**
 * @author user1
 *�۾��� ��ó���� ó���ϴ� ��Ʈ�ѷ�
 */
public class WriteController implements Controller {
	BoardDAO boardDAO;

	public WriteController() {
		boardDAO=new BoardDAO();
	}
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//dispatcherServlet���� �̹� �ѱ�ó���ؼ� ���⼭ ���ص� �ȴ�.
		
		//3�ܰ�: �˸´� ���� ��ü�� �� ��Ų��.
		Board board=new Board();
		board.setWriter(request.getParameter("writer"));
		board.setTitle(request.getParameter("title"));
		board.setContent(request.getParameter("content"));
		boardDAO.insert(board);
		
		//4�ܰ� ���⼭ ���ʿ䰡 ����?
		
		//5�ܰ�
		/*RequestDispatcher dis=request.getRequestDispatcher("/board/list.do");//list.jsp �ٷ� ���� ������ ��� nullpointerException�� ����.
		dis.forward(request, response); 
		���⼭�� forword�� ���� ��� /board/list.do�� ����(���ΰ�ħ�ϸ� ���� ��û�� ����.)�Ǹ鼭 list.jsp�� ���´�.
		forward�� �����Ұ� �������� ����.���⼭�� forward�� �ƴ϶� �׳� list.do�� ������ �Ѵ�. */
		//response.sendRedirect("/board/list.do");//������� ���� ���;� �Ѵ�.
		/*
		 * ��û�� �����ϸ� ������ â�� /board/write.do�� �����ֱ� ������ ����Ʈ���� ���� ��ϵǴ� �̻��� ȿ���� ����.
		 * ���� �������� �ƹ����� �ϴ� ���� �ƴ϶�, ��û�� �����ϰ� ������ ����ϴ� ���̴�.
		 * ����� ��û�� �����ؾ� �ϳ�?
		 * ��Ʈ�ѷ��� ����� ������ ������, �� ����� ������ �ʿ���ϴ� View�����ϰ��� �Ҷ��� ����ϴ� ���̴�.
		 * */
		
		return "/result/board/write";
	}

	@Override
	public boolean isForward() {
		// TODO Auto-generated method stub
		return false;
	}

}
