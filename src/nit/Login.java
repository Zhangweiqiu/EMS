package nit;
import java.util.Scanner;
import java.io.EOFException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.mysql.*;
@SuppressWarnings("unused")
public class Login {
	private static String loginUserName;
	private static String loginPassword;
	private static boolean isNotSignIn = false;
	@SuppressWarnings("static-access")
	public Login(String userName,String password){
		this.setUserName(userName);
		this.setPassword(password);
	}
	public String getUserName() {
		return this.loginUserName;
	}
	public String getPassword() {
		return this.loginPassword;
	}
	public boolean getIsNotSignIn() {
		return this.isNotSignIn;
	}
	public static void setIsNotSignIn(boolean x) {
		isNotSignIn = x;
	}
	public void setUserName(String username) {
		this.loginUserName = username;
	}
	public void setPassword(String password) {
		this.loginPassword = password;
	}
	public static void main(String args[])throws EOFException, ClassNotFoundException, SQLException {
		Connection con = null;
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/mytest";
		String user = "root";
		String password = "a1s2d3f4";
		Class.forName(driver);
		con = DriverManager.getConnection(url, user, password);
		Statement statement = con.createStatement();
		String sql = "select * from user";
		ResultSet rs = statement.executeQuery(sql);
		String userName = null;
		String password1 = null;
		boolean findUser = false;
		while (rs.next()) {
			userName = rs.getString("UserName");
			password1 = rs.getString("Password");
			if (userName.equals(loginUserName)) {
				findUser = true;
				if (password1.equals(loginPassword)) {
					System.out.println("��¼�ɹ���");
					setIsNotSignIn(true);
				}
				else 
					System.out.println("������������");
				break;
			}
		}
		if (!findUser) {
			System.out.println("���û������ڣ���ע�ᣡ");
		}
		con.close();
		rs.close();
		
	}

}
