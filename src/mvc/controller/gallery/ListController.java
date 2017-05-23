package mvc.controller.gallery;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gallery.model.GalleryService;
import mvc.controller.Controller;

public class ListController implements Controller {
	GalleryService service=new GalleryService();//crud작업을 service가 가지고 있다. 
	//dao는 db관련된 업무만 처리하지만 service는 그외 로직도 처리한다.
		
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//3단계: 비즈니스 로직 객체에 일 시킨다.
		List list=service.selectAll();
		
		System.out.println(list.size());
		
		//4단계: 저장
		request.setAttribute("list", list);
		
		return "/result/gallery/list"; //맵핑할때 list.do로 하면 무한 루프에 빠진다.
	}

	@Override
	public boolean isForward() {
		
		return true;
	}

}
