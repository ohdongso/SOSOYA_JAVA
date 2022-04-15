package sosoya.mvc.model.dao;

import java.sql.SQLException;

import sosoya.mvc.model.dto.ErVO;

public interface ErDAO {
	/**
	 * 교환하기
	 * @param ErVO erVo
	 * @return int result
	 * */
	public int insertErVo(ErVO erVo) throws SQLException;
}