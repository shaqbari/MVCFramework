package mvc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.model.BloodAdviser;

/**
 * @author user1
 * ���� �츮�� MVC������ ������ ���ø����̼ǿ����� ��� ��û�� �޴� �������� �ϳ��� �ΰ�,
 * �� ��Ʈ�ѷ��� ���� ��� ��û�� ó���ϴ� ����̾���.
 * ������, �ϳ��� Ŭ������ ��� ��û�� ó���ϴ� ����, �� ��û�м��� if������ ó���ؾ� �ϴ� ������ �߻��ߴ�.
 * �̰� �� �����ΰ�? ��û�� ������ ���� �޼������� �þ�� if�� ���� ���� �þ,
 *  ���������� �� �ڵ��� �Ϻθ� �����ؾ� �ϴ� �������� �ִ�. �� �������� ��������.
 *  �ذ�å??
 *	GOF�� �Ұ��� Command Pattern�� ������ ����.
 *	Command �����̶�? Command�� '���'�̶� ���ε�, ���⼭�� ��ɺ��ٴ� '��û'���� ��������.
 *	�� ��û���� 1:1�����ϴ� if������ ó������ ����, ��üȭ ���� ��ü�� ó������.  
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

		//3�ܰ�: �˸��� ����Ͻ� ���� ��ü�� �� ��Ų��.
		String msg=adviser.getAdvice(blood);
		
		//4�ܰ�: �������
		request.setAttribute("data", msg);
		
		//5�ܰ�:
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
