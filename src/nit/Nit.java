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

	/**�˵�ע��/��¼����***/
	@SuppressWarnings("static-access")
	public static void menu1() {
		Scanner sc = new Scanner(System.in);
		System.out.println("*********************************");
		System.out.println("*\t��ӭʹ�ý������ϵͳ\t*");
		System.out.println("*\t\t\t\t*");
		System.out.println("*\t����1.��¼/2.ע��\t\t*");
		System.out.println("*\t\t\t\t*");
		System.out.println("*********************************");
		int x = sc.nextInt();
		@SuppressWarnings("unused")
		String another = sc.nextLine();
		if (x == 1) {
			System.out.println("��ӭ��¼NIT�������ϵͳ��");
			System.out.println("�������û�����");
			String username = sc.nextLine();
			System.out.println("�������û����룺");
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
			System.out.println("��ӭע��NIT�������ϵͳ��");
			
			System.out.println("����д�����û�����");
			String username = sc.nextLine();
			System.out.println("����д�������룺");
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
			System.out.println("�������ȷ�Ĳ�����");
		
	}
	public static void  menu2() throws EOFException, ClassNotFoundException, SQLException, ParseException {
		@SuppressWarnings("unused")
		User user = new User(userName);
		user.main(null);
	}
	
	}
	



