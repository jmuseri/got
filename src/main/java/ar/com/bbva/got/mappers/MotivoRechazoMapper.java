package ar.com.bbva.got.mappers;

import org.springframework.beans.BeanUtils;

import ar.com.bbva.got.dto.MotivoRechazoDTO;
import ar.com.bbva.got.model.MotivoRechazo;

public class MotivoRechazoMapper {
	
	
	private MotivoRechazoMapper(){}

	public static MotivoRechazoDTO modelToDTO(MotivoRechazo model) {
		
		MotivoRechazoDTO dto = new MotivoRechazoDTO();
		BeanUtils.copyProperties(model, dto);
		dto.setIdTipoTramite(model.getTipoTramite().getId());
		return dto;
	}
	
}
