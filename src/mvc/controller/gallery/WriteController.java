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
		
		//�Ʒ��� Controller�� �ʹ� ���� ���� �ϰ� �ִ�.
		//�ش� �ϵ��� �����ؼ� ���� Service Ŭ������ ���� ����� �Ʒ� ������ ó���ϰ� ����.
		
		//Controller�� ���� ���Ѿ� �Ѵ�. ���������ϴ� Ŭ������ ���� ������
		//ServletFileUpload upload=new ServletFileUpload();
		//���ε� = db insert + ��������
		Gallery gallery=uploader.makeDTO(request);//dto�� ����� �ǳ��ش�.
		//System.out.println(gallery.getWriter());
		//System.out.println(gallery.getTitle());
		//System.out.println(gallery.getContent());
		//System.out.println(gallery.getUserFile());		
		
		
		int seq=galleryDAO.insert(gallery);//�����ϰ� ���ϸ����� ����� seq��´�

		//��������
		uploader.save(seq);
		
		
		return "/result/gallery/write";
	}

	@Override
	public boolean isForward() {
		
		return false;
	}
	
	
	

}
