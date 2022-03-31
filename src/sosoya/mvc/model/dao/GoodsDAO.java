package sosoya.mvc.model.dao;

import java.sql.Connection;
import java.sql.SQLException;   
import java.util.List;
import sosoya.mvc.model.dto.GoodsVO;

public interface GoodsDAO {
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
	 * 상품 평점평균 변경
	 * @param int goodsCode, float goodsAvg, Connection con
	 * @return int result
	 * */
	public int updateGoodsAvg(int goodsCode, float goodsAvg, Connection con) throws SQLException;
	
	/**
	 * 상품의 리뷰개수 변경
	 * @param int goodsCode, Connection con
	 * @return int result
	 * */
	public int updateReviewCount(int goodsCode, Connection con) throws SQLException;
	
	/**
	 * 상품의 평점평균 가져오기
	 * @param int goodsCode, Connection con
	 * @return GoodsVO goodsVO
	 * */
	public GoodsVO selectGoodsAvg(int goodsCode, Connection con) throws SQLException;
	
	/**
	 * 상품코드에 해당하는 상품가져오기
	 * @param int goodsCode
	 * @return GoodsVO goodsVO
	 * */
	public GoodsVO selectByGoods(int goodsCode) throws SQLException;
	
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
	
	/**
	 * 상품코드에 해당하는 상품 이름검색
	 * @param int goodsCode
	 * @return String name
	 * */
	public String selectByNameGoodsOne(int goodsCode) throws SQLException;
}