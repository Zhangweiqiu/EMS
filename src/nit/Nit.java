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

	/**�˵�ע��/��¼����***/
	public static void menu1() {
		Scanner sc = new Scanner(System.in);
		System.out.println("****************************");
		System.out.println("*     ��ӭʹ�ý���ϵͳ                *");
		System.out.println("*                          *");
		System.out.println("*     ����1.��¼/2.ע��             *");
		System.out.println("*                          *");
		System.out.println("****************************");
		int x = sc.nextInt();
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
			} catch (EOFException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (login.isNotSignIn)
				isNotSignIn = true ;
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
	public static void  menu2() {
		System.out.println("****************************");
		System.out.println("*     ��ӭ��¼����ϵͳ                *");
		System.out.println("*                          *");
		System.out.println("*     ����1.��¼/2.ע��             *");
		System.out.println("*                          *");
		System.out.println("****************************");
	}
	}
	



