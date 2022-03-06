package sosoya.mvc.view;

import java.util.Scanner;   

import sosoya.mvc.controller.BasketController;
import sosoya.mvc.model.dto.BasketVO;
import sosoya.mvc.model.dto.MemberVO;

public class BasketView {
	private static Scanner sc = new Scanner(System.in);
	
	// 장바구니 전체검색
	public static void printSelectAllBasket(MemberVO memberVO) {
		System.out.println("\n=== 장바구니상품(전체검색) ===");
		BasketController.selectAllBasket(memberVO);
	}
	
	// 장바구니 수정
	public static void printUpdateBasket(MemberVO memberVO) {
		System.out.println("\n=== 장바구니상품(수정) ===");
		
		System.out.print("수정할 (장바구니코드)를 입력해주세요: ");
		int basketCode = Integer.parseInt(sc.nextLine());
		
		System.out.print("수정할 (상품개수)를 입력해주세요: ");
		int goodsCount = Integer.parseInt(sc.nextLine());
		
		BasketController.updateBasket(basketCode, goodsCount);
	}
	
	// 장바구니 삭제
	public static void printDeleteBasket(MemberVO memberVO) {
		System.out.println("\n=== 장바구니상품(삭제) ===");
		
		// 전체삭제, 부분삭제 부분 추가하자.
		
		System.out.print("삭제할 장바구니 코드 : ");
		int basketCode = Integer.parseInt(sc.nextLine());
		
		System.out.print("정말 삭제하시겠습니까??(Y/N) : ");
		String answer = sc.nextLine();
		if(answer.toUpperCase().equals("Y")) {
			BasketController.deleteBasket(basketCode);
		} else if(answer.toUpperCase().equals("N")) {
			System.out.println("장바구니 상품삭제가 취소 되었습니다.");
		} else {
			System.out.println("Y또는N을 입력해주세요.");
		}
	}
	
	// 장바구니 담기 메뉴
	public static void printInsertBasket(MemberVO memberVO) {
		System.out.println("\n=== 상품 장바구니에 담기 ===");
			
		System.out.print("상품코드 : ");
		int goodsCode = Integer.parseInt(sc.nextLine());
		
		System.out.print("구매개수 : ");
		int goodsCount = Integer.parseInt(sc.nextLine());
		
		BasketVO basketVO = new BasketVO(memberVO.getId(), goodsCode, goodsCount);
		BasketController.inputBasket(basketVO);
	}
}