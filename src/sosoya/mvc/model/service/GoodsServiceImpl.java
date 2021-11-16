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
	
	
}