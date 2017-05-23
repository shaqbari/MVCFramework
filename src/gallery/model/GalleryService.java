package gallery.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.omg.CORBA.Request;

import board.model.Board;

/**
 * @author user1 비즈니스 로직 영역의 복잡한 세부 업무는 컨트롤러가 알 필요도, 알 수도 없다. 따라서 이 시점부터는 파일저장 및
 *         db insert작업을 처리해 줄 비즈니스 로직 파트의 관리자 객체가 필요하다.
 *
 *         이러한 모델 파트의 관리적 업무를 담당하는 객체를 엔터프라이즈급 설계 분야에서는 Service라 한다.
 */
public class GalleryService {
	GalleryDAO galleryDAO = new GalleryDAO();
	ImageUploader uploader = new ImageUploader();

	// 업로드
	public void upload(HttpServletRequest request) {

		Gallery gallery = uploader.makeDTO(request);
		int seq = galleryDAO.insert(gallery);
		uploader.save(seq);
		
		//이미지가 완전히 올라올때까지 기야려야 한다.

	}
	
	//레코드 가져오기
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
		
		//4단계 결과가 있다면 저장
		request.setAttribute("gallery", gallery);
	}
	
	
	
	
	
	

}
