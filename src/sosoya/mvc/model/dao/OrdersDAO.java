package sosoya.mvc.model.dao;

import java.sql.SQLException;

import sosoya.mvc.model.dto.OrdersVO;

public interface OrdersDAO {
	/**
	 * 상품주문하기
	 * @param OrdersVO ordersVO
	 * @return int result
	 * */
	public int insertOrders(OrdersVO ordersVO) throws SQLException;
}