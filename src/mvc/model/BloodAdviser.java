package mvc.model;

/**
 * @author user1
 *	�������� ���� �Ǵ� �޼����� ����ݰ� �������
 *������ �������� �и����� ������ ������ݿ����� ������ �����ϴ�.
 *
 *�̷��� �Ը� ū ���ø����̼ǿ����� mvc������ �����Ͽ� �����ؾ� �ϸ�, 
 *�̶� ������ ���� ������ model�̶� �Ѵ�.
 */
public class BloodAdviser {

	public String getAdvice(String blood){		
		String msg=null;
		if(blood.equals("A")){
			msg="�ҽ�+�����ϰ� �Ĳ��ϴ�.";
			
		}else if(blood.equals("B")){
			msg="�ְ��� �ѷ��ϰ� ������ ����.";
			
		}else if(blood.equals("AB")){
			msg="�����Ұ��ϰ� �߶��ϸ� ������ �� ����.";
			
		}else if(blood.equals("O")){
			msg="���� ����, �������� �������� �д�.";
			
		} 
		
		return msg;
	}
	
}
