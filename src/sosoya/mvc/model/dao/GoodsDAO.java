package sosoya.mvc.model.dao;

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
	 * 상품코드에 해당하는 상품가져오기
	 * @param int goodsCode
	 * @return GoodsVO goodsVO
	 * */
	public GoodsVO selectByGoods(int goodsCode) throws SQLException;
	
	
}
