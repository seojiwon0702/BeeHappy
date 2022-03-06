package com.ssafy.house.model.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ssafy.house.exception.UnAuthorizedException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

//@Component
//public class JwtServiceImpl implements JwtService {
//	private String secretKey = "myKey"; // 서명에 사용할 secretKey
//	private long exp = 1000L * 60 * 60; // 토근 사용가능 시간, 1시간
//
//	// 토큰 생성하는 메서드
//	public String createToken(UserDto userDto) { // 토큰에 담고싶은 값 파라미터로 가져오기
//		String jwt = Jwts.builder().setHeaderParam("typ", "JWT") // 토큰 타입
//				.setHeaderParam("regDate", System.currentTimeMillis()).setSubject("userToken") // 토큰 제목
//				.setExpiration(new Date(System.currentTimeMillis() + exp)) // 토큰 유효시간
//				.claim("user", userDto) // 토큰에 담을 데이터
//				.signWith(SignatureAlgorithm.HS256, secretKey.getBytes()) // secretKey를 사용하여 해싱 암호화 알고리즘 처리
//				.compact(); // 직렬화, 문자열로 변경
//		return jwt;
//	}
//
//	// 토큰에 담긴 정보 가져오기 메서드
//	public Map<String, Object> getInfo(String token) throws Exception {
//		Jws<Claims> claims = null;
//		try {
//			claims = Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token); // secretKey를 사용하여 복호화
//		} catch (Exception e) {
//			throw new Exception();
//		}
//
//		return claims.getBody();
//	}
//
//	// interceptor에서 토큰 유효성을 검증하기 위한 메서드
//	public void checkValid(String token) {
//		Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token);
//	}
//}

@Service
public class JwtServiceImpl implements JwtService {

	public static final Logger logger = LoggerFactory.getLogger(JwtServiceImpl.class);

	private static final String SALT = "ssafySecret";
	private static final int EXPIRE_MINUTES = 60;

	@Override
	public <T> String create(String key, T data, String subject) {
		String jwt = Jwts.builder().setHeaderParam("typ", "JWT").setHeaderParam("regDate", System.currentTimeMillis())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * EXPIRE_MINUTES))
				.setSubject(subject)
				.claim(key, data)
				.signWith(SignatureAlgorithm.HS256, this.generateKey())
				.compact();
		return jwt;
	}

	private byte[] generateKey() {
		byte[] key = null;
		try {
			key = SALT.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			if (logger.isInfoEnabled()) {
				e.printStackTrace();
			} else {
				logger.error("Making JWT Key Error ::: {}", e.getMessage());
			}
		}

		return key;
	}

//	전달 받은 토큰이 제대로 생성된것인지 확인 하고 문제가 있다면 UnauthorizedException을 발생.
	@Override
	public boolean isUsable(String jwt) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(jwt);
			return true;
		} catch (Exception e) {
//			if (logger.isInfoEnabled()) {
//				e.printStackTrace();
//			} else {
				logger.error(e.getMessage());
//			}
//			throw new UnauthorizedException();
//			개발환경
			return false;
		}
	}

	@Override
	public Map<String, Object> get(String key) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		String jwt = request.getHeader("access-token");
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser().setSigningKey(SALT.getBytes("UTF-8")).parseClaimsJws(jwt);
		} catch (Exception e) {
//			if (logger.isInfoEnabled()) {
//				e.printStackTrace();
//			} else {
				logger.error(e.getMessage());
//			}
			throw new UnAuthorizedException();
//			개발환경
//			Map<String,Object> testMap = new HashMap<>();
//			testMap.put("userid", userid);
//			return testMap;
		}
		Map<String, Object> value = claims.getBody();
		logger.info("value : {}", value);
		return value;
	}

	@Override
	public String getUserId() {
		return (String) this.get("user").get("userid");
	}

}

//@Service
//public class JwtServiceImpl implements JwtService {
//	public static final Logger logger = LoggerFactory.getLogger(JwtServiceImpl.class);
//
//	@Value("${jwt.salt}")
//	private String salt;
//
//	@Value("${jwt.expmin}")
//	private Long expireMin;
//
//	public String create(final UserDto userDto) {
//		logger.debug("time : {}", expireMin);
//		final JwtBuilder builder = Jwts.builder();
//		// JWT Token = Header + Payload + Signagure
//		// Header 설정
//		builder.setHeaderParam("typ", "JWT"); // 토큰의 타입, 고정값
//
//		// Payload 설정 - claim 정보 포함
//		builder.setSubject("로그인토큰").setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * expireMin))
//				.claim("UserDto", userDto);
//
//		// signature - screat key를 이용한 암호화
//		builder.signWith(SignatureAlgorithm.HS256, salt.getBytes());
//
//		// 직렬화
//		final String jwt = builder.compact();
//		logger.debug("토큰 발행 : {}", jwt);
//
//		return jwt;
//	}
//	
//	public void checkValid(final String jwt) {
//		logger.debug("토큰 점검 : {}", jwt);
//		Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
//	}
//	
//	public Map<String, Object> get(final String jwt){
//		Jws<Claims> claims = null;
//		try {
//			claims = Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
//			
//		} catch (Exception e) {
//			throw new RuntimeException();
//		}
//		logger.debug("claims : {}", claims);
//		return claims.getBody();
//	}
//}