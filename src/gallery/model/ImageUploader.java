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
	
	FileItem imgItem; //�̹��� ���ε忡 ����� ������
	ServletContext context;
	String fileName;
	String savePath; //���� ������
	
	//�� �޼ҵ带 ȣ���ϸ�, �̹� ä���� DTO�� ����� �ش�.
	public Gallery makeDTO(HttpServletRequest request) {//�̰ɾ��� ������ uploader�� �ȴ�.
		context =request.getServletContext();		
		
		upload=new ServletFileUpload(new DiskFileItemFactory());
		upload.setHeaderEncoding("utf-8");//�ѱ�ó��
		gallery=new Gallery();
		
		try {
			List<FileItem> list=upload.parseRequest(request);
			
			for(int i=0; i<list.size(); i++){
				FileItem item= list.get(i);
				
				if (item.isFormField()) {//text �Ķ���Ͷ��
					String name=item.getFieldName();//�Ķ���͸�
					String value=item.getString("utf-8");//�Ķ���Ͱ� �ѱ�ó���ؼ� ��´�.
					
					if (name.equals("writer")) {
						gallery.setWriter(value);
					}else if(name.equals("title")){
						gallery.setTitle(value);						
					}else if(name.equals("content")){
						gallery.setContent(value);
					}
					
				}else{//���̳ʸ� �Ķ����
					fileName=item.getName();//�����̸�
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
	
	
	//�̹����� ������ �����ϴ� �޼ҵ�
	public void save(int seq){
		//������ ��ġ
		savePath=context.getRealPath("/data/"+seq+"."+FileManager.getExt(fileName));
		//������Ĺ���°��
		//System.out.println(savePath);�� Ȯ��
		
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
