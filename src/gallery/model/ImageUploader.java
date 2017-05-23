package gallery.model;

import java.awt.image.renderable.RenderableImageProducer;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Common.file.FileManager;

public class ImageUploader {
	ServletFileUpload upload;
	Gallery gallery;
	InputStream is;
	FileOutputStream fos;
	
	FileItem imgItem; //이미지 업로드에 사용할 아이템
	ServletContext context;
	String fileName;
	String savePath; //실제 저장경로
	
	//이 메소드를 호출하면, 이미 채워진 DTO를 만들어 준다.
	public Gallery makeDTO(HttpServletRequest request) {//이걸쓰면 웹전용 uploader가 된다.
		context =request.getServletContext();		
		
		upload=new ServletFileUpload(new DiskFileItemFactory());
		upload.setHeaderEncoding("utf-8");//한글처리
		gallery=new Gallery();
		
		try {
			List<FileItem> list=upload.parseRequest(request);
			
			for(int i=0; i<list.size(); i++){
				FileItem item= list.get(i);
				
				if (item.isFormField()) {//text 파라미터라면
					String name=item.getFieldName();//파라메터명
					String value=item.getString("utf-8");//파라메터값 한글처리해서 얻는다.
					
					if (name.equals("writer")) {
						gallery.setWriter(value);
					}else if(name.equals("title")){
						gallery.setTitle(value);						
					}else if(name.equals("content")){
						gallery.setContent(value);
					}
					
				}else{//바이너리 파라미터
					fileName=item.getName();//파일이름
					gallery.setUserFile(fileName);
					imgItem=item;
				}
				
			}
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gallery;
	}
	
	
	//이미지를 서버에 저장하는 메소드
	public void save(int seq){
		//저장할 위치
		savePath=context.getRealPath("/data/"+seq+"."+FileManager.getExt(fileName));
		//내부톰캣쓰는경우
		//System.out.println(savePath);로 확인
		
		try {
			is=imgItem.getInputStream();
			fos=new FileOutputStream(savePath);
			byte[] b=new byte[1024];
			
			int flag;
			while (true) {
				flag=is.read(b);
				if (flag==-1) break;
				fos.write(b);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if (fos!=null) {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (is!=null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		
		
		
		
	}
}
