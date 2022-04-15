package sosoya.mvc.model.service;

import java.sql.SQLException;

import sosoya.mvc.model.dto.ErVO;

public interface ErService {
	/**
	 * 교환하기
	 * @param ErVO erVo
	 * @return
	 * */
	public void insertErVo(ErVO erVo) throws SQLException;
}