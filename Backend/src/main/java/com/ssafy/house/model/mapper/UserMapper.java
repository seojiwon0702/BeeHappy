package com.ssafy.house.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.house.model.UserDto;

@Mapper
public interface UserMapper {
	
	public UserDto login(UserDto userDto) throws SQLException;		//로그인
	
	public List<UserDto> selectUserList() throws SQLException;			//회원 리스트 조회
	public UserDto selectUserById(String userid) throws SQLException;	//회원 정보 조회
	public int insertUser(UserDto userDto) throws SQLException;	//회원 정보 등록
	public int updateUser(UserDto userDto) throws SQLException;	//회원 정보 수정
	public int deleteUser(String userid) throws SQLException;		//회원 정보 삭제
	
	public int numOfId(String userid) throws SQLException;
}
