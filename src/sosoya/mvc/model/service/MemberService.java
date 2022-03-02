package sosoya.mvc.model.service;

import java.sql.SQLException;

import sosoya.mvc.exception.DuplicatedException;
import sosoya.mvc.exception.NotFoundException;
import sosoya.mvc.model.dto.MemberVO;

public interface MemberService {
	
	/**
	 * 로그인
	 * @param String id, String password
	 * @return MemberVO memberVO
	 * */
	public MemberVO login(String id, String password) throws NotFoundException, SQLException;
	
	/**
	 * 회원등록
	 * @param MemberVO memberVO
	 * @return void
	 * */
	public void inputMember(MemberVO memberVO) throws SQLException, DuplicatedException;
	
	/**
	 * 아이디에 해당하는 회원검색
	 * @param String id
	 * @return MemberVO memberVO
	 * */
	public MemberVO selectByMember(String id) throws SQLException;
	
	/**
	 * 회원탈퇴
	 * @param String id
	 * @return void
	 * */
	public void memberStateUpdate(String id) throws SQLException;
	
	/**
	 * 회원정보수정
	 * @param MemberVO memberVO
	 * @return void
	 * */
	public void updateMember(MemberVO memberVO) throws SQLException;
}