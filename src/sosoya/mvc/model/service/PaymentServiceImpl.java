package sosoya.mvc.model.service;

import java.sql.SQLException;

import sosoya.mvc.model.dao.PaymentDAO;
import sosoya.mvc.model.dao.PaymentDAOImpl;
import sosoya.mvc.model.dto.MemberVO;
import sosoya.mvc.model.dto.PaymentVO;

public class PaymentServiceImpl implements PaymentService {
	private PaymentDAO paymentDAO = new PaymentDAOImpl();
	
	/**
	 * 전체결제내역 조회
	 */
	@Override
	public PaymentVO selectAllPayment(MemberVO memberVO) throws SQLException {
		PaymentVO paymentVO = paymentDAO.selectAllPayment(memberVO);
		if(paymentVO == null) throw new SQLException("결제 내역이 존재하지 않습니다.");
				
		return paymentVO;
	}
}