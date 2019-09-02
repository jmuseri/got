package ar.com.bbva.got.mappers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import ar.com.bbva.got.dto.CampoDisponibleDTO;
import ar.com.bbva.got.model.CampoDisponible;
import ar.com.bbva.got.model.TipoTramiteCampo;

public class CampoDisponibleMapper {
	
	private CampoDisponibleMapper(){}

	public static CampoDisponibleDTO modelToDTO(TipoTramiteCampo model) {
		
		CampoDisponibleDTO dto = new CampoDisponibleDTO();
		CampoDisponible campoDisponible = model.getCampoDisponible();
		
		if (model.isActivo() && campoDisponible.isActivo()) {
			dto.setActivo(true);
		}else {
			dto.setActivo(false);
		}
		dto.setTipoDato(campoDisponible.getTipoDato());
		dto.setDescripcion(campoDisponible.getDescripcion());
		
		BeanUtils.copyProperties(model, dto);
		return dto;
	}
	
	public static Set<CampoDisponibleDTO> modelToDTO(Set<TipoTramiteCampo> modelList) {
		
		Set<CampoDisponibleDTO> dtoList = new HashSet<CampoDisponibleDTO>();
		
		for (TipoTramiteCampo campoDisponible : modelList) {
			dtoList.add(modelToDTO(campoDisponible));
		}

		return dtoList;
		
	}

}
