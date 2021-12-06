package sosoya.mvc.controller;

import java.sql.SQLException;
import java.util.List;

import sosoya.mvc.model.dto.MemberVO;
import sosoya.mvc.model.dto.OrdersVO;
import sosoya.mvc.model.service.OrdersService;
import sosoya.mvc.model.service.OrdersServiceImpl;
import sosoya.mvc.view.main.FailView;
import sosoya.mvc.view.main.SuccessView;

public class OrdersController {
	private static OrdersService ordersService = new OrdersServiceImpl();
	
	/**
	 * 상품주문하기
	 * */
	public static void insertOrders(OrdersVO orderVO) {
		try {
			ordersService.insertOrders(orderVO);
			SuccessView.printMessage("주문이 완료 되었습니다.");
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 장바구니 상품 전체 주문하기
	 * */
	public static void insertBasketAllOrder(MemberVO memberVO, String orderDi) {
		try {
			ordersService.insertBasketAllOrder(memberVO, orderDi);
			SuccessView.printMessage("주문이 완료 되었습니다.");
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 주문내역보기
	 * */
	public static void selectOrdersByMemberId(String memberId) {
		try {
			List<OrdersVO> list = ordersService.selectOrdersByMemberId(memberId);
			SuccessView.printOrderByMemberId(list);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
			e.printStackTrace();
		}
	}
}