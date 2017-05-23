package mvc.controller.gallery;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gallery.model.GalleryService;
import mvc.controller.Controller;

public class ListController implements Controller {
	GalleryService service=new GalleryService();//crud�۾��� service�� ������ �ִ�. 
	//dao�� db���õ� ������ ó�������� service�� �׿� ������ ó���Ѵ�.
		
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//3�ܰ�: ����Ͻ� ���� ��ü�� �� ��Ų��.
		List list=service.selectAll();
		
		System.out.println(list.size());
		
		//4�ܰ�: ����
		request.setAttribute("list", list);
		
		return "/result/gallery/list"; //�����Ҷ� list.do�� �ϸ� ���� ������ ������.
	}

	@Override
	public boolean isForward() {
		
		return true;
	}

}
