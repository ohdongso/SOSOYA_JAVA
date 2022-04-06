package sosoya.mvc.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
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
		List<OrdersDetailsVO> orderDetailList = new ArrayList<OrdersDetailsVO>();
		
		// memberId에 해당하는 전체 장바구니 목록 검색
		// 여기서 저장 될 때, 장바구니 객체에 상품객체가 저장되서 넘어온다.
		List<BasketVO> orderBasketList  = basketDao.selectAllBasket(memberVO);
		// memberId에 해당하는 장바구니 목록이 없을 때 예외를 던지자.
		if(orderBasketList.size() == 0) throw new SQLException("장바구니에 상품이 없습니다.");
		
		// 장바구니에 정보를 기반으로 주문상세에 결제금액를 저장한다.
		for(BasketVO basketVo : orderBasketList) {
			// 장바구니의 일부 데이터를 주문객체의 주문상세리스트에 넣는다.
			// 장바구니의 단일금액과 총금액을 주문상세에 저장한다.
			OrdersDetailsVO orderDetailsVO = new OrdersDetailsVO(0, 0, basketVo.getGoodsCode(), 
			basketVo.getGoodsVO().getGoodsPrice(), basketVo.getBasketGoodsCount(), basketVo.getBasketTotalprice());
			
			// 주문상세에 상품이름 저장.
			orderDetailsVO.setGoodsName(basketVo.getGoodsVO().getGoodsName());
			
			orderDetailList.add(orderDetailsVO);
		}
		
		// 장바구니 => 주문상세 => 주문상세 리스트를 주문객체에 저장
		ordersVO.setOrdersDetailsList(orderDetailList);
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
	
	/**
	 * 주문코드에 해당하는 주문내역 삭제하기
	 */
	@Override
	public void deleteOrderList(List<Integer> orderCodeList) throws SQLException {
		int result = ordersDao.deleteOrderList(orderCodeList);
		if(result == 0) throw new SQLException("주문목록이 삭제 되지 않았습니다.");
	}
}