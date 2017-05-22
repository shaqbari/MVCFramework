package mvc.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
	InputStreamReader is;
	BufferedReader buffr; //���׷��̵��ؼ� ���پ� ����
	JSONObject jsonObject;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		//jsp������ application ���尴ü�� �ڷ���!!
		ServletContext context=config.getServletContext();
		//String realPath=context.getRealPath("/WEB-INF/mapping/controller-mapping.json");//�÷����� ��������� realpath�� �̿��Ѵ�.
		//�ּҵ� web.xml�̿��ؼ� cofig���� ���´�.
		String realPath=context.getRealPath(config.getInitParameter("configLocatoin"));
		System.out.println(realPath);
		
		try {
			fis=new FileInputStream(new File(realPath));
			is=new InputStreamReader(fis);
			buffr=new BufferedReader(is);
			
			String str;
			StringBuffer sb=new StringBuffer();
			while (true) {
				str=buffr.readLine();
				if (str==null)break;
				sb.append(str);
			}
			System.out.println(sb.toString());
			//sb�� json���ڿ��� ��� �����Ǿ� �����Ƿ�, �� �����͸� ������� parsing����!!
			JSONParser jsonParser=new JSONParser();
			jsonObject=(JSONObject) jsonParser.parse(sb.toString());
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		
		
		
		//Controller controller=null;
		/*if (uri.equals("/blood.do")) {
			try {
				//Class controllerClass=Class.forName("mvc.controller.BloodController");
				Class controllerClass=Class.forName((String) jsonObject.get(uri));
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
			
			String blood=request.getParameter("blood");
			BloodAdviser adviser=new BloodAdviser();
			msg=adviser.getAdvice(blood);
			dis=request.getRequestDispatcher("/blood/result.jsp");
			
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
			
			String movie=request.getParameter("movie");		
			MovieAdviser adviser=new MovieAdviser();
			msg=adviser.getAdvice(movie);
			dis=request.getRequestDispatcher("/movie/result.jsp");
			
		}*/
		//�ֻ��� ��ü�� �޼ҵ带 ȣ���ϸ� �������̵�� �ڽİ��� ����ȴ�. �̰� �������̴�.
				
		Controller controller=null;
		try {
			Class controllerClass = Class.forName((String) jsonObject.get(uri));
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
		controller.execute(request, response);
		
	
		
	}
	
	@Override
	public void destroy() {
		if (buffr!=null) {
			try {
				buffr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (is!=null) {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (fis!=null) {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
