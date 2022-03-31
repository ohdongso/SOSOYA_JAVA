package sosoya.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import sosoya.mvc.model.dto.GoodsVO;

public interface GoodsService {
	/**
	 * 전체검색
	 * @param
	 * @return List<GoodsVO> list
	 * */
	public List<GoodsVO> selectAllGoods() throws SQLException;
	
	/**
	 * 상품 Top3 리뷰개수로 검색
	 * @param
	 * @return List<GoodsVO> goodsList
	 * */
	public List<GoodsVO> selectByTop3ReviewCount() throws SQLException;
	
	/**
	 * GoodsName에 해당하는 상품 검색하기
	 * @param String GoodsName
	 * @return List<GoodsVO> list
	 * */
	public List<GoodsVO> selectByNameGoods(String GoodsName) throws SQLException;
	
	/**
	 * min, max에 해당하는 상품 검색하기
	 * @param String GoodsName
	 * @return List<GoodsVO> list
	 * */
	public List<GoodsVO> SelectByPriceGoods(int min, int max) throws SQLException;
}