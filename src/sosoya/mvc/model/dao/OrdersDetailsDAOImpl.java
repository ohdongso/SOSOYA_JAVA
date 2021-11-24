package sosoya.mvc.model.dao;

import java.sql.SQLException;
import java.util.Properties;

import sosoya.mvc.model.dto.OrdersDetailsVO;
import sosoya.mvc.util.DbUtil;

public class OrdersDetailsDAOImpl implements OrdersDetailsDAO {
	private Properties sosoyaSql = DbUtil.getProFile();
	
	/**
	 * 상품상세 등록하기
	 */
	@Override
	public int insertOrdersDetailsVO(OrdersDetailsVO ordersDetailsVO) throws SQLException {
		
		return 0;
	}
}