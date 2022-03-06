package com.ssafy.house.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.house.model.UserDto;
import com.ssafy.house.model.service.UserService;
import com.ssafy.house.model.service.JwtServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
@Api("사용자 컨트롤러  API V1")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private JwtServiceImpl jwtService;
	
	@Autowired
	private UserService userService;

	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) UserDto userDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			UserDto loginUser = userService.login(userDto);
			if (loginUser != null) {
				logger.debug(loginUser.toString());
				String token = jwtService.create("userid", loginUser.getUserid(), "access-token");// key, data, subject
				logger.debug("로그인 토큰정보 : {}", token);
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{userid}")
	public ResponseEntity<Map<String, Object>> selectUserById(
			@PathVariable("userid") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userid,
			HttpServletRequest request) {
//		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				UserDto userDto = userService.selectUserById(userid);
				resultMap.put("userInfo", userDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}


//	@ApiOperation(value = "아이디에 해당하는 유저 정보를 반환한다.", response = UserDto.class)
//	@GetMapping("{userid}")
//	public ResponseEntity<UserDto> selectUserById(@PathVariable String userid) throws Exception {
//		logger.debug("selectUserById - 호출");
//		return new ResponseEntity<UserDto>(userService.selectUserById(userid), HttpStatus.OK);
//	}
	
	@ApiOperation(value = "새로운 유저 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/insert")
	public ResponseEntity<String> insertUser(@RequestBody UserDto userDto) throws Exception {
		logger.debug("insertUser - 호출");
		logger.debug("" + userDto);
		
		if (userService.insertUser(userDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "유저 아이디에 해당하는 유저의 정보를 수정한다. 그리고 성공 여부에 따라 'success' 또는 'fail'문자열을 반환한다.", response = String.class)
	@PutMapping("{userid}")
	public ResponseEntity<String> updateUser(@RequestBody UserDto userDto) throws Exception {
		logger.debug("updateUser - 호출");
		logger.debug("" + userDto);
		
		if (userService.updateUser(userDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "유저 아이디에 해당하는 유저의 정보를 삭제한다. 그리고 DB삭제 성공 여부에 따라 'success' 또는 'fail'문자열을 반환한다.", response = String.class)
	@DeleteMapping("{userid}")
	public ResponseEntity<String> deleteUser(@PathVariable String userid) throws Exception {
		logger.debug("deleteUser - 호출");
//		logger.debug("" + userid);
		
		if (userService.deleteUser(userid)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("{userid}")
	public ResponseEntity<String> countId(@PathVariable String userid) throws Exception{
		logger.debug("countId - 호출");
		if(userService.countId(userid)) {
			logger.debug("success");
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		logger.debug("fail");
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@GetMapping("/userlist")
	public ResponseEntity<List<UserDto>> getUserList() throws Exception{
		logger.debug(("userList - 호출"));
		return new ResponseEntity<List<UserDto>>(userService.selectUserList(), HttpStatus.OK);
	}
}
