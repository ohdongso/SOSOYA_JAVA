package sosoya.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import sosoya.mvc.model.dto.MemberVO;
import sosoya.mvc.model.dto.PaymentVO;

public interface PaymentService {
	/**
	 * 전체결제내역 조회
	 * @param MemberVO memberVO
	 * @return List<PaymentVO> paymentVoList
	 * */
	public List<PaymentVO> selectAllPayment(MemberVO memberVO) throws SQLException;
	
	/**
	 * (교환, 환불)가능한 결제목록 전체보기
	 * @Param MemberVO memberVO
	 * @return List<PaymentVO> paymentVoList
	 * */
	public List<PaymentVO> selectAllErPayment(MemberVO memberVO) throws SQLException;
	
	/**
	 * 후기작성가능한 결제내역 전체보기
	 * @param MemberVO memberVO
	 * @return List<PaymentVO> paymentVoList
	 * */
	public List<PaymentVO> selectAllReviewPayment(MemberVO memberVO) throws SQLException;
	
	/**
	 * 결제내역 삭제
	 * @param List<Integer> paymentCodeList
	 * @return int result
	 * */
	public void deletePayment(List<Integer> paymentCodeList) throws SQLException;
}
