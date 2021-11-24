package sosoya.mvc.model.service;

import java.sql.SQLException;

import sosoya.mvc.model.dto.OrdersVO;

public interface OrdersService {
	/**
	 * 상품주문하기
	 * @param OrdersVO ordersVO
	 * @return int result
	 * */
	public void insertOrders(OrdersVO ordersVO) throws SQLException;
}