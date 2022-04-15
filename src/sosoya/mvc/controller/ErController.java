package sosoya.mvc.controller;

import java.sql.SQLException;
import sosoya.mvc.model.dto.ErVO;
import sosoya.mvc.model.service.ErService;
import sosoya.mvc.model.service.ErServiceImpl;
import sosoya.mvc.view.main.FailView;
import sosoya.mvc.view.main.SuccessView;

public class ErController {
	private static ErService erService = new ErServiceImpl();
	
	/**
	 * 교환하기
	 */
	public static void insertErVo(ErVO erVo) {
		try {
			erService.insertErVo(erVo);
			SuccessView.printMessage("\n교환 신청이 완료되었습니다.");
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
}