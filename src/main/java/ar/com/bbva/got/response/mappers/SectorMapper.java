package ar.com.bbva.got.response.mappers;

import org.springframework.beans.BeanUtils;

import ar.com.bbva.got.model.Sector;
import ar.com.bbva.got.response.dto.SectorDTO;

public class SectorMapper {
	
	
	private SectorMapper(){}

	public static SectorDTO modelToDTO(Sector model) {
		SectorDTO dto = new SectorDTO();
		BeanUtils.copyProperties(model, dto);
		dto.setCanal(model.getId().getCanal());
		dto.setSector(model.getId().getSector());
		return dto;
	}
	
}
