package mvc.controller.gallery;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.Board;
import board.model.BoardDAO;
import gallery.model.GalleryService;
import mvc.controller.Controller;

public class DetailController implements Controller {
	GalleryService service;
	
	public DetailController() {
		service=new GalleryService();
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println(request.getParameter("gallery_id"));
		
		service.select(request);
		
		
		return "/result/gallery/detail";
	}

	@Override
	public boolean isForward() {
		
		return true;
	}

}
