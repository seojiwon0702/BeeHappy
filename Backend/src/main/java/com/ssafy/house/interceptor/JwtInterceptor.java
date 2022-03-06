package com.ssafy.house.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.house.exception.UnAuthorizedException;
import com.ssafy.house.model.service.JwtService;

//@Component
//public class JwtInterceptor implements HandlerInterceptor {
//	public static final Logger logger = LoggerFactory.getLogger(JwtInterceptor.class);
//	
//	@Autowired
//	private JwtService jwtService;
//
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//		if (request.getMethod().equals("OPTIONS")) { // preflight로 넘어온 options는 통과
//			return true;
//		} else {
//			String token = request.getHeader("jwt-auth-token"); // client에서 요청할 때 header에 넣어둔 "jwt-auth-token"이라는 키 값을
//																// 확인
//			if (token != null && token.length() > 0) {
//				jwtService.checkValid(token); // 토큰 유효성 검증
//				logger.info("토큰 사용 가능 : {}", token);
//				return true;
//			} else { // 유효한 인증토큰이 아닐 경우
//				logger.info("토큰 사용 불가능 : {}", token);
//				throw new UnAuthorizedException();
//			}
//		}
//	}
//}
//
@Component
public class JwtInterceptor implements HandlerInterceptor {

public static final Logger logger = LoggerFactory.getLogger(JwtInterceptor.class);
	
	private static final String HEADER_AUTH = "access-token";	//프론트에서 보낸 헤더명

	@Autowired
	private JwtService jwtService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if (request.getMethod().equals("GET")) {	//GET요청은 토큰 체크 x => 조회
			logger.info("get 요청으로 사용 가능");
			return true;
		}
		final String token = request.getHeader(HEADER_AUTH);

		if(token != null && jwtService.isUsable(token)){
			logger.info("토큰 사용 가능 : {}", token);
			return true;
		}else{
			logger.info("토큰 사용 불가능 : {}", token);
			throw new UnAuthorizedException();
		}

	}
	
}

//@Component
//public class JwtInterceptor implements HandlerInterceptor {
//
//	public static final Logger logger = LoggerFactory.getLogger(JwtInterceptor.class);
//
//	@Autowired
//	private JwtService jwtService;
//
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//		System.out.println(request.getMethod() + " : " + request.getServletPath());
//
//		// option 요청은 바로 통과시켜주자
//
//		if (request.getMethod().equals("OPTIONS")) {
//			return true;
//		} else {
//
//			final String token = request.getHeader("jwt-auth-token");
//
//			if (token != null && token.length() >0) {
//				logger.info("토큰 사용 가능 : {}", token);
//				jwtService.checkValid(token); // 토큰 유효성 검증
//				return true;
//			} else {
//				logger.info("토큰 사용 불가능 : {}", token);
//				throw new RuntimeException("인증 토큰이 없습니다.");
//			}
//		}
//
//	}
//}
