package sosoya.mvc.view;

import java.util.Scanner; 

import sosoya.mvc.controller.FaqController;
import sosoya.mvc.model.dto.MemberVO;

public class FaqView {
	private static Scanner sc = new Scanner(System.in);
	
	public static void printFaq(MemberVO memberVO) {
		while(true) {
			System.out.println("\n----- " + memberVO.getId() + "님 방문을 환영합니다."   + " / 회원등급[" + memberVO.getGrade() + "] -----");
			System.out.println("┌──────────────┐");
			System.out.println("  	 1. 전체검색						    ");
			System.out.println("  	 2. 부분검색						    ");
			System.out.println("  	 3. 뒤로가기						    ");
			System.out.println("└──────────────┘");
			System.out.print("선택>>");
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				// 전체검색
				FaqController.selectAllFaq();
				break;
			case 2:
				// 부분검색
				selectByFaq(memberVO);
				break;
			case 3:
				// 뒤로가기
				return;
			default:
				System.out.println("1에서 3사이의 숫자를 입력해주세요.");
			}
		}
	}
	
	// 부분 검색
	public static void selectByFaq(MemberVO memberVO) {
		while(true) {
			System.out.println("\n----- " + memberVO.getId() + "님 방문을 환영합니다."   + " / 회원등급[" + memberVO.getGrade() + "] -----");
			System.out.println("┌──────────────┐");
			System.out.println("  	 1. 교환						    ");
			System.out.println("  	 2. 환불						    ");
			System.out.println("  	 3. 주문						    ");
			System.out.println("  	 4. 결제						    ");
			System.out.println("  	 5. 회원등급					    ");
			System.out.println("  	 6. 뒤로가기					    ");
			System.out.println("└──────────────┘");
			System.out.print("선택>>");
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				// 교환
				FaqController.selectByFaq(menu);
				break;
			case 2:
				// 환불
				FaqController.selectByFaq(menu);
				break;
			case 3:
				// 주문
				FaqController.selectByFaq(menu);
				break;
			case 4:
				// 결제
				FaqController.selectByFaq(menu);
				break;
			case 5:
				// 회원등급
				FaqController.selectByFaq(menu);
				break;
			case 6:
				// 뒤로가기
				return;
			default:
				System.out.println("1에서 6사이의 숫자를 입력해주세요.");
			}
		}
	}	
}