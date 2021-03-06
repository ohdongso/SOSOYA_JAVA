package sosoya.mvc.model.dao;

import java.sql.SQLException; 
import java.util.List;

import sosoya.mvc.model.dto.OrdersDetailsVO;
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
	 * 주문상세 가져오기
	 * @param int orderCode
	 * @return List<OrdersDetailsVO> list
	 * */
	public List<OrdersDetailsVO> selectOrdersDetailsVO(int orderCode) throws SQLException;
	
	/**
	 * 주문코드에 해당하는 리뷰가 작성되지 않은 주문상세를 가져온다.
	 * @param int orderCode
	 * @return List<OrdersDetailsVO> list
	 * */
	public List<OrdersDetailsVO> selectOrdersReviewDetailsVO(int orderCode) throws SQLException;
	
	/**
	 * 주문코드에 해당하는 주문상세의 상태가 1,주문완료상태 것만 들고온다.
	 * @param int orderCode
	 * @return List<OrdersDetailsVO> list
	 * */
	public List<OrdersDetailsVO> selectOrdersDetailsVoState(int orderCode) throws SQLException;
	
	/**
	 * 주문내역보기
	 * @param String memberId
	 * @return List<OrdersVO> ordersVO
	 * */
	public List<OrdersVO> selectOrdersByMemberId(String memberId) throws SQLException;

	/**
	 * 주문코드에 해당하는 주문내역 가져오기
	 * @param int orderCode
	 * @return OrdersVO orderVO
	 * */
	public OrdersVO selectOrdersByOrderCode(int orderCode) throws SQLException;
	
	/**
	 * 주문코드에 해당하는 주문내역 삭제하기
	 * @param List<Integer> orderCodeList
	 * @return int result
	 */
	public int deleteOrderList(List<Integer> orderCodeList) throws SQLException;
}