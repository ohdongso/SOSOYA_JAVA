package sosoya.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

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
	public List<PaymentVO> selectAllPayment(MemberVO memberVO) throws SQLException {
		List<PaymentVO> paymentVoList = paymentDAO.selectAllPayment(memberVO);
		if(paymentVoList.size() == 0) throw new SQLException("결제 내역이 존재하지 않습니다.");
				
		return paymentVoList;
	}
	
	/**
	 * (교환, 환불)가능한 결제목록 전체보기
	 */
	@Override
	public List<PaymentVO> selectAllErPayment(MemberVO memberVO) throws SQLException {
		List<PaymentVO> paymentVoList = paymentDAO.selectAllErPayment(memberVO);
		if(paymentVoList.size() == 0) throw new SQLException("(교환,환불)이 가능한 결제내역이 존재하지 않습니다.");
		return paymentVoList;
	}
	
	/**
	 * 후기작성가능한 결제내역 전체보기
	 */
	@Override
	public List<PaymentVO> selectAllReviewPayment(MemberVO memberVO) throws SQLException {
		List<PaymentVO> paymentVoList = paymentDAO.selectAllReviewPayment(memberVO);
		if(paymentVoList.size() == 0) throw new SQLException("리뷰작성 가능한 결제내역이 존재하지 않습니다.");
		return paymentVoList;
	}

	/**
	 * 결제내역 삭제
	 */
	@Override
	public void deletePayment(List<Integer> paymentCodeList) throws SQLException {
		int result = paymentDAO.deletePayment(paymentCodeList);
		if(result == 0) throw new SQLException("결제목록이 삭제 되지 않았습니다.");
	}
}