package mvc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.model.BloodAdviser;
import mvc.model.MovieAdviser;

/**
 * @author user1
 *	��Ŭ������ �����ø����̼��� ��� ��û�� 1�������� �޴� ��Ʈ�ѷ��̹Ƿ�,
 *������ ��û�� �������� �������� ���� �ؾ� �Ѵ�.
 */
public class DispatcherServlet extends HttpServlet{
	//Post, get�� � ����� ��û�� ������ �����Ұ��ϹǷ�, ���� ��û�� ó���ϴ� �޼ҵ带 ���ε� ������ ����!!
	
	FileInputStream fis;//�����̱⶧���� ��θ� ��Ȯ�� �������� �Ѵ�.
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		//jsp������ application ���尴ü�� �ڷ���!!
		ServletContext context=config.getServletContext();
		String realPath=context.getRealPath("/WEP-INF/mapping/controller-mapping.json");//�÷����� ��������� realpath�� �̿��Ѵ�.
		System.out.println(realPath);
		
		//fis=new FileInputStream(new File(pathname));
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doRequest(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doRequest(req, resp);
	}
	
	
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{//ȣ���ϴ� ��(��Ĺ)�� ����ó���ϵ��� �Ѵ�.
		request.setCharacterEncoding("utf-8");//�ѱ� �ȱ�����
		
		//jsp�� page ���ÿ����� ȿ���� ����
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		
		
		//1�ܰ� ��û�� �޴´�.
		//System.out.println("��û�� ���Գ׿�");
		
		//2�ܰ� ��û�� �м��Ѵ�(� ��û�� ������ �𸣴ϱ�)
		String uri=request.getRequestURI();
		System.out.println(uri);
		
		
		/*RequestDispatcher dis=null;
		String msg=null;*/
		//3�ܰ�: �˸��� ���� ��ü�� �� ��Ų��.
		//��û�� �������� if else���� �ʹ� �������.
		//if���� ���ֱ� ���� Command pattern���� ��û���� ���� controller ��ü�� ��������ش�. 	
		//���� controller���� 3,4,5�ܰ� ����
		
		//BloodController controller=null;
		//MovieController controller=null;
		
		//��ü���� ����� Ư¡
		/**
		 * -���
		 * -����ȭ
		 * -������
		 *  
		 */
		/*xml, json�� ������ ���ǹ��ִ� database��� ��������.
		 * json�� �ؼ��ؼ� ���ǹ���� �̿��غ���
		 * */
		
		
		
		Controller controller=null;
		if (uri.equals("/blood.do")) {
			try {
				Class controllerClass=Class.forName("mvc.controller.BloodController");
				controller=(Controller)controllerClass.newInstance();//�ν��Ͻ� ���� �޼ҵ�
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//controller=new BloodController();//new������ ���� ��ü�� �޸𸮿� �÷��� if���� ��������. Class.forName()���� �������� �ø���.
			
			//BloodController controller=new BloodController();
			//controller.execute(request, response);
			
			/*String blood=request.getParameter("blood");
			BloodAdviser adviser=new BloodAdviser();
			msg=adviser.getAdvice(blood);
			dis=request.getRequestDispatcher("/blood/result.jsp");*/
			
		}else if(uri.equals("/movie.do")){
			try {
				Class controllerClass=Class.forName("mvc.controller.MovieController");
				controller=(Controller)controllerClass.newInstance();//�ν��Ͻ� ���� �޼ҵ�
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//controller=new MovieController();
			
			//MovieController controller=new MovieController();
			//controller.execute(request, response);
			
			/*String movie=request.getParameter("movie");		
			MovieAdviser adviser=new MovieAdviser();
			msg=adviser.getAdvice(movie);
			dis=request.getRequestDispatcher("/movie/result.jsp");*/
			
		}
		//�ֻ��� ��ü�� �޼ҵ带 ȣ���ϸ� �������̵�� �ڽİ��� ����ȴ�. �̰� �������̴�.
		controller.execute(request, response);
		
	
		
	}
	
}
