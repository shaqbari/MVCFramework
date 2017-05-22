package mvc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author user1
 *	이 인터페이스는 하위 컨트롤러 들이 모두 구현해야 하는 메소드를 보유한 최상위 객체로 정의한다.!!
 *******최상위 객체로 정의하면 구현강제뿐 아니라, 같은 자료형으로 묶을 수 있다!!!!**********
 */
public interface Controller {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
