package nit;

import java.io.EOFException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
public class User {
	private static String userName;
	private static String password;
	private static byte level;
	private byte status;
	User(String userName){
		setUserName(userName);
	}
	
	public static  void setUserName(String username) {
		userName = username;
	}
	public static void setPassword(String password) {
		password = password;
	}
	public static void setLevel(byte level) {
		level = level;
	}
	public static void setStatus(byte status) {
		status = status;
	}
	public String getUserName() {
		return this.userName;
	}
	public String getPassword() {
		return this.password;
	}
	public static byte getLevel() {
		return level;
	}
	public byte getStatus() {
		return this.status;
	}
	public static void main(String[] args)throws EOFException, ClassNotFoundException, SQLException, ParseException {
		@SuppressWarnings("unused")
		Connection con = null;
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/mytest";
		String user = "root";
		String password1 = "a1s2d3f4";
		Class.forName(driver);
		con = DriverManager.getConnection(url, user, password1);
		Statement statement = con.createStatement();
		String sql = "select * from user";
		ResultSet rs = statement.executeQuery(sql);
		String checkUserName = null;
		//boolean findUser = false;
		while (rs.next()) {
			checkUserName = rs.getString("UserName");
			if (checkUserName.equals(userName)) {
				setPassword(rs.getString("Password"));
				setLevel(rs.getByte("Level"));
				setStatus(rs.getByte("Status"));
				break;
			}
		}
		menu();
	}
	public static void menu() throws EOFException, ClassNotFoundException, SQLException, ParseException {
		Scanner sc = new Scanner(System.in);
		while (getLevel() == 0) {
			System.out.println("*********************************");
			System.out.println("*\t欢迎使用教务系统-教师\t*");
			System.out.println("*\t\t\t\t*");
			System.out.println("*\t1.进行课程管理\t\t*");
			System.out.println("*\t2.查看学生成绩\t\t*");
			System.out.println("*\t3.修改用户密码\t\t*");
			System.out.println("*\t\t\t\t*");
			System.out.println("*********************************");	
			int x = sc.nextInt();
			Connection con = null;
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/mytest";
			String user = "root";
			String password1 = "a1s2d3f4";
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password1);
			if (x == 1) {
				System.out.println("您要进行的是：1.增加课程 2.删除课程");
				int y = sc.nextInt();
				//
				PreparedStatement psql;
				String another = sc.nextLine();
				if (y == 1) {
					psql = con.prepareStatement("insert into course (CourseID,ClassName,SubjectName,Teacher,BeginDate,FinishDate,Remark) values (?,?,?,?,?,?,?)");
					System.out.println("设置课程ID：");
					psql.setInt(1, sc.nextInt());
					String other = sc.nextLine();
					System.out.println("设置授课班级：");
					psql.setString(2, sc.nextLine());
					System.out.println("设置课程名称：");
					psql.setString(3, sc.nextLine());
					psql.setString(4, userName);
					System.out.println("请设置课程开始时间(格式：yyyy-MM-dd)：");
					psql.setString(5, sc.nextLine());
					System.out.println("请设置课程结束时间(格式：yyyy-MM-dd)：");
					psql.setString(6, sc.nextLine());
					System.out.println("添加课程成功！");
				}
				if (y == 2) {
					Statement statement = con.createStatement();
					//方法1
					String str = "select * from course where Teacher = '%s'";
					String msg = String.format(str, userName);
					//方法2
					ResultSet rs = statement.executeQuery("select * from course where Teacher =  '"+userName+"'");
					//"select * from course where Teacher =  '"+userName+"'";
				
						
					System.out.println("-----------------");
					System.out.println("现在的课程有：");
					System.out.println("-----------------");
					
					int id = 0;
					String className = null ;
					String subjectName = null ;
					while (rs.next()) {
						id = rs.getInt("id");
						subjectName = rs.getString("SubjectName");
						System.out.println("课程id：" + id + "\t授课班级：" + className + "\t课程名称：" + subjectName);
					}
					rs.close();
					System.out.println("请输入您要删除的课程id：");
					int m = sc.nextInt();
					psql = con.prepareStatement("delete from course where id = ?");
					psql.setInt(1, m);
					con.close();
					sc.close();
					psql.close();
				}
			}
			else if (x == 2) {
				System.out.println("*********************************");
				System.out.println("*\t\t\t\t*");
				System.out.println("*\t1.按课程查询\t*");
				System.out.println("*\t2.按学生查询\t*");
				System.out.println("*\t\t\t\t*");
				System.out.println("*********************************");
				int y = sc.nextInt();
				String ano = sc.nextLine();
				if (y == 1) {
					Statement statement = con.createStatement();
					System.out.println("请输入要查询的课程的ID：");
					int courseID = sc.nextInt();
					ResultSet rs = statement.executeQuery("select * from score where CourseID =  '"+courseID+"'");
					System.out.println("-----------------");
					System.out.println("该课程的成绩为：");
					System.out.println("-----------------");
					int studentNo = 0;
					float score = 0.0f;
					while (rs.next()) {
						studentNo = rs.getInt("StudentNo");
						score = rs.getFloat("Score");
						System.out.println("课程ID：" + courseID + "\t学生学号：" + studentNo + "\t成绩：" + score);
					}
					rs.close();
				}
				else if (y == 2) {
					Statement statement = con.createStatement();
					System.out.println("请输出要查询的学生的学号：");
					String studentno = sc.nextLine();
					ResultSet rs = statement.executeQuery("select * from score where StudentNo =  '"+studentno+"'");
					System.out.println("-----------------");
					System.out.println("该学生的成绩为：");
					System.out.println("-----------------");
					int courseID = 0;
					float score = 0.0f;
					while (rs.next()) {
						courseID = rs.getInt("CourseID");
						score = rs.getFloat("Score");
						System.out.println("课程ID：" + courseID + "\t成绩：" + score);
					}
					rs.close();
				}
				else System.out.println("请进行正确的操作！");
			}
			else if (x == 3) {
				String another = sc.nextLine();
				System.out.println("请输入新的密码：");
				String newPassword = sc.nextLine();
				PreparedStatement sql;
				sql = con.prepareStatement("update user set Password = ? where UserName = ? ");
				sql.setString(1, newPassword);
				sql.setString(2, userName);
				sql.executeUpdate();
				System.out.println("修改密码成功！");
			}
			else System.out.println("请进行正确的操作！");
			}
		}
	}
