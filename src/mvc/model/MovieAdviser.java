package mvc.model;

/**
 * @author user1
 *	혈액형에 대한 판단 메세지는 웹기반과 상관없이
 *순수한 로직으로 분리시켜 놓으면 스윙기반에서도 재사용이 가능하다.
 *
 *이렇게 규모가 큰 어플리케이션에서는 mvc패턴을 적용하여 개발해야 하며, 
 *이때 순수한 로직 영역을 model이라 한다.
 */
public class MovieAdviser {

	public String getAdvice(String movie){		
		String msg=null;
		if(movie.equals("겟아웃")){
			msg="전미 박스오피스 1위 스릴러";
			
		}else if(movie.equals("보스베이비")){
			msg="코믹 디즈니 애니메이션";

		}else if(movie.equals("킹아서")){
			msg="액션 어드벤처";

		}else if(movie.equals("에이리언 커버넌트")){
			msg="외계인 SF 호러";

		} 
		
		return msg;
	}
	
}
