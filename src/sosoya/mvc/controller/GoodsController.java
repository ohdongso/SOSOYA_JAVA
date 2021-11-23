package sosoya.mvc.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sosoya.mvc.model.dto.GoodsVO;
import sosoya.mvc.model.service.GoodsService;
import sosoya.mvc.model.service.GoodsServiceImpl;
import sosoya.mvc.view.FailView;
import sosoya.mvc.view.SuccessView;

public class GoodsController {
	private static GoodsService goodsService = new GoodsServiceImpl();
	
	/**
	 * 전체검색
	 * */
	public static void selectAllGoods() {
		try {
			List<GoodsVO> list = goodsService.selectAllGoods();
			SuccessView.printSelectGoods(list);
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * GoodsName에 해당하는 상품 검색하기
	 * */
	public static void selectByNameGoods(String goodsName) {
		try {
			List<GoodsVO> list = goodsService.selectByNameGoods(goodsName);
			SuccessView.printSelectGoods(list);
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * min, max에 해당하는 상품 검색하기
	 * */
	public static void SelectByPriceGoods(int min, int max) {
		try {
			List<GoodsVO> list = goodsService.SelectByPriceGoods(min, max);
			SuccessView.printSelectGoods(list);
		} catch (SQLException e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
}