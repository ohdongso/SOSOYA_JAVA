package sosoya.mvc.view;

import java.util.Scanner;

public class MenuView {
	private static Scanner sc = new Scanner(System.in);
	
	public static void menu() {
		while(true) {
			ViewStore.printMenu();
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1:
				// 로그인
				System.out.println("로그인");
				break;
			case 2:
				// 회원가입
				System.out.println("회원가입");
				break;
			case 3:
				// 종료
				System.out.println("종료");
				break;
			default :
				// 1~3번 까지 숫자를 입력해주세요.
				System.out.println("1~3번 까지 숫자를 입력해주세요.");
				break;
			}
			
			break;
		}
	}
	
	
	
	
}