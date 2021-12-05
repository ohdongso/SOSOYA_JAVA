package sosoya.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import sosoya.mvc.model.dto.OrdersVO;

public interface OrdersService {
	/**
	 * 상품주문하기
	 * @param OrdersVO ordersVO
	 * @return int result
	 * */
	public void insertOrders(OrdersVO ordersVO) throws SQLException;
	
	/**
	 * 주문내역보기
	 * @param String memberId
	 * @return List<OrdersVO> ordersVO
	 * */
	public List<OrdersVO> selectOrdersByMemberId(String memberId) throws SQLException;
}