package sosoya.mvc.view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import sosoya.mvc.controller.BasketController;
import sosoya.mvc.controller.OrdersController;
import sosoya.mvc.model.dao.BasketDAO;
import sosoya.mvc.model.dao.BasketDAOImpl;
import sosoya.mvc.model.dao.OrdersDAO;
import sosoya.mvc.model.dao.OrdersDAOImpl;
import sosoya.mvc.model.dto.BasketVO;
import sosoya.mvc.model.dto.MemberVO;
import sosoya.mvc.model.dto.OrdersDetailsVO;
import sosoya.mvc.model.dto.OrdersVO;
import sosoya.mvc.view.main.FailView;

public class OrderView {
	private static Scanner sc = new Scanner(System.in);
	private static BasketDAO basketDao = new BasketDAOImpl();
	private static OrdersDAO ordersDao = new OrdersDAOImpl();
	
	// 상품 검색후 주문하는 기능
	public static void printOrder(MemberVO memberVO) {
		System.out.println("\n=== 상품주문하기 ===");
		
		// 1개가 아니고 다수의 상품을 주문할수 있게 수정
		System.out.print("몇개의 상품을 주문 하시겠습니까?? : ");
		int count = Integer.parseInt(sc.nextLine());
		
		List<OrdersDetailsVO> list = new ArrayList<>();
		for(int i = 1; i <= count; i++) {
			System.out.println("\n--------- " + i + "번째 상품 ---------");
			System.out.print("주문할 상품 코드를 입력해주세요 : ");
			int goodsCode = Integer.parseInt(sc.nextLine());
			
			System.out.print("주문할 상품 개수를 입력해주세요 : ");
			int goodsCount = Integer.parseInt(sc.nextLine());
			
			OrdersDetailsVO ordersDetailsVO = new OrdersDetailsVO(0, 0, goodsCode, 0, goodsCount, 0);
			list.add(ordersDetailsVO);
		} // for문 끝.
		
		System.out.print("\n배송지를 입력해주세요 : ");
		String orderDi = sc.nextLine();
	
		OrdersVO ordersVO = new OrdersVO(0, memberVO.getId(), orderDi, 0, null);
		ordersVO.setOrdersDetailsList(list);
		
		OrdersController.insertOrders(ordersVO);
	}
	
	// 장바구니상품 전체주문하는 기능
	public static void printBasketAllOrder(MemberVO memberVO) {
		System.out.println("\n=== 장바구니 상품전체주문하기 ===");
		
		System.out.print("배송지를 입력해주세요 : ");
		String orderDi = sc.nextLine();
		
		OrdersController.insertBasketAllOrder(memberVO, orderDi);
	}
	
	// 장바구니상품 선택주문하는 기능
	public static void printBasketByOrder(MemberVO memberVO) {
		System.out.println("\n=== 장바구니 상품선택주문하기 ===");
		
		// 여기서 장바구니에 담긴 상품 전체목록을 한번 뿌려줘야 한다.
		BasketController.selectAllBasket(memberVO);
		
		System.out.print("\n주문할 장바구니 상품 개수를 입력해주세요 : ");
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
		System.out.println("\n=== 주문내역 ===");
		
		// 주문내역 개수를 초과하거나 0개 이하를 입력하면 다시 입력받는다.
		List<OrdersVO> list = null;
		try {
			list = ordersDao.selectOrdersByMemberId(memberVO.getId());
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
			return;
		}
		
		int size = list.size();
		if(size == 0) {
			System.out.println("주문 내역이 존재하지 않습니다.");
			return;
		}
		
		OrdersController.selectOrdersByMemberId(memberVO.getId());
		System.out.println();
		
		// 주문내역(삭제하기, 뒤로가기)
		while(true) {
			System.out.print("(1,삭제하기 2,뒤로가기) 원하는 번호를 입력해주세요 : ");
			int input = Integer.parseInt(sc.nextLine());
			
			if(input == 1) {
				int count = 0;
				System.out.print("삭제할 주문내역 개수를 입력주세요 : ");
				count = Integer.parseInt(sc.nextLine());
				
				if(count > size) {
					System.out.println("주문내역 총 개수가 " + size + "개 입니다. 1개~" + size +"개 사이 숫자를 입력해주세요.");
					return;
				} else if(count < 1) {
					System.out.println("1개 이상을 입력해주세요.");
					return;
				}
				
				List<Integer> orderCodeList = new ArrayList<Integer>();
				for(int i = 1; i <= count; i++) {
					System.out.print("삭제할 주문코드를 입력해주세요: ");
					int orderCode = Integer.parseInt(sc.nextLine());
					orderCodeList.add(orderCode);
				}
				
				// 삭제 기능처리
				OrdersController.deleteOrderList(orderCodeList);
				return;
			} else if(input == 2) {
				return;
			} else {
				System.out.println("1번 또는 2번을 입력해주세요.\n");
				continue;
			}
		}
	}
}