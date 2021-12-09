package sosoya.mvc.model.service;

import java.sql.SQLException; 
import java.util.List;

import sosoya.mvc.model.dao.BasketDAO;
import sosoya.mvc.model.dao.BasketDAOImpl;
import sosoya.mvc.model.dao.OrdersDAO;
import sosoya.mvc.model.dao.OrdersDAOImpl;
import sosoya.mvc.model.dto.BasketVO;
import sosoya.mvc.model.dto.MemberVO;
import sosoya.mvc.model.dto.OrdersDetailsVO;
import sosoya.mvc.model.dto.OrdersVO;

public class OrdersServiceImpl implements OrdersService {
	private OrdersDAO ordersDao = new OrdersDAOImpl();
	private BasketDAO basketDao = new BasketDAOImpl();
	
	/**
	 * 상품주문하기
	 */
	@Override
	public void insertOrders(OrdersVO ordersVO) throws SQLException {
		int result = ordersDao.insertOrders(ordersVO);
		if(result == 0) throw new SQLException("주문에 실패했습니다.");
	}

	/**
	 * 장바구니상품전체주문
	 */
	@Override
	public void insertBasketAllOrder(MemberVO memberVO, String orderDi) throws SQLException {
		// 주문객체생성, 회원아이디, 주소는 넣는다.
		OrdersVO ordersVO = new OrdersVO(0, memberVO.getId(), orderDi, 0, null);
		
		// 주문객체의 주문상세 List를 가져온다.
		List<OrdersDetailsVO> orderDetailList = ordersVO.getOrdersDetailsList();
		
		// 장바구니에 담긴 상품 아이디와, 상품 개수를 주문상세 객체의 생성자에 대입해 List에 넣는다.
		// 장바구니 객체의 일부 데이터를 주문객체의 주문상세리스트에 넣는다.
		List<BasketVO> orderBasketList  = basketDao.selectAllBasket(memberVO);
		for(BasketVO basketVO : orderBasketList) {
			//(상품코드, 상품구매개수)를 주문상세 객체에 저장한다.
			OrdersDetailsVO orderDetailsVO = new OrdersDetailsVO(0, 0, basketVO.getGoodsCode(), 0, basketVO.getBasketGoodsCount(), 0);
			orderDetailList.add(orderDetailsVO);
		}
		
		int result = ordersDao.insertBasketAllOrder(ordersVO);
		if(result == 0) throw new SQLException("장바구니 전체 주문이 실패했습니다.");
	}
	
	/**
	 * 장바구니상품선택주문
	 */
	@Override
	public void insertBasketByOrder(OrdersVO ordersVO) throws SQLException {
		int result = ordersDao.insertBasketByOrder(ordersVO);
		if(result == 0) throw new SQLException("장바구니 선택 주문이 실패했습니다.");
	}
	
	/**
	 * 주문내역보기
	 */
	@Override
	public  List<OrdersVO> selectOrdersByMemberId(String memberId) throws SQLException {
		List<OrdersVO> list = ordersDao.selectOrdersByMemberId(memberId);
		if(list.size() == 0) throw new SQLException("주문내역이 없습니다.");
		return list;
	}
}