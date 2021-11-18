package sosoya.mvc.view;

import java.util.Scanner;

import sosoya.mvc.controller.GoodsController;
import sosoya.mvc.model.dto.MemberVO;

public class GoodsView {
	private static Scanner sc = new Scanner(System.in);
	
	// 상품검색 메뉴선택
	public static void printGoods(MemberVO memberVO) {
		while(true) {
			System.out.println("\n----- " + memberVO.getId() + "님 방문을 환영합니다." + " / 회원등급[" + memberVO.getGrade() + "] -----");
			System.out.println("┌──────────────┐");
			System.out.println("  	 1. 전체검색						    ");
			System.out.println("  	 2. 부분검색							");
			System.out.println("  	 3. 뒤로가기   					    ");
			System.out.println("└──────────────┘");
			System.out.print("선택>>");
			
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1:
				// 전체검색
				GoodsController.selectAllGoods();
				// 1,주문하기 2,장바구니에담기
				printOrderBasket(memberVO);
				break;
			case 2:
				// 부분검색
				selectByGoods();
				break;
			case 3:
				// 뒤로가기
				return;
			default :
				// 1~3번 까지 숫자를 입력해주세요.
				System.out.println("1~3번 까지 숫자를 입력해주세요.");
				break;
			}
		}
	}
	
	// 전체검색 후, 상품주문 or 장바구니에 담는 view
	public static void printOrderBasket(MemberVO memberVO) {
		while(true) {
			System.out.println("\n----- " + memberVO.getId() + "님 방문을 환영합니다." + " / 회원등급[" + memberVO.getGrade() + "] -----");
			System.out.println("┌──────────────┐");
			System.out.println("  	 1. 주문하기						    ");
			System.out.println("  	 2. 장바구니에담기						");
			System.out.println("  	 3. 뒤로가기   					    ");
			System.out.println("└──────────────┘");
			System.out.print("선택>>");
			
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1:
				// 주문하기
				break;
			case 2:
				// 장바구니에담기
				BasketView.printBasket(memberVO);
				break;
			case 3:
				// 뒤로가기
				return;
			default :
				// 1~3번 까지 숫자를 입력해주세요.
				System.out.println("1~3번 까지 숫자를 입력해주세요.");
				break;
			}
		}
	}
	
	// 장바구니에 담기
	public static void printBasket() {
		
	}
	
	// 부분검색
	public static void selectByGoods() {
		
	}
	
}