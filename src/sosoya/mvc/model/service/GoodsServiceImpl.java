package sosoya.mvc.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sosoya.mvc.model.dao.GoodsDAO;
import sosoya.mvc.model.dao.GoodsDAOImpl;
import sosoya.mvc.model.dto.GoodsVO;


public class GoodsServiceImpl implements GoodsService {
	private GoodsDAO goodsService = new GoodsDAOImpl();

	/**
	 * 전체검색
	 */
	@Override
	public List<GoodsVO> selectAllGoods() throws SQLException {
		List<GoodsVO> list = goodsService.selectAllGoods();
		if(list.size() == 0) throw new SQLException("상품이 존재하지 않습니다.");
		return list;
	}
	
	/**
	 * Name에 해당하는 상품 검색하기
	 */
	@Override
	public List<GoodsVO> selectByNameGoods(String GoodsName) throws SQLException {
		List<GoodsVO> list = goodsService.selectByNameGoods(GoodsName);
		if(list.size() == 0) throw new SQLException("상품이 존재하지 않습니다.");
		return list;
	}
	
	/**
	 * min, max에 해당하는 상품 검색하기
	 */
	@Override
	public List<GoodsVO> SelectByPriceGoods(int min, int max) throws SQLException {
		List<GoodsVO> list = goodsService.SelectByPriceGoods(min, max);
		if(list.size() == 0) throw new SQLException("상품이 존재하지 않습니다.");
		return list;
	}
}