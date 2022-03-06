package com.ssafy.house.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.ssafy.house.model.QnABoardDto;
import com.ssafy.house.model.QnABoardParameterDto;
import com.ssafy.house.model.UserDto;
import com.ssafy.house.model.service.QnABoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin("*")
@RequestMapping("/qnaboard")
@Api("QnABoard 컨트롤러  API V1")
public class QnABoardController {

	private static final Logger logger = LoggerFactory.getLogger(QnABoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private QnABoardService qnaBoardService;	

//	@ApiOperation(value = "게시판 글목록", notes = "모든 게시글의 정보를 반환한다.", response = List.class)
    @ApiOperation(value = "모든 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<QnABoardDto>> selectQnABoardList(@ApiParam(value = "게시글을 얻기위한 부가정보.", required = true) QnABoardParameterDto qnaBoardParameterDto) throws Exception {
		logger.debug("selectQnABoardList - 호출");
		return new ResponseEntity<List<QnABoardDto>>(qnaBoardService.selectQnABoardList(qnaBoardParameterDto), HttpStatus.OK);
	}
    
	
	@ApiOperation(value = "게시물 번호에 해당하는 게시물 정보를 반환한다.", response = QnABoardDto.class)
	@GetMapping("/{no}")
	public ResponseEntity<QnABoardDto> selectQnABoardByNo(@PathVariable int no) throws Exception {
		logger.debug("selectQnABoardByNo - 호출");
		return new ResponseEntity<QnABoardDto>(qnaBoardService.selectQnABoardByNo(no), HttpStatus.OK);
	}
	
	@ApiOperation(value = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> insertQnABoard(@RequestBody QnABoardDto qnaBoardDto) throws Exception {
		logger.debug("insertQnABoard - 호출");
		logger.debug("" + qnaBoardDto);
		
		if (qnaBoardService.insertQnABoard(qnaBoardDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	

	@ApiOperation(value = "글번호에 해당하는 게시글의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/{no}")
	public ResponseEntity<String> updateQnA(@RequestBody QnABoardDto qnaBoardDto) throws Exception {
		logger.debug("updateQnABoard - 호출");
		logger.debug("" + qnaBoardDto);

		if (qnaBoardService.updateQnABoard(qnaBoardDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{no}")
	public ResponseEntity<String> deleteQnA(@PathVariable int no) throws Exception {
		logger.debug("deleteBoard - 호출");
		
		if (qnaBoardService.deleteQnABoard(no)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
}
