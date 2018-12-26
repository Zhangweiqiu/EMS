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
public class Signin {
	private static String signinUserName;
	private static String signinPassword;
	
	@SuppressWarnings("static-access")
	public Signin(String username,String password){
		this.setUserName(username);
		this.setPassword(password);
	}
	public void setUserName(String username) {
		this.signinUserName = username;
	}
	public void setPassword(String password) {
		this.signinPassword = password;
	}
	public String getUserName() {
		return this.signinUserName;
	}
	public String getPassword() {
		return this.signinPassword;
	}
	public static void main(String args[])throws EOFException, ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("你的身份是：1.老师    2.学生");
		boolean isNotTeacher = false ;
		int x = sc.nextInt();
		if (x == 1)
			isNotTeacher = true;
		Connection con = null;
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/mytest";
		String user = "root";
		String password = "a1s2d3f4";
		Class.forName(driver);
		con = DriverManager.getConnection(url, user, password);
		@SuppressWarnings("unused")
		Statement statement = con.createStatement();
		
		PreparedStatement sql ;
		sql = con.prepareStatement("insert into user (UserName,Password,Level,Status) values (?,?,?,?) ");
		sql.setString(1,signinUserName);
		sql.setString(2, signinPassword);
		if (isNotTeacher) {
			sql.setBoolean(3,false);
		}
		else
			sql.setBoolean(3, true);
		sql.setBoolean(4,true);
		sql.executeUpdate();
		
		sql.close();
		System.out.println("注册成功！请登录");
	}
}
