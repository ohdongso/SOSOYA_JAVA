package sosoya.mvc.view;

import java.util.List;

import sosoya.mvc.model.dto.FaqVO;
import sosoya.mvc.model.dto.GoodsVO;

public class SuccessView {
	/**
	 * 성공했을 때 메시지 출력
	 * */
	public static void printMessage(String message) {
		System.out.println(message);
	}
	
	/**
	 * FAQ전체 목록 출력
	 * */
	public static void printSelecFaq(List<FaqVO> list) {
		for(FaqVO vo : list) {
			System.out.println(vo.getFaqCategory() + ", " + vo.getFaqTitle() + " : " + vo.getFaqContent());
		}
	}
	
	/**
	 * Goods전체 목록 출력
	 * */
	public static void printSelectGoods(List<GoodsVO>  list) {
		for(GoodsVO vo : list) {
			System.out.println(vo);
		}
	}
	
	
}