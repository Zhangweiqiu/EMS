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
			System.out.println("*\t��ӭʹ�ý���ϵͳ-��ʦ\t*");
			System.out.println("*\t\t\t\t*");
			System.out.println("*\t1.���пγ̹���\t\t*");
			System.out.println("*\t2.�鿴ѧ���ɼ�\t\t*");
			System.out.println("*\t3.�޸��û�����\t\t*");
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
				System.out.println("��Ҫ���е��ǣ�1.���ӿγ� 2.ɾ���γ�");
				int y = sc.nextInt();
				//
				PreparedStatement psql;
				String another = sc.nextLine();
				if (y == 1) {
					psql = con.prepareStatement("insert into course (CourseID,ClassName,SubjectName,Teacher,BeginDate,FinishDate,Remark) values (?,?,?,?,?,?,?)");
					System.out.println("���ÿγ�ID��");
					psql.setInt(1, sc.nextInt());
					String other = sc.nextLine();
					System.out.println("�����ڿΰ༶��");
					psql.setString(2, sc.nextLine());
					System.out.println("���ÿγ����ƣ�");
					psql.setString(3, sc.nextLine());
					psql.setString(4, userName);
					System.out.println("�����ÿγ̿�ʼʱ��(��ʽ��yyyy-MM-dd)��");
					psql.setString(5, sc.nextLine());
					System.out.println("�����ÿγ̽���ʱ��(��ʽ��yyyy-MM-dd)��");
					psql.setString(6, sc.nextLine());
					System.out.println("��ӿγ̳ɹ���");
				}
				if (y == 2) {
					Statement statement = con.createStatement();
					//����1
					String str = "select * from course where Teacher = '%s'";
					String msg = String.format(str, userName);
					//����2
					ResultSet rs = statement.executeQuery("select * from course where Teacher =  '"+userName+"'");
					//"select * from course where Teacher =  '"+userName+"'";
				
						
					System.out.println("-----------------");
					System.out.println("���ڵĿγ��У�");
					System.out.println("-----------------");
					
					int id = 0;
					String className = null ;
					String subjectName = null ;
					while (rs.next()) {
						id = rs.getInt("id");
						subjectName = rs.getString("SubjectName");
						System.out.println("�γ�id��" + id + "\t�ڿΰ༶��" + className + "\t�γ����ƣ�" + subjectName);
					}
					rs.close();
					System.out.println("��������Ҫɾ���Ŀγ�id��");
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
				System.out.println("*\t1.���γ̲�ѯ\t*");
				System.out.println("*\t2.��ѧ����ѯ\t*");
				System.out.println("*\t\t\t\t*");
				System.out.println("*********************************");
				int y = sc.nextInt();
				String ano = sc.nextLine();
				if (y == 1) {
					Statement statement = con.createStatement();
					System.out.println("������Ҫ��ѯ�Ŀγ̵�ID��");
					int courseID = sc.nextInt();
					ResultSet rs = statement.executeQuery("select * from score where CourseID =  '"+courseID+"'");
					System.out.println("-----------------");
					System.out.println("�ÿγ̵ĳɼ�Ϊ��");
					System.out.println("-----------------");
					int studentNo = 0;
					float score = 0.0f;
					while (rs.next()) {
						studentNo = rs.getInt("StudentNo");
						score = rs.getFloat("Score");
						System.out.println("�γ�ID��" + courseID + "\tѧ��ѧ�ţ�" + studentNo + "\t�ɼ���" + score);
					}
					rs.close();
				}
				else if (y == 2) {
					Statement statement = con.createStatement();
					System.out.println("�����Ҫ��ѯ��ѧ����ѧ�ţ�");
					String studentno = sc.nextLine();
					ResultSet rs = statement.executeQuery("select * from score where StudentNo =  '"+studentno+"'");
					System.out.println("-----------------");
					System.out.println("��ѧ���ĳɼ�Ϊ��");
					System.out.println("-----------------");
					int courseID = 0;
					float score = 0.0f;
					while (rs.next()) {
						courseID = rs.getInt("CourseID");
						score = rs.getFloat("Score");
						System.out.println("�γ�ID��" + courseID + "\t�ɼ���" + score);
					}
					rs.close();
				}
				else System.out.println("�������ȷ�Ĳ�����");
			}
			else if (x == 3) {
				String another = sc.nextLine();
				System.out.println("�������µ����룺");
				String newPassword = sc.nextLine();
				PreparedStatement sql;
				sql = con.prepareStatement("update user set Password = ? where UserName = ? ");
				sql.setString(1, newPassword);
				sql.setString(2, userName);
				sql.executeUpdate();
				System.out.println("�޸�����ɹ���");
			}
			else System.out.println("�������ȷ�Ĳ�����");
			}
		}
	}
