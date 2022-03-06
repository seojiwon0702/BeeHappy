package com.ssafy.house.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.house.model.NoticeBoardDto;
import com.ssafy.house.model.NoticeBoardParameterDto;
import com.ssafy.house.model.service.NoticeBoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin("*")
@RequestMapping("/noticeboard")
@Api("QnABoard 컨트롤러  API V1")
public class NoticeBoardController {

	private static final Logger logger = LoggerFactory.getLogger(NoticeBoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private NoticeBoardService noticeBoardService;	

//	@ApiOperation(value = "게시판 글목록", notes = "모든 게시글의 정보를 반환한다.", response = List.class)
    @ApiOperation(value = "모든 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<NoticeBoardDto>> selectQnABoardList(@ApiParam(value = "게시글을 얻기위한 부가정보.", required = true) NoticeBoardParameterDto noticeBoardParameterDto) throws Exception {
		logger.debug("selectNoticeBoardList - 호출");
		return new ResponseEntity<List<NoticeBoardDto>>(noticeBoardService.selectNoticeBoardList(noticeBoardParameterDto), HttpStatus.OK);
	}
    
	
	@ApiOperation(value = "게시물 번호에 해당하는 게시물 정보를 반환한다.", response = NoticeBoardDto.class)
	@GetMapping("/{no}")
	public ResponseEntity<NoticeBoardDto> selectNoticeBoardByNo(@PathVariable int no) throws Exception {
		logger.debug("selectNoticeBoardByNo - 호출");
		return new ResponseEntity<NoticeBoardDto>(noticeBoardService.selectNoticeBoardByNo(no), HttpStatus.OK);
	}
	
	@ApiOperation(value = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> insertNoticeBoard(@RequestBody NoticeBoardDto noticeBoardDto) throws Exception {
		logger.debug("insertNoticeBoard - 호출");
		logger.debug("" + noticeBoardDto);
		
		if (noticeBoardService.insertNoticeBoard(noticeBoardDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	

	@ApiOperation(value = "글번호에 해당하는 게시글의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/{no}")
	public ResponseEntity<String> updateNoticeBoard(@RequestBody NoticeBoardDto noticeBoardDto) throws Exception {
		logger.debug("updateNoticeBoard - 호출");
		logger.debug("" + noticeBoardDto);

		if (noticeBoardService.updateNoticeBoard(noticeBoardDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{no}")
	public ResponseEntity<String> deleteNoticeBoard(@PathVariable int no) throws Exception {
		logger.debug("deleteNoticeBoard - 호출");
		
		if (noticeBoardService.deleteNoticeBoard(no)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
}
