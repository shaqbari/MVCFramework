package mvc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author user1
 *	�� �������̽��� ���� ��Ʈ�ѷ� ���� ��� �����ؾ� �ϴ� �޼ҵ带 ������ �ֻ��� ��ü�� �����Ѵ�.!!
 *******�ֻ��� ��ü�� �����ϸ� ���������� �ƴ϶�, ���� �ڷ������� ���� �� �ִ�!!!!**********
 */
public interface Controller {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
