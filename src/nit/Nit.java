package nit;
import java.io.EOFException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class Nit {
	
	private static boolean isNotSignIn = false;
	private static String userName;
	public static void setUserName(String username) {
		userName = username;
	}
	public String getUserName() {
		return this.userName;
	}
	public static void main(String args[]) throws EOFException, ClassNotFoundException, SQLException, ParseException{
		while(!isNotSignIn) {
		menu1();
		}
		while(isNotSignIn)
		menu2();
	}

	/**菜单注册/登录界面***/
	@SuppressWarnings("static-access")
	public static void menu1() {
		Scanner sc = new Scanner(System.in);
		System.out.println("*********************************");
		System.out.println("*\t欢迎使用教务管理系统\t*");
		System.out.println("*\t\t\t\t*");
		System.out.println("*\t请先1.登录/2.注册\t\t*");
		System.out.println("*\t\t\t\t*");
		System.out.println("*********************************");
		int x = sc.nextInt();
		@SuppressWarnings("unused")
		String another = sc.nextLine();
		if (x == 1) {
			System.out.println("欢迎登录NIT教务管理系统！");
			System.out.println("请输入用户名：");
			String username = sc.nextLine();
			System.out.println("请输入用户密码：");
			String password = sc.nextLine();		
			@SuppressWarnings("unused")
			Login login = new Login(username,password);
			try {
				login.main(null);
				if (login.getIsNotSignIn()){
					isNotSignIn = true ;
					setUserName(login.getUserName());
				}
			} catch (EOFException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (x == 2) {
			System.out.println("欢迎注册NIT教务管理系统！");
			
			System.out.println("请填写您的用户名：");
			String username = sc.nextLine();
			System.out.println("请填写您的密码：");
			String password = sc.nextLine();
			@SuppressWarnings("unused")
			Signin signin = new Signin(username,password);
			try {
				signin.main(null);
			} catch (EOFException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
			System.out.println("请进行正确的操作！");
		
	}
	public static void  menu2() throws EOFException, ClassNotFoundException, SQLException, ParseException {
		@SuppressWarnings("unused")
		User user = new User(userName);
		user.main(null);
	}
	
	}
	



