package sosoya.mvc.controller;

import java.sql.SQLException;
import java.util.List;

import sosoya.mvc.model.dto.ErVO;
import sosoya.mvc.model.dto.MemberVO;
import sosoya.mvc.model.service.ErService;
import sosoya.mvc.model.service.ErServiceImpl;
import sosoya.mvc.view.main.FailView;
import sosoya.mvc.view.main.SuccessView;

public class ErController {
	private static ErService erService = new ErServiceImpl();
	
	/**
	 * 교환하기
	 */
	public static void insertErExchange(ErVO erVo) {
		try {
			erService.insertErExchange(erVo);
			SuccessView.printMessage("\n교환 신청이 완료되었습니다.");
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 환불하기
	 */
	public static void insertErRefund(ErVO erVo) {
		try {
			erService.insertErExchange(erVo);
			SuccessView.printMessage("\n환불 신청이 완료되었습니다.");
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * (교환,환불) 내역전체조회
	 * */
	public static void selectAllEr(MemberVO memberVo) {
		try {
			List<ErVO> list = erService.selectAllEr(memberVo);
			SuccessView.printSelectAllEr(list);
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
}