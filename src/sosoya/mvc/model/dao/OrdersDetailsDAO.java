package sosoya.mvc.model.dao;

import java.sql.SQLException;

import sosoya.mvc.model.dto.OrdersDetailsVO;

public interface OrdersDetailsDAO {
	/**
	 * 상품상세 등록하기
	 * @param OrdersDetailsVO ordersDetailsVO
	 * @return int result
	 * */
	public int insertOrdersDetailsVO(OrdersDetailsVO ordersDetailsVO) throws SQLException;
}
