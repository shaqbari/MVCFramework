package mvc.model;

/**
 * @author user1
 *	혈액형에 대한 판단 메세지는 웹기반과 상관없이
 *순수한 로직으로 분리시켜 놓으면 스윙기반에서도 재사용이 가능하다.
 *
 *이렇게 규모가 큰 어플리케이션에서는 mvc패턴을 적용하여 개발해야 하며, 
 *이때 순수한 로직 영역을 model이라 한다.
 */
public class BloodAdviser {

	public String getAdvice(String blood){		
		String msg=null;
		if(blood.equals("A")){
			msg="소심+세심하고 꼼꼼하다.";
			
		}else if(blood.equals("B")){
			msg="주관이 뚜렷하고 고집이 세다.";
			
		}else if(blood.equals("AB")){
			msg="예측불가하고 발랄하며 종잡을 수 없다.";
			
		}else if(blood.equals("O")){
			msg="성격 좋고, 쓸데없이 오지랖이 넓다.";
			
		} 
		
		return msg;
	}
	
}
