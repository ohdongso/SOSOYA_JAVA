package sosoya.mvc.model.dao;

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
		// TODO Auto-generated method stub
		return 0;
	}
}