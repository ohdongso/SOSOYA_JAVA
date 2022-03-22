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
	 * 결제내역 보기
	 * */
	public static void selectAllPayment(MemberVO memberVO) {
		try {
			List<PaymentVO> paymentVoList = paymentService.selectAllPayment(memberVO);
			SuccessView.printPayment(paymentVoList);
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
			e.printStackTrace();
		}
	}
}