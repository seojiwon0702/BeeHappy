package com.ssafy.house.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.house.model.NoticeBoardDto;
import com.ssafy.house.model.NoticeBoardParameterDto;

@Mapper
public interface NoticeBoardMapper {
	
	public List<NoticeBoardDto> selectNoticeBoardList(NoticeBoardParameterDto noticeBoardParameterDto) throws SQLException;
	public NoticeBoardDto selectNoticeBoardByNo(int no) throws SQLException;
	public int insertNoticeBoard(NoticeBoardDto noticeBoardDto) throws SQLException;
	public int updateNoticeBoard(NoticeBoardDto noticeBoardDto) throws SQLException;
	public int deleteNoticeBoard(int no) throws SQLException;
	
//	public int getTotalCount(NoticeBoardParameterDto noticeBoardParameterDto) throws SQLException;
//	public void updateHit(int no) throws SQLException;
//	public void deleteMemo(int no) throws SQLException;
}
