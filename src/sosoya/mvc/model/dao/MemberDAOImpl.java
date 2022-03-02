package sosoya.mvc.model.dao;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import sosoya.mvc.model.dto.MemberVO;
import sosoya.mvc.util.DbUtil;

public class MemberDAOImpl implements MemberDAO {
	private Properties sosoyaSql = DbUtil.getProFile();
	
	/**
	 * 로그인
	 * */
	@Override
	public MemberVO login(String id, String password) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = sosoyaSql.getProperty("MEMBER.LOGIN");
		MemberVO memberVO = null;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, password);
			
			// 1.활동중, 2.관리자, 3.탈퇴한회원
			ps.setInt(3, 1);
			ps.setInt(4, 2);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				int purchasesCount = rs.getInt("PURCHASESCOUNT");
				int state = rs.getInt("STATE");
				
				memberVO = new MemberVO(id, password, rs.getString("NAME"), rs.getInt("PURCHASESCOUNT"), rs.getString("GRADE"), 
						   rs.getInt("STATE"), rs.getString("EMAIL"), rs.getString("PHONE"), rs.getString("ADDRESS"), rs.getString("REGDATE"));
								
				if(state == 1) {
					if(purchasesCount <= 10) memberVO.setGrade("C");
					else if(purchasesCount <= 20) memberVO.setGrade("B");
					else memberVO.setGrade("A");
					
					int result = this.updateMemberGrade(id, memberVO.getGrade());
					if(result == 0) throw new SQLException("로그인 후 등급이 조회되지 않았습니다.");
				}
			}		
		} finally {
			DbUtil.close(con, ps, rs);
		}
		return memberVO;
	}
	
	/**
	 * 회원등록
	 * */
	@Override
	public int insertMember(MemberVO memberVO) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = sosoyaSql.getProperty("MEMBER.INSERT");
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, memberVO.getId());
			ps.setString(2, memberVO.getPassword());
			ps.setString(3, memberVO.getName());
			ps.setString(4, memberVO.getGrade());
			ps.setInt(5, memberVO.getState());
			ps.setString(6, memberVO.getEmail());
			ps.setString(7, memberVO.getPhone());
			ps.setString(8, memberVO.getAddress());
			
			result = ps.executeUpdate();
		} finally {
			DbUtil.close(con, ps);
		}
		
		return result;
	}
	
	/**
	 * 회원정보수정
	 */
	@Override
	public int updateMember(MemberVO memberVO) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = sosoyaSql.getProperty("MEMBER.UPDATEMEMBER");
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, memberVO.getPassword());
			ps.setString(2, memberVO.getPhone());
			ps.setString(3, memberVO.getEmail());
			ps.setString(4, memberVO.getAddress());
			ps.setString(5, memberVO.getId());
			
			result = ps.executeUpdate();
		} finally {
			DbUtil.close(con, ps);
		}
		
		return result;
	}
	
	/**
	 * 회원 아이디 중복체크
	 * */
	@Override
	public boolean duplicateByMemberId(String id) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean result = false;
		String sql = sosoyaSql.getProperty("MEMBER.DUPLICATEID");
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				result = true;
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}
		return result;
	}
	
	/**
	 * 회원 이메일 중복체크
	 * */
	@Override
	public boolean duplicateByMemberEmail(String email) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean result = false;
		String sql = sosoyaSql.getProperty("MENBER.DUPLICATEEMAIL");
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				result = true;
			}
			
		} finally {
			DbUtil.close(con, ps, rs);
		}	
		return result;
	}
	
	/**
	 * 아이디에 해당하는 회원검색
	 */
	@Override
	public MemberVO selectByMember(String id) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = sosoyaSql.getProperty("MEMBER.SELECTBYMEMBER");
		MemberVO memberVO = null;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				memberVO = new MemberVO(id, rs.getString("PASSWORD"), rs.getString("NAME"), rs.getInt("PURCHASESCOUNT"), rs.getString("GRADE"), 
						   rs.getInt("STATE"), rs.getString("EMAIL"), rs.getString("PHONE"), rs.getString("ADDRESS"), rs.getString("REGDATE"));
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}
		
		return memberVO;
	}

	/**
	 * 구매횟수에 따라, 회원등급 변경
	 * */
	@Override
	public int updateMemberGrade(String id, String grade) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = sosoyaSql.getProperty("MEMBER.UPDATEMEMBERGRADE");
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, grade);
			ps.setString(2, id);

			result = ps.executeUpdate();
		} finally {
			DbUtil.close(con, ps);
		}
			
		return result;
	}
	
	/**
	 * 회원탈퇴
	 * */
	@Override
	public int memberStateUpdate(String id) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = sosoyaSql.getProperty("MEMBER.UPDATEMEMBERSTATE");
		int result = 0;
		
		try {			
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			
			result = ps.executeUpdate();
		} finally {
			DbUtil.close(con, ps);
		}
		
		return result;
	}
}