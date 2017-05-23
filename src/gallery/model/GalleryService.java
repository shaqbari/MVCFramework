package gallery.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.omg.CORBA.Request;

import board.model.Board;

/**
 * @author user1 ����Ͻ� ���� ������ ������ ���� ������ ��Ʈ�ѷ��� �� �ʿ䵵, �� ���� ����. ���� �� �������ʹ� �������� ��
 *         db insert�۾��� ó���� �� ����Ͻ� ���� ��Ʈ�� ������ ��ü�� �ʿ��ϴ�.
 *
 *         �̷��� �� ��Ʈ�� ������ ������ ����ϴ� ��ü�� ������������� ���� �о߿����� Service�� �Ѵ�.
 */
public class GalleryService {
	GalleryDAO galleryDAO = new GalleryDAO();
	ImageUploader uploader = new ImageUploader();

	// ���ε�
	public void upload(HttpServletRequest request) {

		Gallery gallery = uploader.makeDTO(request);
		int seq = galleryDAO.insert(gallery);
		uploader.save(seq);
		
		//�̹����� ������ �ö�ö����� ��߷��� �Ѵ�.

	}
	
	//���ڵ� ��������
	public List selectAll(){		
		return galleryDAO.select();		
	}

	
	public void delete(HttpServletRequest request) {
		String gallery_id=request.getParameter("gallery_id");
		galleryDAO.delete(Integer.parseInt(gallery_id));
	}
	
	public void update(HttpServletRequest request){
		Gallery gallery=new Gallery();
		gallery.setGallery_id(Integer.parseInt(request.getParameter("gallery_id")));
		gallery.setWriter(request.getParameter("writer"));
		gallery.setContent(request.getParameter("content"));
		gallery.setTitle(request.getParameter("title"));
		
		galleryDAO.update(gallery);
		
		request.setAttribute("gallery", gallery);
		
	}

	public void select(HttpServletRequest request) {		
		String gallery_id=request.getParameter("gallery_id");
		Gallery gallery=galleryDAO.select(Integer.parseInt(gallery_id));
		
		//4�ܰ� ����� �ִٸ� ����
		request.setAttribute("gallery", gallery);
	}
	
	
	
	
	
	

}
