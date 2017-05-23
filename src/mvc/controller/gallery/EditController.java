package mvc.controller.gallery;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.Board;
import board.model.BoardDAO;
import gallery.model.GalleryService;
import mvc.controller.Controller;

public class EditController implements Controller {
	GalleryService service;
	
	public EditController() {
		service=new GalleryService();
	}
	
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		service.update(request);
		
		return "/result/gallery/edit";
	}

	@Override
	public boolean isForward() {
		return true;
	}

}
