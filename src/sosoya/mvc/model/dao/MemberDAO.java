package sosoya.mvc.model.dao;

import java.sql.SQLException;

import sosoya.mvc.model.dto.MemberVO;

public interface MemberDAO {
	
	/**
	 * 로그인
	 * @param String id, String password
	 * @return MemberVO memberVO
	 * */
	public MemberVO login(String id, String password) throws SQLException;
	
	/**
	 * 회원등록
	 * @param MemberVO memberVO
	 * @return int result
	 * */
	public int insertMember(MemberVO memberVO) throws SQLException;
	
	/**
	 * 회원탈퇴
	 * @param String id
	 * @return int result
	 * */
	public int memberStateUpdate(String id) throws SQLException;
	
	/**
	 * 회원정보수정
	 * @param MemberVO memberVO
	 * @return int result
	 * */
	public int updateMember(MemberVO memberVO) throws SQLException;
	
	/**
	 * 회원등록시 ID중복 체크
	 * @param String id
	 * @return boolean result
	 * */
	public boolean duplicateByMemberId(String id) throws SQLException;
	
	/**
	 * 회원등록시 EMAIL중복 체크
	 * @param String email
	 * @return boolean result
	 * */          
	public boolean duplicateByMemberEmail(String email) throws SQLException;
	
	/**
	 * 아이디에 해당하는 회원검색
	 * @param String id
	 * @return MemberVO memberVO
	 * */
	public MemberVO selectByMember(String id) throws SQLException;
	
	/**
	 * 구매횟수에 따라, 회원등급 변경
	 * @param String id, String grade
	 * @return int result
	 * */
	public int updateMemberGrade(String id, String grade) throws SQLException;
}