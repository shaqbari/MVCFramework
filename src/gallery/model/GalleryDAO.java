package gallery.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import pool.DBManager;

/**
 * @author user1
 *	일반적으로 DAO를 작성할때는 플랫폼 특성을 타면 안된다.!
 *즉	중립적이어야 한다. 그래야 웹이건, 응용이건 사용이 가능하다.
 *
 *CRUD
 */
public class GalleryDAO {
	//요즘에는 클래스 안에 정보를 두지 않는것이 원칙이다.
	//그리고 아래와 같이 정보를 쓰면 성능이 느리다.!
	//미리 접속예상자에 따라 접속을 해두고 돌려서 쓰자. Connection Pooling 기법!!을 이용하자
	//왠만한 서버는 자체 connection pooling을 지원한다.
	
	/*String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="scott";
	String password="tiger";*/
	//테이블이 많아지면서 DAO가 많아지면 위의 접속 정보도 중복이 일어나게 된다.
	
	DBManager manager=DBManager.getInstance();
	
	
	/**
	 * 레코드 한건 넣기!!
	 */
	/*public void insert(String writer, String title, String content){*///입력사항이 많아지면 힘들다.
	public int insert(Gallery board){//매개변수로 DTO를 사용하자!!
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		int result=0; //지역변수는 항상 초기화시켜줘야 한다.
		int seq=0;
		
		try {
			/*Class.forName(driver);
			con=DriverManager.getConnection(url, user, password);*/
			//풀로부터 얻어온다. singleton 클래스를 만들어 가져오자
			con=manager.getConnection();
			
			
			String sql="insert INTO Gallery (Gallery_ID, TITLE, WRITER, CONTENT, user_fileName) VALUES (seq_gallery.nextVal, ?, ?, ?, ? )";
			pstmt=con.prepareStatement(sql);
			/*pstmt.setString(1, writer);//변수가 와야 환경에 중립적일 수 있다.
			pstmt.setString(2, title);
			pstmt.setString(3, content);*/
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getUserFile());
			
			result=pstmt.executeUpdate();
			
			if(result!=0){
				//시퀀스 얻기!!
				sql="select seq_gallery.currval as seq from dual";
				pstmt=con.prepareStatement(sql);
				rs=pstmt.executeQuery();
				if (rs.next()) {
					seq=rs.getInt("seq");							
							
				}
			}
			
			
			/*if (result==1) {
				JOptio 
				out.print
			}else{
				
			}
			여기서 처리하지 말고 결과를 호출하는 사람에게 알려주자!!!*/
			
		} /*catch (ClassNotFoundException e) {
			e.printStackTrace();
		} */catch (SQLException e) {
			e.printStackTrace();
		} finally{
			/*if (pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}*/
			
			manager.freeConnection(con, pstmt, rs);
			
			
		}
		
		return seq;
		
	}
	
	/**
	 * 목록 가져오기
	 */
	public List select(){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<Gallery> list=new ArrayList<Gallery>();
		
		try {			
			con=manager.getConnection();
			
			String sql="select * from Gallery order by board_id desc";
			pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs=pstmt.executeQuery();
			
			while (rs.next()) {
				Gallery dto=new Gallery();
				dto.setGallery_id(rs.getInt("Gallery_id"));
				dto.setTitle(rs.getString("title"));
				dto.setWriter(rs.getString("writer"));
				dto.setContent(rs.getString("content"));
				dto.setRegdate(rs.getString("regDate"));
				dto.setHit(rs.getInt("hit"));
				dto.setUserFile(rs.getString("user_filename"));
				
				list.add(dto);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			manager.freeConnection(con, pstmt, rs);			
		}
		
		return list;
		
		
	}
	
	//레코드 한건 가져오기 변수명 새로 짓지 말고 오버로딩을 하자!!! 왜? 변수명은 소중하니깐
	public Gallery select(int board_id){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Gallery dto=null;
		
		try {
			con=manager.getConnection();
			
			String sql="select * from board where Gallery_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			rs=pstmt.executeQuery();//id를 이용하기때문에 1건만 검색된다.
			
			if(rs.next()){//레코드가 있다면
				dto=new Gallery();
				dto.setGallery_id(rs.getInt("Gallery_id"));
				dto.setTitle(rs.getString("title"));
				dto.setWriter(rs.getString("writer"));
				dto.setContent(rs.getString("content"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setHit(rs.getInt("hit"));
				dto.setUserFile(rs.getString("user_filename"));
			}
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			manager.freeConnection(con, pstmt, rs);
			
		}
		
		return dto;
		
	}
	
	public int delete(int board_id){
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0;
		
		try {
			con=manager.getConnection();
			
			String sql="delete from board where Gallery_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			result=pstmt.executeUpdate();//id를 이용하기때문에 1건만 검색된다.
							
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{			
			manager.freeConnection(con, pstmt);
			
		}
		
		
		return result;
	}
	
	//레코드 1건 수정
	
	public int update(Gallery dto){
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0;
		
		try {
			con=manager.getConnection();

			String sql="update board set title=?, writer=?, content=? where Gallery_id=? ";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getWriter());
			pstmt.setString(3, dto.getContent());
			pstmt.setInt(4, dto.getGallery_id());
			result=pstmt.executeUpdate();//id를 이용하기때문에 1건만 검색된다.
							
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{			
			manager.freeConnection(con, pstmt);
			
		}
		
		
		return result;
		
	}
	
	
}






