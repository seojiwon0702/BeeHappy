package com.ssafy.house.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.house.model.QnABoardDto;
import com.ssafy.house.model.QnABoardParameterDto;
import com.ssafy.house.model.UserDto;

@Mapper
public interface QnABoardMapper {
	
	public List<QnABoardDto> selectQnABoardList(QnABoardParameterDto qnaBoardParameterDto) throws SQLException;
	public QnABoardDto selectQnABoardByNo(int no) throws SQLException;
	public int insertQnABoard(QnABoardDto qnaBoardDto) throws SQLException;
	public int updateQnABoard(QnABoardDto qnaBoardDto) throws SQLException;
	public int deleteQnABoard(int no) throws SQLException;
	
//	public int getTotalCount(QnABoardParameterDto qnaBoardParameterDto) throws SQLException;
//	public void updateHit(int no) throws SQLException;
//	public void deleteMemo(int no) throws SQLException;
}
