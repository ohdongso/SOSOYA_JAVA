package sosoya.mvc.controller;

import java.sql.SQLException;

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
	
	
	
	
}