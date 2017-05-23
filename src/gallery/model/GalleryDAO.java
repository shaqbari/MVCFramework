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
 *	�Ϲ������� DAO�� �ۼ��Ҷ��� �÷��� Ư���� Ÿ�� �ȵȴ�.!
 *��	�߸����̾�� �Ѵ�. �׷��� ���̰�, �����̰� ����� �����ϴ�.
 *
 *CRUD
 */
public class GalleryDAO {
	//���򿡴� Ŭ���� �ȿ� ������ ���� �ʴ°��� ��Ģ�̴�.
	//�׸��� �Ʒ��� ���� ������ ���� ������ ������.!
	//�̸� ���ӿ����ڿ� ���� ������ �صΰ� ������ ����. Connection Pooling ���!!�� �̿�����
	//�ظ��� ������ ��ü connection pooling�� �����Ѵ�.
	
	/*String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="scott";
	String password="tiger";*/
	//���̺��� �������鼭 DAO�� �������� ���� ���� ������ �ߺ��� �Ͼ�� �ȴ�.
	
	DBManager manager=DBManager.getInstance();
	
	
	/**
	 * ���ڵ� �Ѱ� �ֱ�!!
	 */
	/*public void insert(String writer, String title, String content){*///�Է»����� �������� �����.
	public int insert(Gallery board){//�Ű������� DTO�� �������!!
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		int result=0; //���������� �׻� �ʱ�ȭ������� �Ѵ�.
		int seq=0;
		
		try {
			/*Class.forName(driver);
			con=DriverManager.getConnection(url, user, password);*/
			//Ǯ�κ��� ���´�. singleton Ŭ������ ����� ��������
			con=manager.getConnection();
			
			
			String sql="insert INTO Gallery (Gallery_ID, TITLE, WRITER, CONTENT, user_fileName) VALUES (seq_gallery.nextVal, ?, ?, ?, ? )";
			pstmt=con.prepareStatement(sql);
			/*pstmt.setString(1, writer);//������ �;� ȯ�濡 �߸����� �� �ִ�.
			pstmt.setString(2, title);
			pstmt.setString(3, content);*/
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getUserFile());
			
			result=pstmt.executeUpdate();
			
			if(result!=0){
				//������ ���!!
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
			���⼭ ó������ ���� ����� ȣ���ϴ� ������� �˷�����!!!*/
			
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
	 * ��� ��������
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
	
	//���ڵ� �Ѱ� �������� ������ ���� ���� ���� �����ε��� ����!!! ��? �������� �����ϴϱ�
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
			rs=pstmt.executeQuery();//id�� �̿��ϱ⶧���� 1�Ǹ� �˻��ȴ�.
			
			if(rs.next()){//���ڵ尡 �ִٸ�
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
			result=pstmt.executeUpdate();//id�� �̿��ϱ⶧���� 1�Ǹ� �˻��ȴ�.
							
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{			
			manager.freeConnection(con, pstmt);
			
		}
		
		
		return result;
	}
	
	//���ڵ� 1�� ����
	
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
			result=pstmt.executeUpdate();//id�� �̿��ϱ⶧���� 1�Ǹ� �˻��ȴ�.
							
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{			
			manager.freeConnection(con, pstmt);
			
		}
		
		
		return result;
		
	}
	
	
}






