package sosoya.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import sosoya.mvc.model.dao.GoodsDAO;
import sosoya.mvc.model.dao.GoodsDAOImpl;
import sosoya.mvc.model.dao.OrdersDAO;
import sosoya.mvc.model.dao.OrdersDAOImpl;
import sosoya.mvc.model.dto.GoodsVO;
import sosoya.mvc.model.dto.OrdersVO;

public class OrdersServiceImpl implements OrdersService {
	private OrdersDAO ordersDao = new OrdersDAOImpl();

	/**
	 * 상품주문하기
	 */
	@Override
	public void insertOrders(OrdersVO ordersVO) throws SQLException {
		int result = ordersDao.insertOrders(ordersVO);
		if(result == 0) throw new SQLException("주문에 실패했습니다.");
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