package ar.com.bbva.got.response.mappers;

import org.springframework.beans.BeanUtils;

import ar.com.bbva.got.model.TipoTramite;
import ar.com.bbva.got.response.dto.TipoTramiteDTO;

public class TipoTramiteMapper {
	
	
	private TipoTramiteMapper(){}

	public static TipoTramiteDTO modelToDTO(TipoTramite model) {
		
		TipoTramiteDTO dto = new TipoTramiteDTO();
		BeanUtils.copyProperties(model, dto);
		dto.setSectorInicial(SectorMapper.modelToDTO(model.getSectorInicial()));
		dto.setCampos(CampoDisponibleMapper.modelToDTO(model.getCampos()));
		
		return dto;
	}
	
}
