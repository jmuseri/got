	package ar.com.bbva.got.mappers;

import org.springframework.beans.BeanUtils;

import ar.com.bbva.got.dto.TramiteDTO;
import ar.com.bbva.got.model.Tramite;


public class TramiteMapper {

	private TramiteMapper(){}

	public static Tramite DTOtoModel(TramiteDTO dto) {
		
		Tramite model = new Tramite();
		
		BeanUtils.copyProperties(dto, model);

		return model ;
	}
	
}
