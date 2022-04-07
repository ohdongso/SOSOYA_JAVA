package sosoya.mvc.controller;

import java.sql.SQLException;
import java.util.List;

import sosoya.mvc.model.dto.MemberVO;
import sosoya.mvc.model.dto.PaymentVO;
import sosoya.mvc.model.service.PaymentService;
import sosoya.mvc.model.service.PaymentServiceImpl;
import sosoya.mvc.view.main.FailView;
import sosoya.mvc.view.main.SuccessView;

public class PaymentController {
	private static PaymentService paymentService = new PaymentServiceImpl();
	
	/**
	 * 결제내역 전체보기
	 * */
	public static void selectAllPayment(MemberVO memberVO) {
		try {
			List<PaymentVO> paymentVoList = paymentService.selectAllPayment(memberVO);
			SuccessView.printPayment(paymentVoList);
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * (교환, 환불)가능한 결제목록 전체보기
	 * */
	public static void selectAllErPayment(MemberVO memberVO) {
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * 후기작성가능한 결제내역 전체보기
	 * */
	public static void selectAllReviewPayment(MemberVO memberVO) {
		try {
			// 조회할 때 리뷰가 작성된 주문상세는 가져오지 않고
			// 주문상세에 모든 리뷰작성이 완료되면 주문코드, 결제 코드도 안가져오면 된다.
			List<PaymentVO> paymentVoList = paymentService.selectAllReviewPayment(memberVO);
			SuccessView.printPayment(paymentVoList);
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 결제내역 삭제
	 */
	public static void printPaymentDelete(List<Integer> paymentCodeList) {
		try {
			paymentService.deletePayment(paymentCodeList);
			SuccessView.printMessage("결제내역이 삭제 되었습니다.");
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
}