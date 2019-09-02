package ar.com.bbva.got.mappers;


import org.springframework.beans.BeanUtils;

import ar.com.bbva.got.dto.AutorizadoDTO;
import ar.com.bbva.got.model.Autorizado;


public class AutorizadoMapper {

	private AutorizadoMapper(){}

	
	public static AutorizadoDTO modelToDTO(Autorizado model) {
		
		AutorizadoDTO dto = new AutorizadoDTO();
		BeanUtils.copyProperties(model, dto);
		return dto ;
	}
	
	public static Autorizado DTOtoModel(AutorizadoDTO dto) {
		
		Autorizado model = new Autorizado();
		
		BeanUtils.copyProperties(dto, model);

		return model ;
	}
	
}
