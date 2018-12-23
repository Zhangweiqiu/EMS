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
public class Signin {
	static String signinUserName;
	static String signinPassword;
	public Signin(String userName,String password){
		this.signinUserName = userName;
		this.signinPassword = password;
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
		
		PreparedStatement sql ;
		ResultSet res ;
		sql = con.prepareStatement("insert into user (UserName,Password,Level,Status) values (?,?,?,?) ");
		sql.setString(1,signinUserName);
		sql.setString(2, signinPassword);
		sql.setString(3, null);
		sql.setString(4, null);
		sql.executeUpdate();
		
	}
}
