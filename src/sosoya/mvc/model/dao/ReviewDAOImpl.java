package sosoya.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import sosoya.mvc.model.dto.ReviewVO;
import sosoya.mvc.util.DbUtil;

public class ReviewDAOImpl implements ReviewDAO {
	private Properties sosoyaSql = DbUtil.getProFile();
	
	/**
	 * 상품주문하기
	 */
	@Override
	public int insertReview(ReviewVO reviewVO) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = sosoyaSql.getProperty("REVIEW.INSERT");
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			
			// 오토커밋을 하지 않겠다.
			con.setAutoCommit(false);
			
			ps = con.prepareStatement(sql);
			
			// 여기서 부터 ==> insert구문에 ?값 넣어주면 된다.
			ps.setString(1, reviewVO.getId());
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return 0;
	}
	
	
}