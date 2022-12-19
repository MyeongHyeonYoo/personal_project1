package noticeboard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BoardInsert {

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
			
			// 매개변수화된 SQL 문 작성
			String sql = "" +
				"INSERT INTO boards (btitle, bcontent, bwriter, bdate) " + "VALUES (?, ?, ?, now())";
			
			// PreparedStatement 얻기 및 값 지정
			PreparedStatement pstmt = conn.prepareStatement(
					sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, "관리자 테스트");
			pstmt.setString(2, "테스트");
			pstmt.setString(3, "admin");
			
			// SQL 문 실행
			int rows = pstmt.executeUpdate();
			System.out.println("저장된 행 수 : " + rows);
			
			// bno 값 얻기
			if (rows == 1) {
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					int bno = rs.getInt(1);
					System.out.println("저장된 bno : " + bno);
				}
				rs.close();
			}
			
			// PreparedStatement 닫기
			pstmt.close();
		} catch (Exception e) {
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
