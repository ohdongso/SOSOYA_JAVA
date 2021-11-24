package sosoya.mvc.controller;

import java.sql.SQLException;
import java.util.List;

import sosoya.mvc.model.dto.FaqVO;
import sosoya.mvc.model.service.FaqService;
import sosoya.mvc.model.service.FaqServiceImpl;
import sosoya.mvc.view.main.FailView;
import sosoya.mvc.view.main.SuccessView;

public class FaqController {
	private static FaqService faqService = new FaqServiceImpl();
	
	/**
	 * 전체검색
	 * */
	public static void selectAllFaq() {
		try {
			List<FaqVO> list = faqService.selectAllFaq();
			SuccessView.printSelectFaq(list);
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 조건검색
	 * */
	public static void selectByFaq(int category) {
		try {
			List<FaqVO> list = faqService.selectByFaq(category);
			SuccessView.printSelectFaq(list);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}
}