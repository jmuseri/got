	package ar.com.bbva.got.mappers;

import org.springframework.beans.BeanUtils;

import ar.com.bbva.got.dto.TramiteDetalleDTO;
import ar.com.bbva.got.model.TramiteDetalle;
import ar.com.bbva.got.model.TramiteDetalleKey;


public class TramiteDetalleMapper {

	private TramiteDetalleMapper(){}

	public static TramiteDetalle DTOtoModel(TramiteDetalleDTO dto) {
		
		TramiteDetalle model = new TramiteDetalle();
		model.setId(new TramiteDetalleKey(dto.getTramiteId(), dto.getIdTipoTramite(), dto.getCampoDisponibleId()));
		BeanUtils.copyProperties(dto, model);

		return model ;
	}
	
	
}
