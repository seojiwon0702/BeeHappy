package com.ssafy.house.model.service;


import java.util.List;

import com.ssafy.house.model.NoticeBoardDto;
import com.ssafy.house.model.NoticeBoardParameterDto;
import com.ssafy.house.model.QnABoardParameterDto;
import com.ssafy.util.PageNavigation;

public interface NoticeBoardService {
	public List<NoticeBoardDto> selectNoticeBoardList(NoticeBoardParameterDto noticeBoardParameterDto) throws Exception;
	public PageNavigation makePageNavigation(NoticeBoardParameterDto noticeBoardParameterDto) throws Exception;
	
	public NoticeBoardDto selectNoticeBoardByNo(int no) throws Exception;
	public boolean insertNoticeBoard(NoticeBoardDto noticeBoardDto) throws Exception;
	public boolean updateNoticeBoard(NoticeBoardDto noticeBoardDto) throws Exception;
	public boolean deleteNoticeBoard(int no) throws Exception;
	
//	public int getTotalCount(NoticeBoardParameterDto noticeBoardParameterDto) throws Exception;
//	public void updateHit(int no) throws Exception;
//	public void deleteMemo(int no) throws Exception;
}
