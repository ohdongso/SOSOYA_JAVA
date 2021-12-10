package sosoya.mvc.model.service;

import java.sql.SQLException;

import sosoya.mvc.model.dto.MemberVO;
import sosoya.mvc.model.dto.PaymentVO;

public interface PaymentService {
	/**
	 * 전체결제내역 조회
	 * @param MemberVO memberVOs
	 * @return List<PaymentVO> paymentVoList
	 * */
	public PaymentVO selectAllPayment(MemberVO memberVO) throws SQLException;
}
