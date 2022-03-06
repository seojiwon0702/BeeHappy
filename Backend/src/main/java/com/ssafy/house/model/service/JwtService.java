package com.ssafy.house.model.service;

import java.util.Map;

import com.ssafy.house.model.UserDto;

public interface JwtService {

//	String createToken(UserDto userDto);
//	public Map<String, Object> getInfo(String token) throws Exception;
//	public void checkValid(String token);
	
	<T> String create(String key, T data, String subject);
	Map<String, Object> get(String key);
	String getUserId();
	boolean isUsable(String jwt);
//	
//	public String create(final UserDto userDto);
//	public void checkValid(final String jwt);
//	public Map<String, Object> get(final String jwt);
	
}
