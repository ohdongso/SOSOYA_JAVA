package sosoya.mvc.view;

import java.util.Scanner;

import sosoya.mvc.model.dto.MemberVO;

public class MyPageView {
	private static Scanner sc = new Scanner(System.in);
	
	public static void printMyPage(MemberVO memberVO) {
		while(true) {
			System.out.println("\n----- " + memberVO.getId() + "님 방문을 환영합니다."   + " / 회원등급[" + memberVO.getGrade() + "] -----");
			System.out.println("┌──────────────┐");
			System.out.println("  	 1. 장바구니						    ");
			System.out.println("  	 2. 주문내역							");
			System.out.println("  	 3. 결제내역  					        ");
			System.out.println("  	 4. (교환,환불)내역  					");
			System.out.println("  	 5. 작성한리뷰						    ");
			System.out.println("  	 6. 뒤로가기						    ");
			System.out.println("└──────────────┘");
			System.out.print("선택>>");
			
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1:
				// 장바구니		
				printBasket(memberVO);
				break;
			case 2:
				// 주문내역
				break;
			case 3:
				// 결제내역
				break;
			case 4:
				// (교환,환불)내역
				break;
			case 5:
				// 작성한리뷰
				break;
			case 6:
				// 뒤로가기
				return;
			default :
				// 1~6번 까지 숫자를 입력해주세요.
				System.out.println("1~6번 까지 숫자를 입력해주세요.");
				break;
			}
		}
	}
	
	// 장바구니
	public static void printBasket(MemberVO memberVO) {
		while(true) {
			System.out.println("\n----- " + memberVO.getId() + "님 방문을 환영합니다."   + " / 회원등급[" + memberVO.getGrade() + "] -----");
			System.out.println("┌──────────────┐");
			System.out.println("  	 1. 장바구니상품(전체검색)					            ");
			System.out.println("  	 2. 장바구니상품(주문)					            ");
			System.out.println("  	 3. 장바구니상품(수정)							    ");
			System.out.println("  	 4. 장바구니상품(삭제)  					            ");
			System.out.println("  	 5. 뒤로가기   						            ");
			System.out.println("└──────────────┘");
			System.out.print("선택>>");
			
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1:
				// 장바구니상품(전체검색)
				BasketView.printSelectAllBasket(memberVO);
				break;
			case 2:
				// 장바구니상품(주문)
				
				break;
			case 3:
				// 장바구니상품(수정)
				BasketView.printUpdateBasket(memberVO);
				break;
			case 4:
				// 장바구니상품(삭제)
				
				break;
			case 5:
				// (교환,환불)
				// 자주묻는 질문 FAQ
				FaqView.printFaq(memberVO);
				break;
			case 6:
				// 로그아웃
				System.out.println("\n=== SoSoYa Shopping Mall을 이용해 주셔서 감사합니다.^^ ===");
				System.out.println("=== 로그아웃 완료. ===");
				return;
			default :
				// 1~6번 까지 숫자를 입력해주세요.
				System.out.println("1~6번 까지 숫자를 입력해주세요.");
				break;
			}
		}
	}
}