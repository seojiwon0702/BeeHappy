package com.ssafy.house.model.service;

import java.util.List;

import com.ssafy.house.model.HouseInfoDto;
import com.ssafy.house.model.SidoGugunCodeDto;

public interface MapService {
	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;
	List<HouseInfoDto> getAptInDong(String dong) throws Exception;
}
