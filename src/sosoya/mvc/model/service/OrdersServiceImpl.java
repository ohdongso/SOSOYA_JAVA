package sosoya.mvc.model.service;

import java.sql.SQLException;

import sosoya.mvc.model.dao.GoodsDAO;
import sosoya.mvc.model.dao.GoodsDAOImpl;
import sosoya.mvc.model.dao.OrdersDAO;
import sosoya.mvc.model.dao.OrdersDAOImpl;
import sosoya.mvc.model.dto.GoodsVO;
import sosoya.mvc.model.dto.OrdersVO;

public class OrdersServiceImpl implements OrdersService {
	private OrdersDAO ordersService = new OrdersDAOImpl();
	private GoodsDAO goodsDAO = new GoodsDAOImpl();

	
	/**
	 * 상품주문하기
	 */
	@Override
	public void insertOrders(OrdersVO ordersVO) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	/*
	@Override
	public void insertOrders(OrdersVO ordersVO) throws SQLException {
		GoodsVO goodsVO = goodsDAO.selectByGoods(ordersVO.getGoodsCode().get(0));
		ordersVO.getGoodsList().add(goodsVO);
		
		// 상품 주문시 주문개수가 재고량 보다 많다면 예외 발생
		if(ordersVO.getOrdersGoodscount() > goodsVO.getGoodsStock()) {
			throw new SQLException("상품 재고량이 부족합니다. " + 
					"[ 상품이름: " + goodsVO.getGoodsName() + ", 재고량: " + goodsVO.getGoodsStock() + "개 ]");
		}
		
		int result = ordersService.insertOrders(ordersVO);
		if(result == 0) throw new SQLException("주문이 되지 않았습니다.");
		
		// ORDERS_DETAILS테이블에 데이터 삽입
		
	}
	*/
}