package sosoya.mvc.model.dao;

import java.sql.SQLException;
import java.util.List;

import sosoya.mvc.model.dto.MemberVO;
import sosoya.mvc.model.dto.PaymentVO;

public interface PaymentDAO {
	/**
	 * 전체결제내역 조회
	 * @param MemberVO memberVO
	 * @return List<PaymentVO> paymentVoList
	 * */
	public List<PaymentVO> selectAllPayment(MemberVO memberVO) throws SQLException;
	
	/**
	 * 후기작성가능한 결제내역 전체보기
	 * @param MemberVO memberVO
	 * @return List<PaymentVO> paymentVoList
	 * */
	public List<PaymentVO> selectAllReviewPayment(MemberVO memberVO) throws SQLException;
}