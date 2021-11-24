package sosoya.mvc.model.dao;

import java.sql.SQLException;

import sosoya.mvc.model.dto.PaymentVO;

public interface PaymentDAO {
	/**
	 * 결제하기
	 * @param PaymentVO paymentVO
	 * @return int result
	 * */
	public int insertPayment(PaymentVO paymentVO) throws SQLException; 
}