package com.ssafy.house.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.house.model.QnABoardDto;
import com.ssafy.house.model.QnABoardParameterDto;
import com.ssafy.house.model.mapper.QnABoardMapper;
import com.ssafy.util.PageNavigation;

@Service
public class QnABoardServiceImpl implements QnABoardService {
	
	private static final Logger logger = LoggerFactory.getLogger(QnABoardServiceImpl.class);
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<QnABoardDto> selectQnABoardList(QnABoardParameterDto qnaBoardParameterDto) throws Exception {
		int start = qnaBoardParameterDto.getPg() == 0 ? 0 : (qnaBoardParameterDto.getPg() - 1) * qnaBoardParameterDto.getSpp();
		qnaBoardParameterDto.setStart(start);
		return sqlSession.getMapper(QnABoardMapper.class).selectQnABoardList(qnaBoardParameterDto);
	}

	@Override
	public PageNavigation makePageNavigation(QnABoardParameterDto qnaBoardParameterDto) throws Exception {
		int naviSize = 5;
		PageNavigation pageNavigation = new PageNavigation();
		pageNavigation.setCurrentPage(qnaBoardParameterDto.getPg());
		pageNavigation.setNaviSize(naviSize);
//		int totalCount = sqlSession.getMapper(QnABoardMapper.class).getTotalCount(qnaBoardParameterDto);//총글갯수  269
//		pageNavigation.setTotalCount(totalCount);  
//		int totalPageCount = (totalCount - 1) / qnaBoardParameterDto.getSpp() + 1;//27
//		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = qnaBoardParameterDto.getPg() <= naviSize;
		pageNavigation.setStartRange(startRange);
//		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < qnaBoardParameterDto.getPg();
//		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		return pageNavigation;
	}

	@Override
	public QnABoardDto selectQnABoardByNo(int no) throws Exception {
		return sqlSession.getMapper(QnABoardMapper.class).selectQnABoardByNo(no);
	}

	@Override
	public boolean insertQnABoard(QnABoardDto qnaBoardDto) throws Exception {
		if(qnaBoardDto.getSubject() == null || qnaBoardDto.getContent() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(QnABoardMapper.class).insertQnABoard(qnaBoardDto) == 1;
	}

	@Override
	public boolean updateQnABoard(QnABoardDto qnaBoardDto) throws Exception {
		return sqlSession.getMapper(QnABoardMapper.class).updateQnABoard(qnaBoardDto) == 1;
	}

	@Override
	public boolean deleteQnABoard(int no) throws Exception {
		return sqlSession.getMapper(QnABoardMapper.class).deleteQnABoard(no) == 1;
	}

}
