package sosoya.mvc.view;

import java.sql.SQLException;  
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import sosoya.mvc.controller.OrdersController;
import sosoya.mvc.model.dao.BasketDAO;
import sosoya.mvc.model.dao.BasketDAOImpl;
import sosoya.mvc.model.dto.BasketVO;
import sosoya.mvc.model.dto.MemberVO;
import sosoya.mvc.model.dto.OrdersDetailsVO;
import sosoya.mvc.model.dto.OrdersVO;
import sosoya.mvc.view.main.FailView;

public class OrderView {
	private static Scanner sc = new Scanner(System.in);
	private static BasketDAO basketDao = new BasketDAOImpl();
	
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
	public static void printBasketAllOrder(MemberVO memberVO) {
		System.out.println("\n----- " + memberVO.getId() + "님 방문을 환영합니다." + " / 회원등급[" + memberVO.getGrade() + "] -----");
		System.out.println("=== 장바구니 상품전체주문하기 ===");
		
		System.out.print("배송지를 입력해주세요 : ");
		String orderDi = sc.nextLine();
		
		OrdersController.insertBasketAllOrder(memberVO, orderDi);
	}
	
	// 장바구니상품 선택주문하는 기능
	public static void printBasketByOrder(MemberVO memberVO) {
		System.out.println("\n----- " + memberVO.getId() + "님 방문을 환영합니다." + " / 회원등급[" + memberVO.getGrade() + "] -----");
		System.out.println("=== 장바구니 상품선택주문하기 ===");
		
		System.out.print("주문할 장바구니 상품 개수를 입력해주세요 : ");
		int count = Integer.parseInt(sc.nextLine());
		
		Map<Integer, Integer> goodsCodeMap = new HashMap<>();
		for(int i = 1; i <= count; i++) {
			System.out.print(i + "번째 주문할 장바구니 코드를 입력해주세요 : ");
			int basketCode = (Integer.parseInt(sc.nextLine()));
			BasketVO basketVO = null;
			
			try {
				basketVO = basketDao.selectByBasketCode(basketCode);
			} catch (SQLException e) {
				FailView.errorMessage(e.getMessage());
			}
			
			goodsCodeMap.put(basketVO.getGoodsCode(), basketVO.getBasketGoodsCount());
		}
		
		System.out.print("배송지를 입력해주세요 : ");
		String orderDi = sc.nextLine();
	
		OrdersVO ordersVO = new OrdersVO(0, memberVO.getId(), orderDi, 0, null);
		
		for(Integer key : goodsCodeMap.keySet()) {
			OrdersDetailsVO ordersDetailsVO = new OrdersDetailsVO(0, 0, key, 0, goodsCodeMap.get(key), 0);
			
			ordersVO.getOrdersDetailsList().add(ordersDetailsVO);
		}
		
		OrdersController.insertBasketByOrder(ordersVO);
	}
	
	// 주문내역 보기
	public static void printSelectAllOrders(MemberVO memberVO) {
		System.out.println("\n----- " + memberVO.getId() + "님 방문을 환영합니다." + " / 회원등급[" + memberVO.getGrade() + "] -----");
		System.out.println("=== 주문내역 ===");
		
		OrdersController.selectOrdersByMemberId(memberVO.getId());
	}
}