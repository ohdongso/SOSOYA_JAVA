package sosoya.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import sosoya.mvc.model.dto.PaymentVO;
import sosoya.mvc.util.DbUtil;

public class PaymentDAOImpl implements PaymentDAO {
	private Properties sosoyaSql = DbUtil.getProFile();
	
	/**
	 * 결제하기
	 */
	@Override
	public int insertPayment(PaymentVO paymentVO) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = sosoyaSql.getProperty("PAYMENT.INSERT");
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, paymentVO.getOrdersCode());
			ps.setString(2, paymentVO.getId());
			ps.setInt(3, paymentVO.getGoodsCode());
			
			result = ps.executeUpdate();
			
		} finally {
			DbUtil.close(con, ps);
		}
		
		return result;
	}
}
