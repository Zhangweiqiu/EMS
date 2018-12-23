package nit;
import java.io.EOFException;
import java.sql.SQLException;
import java.util.Scanner;

public class Nit {
	
	static boolean isNotSignIn = false;
	public static void main(String args[]){
		while(!isNotSignIn) {
		menu1();
		}	
		menu2();
	}

	/**菜单注册/登录界面***/
	public static void menu1() {
		Scanner sc = new Scanner(System.in);
		System.out.println("****************************");
		System.out.println("*     欢迎使用教务系统                *");
		System.out.println("*                          *");
		System.out.println("*     请先1.登录/2.注册             *");
		System.out.println("*                          *");
		System.out.println("****************************");
		int x = sc.nextInt();
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
			} catch (EOFException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (login.isNotSignIn)
				isNotSignIn = true ;
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
	public static void  menu2() {
		System.out.println("****************************");
		System.out.println("*     欢迎登录教务系统                *");
		System.out.println("*                          *");
		System.out.println("*     请先1.登录/2.注册             *");
		System.out.println("*                          *");
		System.out.println("****************************");
	}
	}
	



