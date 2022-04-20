package sosoya.mvc.model.dao;

import java.sql.SQLException;

import sosoya.mvc.model.dto.ErVO;

public interface ErDAO {
	/**
	 * 교환하기
	 * @param ErVO erVo
	 * @return int result
	 * */
	public int insertErExchange(ErVO erVo) throws SQLException;
	
	/**
	 * 환불하기
	 * @param ErVO erVo
	 * @return int result
	 * */
	public int insertErRefund(ErVO erVo) throws SQLException;
}