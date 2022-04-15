package sosoya.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import sosoya.mvc.model.dto.OrdersDetailsVO;
import sosoya.mvc.util.DbUtil;

public class OrdersDetailsDAOImpl implements OrdersDetailsDAO {
	private Properties sosoyaSql = DbUtil.getProFile();
	
	/**
	 * 상품상세 상태변경하기(1,주문완료상태 2,교환상태 3,환불상태 4,관리자최종승인)
	 */
	@Override
	public int updateOrderDetailState(int orderDetailState, int orderDetailCode, Connection con) throws SQLException {
		PreparedStatement ps = null;
		String sql = sosoyaSql.getProperty("ORDERS_DETAILS.UPDATESTATE");
		int result = 0;
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, orderDetailState);
			ps.setInt(2, orderDetailCode);
			result = ps.executeUpdate();
		} finally {
			// connection 끊으면 안된다.
			DbUtil.close(null, ps, null);
		}
		return result;
	}
}