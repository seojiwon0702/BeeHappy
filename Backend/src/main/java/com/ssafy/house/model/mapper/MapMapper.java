package com.ssafy.house.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.house.model.HouseInfoDto;
import com.ssafy.house.model.SidoGugunCodeDto;

public interface MapMapper {
	List<SidoGugunCodeDto> getSido() throws SQLException;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;
	List<HouseInfoDto> getDongInGugun(String gugun) throws SQLException;
	List<HouseInfoDto> getAptInDong(String dong) throws SQLException;
}
