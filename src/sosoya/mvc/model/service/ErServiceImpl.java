package sosoya.mvc.model.service;

import java.sql.SQLException;

import sosoya.mvc.model.dao.ErDAO;
import sosoya.mvc.model.dao.ErDAOImpl;
import sosoya.mvc.model.dto.ErVO;

public class ErServiceImpl implements ErService {
	private ErDAO erDao = new ErDAOImpl();
	
	/**
	 * 교환하기
	 */
	@Override
	public void insertErVo(ErVO erVo) throws SQLException {
		int result = erDao.insertErVo(erVo);
		if(result == 0) {
			throw new SQLException("상품교환이 진행되지 않았습니다.");
		}
	}
}
