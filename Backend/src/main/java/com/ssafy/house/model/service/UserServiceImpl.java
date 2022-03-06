package com.ssafy.house.model.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.house.model.UserDto;
import com.ssafy.house.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public UserDto login(UserDto userDto) throws SQLException {
		if(userDto.getUserid() == null || userDto.getUserpwd() == null)
			return null;
		return sqlSession.getMapper(UserMapper.class).login(userDto);
	}

	@Override
	public List<UserDto> selectUserList() throws SQLException {
		return sqlSession.getMapper(UserMapper.class).selectUserList();
	}

	@Override
	public UserDto selectUserById(String userid) throws SQLException {
		return sqlSession.getMapper(UserMapper.class).selectUserById(userid);
	}

	@Override
	public boolean insertUser(UserDto userDto) throws SQLException {
		return sqlSession.getMapper(UserMapper.class).insertUser(userDto) == 1;
	}

	@Override
//	@Transactional
	public boolean updateUser(UserDto userDto) throws SQLException {
		return sqlSession.getMapper(UserMapper.class).updateUser(userDto) == 1;
	}

	@Override
//	@Transactional
	public boolean deleteUser(String userid) throws SQLException {
		return sqlSession.getMapper(UserMapper.class).deleteUser(userid) == 1;
	}
	
	@Override
	public boolean countId(String userid) throws SQLException{
		return sqlSession.getMapper(UserMapper.class).numOfId(userid)==0;
	}

}
