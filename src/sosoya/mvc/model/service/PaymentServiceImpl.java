package sosoya.mvc.model.service;

import java.sql.SQLException;

import sosoya.mvc.model.dao.PaymentDAO;
import sosoya.mvc.model.dao.PaymentDAOImpl;
import sosoya.mvc.model.dto.PaymentVO;

public class PaymentServiceImpl implements PaymentService {
	private PaymentDAO paymentDAO = new PaymentDAOImpl();
	
	/**
	 * 결제하기
	 */
	@Override
	public void insertPayment(PaymentVO paymentVO) throws SQLException {
		int result = paymentDAO.insertPayment(paymentVO);
		if(result == 0) throw new SQLException("결제가 되지 않았습니다.");
	}
}