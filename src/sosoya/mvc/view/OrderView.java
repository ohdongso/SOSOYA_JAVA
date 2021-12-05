package sosoya.mvc.view;

import java.util.List;
import java.util.Scanner; 

import sosoya.mvc.controller.OrdersController;
import sosoya.mvc.model.dto.MemberVO;
import sosoya.mvc.model.dto.OrdersDetailsVO;
import sosoya.mvc.model.dto.OrdersVO;

public class OrderView {
	private static Scanner sc = new Scanner(System.in);
	
	// 상품 검색후 주문하는 기능
	public static void printOrder(MemberVO memberVO) {
		System.out.println("\n----- " + memberVO.getId() + "님 방문을 환영합니다." + " / 회원등급[" + memberVO.getGrade() + "] -----");
		System.out.println("=== 상품주문하기 ===");
		
		System.out.print("주문할 상품 코드를 입력해주세요 : ");
		int goodsCode = Integer.parseInt(sc.nextLine());
		
		System.out.print("주문할 상품 개수를 입력해주세요 : ");
		int goodsCount = Integer.parseInt(sc.nextLine());
		
		System.out.print("배송지를 입력해주세요 : ");
		String orderDi = sc.nextLine();
	
		OrdersVO ordersVO = new OrdersVO(0, memberVO.getId(), orderDi, 0, null);
		OrdersDetailsVO ordersDetailsVO = new OrdersDetailsVO(0, 0, goodsCode, 0, goodsCount, 0);
		
		ordersVO.getOrdersDetailsList().add(ordersDetailsVO);
		
		OrdersController.insertOrders(ordersVO);
	}
	
	// 장바구니상품 전체주문하는 기능
	public static void printDetailAllOrder(MemberVO memberVO) {
		System.out.println("\n----- " + memberVO.getId() + "님 방문을 환영합니다." + " / 회원등급[" + memberVO.getGrade() + "] -----");
		System.out.println("=== 장바구니 상품전체주문하기 ===");
		
		System.out.print("배송지를 입력해주세요 : ");
		String orderDi = sc.nextLine();
		
		 // 주문객체생성, 회원아이디, 주소는 넣는다.
		OrdersVO ordersVO = new OrdersVO(0, memberVO.getId(), orderDi, 0, null);
		
		// 주문객체의 주문상세 List를 가져온다.
		List<OrdersDetailsVO> orderDetailList = ordersVO.getOrdersDetailsList();
		
		// 장바구니에 담긴 상품 아이디와, 상품 개수를 주문상세 객체의 생성자에 대입해 List에 넣는다.
		
		
	}
	
	// 장바구니상품 선택주문하는 기능
	
	// 주문내역 보기
	public static void printSelectAllOrders(MemberVO memberVO) {
		System.out.println("\n----- " + memberVO.getId() + "님 방문을 환영합니다." + " / 회원등급[" + memberVO.getGrade() + "] -----");
		System.out.println("=== 주문내역 ===");
		
		OrdersController.selectOrdersByMemberId(memberVO.getId());
	}
}