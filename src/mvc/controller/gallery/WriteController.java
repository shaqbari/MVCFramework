package mvc.controller.gallery;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import gallery.model.Gallery;
import gallery.model.GalleryDAO;
import gallery.model.ImageUploader;
import mvc.controller.Controller;

public class WriteController implements Controller {
	GalleryDAO galleryDAO=new GalleryDAO();
	ImageUploader uploader=new ImageUploader();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//아래는 Controller가 너무 많은 일을 하고 있다.
		//해당 일들을 전담해서 맡을 Service 클래스를 따로 만들어 아래 로직을 처리하게 하자.
		
		//Controller는 일을 시켜야 한다. 파일저장하는 클래스를 따로 만들자
		//ServletFileUpload upload=new ServletFileUpload();
		//업로드 = db insert + 파일저장
		Gallery gallery=uploader.makeDTO(request);//dto를 만들어 건네준다.
		//System.out.println(gallery.getWriter());
		//System.out.println(gallery.getTitle());
		//System.out.println(gallery.getContent());
		//System.out.println(gallery.getUserFile());		
		
		
		int seq=galleryDAO.insert(gallery);//삽입하고 파일명으로 사용할 seq얻는다

		//파일저장
		uploader.save(seq);
		
		
		return "/result/gallery/write";
	}

	@Override
	public boolean isForward() {
		
		return false;
	}
	
	
	

}
