package sosoya.mvc.view;

import java.util.Scanner;

import sosoya.mvc.model.dto.MemberVO;

public class OrderView {
	private static Scanner sc = new Scanner(System.in);
	
	// 검색후 주문하는 기능
	public static void printOrder(MemberVO memberVO) {
		System.out.println("\n----- " + memberVO.getId() + "님 방문을 환영합니다." + " / 회원등급[" + memberVO.getGrade() + "] -----");
		System.out.println("\n=== 상품주문하기 ===");
		
		int goodsCode = 0;
		
		System.out.print("상품 코드를 입력해주세요 : ");
		goodsCode = Integer.parseInt(sc.nextLine());
		
		
	}
	
	
}