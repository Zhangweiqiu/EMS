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
public class Login {
	static String loginUserName;
	static String loginPassword;
	static boolean isNotSignIn = false;
	public Login(String userName,String password){
		this.loginUserName = userName;
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
					System.out.println("登录成功！");
					isNotSignIn = true;
				}
				else 
					System.out.println("密码输入有误！");
				break;
			}
		}
		if (!findUser) {
			System.out.println("该用户不存在！请注册！");
		}
		
	}

}
