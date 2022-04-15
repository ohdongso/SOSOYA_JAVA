package sosoya.mvc.model.dao;

import java.sql.Connection; 
import java.sql.SQLException;

import sosoya.mvc.model.dto.OrdersDetailsVO;

public interface OrdersDetailsDAO {
	/**
	 * 상품상세 상태변경하기(1,주문완료상태 2,교환상태 3,환불상태 4,관리자최종승인)
	 * @param OrdersDetailsVO ordersDetailsVO
	 * @return int result
	 * */
	public int updateOrderDetailState(int orderDetailState, int orderDetailCode, Connection con) throws SQLException;
}
