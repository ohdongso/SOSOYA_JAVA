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
	 * 장바구니상품전체주문
	 * @param MemberVO memberVO, String orderDi
	 * @return
	 * */
	public int insertBasketAllOrder(OrdersVO ordersVO) throws SQLException;
	
	/**
	 * 장바구니상품선택주문
	 * @param OrdersVO ordersVO
	 * @return int result
	 * */
	public int insertBasketByOrder(OrdersVO ordersVO) throws SQLException;
	
	
	/**
	 * 주문내역보기
	 * @param String memberId
	 * @return List<OrdersVO> ordersVO
	 * */
	public List<OrdersVO> selectOrdersByMemberId(String memberId) throws SQLException;
}