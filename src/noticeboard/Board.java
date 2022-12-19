package noticeboard;

import java.util.Date;
import lombok.Data;

@Data
public class Board {
	// join, login
	private String userId;
	private String userName;
	private String userPassword;
	private int userAge;
	private String userEmail; 
	
	// board
	private int bno;
	private String btitle;
	private String bcontent;
	private String bwriter;
	private Date bdate;
}
