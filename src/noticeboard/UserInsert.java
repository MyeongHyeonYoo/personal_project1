package noticeboard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserInsert {

	public static void main(String[] args) {
		Connection conn = null;
		try {
			// JDBC Driver 등록
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 연결하기
			conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/java_db",
				"java",
				"12345"
				);
			// 매개변수화된 SQL문 작성
			String sql = "" +
				"INSERT INTO users (userid, username, userpassword, userage, useremail) " + "VALUES (?, ?, ?, ?, ?)";
			
			// PreparedStatement 얻기 및 값 지정
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "admin");
			pstmt.setString(2, "관리자");
			pstmt.setString(3, "12345");
			pstmt.setInt(4, 25);
			pstmt.setString(5, "admin@mycompany.com");
			
			// SQL 문 실행
			int rows = pstmt.executeUpdate();
			System.out.println("저장된 행 수 : " + rows);
			
			// PreparedStatement 닫기
			pstmt.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					// 연결 끊기
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
}
