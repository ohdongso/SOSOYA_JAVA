package sosoya.mvc.controller;

import java.sql.SQLException;

import sosoya.mvc.model.dto.PaymentVO;
import sosoya.mvc.model.service.PaymentService;
import sosoya.mvc.model.service.PaymentServiceImpl;
import sosoya.mvc.view.main.FailView;
import sosoya.mvc.view.main.SuccessView;

public class PaymentController {
	private PaymentService paymentService = new PaymentServiceImpl();
	
	public void insertPayment(PaymentVO paymentVO) {
		try {
			paymentService.insertPayment(paymentVO);
			SuccessView.printMessage("결제가 완료 되었습니다.");
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
}