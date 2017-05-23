package mvc.controller.gallery;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAO;
import gallery.model.GalleryDAO;
import gallery.model.GalleryService;
import mvc.controller.Controller;

public class DeleteController implements Controller {
	GalleryService service;
	
	public DeleteController() {
		service=new GalleryService();
	}
	
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		service.delete(request);
		
		return "/result/gallery/delete";
	}

	@Override
	public boolean isForward() {
		return false;
	}

}
