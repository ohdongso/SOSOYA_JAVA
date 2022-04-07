package sosoya.mvc.view;

import java.util.Scanner;

import sosoya.mvc.model.dto.MemberVO;

public class ErView {
	private static Scanner sc = new Scanner(System.in);
	
	public static void printErMenu(MemberVO memberVO) {
		while(true) {
			System.out.println("\n----- " + memberVO.getId() + "님 방문을 환영합니다."   + " / 회원등급[" + memberVO.getGrade() + "] -----");
			System.out.println("┌──────────────┐");
			System.out.println("  	 1. 교환				                        ");
			System.out.println("  	 2. 환불				                        ");
		    System.out.println("  	 3. 뒤로가기          						    ");
			System.out.println("└──────────────┘");
			System.out.print("선택>>");
			
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1:
				// 교환가능한 결제목록
				
				
				// 교환
				printExchangePage(memberVO);
				break;
			case 2:
				// 환불가능한 결제목록
				
				
				// 환불
				printRefundPage(memberVO);
				break;
			case 3:
				// 뒤로가기
				return;
			default :
				// 1~3번 까지 숫자를 입력해주세요.
				System.out.println("1~3번 까지 숫자를 입력해주세요.");
				break;
			}
		} // while문 끝.
	}
	
	// 교환
	public static void printExchangePage(MemberVO memberVO) {
		System.out.println("\n=== 상품교환 ===");
		
	}
	
	// 환불
	public static void printRefundPage(MemberVO memberVO) {
		
	}
}