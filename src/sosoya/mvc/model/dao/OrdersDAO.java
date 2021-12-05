package sosoya.mvc.model.dao;

import java.sql.SQLException;
import java.util.List;

import sosoya.mvc.model.dto.OrdersVO;

public interface OrdersDAO {
	/**
	 * 상품주문하기
	 * @param OrdersVO ordersVOs
	 * @return int result
	 * */
	public int insertOrders(OrdersVO ordersVO) throws SQLException;
	
	/**
	 * 주문내역보기
	 * @param String memberId
	 * @return List<OrdersVO> ordersVO
	 * */
	public List<OrdersVO> selectOrdersByMemberId(String memberId) throws SQLException;
}