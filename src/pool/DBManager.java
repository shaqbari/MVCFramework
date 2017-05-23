package pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * @author user1
 * Ŀ�ؼ� ��ü�� �ʿ�� �ϴ� �ܺ� ��ü�鿡�� Connection�� �������ִ� �޴���
 * javaSE���� ����ߴ� �޴����ʹ� Ʋ����
 * � �κп��� Ʋ����?
 * �̸޴����� JNDI�� ���� Ŀ�ؼ��� ���� ���̴�.
 * 
 * ������: �̱������� ���߿���
 * 
 * 
 */
public class DBManager {	
	
	private static DBManager instance;
	
	InitialContext context; //�˻���ü
	DataSource ds; //Ŀ�ؼ��� ��� ���� ��ü javax.sql.�� ���Ѱ��̾�� �Ѵ�.
	
	
	//1. new �Ҽ� ���� �����ڸ� ���´�.
	private DBManager(){
		try {
			context=new InitialContext();
			ds=(DataSource)context.lookup("java:comp/env/jdbc/myoracle");//server.xml�� �˻��ϴ� �˻���� �˻��Ѵ�.
								
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static DBManager getInstance() {
		if (instance==null) {
			instance=new DBManager();
		}
		
		return instance;
	}
	
	//Ŀ�ؼ� ������ �޼ҵ�	
	public Connection getConnection(){
		Connection con=null;
		
		try {
			con=ds.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		};	
		
		return con;
	}
	
	//Ŀ�ؼ� �ݳ�
	public void freeConnection(Connection con, PreparedStatement pstmt){
		if (pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//rs�� �߰� �̿��ϴ� ��� �����ε�
	public void freeConnection(Connection con, PreparedStatement pstmt, ResultSet rs){
		if (rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		
		if (pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}










