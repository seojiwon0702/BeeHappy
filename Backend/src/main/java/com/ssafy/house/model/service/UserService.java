package com.ssafy.house.model.service;

import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Service;
import com.ssafy.house.model.UserDto;


public interface UserService {
	
	public UserDto login(UserDto userDto) throws SQLException;		//로그인
	
	public List<UserDto> selectUserList() throws SQLException;			//회원 리스트 조회
	public UserDto selectUserById(String userid) throws SQLException;	//회원 정보 조회
	public boolean insertUser(UserDto userDto) throws SQLException;	//회원 정보 등록
	public boolean updateUser(UserDto userDto) throws SQLException;	//회원 정보 수정
	public boolean deleteUser(String userid) throws SQLException;		//회원 정보 삭제

	boolean countId(String userid) throws SQLException;
}
