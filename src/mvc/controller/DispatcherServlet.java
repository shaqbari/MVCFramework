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
 *	이클래스는 웹어플리케이션의 모든 요청을 1차적으로 받는 컨트롤러이므로,
 *웹상의 요청을 받으려면 서블릿으로 정의 해야 한다.
 */
public class DispatcherServlet extends HttpServlet{
	//Post, get중 어떤 방식의 요청이 들어올지 예층불가하므로, 실제 요청을 처리하는 메소드를 별로도 정의해 놓자!!
	
	FileInputStream fis;//응용이기때문에 경로를 정확히 명시해줘야 한다.
	InputStreamReader is;
	BufferedReader buffr; //업그레이드해서 한줄씩 읽자
	JSONObject jsonObject;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		//jsp에서의 application 내장객체의 자료형!!
		ServletContext context=config.getServletContext();
		//String realPath=context.getRealPath("/WEB-INF/mapping/controller-mapping.json");//플랫폼에 관계없도록 realpath를 이용한다.
		//주소도 web.xml이용해서 cofig에서 얻어온다.
		String realPath=context.getRealPath(config.getInitParameter("configLocatoin"));
		System.out.println("json설정파일 경로는"+realPath);
		
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
			System.out.println("json설정파일 내용은"+sb.toString());
			//sb에 json문자열이 모두 누적되어 있으므로, 이 데이터를 대상으로 parsing하자!!
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
	
	
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{//호출하는 쪽(톰캣)이 예외처리하도록 한다.
		request.setCharacterEncoding("utf-8");//한글 안깨지게
		
		//jsp의 page 지시영역의 효과와 같음
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		
		
		//1단계 요청을 받는다.
		//System.out.println("요청이 들어왔네요");
		
		//2단계 요청을 분석한다(어떤 요청이 들어올지 모르니깐)
		String uri=request.getRequestURI();
		System.out.println("요청들어온 uri는"+uri);
		
		
		/*RequestDispatcher dis=null;
		String msg=null;*/
		//3단계: 알맞은 로직 객체에 일 시킨다.
		//요청이 많아지면 if else문이 너무 길어진다.
		//if문을 없애기 위해 Command pattern으로 요청마다 세부 controller 객체를 연결시켜준다. 	
		//세부 controller에서 3,4,5단계 수행
		
		//BloodController controller=null;
		//MovieController controller=null;
		
		//객체지향 언어의 특징
		/**
		 * -상속
		 * -은닉화
		 * -다형성
		 *  
		 */
		/*xml, json을 볼때는 조건문있는 database라고 생각하자.
		 * json을 해석해서 조건문대신 이용해보자
		 * */
		
		
		
		//Controller controller=null;
		/*if (uri.equals("/blood.do")) {
			try {
				//Class controllerClass=Class.forName("mvc.controller.BloodController");
				Class controllerClass=Class.forName((String) jsonObject.get(uri));
				controller=(Controller)controllerClass.newInstance();//인스턴스 생성 메소드
				
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
			
			//controller=new BloodController();//new연산자 없이 객체를 메모리에 올려야 if문이 없어진다. Class.forName()으로 수동으로 올리자.
			
			//BloodController controller=new BloodController();
			//controller.execute(request, response);
			
			String blood=request.getParameter("blood");
			BloodAdviser adviser=new BloodAdviser();
			msg=adviser.getAdvice(blood);
			dis=request.getRequestDispatcher("/blood/result.jsp");
			
		}else if(uri.equals("/movie.do")){
			try {
				Class controllerClass=Class.forName("mvc.controller.MovieController");
				controller=(Controller)controllerClass.newInstance();//인스턴스 생성 메소드
				
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
		//최상위 객체의 메소드를 호출하면 오버라이드된 자식것이 적용된다. 이게 다형성이다.
				
		Controller controller=null;
		JSONObject controllerObj=(JSONObject)jsonObject.get("controllerMapping");
		JSONObject viewObj=(JSONObject)jsonObject.get("viewMapping");
		try {
			Class controllerClass = Class.forName((String) controllerObj.get(uri));
			controller=(Controller)controllerClass.newInstance();//인스턴스 생성 메소드
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
		String key=controller.execute(request, response);//넘겨받은 주소로 json에서 검색
				
		//5단계 결과 보여주기 forward와 redirect의 조건을 봐서 처리한다.
		if(controller.isForward()){
			RequestDispatcher dis=request.getRequestDispatcher((String)viewObj.get(key));//보내는 주소도 설정파일의 가상의 이름으로 관리하자!! ControllerMapping+ViewMapping
			dis.forward(request, response);
		}else{
			response.sendRedirect((String)viewObj.get(key));
		}
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
