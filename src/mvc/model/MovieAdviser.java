package mvc.model;

/**
 * @author user1
 *	�������� ���� �Ǵ� �޼����� ����ݰ� �������
 *������ �������� �и����� ������ ������ݿ����� ������ �����ϴ�.
 *
 *�̷��� �Ը� ū ���ø����̼ǿ����� mvc������ �����Ͽ� �����ؾ� �ϸ�, 
 *�̶� ������ ���� ������ model�̶� �Ѵ�.
 */
public class MovieAdviser {

	public String getAdvice(String movie){		
		String msg=null;
		if(movie.equals("�پƿ�")){
			msg="���� �ڽ����ǽ� 1�� ������";
			
		}else if(movie.equals("�������̺�")){
			msg="�ڹ� ����� �ִϸ��̼�";

		}else if(movie.equals("ŷ�Ƽ�")){
			msg="�׼� ��庥ó";

		}else if(movie.equals("���̸��� Ŀ����Ʈ")){
			msg="�ܰ��� SF ȣ��";

		} 
		
		return msg;
	}
	
}
