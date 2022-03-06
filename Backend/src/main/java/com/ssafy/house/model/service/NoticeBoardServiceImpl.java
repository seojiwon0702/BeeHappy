package com.ssafy.house.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.house.model.NoticeBoardDto;
import com.ssafy.house.model.NoticeBoardParameterDto;
import com.ssafy.house.model.mapper.NoticeBoardMapper;
import com.ssafy.util.PageNavigation;

@Service
public class NoticeBoardServiceImpl implements NoticeBoardService {
	
	private static final Logger logger = LoggerFactory.getLogger(NoticeBoardServiceImpl.class);

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<NoticeBoardDto> selectNoticeBoardList(NoticeBoardParameterDto noticeBoardParameterDto) throws Exception {
		int start = noticeBoardParameterDto.getPg() == 0 ? 0 : (noticeBoardParameterDto.getPg() - 1) * noticeBoardParameterDto.getSpp();
		noticeBoardParameterDto.setStart(start);
		return sqlSession.getMapper(NoticeBoardMapper.class).selectNoticeBoardList(noticeBoardParameterDto);
	}

	@Override
	public PageNavigation makePageNavigation(NoticeBoardParameterDto noticeBoardParameterDto) throws Exception {
		int naviSize = 5;
		PageNavigation pageNavigation = new PageNavigation();
		pageNavigation.setCurrentPage(noticeBoardParameterDto.getPg());
		pageNavigation.setNaviSize(naviSize);
//		int totalCount = sqlSession.getMapper(NoticeBoardMapper.class).getTotalCount(noticeBoardParameterDto);//총글갯수  269
//		pageNavigation.setTotalCount(totalCount);  
//		int totalPageCount = (totalCount - 1) / noticeBoardParameterDto.getSpp() + 1;//27
//		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = noticeBoardParameterDto.getPg() <= naviSize;
		pageNavigation.setStartRange(startRange);
//		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < noticeBoardParameterDto.getPg();
//		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		return pageNavigation;
	}
	@Override
	public NoticeBoardDto selectNoticeBoardByNo(int no) throws Exception {
		return sqlSession.getMapper(NoticeBoardMapper.class).selectNoticeBoardByNo(no);
	}

	@Override
	public boolean insertNoticeBoard(NoticeBoardDto noticeBoardDto) throws Exception {
		if(noticeBoardDto.getSubject() == null || noticeBoardDto.getContent() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(NoticeBoardMapper.class).insertNoticeBoard(noticeBoardDto) == 1;
	}

	@Override
	public boolean updateNoticeBoard(NoticeBoardDto noticeBoardDto) throws Exception {
		return sqlSession.getMapper(NoticeBoardMapper.class).updateNoticeBoard(noticeBoardDto) == 1;
	}

	@Override
	public boolean deleteNoticeBoard(int no) throws Exception {
		return sqlSession.getMapper(NoticeBoardMapper.class).deleteNoticeBoard(no) == 1;
	}

}
