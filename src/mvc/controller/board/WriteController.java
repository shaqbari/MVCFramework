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
 *글쓰기 요처응ㄹ 처리하는 컨트롤러
 */
public class WriteController implements Controller {
	BoardDAO boardDAO;

	public WriteController() {
		boardDAO=new BoardDAO();
	}
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//dispatcherServlet에서 이미 한글처리해서 여기서 안해도 된다.
		
		//3단계: 알맞는 로직 객체에 일 시킨다.
		Board board=new Board();
		board.setWriter(request.getParameter("writer"));
		board.setTitle(request.getParameter("title"));
		board.setContent(request.getParameter("content"));
		boardDAO.insert(board);
		
		//4단계 여기서 할필요가 없다?
		
		//5단계
		/*RequestDispatcher dis=request.getRequestDispatcher("/board/list.do");//list.jsp 바로 가면 담은게 없어서 nullpointerException이 난다.
		dis.forward(request, response); 
		여기서는 forword를 쓰면 계속 /board/list.do가 유지(새로고침하면 쓰기 요청이 들어간다.)되면서 list.jsp가 나온다.
		forward는 전달할게 있을때만 쓴다.여기서는 forward가 아니라 그냥 list.do로 보내야 한다. */
		//response.sendRedirect("/board/list.do");//연결끊고 새로 들어와야 한다.
		/*
		 * 요청을 유지하면 브라우저 창에 /board/write.do가 남아있기 때문에 리스트에서 글이 등록되는 이상한 효과가 난다.
		 * 따라서 포워딩은 아무때나 하는 것이 아니라, 요청을 유지하고 싶을때 사용하는 것이다.
		 * 어느때 요청을 유지해야 하나?
		 * 컨트롤럴가 결과를 가지고 있을때, 그 결과를 별도로 필요로하는 View전달하고자 할때만 사용하는 것이다.
		 * */
		
		return "/result/board/write";
	}

	@Override
	public boolean isForward() {
		// TODO Auto-generated method stub
		return false;
	}

}
