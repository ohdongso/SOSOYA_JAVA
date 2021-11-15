package sosoya.mvc.model.service;

import java.sql.SQLException;

import sosoya.mvc.exception.DuplicatedException;
import sosoya.mvc.exception.NotFoundException;
import sosoya.mvc.model.dto.MemberVO;

public interface MemberService {
	/**
	 * 회원등록
	 * @param MemberVO memberVO
	 * @return void
	 * */
	public void inputMember(MemberVO memberVO) throws SQLException, DuplicatedException;
	
	/**
	 * 로그인
	 * @param String id, String password
	 * @return MemberVO memberVO
	 * */
	public MemberVO login(String id, String password) throws NotFoundException, SQLException;
}