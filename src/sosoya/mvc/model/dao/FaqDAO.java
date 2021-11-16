package sosoya.mvc.model.dao;

import java.sql.SQLException;
import java.util.List;

import sosoya.mvc.model.dto.FaqVO;

public interface FaqDAO {
	/**
	 * 전체검색
	 * @param
	 * @return List<FaqVO> list
	 * */
	public List<FaqVO> selectAllFaq() throws SQLException;
	
	/**
	 * 조건검색
	 * @param int category
	 * @return List<FaqVO> list
	 * */
	public List<FaqVO> selectByFaq(int category) throws SQLException;
}