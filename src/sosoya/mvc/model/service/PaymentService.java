package sosoya.mvc.model.service;

import java.sql.SQLException;

import sosoya.mvc.model.dto.PaymentVO;

public interface PaymentService {
	/**
	 * 결제하기
	 * @param PaymentVO paymentVO
	 * @return
	 * */
	public void insertPayment(PaymentVO paymentVO) throws SQLException; 
}
