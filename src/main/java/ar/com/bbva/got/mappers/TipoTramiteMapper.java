package ar.com.bbva.got.mappers;

import org.springframework.beans.BeanUtils;

import ar.com.bbva.got.dto.TipoTramiteDTO;
import ar.com.bbva.got.model.TipoTramite;

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
