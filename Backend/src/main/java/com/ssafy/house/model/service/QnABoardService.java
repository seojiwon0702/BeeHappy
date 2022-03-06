package com.ssafy.house.model.service;

import java.util.List;

import com.ssafy.house.model.QnABoardDto;
import com.ssafy.house.model.QnABoardParameterDto;
import com.ssafy.util.PageNavigation;

public interface QnABoardService {
	public List<QnABoardDto> selectQnABoardList(QnABoardParameterDto qnaBoardParameterDto) throws Exception;
	public PageNavigation makePageNavigation(QnABoardParameterDto qnaBoardParameterDto) throws Exception;

	public QnABoardDto selectQnABoardByNo(int no) throws Exception;
	public boolean insertQnABoard(QnABoardDto qnaBoardDto) throws Exception;
	public boolean updateQnABoard(QnABoardDto qnaBoardDto) throws Exception;
	public boolean deleteQnABoard(int no) throws Exception;
	
	
//	public int getTotalCount(QnABoardParameterDto qnaBoardParameterDto) throws Exception;
//	public void updateHit(int no) throws Exception;
//	public void deleteMemo(int no) throws Exception;
}
