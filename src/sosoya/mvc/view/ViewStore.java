package sosoya.mvc.view;

import java.util.Scanner;

public class ViewStore {
	private static Scanner sc = new Scanner(System.in);
	
	/**
	 * 로그인전 메인메뉴 프린트
	 */
	public static void printMenu() {
		System.out.println("=== SoSoYa Shopping Mall ===");
		System.out.println("┌──────────────┐");
		System.out.println("  	 1. 로그인								");
		System.out.println("  	 2. 회원가입								");
		System.out.println("  	 3. 종료								    ");
		System.out.println("└──────────────┘");
		System.out.print("선택>>");
	}
	
	
}