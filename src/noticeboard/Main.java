package noticeboard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class Main {
	
	// Field
	private Scanner scanner = new Scanner(System.in);
	private Connection conn;
	private String loginId;
	private String loginpassword;
	private String menuNo = null;
	private Board user = new Board();
	private Board board = new Board();
	
	
	// Constructor
	public Main() {
		try {
			// JDBC Driver 등록
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 연결하기
			conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/java_db",
				"java",
				"12345"
				);
		} catch (Exception e) {
			e.printStackTrace();
			exit();
		}
	}
	
	// Method
	public void list() {
		// 타이틀 및 컬럼명 출력
		System.out.println("\n [게시물 목록]");
		System.out.println("────────────────────────────────────────────────────────");
		System.out.printf(" %-6s %-12s %-16s %-40s\n", " no", " writer", " date", "  title");
		System.out.println("────────────────────────────────────────────────────────");

		// boards 테이블에서 게시물 정보를 가져와서 출력하기
		try {
			String sql = "" +
					"SELECT bno, btitle, bcontent, bwriter, bdate " +
					"FROM boards " +
					"ORDER BY bno DESC";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				board.setBno(rs.getInt("bno"));
				board.setBtitle(rs.getString("btitle"));
				board.setBcontent(rs.getString("bcontent"));
				board.setBwriter(rs.getString("bwriter"));
				board.setBdate(rs.getDate("bdate"));
				System.out.printf("  %-6s  %-12s%-16s   %-40s\n",
						board.getBno(),
						board.getBwriter(),
						board.getBdate(),
						board.getBtitle());
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			exit();
		}
		
		// 메인 메뉴 출력
		mainMenu();
	}
	
	// 회원가입
	public void join() {
		// 입력 받기 
		System.out.println("[새 사용자 입력]");
		System.out.print(" 아이디  : ");
		user.setUserId(scanner.nextLine());
		System.out.print(" 이름    : ");
		user.setUserName(scanner.nextLine());
		System.out.print(" 비밀번호 : ");
		user.setUserPassword(scanner.nextLine());
		System.out.print(" 나이    : ");
		user.setUserAge(Integer.parseInt(scanner.nextLine()));
		System.out.print(" 이메일   : ");
		user.setUserEmail(scanner.nextLine());
		
		
		//보조 메뉴 출력
		System.out.println("──────────────────────────────");
		System.out.println(" 보조 메뉴: 1. Join | 2. Cancel");
		System.out.print(" 메뉴 선택 : ");
		menuNo = scanner.nextLine();
		if(menuNo.equals("1")) {
			
			// user 테이블에 개인 정보 저장
			try {
				String sql = "" + 
						"INSERT INTO users (userid, username, userpassword, userage, useremail) " +
						"VALUES (?, ?, ?, ?, ?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, user.getUserId());
				pstmt.setString(2, user.getUserName());
				pstmt.setString(3, user.getUserPassword());
				pstmt.setInt(4, user.getUserAge());
				pstmt.setString(5, user.getUserEmail());
				pstmt.executeUpdate();
				timer(200); System.out.println("\n [회원가입]이 완료되었습니다.");
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
				exit();
			}
		}
		// 메인 메뉴 실행
		mainMenu();
	}
	
	// 로그인
	public void login() {
		//입력받기 
		System.out.println("[로그인]");
		System.out.print(" 아이디  : ");
		user.setUserId(scanner.nextLine());
		System.out.print(" 비밀번호 : ");
		user.setUserPassword(scanner.nextLine());
		
		//보조 메뉴 출력
		System.out.println("─────────────────────────────────");
		System.out.println(" 보조 메뉴 : 1. login | 2. Cancel");
		System.out.print(" 메뉴 선택 : ");
		menuNo = scanner.nextLine();
		
		if(menuNo.equals("1")) {
			try {
				//sql문 작성 
				String sql = "" + 
						"SELECT userpassword " + 
						"FROM users " + 
						"WHERE userid = ?";
				
				//prepareStatement 얻기 및 값 지정
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, user.getUserId());
				
				//SQL문 실행 후, ResultSet을 통해 데이터 읽기
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					String dbPassword = rs.getString("userpassword");
					if(dbPassword.equals(user.getUserPassword())) {
						// 차후 아이디 비밀번호 확인을 위해 
						// 로그인 아이디 비밀번호 값 얻기
						loginId = user.getUserId();
						loginpassword = user.getUserPassword();
						System.out.println();
						timer(300); System.out.println(" " + user.getUserId() + "님 환영합니다.");
					} else {
						timer(300); System.out.println("\n 비밀번호가 일치하지 않습니다. \n");
						login();
					}
				} else { //db에서 일치하는 값 없을 때
					timer(300); System.out.println("\n 아이디가 존재하지 않습니다. \n");
					login();
				}
				rs.close();
				pstmt.close();
				
			} catch (Exception e) {
				e.printStackTrace();
				exit();
			}
		}
		// 메인 메뉴 실행
		list();
	}
	
	// 로그 아웃
	public void logout() {
		//로그인 아이디 없애기
		timer(500); System.out.println("\n [로그아웃] 합니다. ");
		loginId = null;
		
		//게시물 목록 출력
		list();
	}
	
	// 메인 메뉴
	public void mainMenu() {
		// 로그인 전 (공통)
		if (loginId == null) {
		System.out.println("\n─────────────────────────────────────────────────────────────────");
		System.out.println(" 메인 메뉴 : │ 0. join │ 1. login │ 2. Create │ 3. Read │ 9. Exit │");
		System.out.print(" 메뉴 선택 : ");
		String menuNo = scanner.nextLine();
		System.out.println();
		
				switch(menuNo) {
				case "0" -> join();
				case "1" -> login();
				case "2" -> create();
				case "3" -> read();
				case "9" -> exit();
			}
		
		// 로그인 후 [관리자]
		} else if (loginId.equals("admin")) {
			System.out.println("\n───────────────────────────────────────────────────────────────────");
			System.out.println(" 메인 메뉴 : │ 2. Create │ 3. Read │ 4. Clear │ 5. logout │ 9. Exit │");
			System.out.print(" 메뉴 선택 : ");
			String menuNo = scanner.nextLine();
			System.out.println();
			
				switch(menuNo) {
				case "2" -> create();
				case "3" -> read();
				case "4" -> clear();
				case "5" -> logout();
				case "9" -> exit();
				}		
				
		// 로그인 후 [일반 회원]
		} else if (loginId != null && loginId.equals(user.getUserId())) {
			System.out.println("\n──────────────────────────────────────────────────────────────────────────");
			System.out.println(" 메인 메뉴 : │ 2. Create │ 3. Read  │ 5. logout │ 6. withdrawal  │ 9. Exit │");
			System.out.print(" 메뉴 선택 : ");
			String menuNo = scanner.nextLine();
			System.out.println();
			
				switch(menuNo) {
				case "2" -> create();
				case "3" -> read();
				case "5" -> logout();
				case "6" -> deleteUser();
				case "9" -> exit();
			}
		} 
	}
	
	// 게시글 작성
	public void create() {
		// 입력 받기
		System.out.println("[새 게시물 입력]");
		System.out.print(" 제목  : ");
		board.setBtitle(scanner.nextLine());
		System.out.print(" 내용  : ");
		board.setBcontent(scanner.nextLine());
		System.out.print(" 작성자 : ");
		board.setBwriter(scanner.nextLine());
		
		// 보조 메뉴 출력
		System.out.println("───────────────────────────────");
		System.out.println(" 보조 메뉴 : 1. Ok │ 2. Cancel ");
		System.out.print(" 메뉴 선택 : ");
		String menuNo = scanner.nextLine();
		if (menuNo.equals("1")) {
			// boards 테이블에 게시물 정보 저장
			try {
				String sql = "" +
					"INSERT INTO boards (btitle, bcontent, bwriter, bdate) " +
					"VALUES (?, ?, ?, now())";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, board.getBtitle());
				pstmt.setString(2, board.getBcontent());
				pstmt.setString(3, board.getBwriter());
				pstmt.executeUpdate();
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
				exit();
			}
		}
		// 게시물 목록 출력
		list();
	}
	
	// 게시글 읽기
	public void read() {
		// 입력 받기
		System.out.println("[게시물 읽기]");
		System.out.print(" bno : ");
		int bno = Integer.parseInt(scanner.nextLine());
		
		// boards 테이블에서 해당 게시물을 가져와 출력
		
		try {
			String sql = "" +
				"SELECT bno, btitle, bcontent, bwriter, bdate " +
				"FROM boards " +
				"WHERE bno=?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				board.setBno(rs.getInt("bno"));
				board.setBtitle(rs.getString("btitle"));
				board.setBcontent(rs.getString("bcontent"));
				board.setBwriter(rs.getString("bwriter"));
				board.setBdate(rs.getDate("bdate"));
				System.out.println("===============");
				System.out.println(" 번호   : " + board.getBno());
				System.out.println(" 제목   : " + board.getBtitle());
				System.out.println(" 내용   : " + board.getBcontent());
				System.out.println(" 작성자 : " + board.getBwriter());
				System.out.println(" 날짜   : " + board.getBdate());
				System.out.println("===============");
				
				
				// 보조 메뉴 출력
				System.out.println("────────────────────────────────────────────");
				System.out.println(" 보조 메뉴 : 1. Update │ 2. Delete │ 3. List");
				System.out.print(" 메뉴 선택 : ");
				String menuNo = scanner.nextLine();
				System.out.println();
				
				// 로그인한 사용자 비밀번호 확인 후 수정, 삭제 진행 가능
				if (menuNo.equals("1")) {
					update(board);
				} else if (menuNo.equals("2")) {
					delete(board);
				} 
			} 
	
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			exit();
		}
		
		// 게시물 목록 출력
		list();
	}
	
	// 게시글 수정
	public void update(Board board) {
		// 사용자 확인(비밀번호 입력)
		System.out.println(" 사용자 확인을 위해 비밀번호를 입력하세요. ");
		System.out.print(" 비밀번호 : ");
		loginpassword = scanner.nextLine();
		if (loginpassword.equals(user.getUserPassword())) {
			
			// 수정 내용 입력 받기
			System.out.println("[수정 내용 입력]");
			System.out.print(" 제목  : ");
			board.setBtitle(scanner.nextLine());
			System.out.print(" 내용  : ");
			board.setBcontent(scanner.nextLine());
			System.out.print(" 작성자 : ");
			board.setBwriter(scanner.nextLine());
			
			// 보조 메뉴 출력
			System.out.println("───────────────────────────────");
			System.out.println(" 보조 메뉴 : 1. Ok │ 2. Cancel ");
			System.out.print(" 메뉴 선택 : ");
			String menuNo = scanner.nextLine();
			if (menuNo.equals("1")) {
				// boards 테이블에 게시물 정보 저장
				try {
					String sql = "" +
						"UPDATE boards SET btitle=?, bcontent=?, bwriter=? " +
						"WHERE bno=?";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, board.getBtitle());
					pstmt.setString(2, board.getBcontent());
					pstmt.setString(3, board.getBwriter());
					pstmt.setInt(4, board.getBno());
					pstmt.executeUpdate();
					System.out.println("\n 선택한 게시글이 [수정]되었습니다.");
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
					exit();
				}
			}
		}
		// 게시물 목록 출력
		list();
	}
	
	// 게시글 선택 삭제
	public void delete(Board board) {
		// 사용자 확인(비밀번호 입력)
				System.out.println(" 사용자 확인을 위해 비밀번호를 입력하세요. ");
				System.out.print(" 비밀번호 : ");
				loginpassword = scanner.nextLine();
				if (loginpassword.equals(user.getUserPassword())) {
		
			// boards 테이블에 게시물 정보 삭제
			try {
				String sql = "DELETE FROM boards WHERE bno=?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, board.getBno());
				pstmt.executeUpdate();
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
				exit();
			}
		}
		// 게시물 목록 출력
		list();
	}
	
	public void clear() {
		System.out.println("[게시물 전체 삭제]");
		System.out.println("──────────────────────────────");
		System.out.println(" 보조 메뉴 : 1. Ok │ 2. Cancel");
		System.out.print(" 메뉴 선택 : ");
		String menuNo = scanner.nextLine();
		if (menuNo.equals("1")) {
			// boards 테이블에 게시물 정보 전체 삭제
			if(loginId.equals(user.getUserId())) {
				try {
					String sql = "TRUNCATE TABLE boards";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.executeUpdate();
					System.out.println("\n 게시글이 모두 [삭제]되었습니다.");
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
					exit();
				}
			}
		}
		// 게시물 목록 출력
		list();
	}
	
	// 회원 탈퇴
	public void deleteUser() {
		
		// 회원정보 확인 후 삭제 진행
		System.out.print(" 이름    : ");
		user.setUserName(scanner.nextLine());
		System.out.print(" 아이디   : ");
		user.setUserId(scanner.nextLine());
		System.out.print(" 비밀번호 : ");
		user.setUserPassword(scanner.nextLine());
		
		if ( loginId.equals(user.getUserId()) && loginpassword.equals(user.getUserPassword())) {
			try {
				String sql = "DELETE FROM users WHERE userid=?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, user.getUserId());
				pstmt.executeUpdate();
				timer(600); System.out.println("\n [회원 탈퇴]가 정상적으로 이루어졌습니다.\n");
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
				exit();
			}
		} else {
			timer(500); System.out.println("\n [회원탈퇴]에 실패하였습니다.(가입정보 불일치) \n");
			timer(700); System.out.print(" 로그아웃 합니다 ");
			timer(800); System.out.print(".");
			timer(800); System.out.print(".");
			timer(800); System.out.print(". \n");
			timer(1000); main(null);
			
		}
		// 게시물 목록 출력
		list();
	}
	
	// 프로그램 종료
	public void exit() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {}
		}
		System.out.print(" 게시판 종료 중 ");
		System.out.print("."); timer(800);
		System.out.print("."); timer(800);
		System.out.print("."); timer(800);
		System.out.print("."); timer(800);
		System.out.print("."); timer(800);
		System.out.println("\n\n == 프로그램이 정상 종료되었습니다. == ");
		System.exit(0);
	}
	
	// 메서드 또는 안내 문구 딜레이 주기
	public void timer(int mTime) {
        try { Thread.sleep(mTime); }  catch (Exception e) {System.out.print(""); };
     }    
    
	
	// 메인 실행 함수
	public static void main (String[] args) {
		System.out.println("\n *** Ezen Academy - Java 게시판에 오신 것을 환영합니다. *** \n");
		Main board = new Main();
		board.mainMenu();
	}
}
