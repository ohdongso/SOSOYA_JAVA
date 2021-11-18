package sosoya.mvc.view;

import java.util.Scanner;

import sosoya.mvc.controller.BasketController;
import sosoya.mvc.model.dto.BasketVO;
import sosoya.mvc.model.dto.MemberVO;

public class BasketView {
	private static Scanner sc = new Scanner(System.in);
	
	// 장바구니 전체검색
	public static void printSelectAllBasket(MemberVO memberVO) {
		System.out.println("\n----- " + memberVO.getId() + "님 방문을 환영합니다." + " / 회원등급[" + memberVO.getGrade() + "] -----");
		System.out.println("=== 장바구니상품(전체검색) ===");
		BasketController.selectAllBasket(memberVO);
	}
	
	// 장바구니 주문
	
	// 장바구니 수정
	public static void printUpdateBasket(MemberVO memberVO) {
		System.out.println("\n----- " + memberVO.getId() + "님 방문을 환영합니다." + " / 회원등급[" + memberVO.getGrade() + "] -----");
		System.out.println("=== 장바구니상품(수정) ===");
		
		System.out.print("수정할 (장바구니코드)를 입력해주세요: ");
		int basketCode = Integer.parseInt(sc.nextLine());
		
		System.out.print("수정할 (상품개수)를 입력해주세요: ");
		int goodsCount = Integer.parseInt(sc.nextLine());
		
		BasketController.updateBasket(basketCode, goodsCount);
	}
	
	// 장바구니 삭제
	public static void printSelectBasket(MemberVO memberVO) {
		System.out.println("\n----- " + memberVO.getId() + "님 방문을 환영합니다." + " / 회원등급[" + memberVO.getGrade() + "] -----");
		System.out.println("=== 장바구니상품(삭제) ===");
		
	}
	
	// 장바구니 담기 메뉴
	public static void printBasket(MemberVO memberVO) {
		System.out.println("\n----- " + memberVO.getId() + "님 방문을 환영합니다." + " / 회원등급[" + memberVO.getGrade() + "] -----");
		System.out.println("=== 상품 장바구니에 담기 ===");
			
		System.out.print("상품코드 : ");
		int goodsCode = Integer.parseInt(sc.nextLine());
		
		System.out.print("구매개수 : ");
		int goodsCount = Integer.parseInt(sc.nextLine());
		
		BasketVO basketVO = new BasketVO(memberVO.getId(), goodsCode, goodsCount, null);
		BasketController.inputBasket(basketVO);
	}
}