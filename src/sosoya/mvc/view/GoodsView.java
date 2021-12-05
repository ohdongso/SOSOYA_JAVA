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
				// 1,주문하기 2,장바구니에담기 3,뒤로가기
				printOrderBasket(memberVO);
				break;
			case 2:
				// 부분검색(1,제품이름 2,가격 3,리뷰개수)
				printSelectPartSearch(memberVO);
				// 1,주문하기 2,장바구니에담기 3,뒤로가기
				printOrderBasket(memberVO);
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
	
	// 검색 후, 상품주문 or 장바구니에 담는 view
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
				OrderView.printOrder(memberVO);
				break;
			case 2:
				// 장바구니에담기
				BasketView.printInsertBasket(memberVO);
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
	
	// 부분검색(1,제품이름 2,가격 3,리뷰개수)
	public static void printSelectPartSearch(MemberVO memberVO) {
		while(true) {
			System.out.println("\n----- " + memberVO.getId() + "님 방문을 환영합니다." + " / 회원등급[" + memberVO.getGrade() + "] -----");
			System.out.println("┌──────────────┐");
			System.out.println("  	 1. 상품이름으로검색						");
			System.out.println("  	 2. 상품가격으로검색						");
			System.out.println("  	 3. Top3리뷰개수로검색        		    ");
			System.out.println("  	 4. 뒤로가기                		    ");
			System.out.println("└──────────────┘");
			System.out.print("선택>>");
			
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1:
				// 상품이름으로검색
				printSelectByNameGoods(memberVO);
				break;
			case 2:
				// 상품가격으로검색
				printSelectByPriceGoods(memberVO);
				break;
			case 3:
				// Top3리뷰개수로검색
				break;
			case 4:
				// 뒤로가기
				return;
			default :
				// 1~4번 까지 숫자를 입력해주세요.
				System.out.println("1~34번 까지 숫자를 입력해주세요.");
				break;
			}
		}
	}
	
	public static void printSelectByNameGoods(MemberVO memberVO) {
		System.out.println("\n----- " + memberVO.getId() + "님 방문을 환영합니다." + " / 회원등급[" + memberVO.getGrade() + "] -----");
		System.out.println("\n=== 상품이름으로검색 ===");
		
		String goodsName = "";
		System.out.print("검색할 상품 이름을 입력해주세요 : ");
		goodsName = sc.nextLine();
		
		GoodsController.selectByNameGoods(goodsName);
	}
	
	public static void printSelectByPriceGoods(MemberVO memberVO) {
		System.out.println("\n----- " + memberVO.getId() + "님 방문을 환영합니다." + " / 회원등급[" + memberVO.getGrade() + "] -----");
		System.out.println("\n=== 상품가격으로검색 ===");
		
		int minPrice = 0;
		int maxPrice = 0;
		
		System.out.print("최소 값 입력 : ");
		minPrice = Integer.parseInt(sc.nextLine());
				
		System.out.print("최대 값 입력 : ");
		maxPrice = Integer.parseInt(sc.nextLine());
		
		GoodsController.SelectByPriceGoods(minPrice, maxPrice);
	}
}