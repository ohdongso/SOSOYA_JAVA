package sosoya.mvc.view;

import java.util.ArrayList; 
import java.util.List;
import java.util.Scanner;

import sosoya.mvc.controller.OrdersController;
import sosoya.mvc.model.dto.MemberVO;
import sosoya.mvc.model.dto.OrdersVO;

public class OrderView {
	private static Scanner sc = new Scanner(System.in);
	
	// 검색후 주문하는 기능
	public static void printOrder(MemberVO memberVO) {
		System.out.println("\n----- " + memberVO.getId() + "님 방문을 환영합니다." + " / 회원등급[" + memberVO.getGrade() + "] -----");
		System.out.println("=== 상품주문하기 ===");
		
		int goodsCode = 0;
		int goodsCount = 0;
		String orderDi = "";
		
		System.out.print("주문할 상품 코드를 입력해주세요 : ");
		goodsCode = Integer.parseInt(sc.nextLine());
		
		System.out.print("주문할 상품 개수를 입력해주세요 : ");
		goodsCount = Integer.parseInt(sc.nextLine());
		
		System.out.print("배송지를 입력해주세요 : ");
		orderDi = sc.nextLine();
	
		List<Integer> goodsCodeList = new ArrayList<>();
		goodsCodeList.add(goodsCode);
		
		// OrdersVO ordersVO = new OrdersVO(0, memberVO.getId(), goodsCodeList, orderDi, 0, null, goodsCount);
		// OrdersController.insertOrders(ordersVO);
	}
}