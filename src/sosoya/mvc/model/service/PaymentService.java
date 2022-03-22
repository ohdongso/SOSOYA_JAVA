package sosoya.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import sosoya.mvc.model.dto.MemberVO;
import sosoya.mvc.model.dto.PaymentVO;

public interface PaymentService {
	/**
	 * 전체결제내역 조회
	 * @param MemberVO memberVOs
	 * @return List<PaymentVO> paymentVoList
	 * */
	public List<PaymentVO> selectAllPayment(MemberVO memberVO) throws SQLException;
}
