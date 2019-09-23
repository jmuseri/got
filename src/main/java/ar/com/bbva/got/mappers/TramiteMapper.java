	package ar.com.bbva.got.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import ar.com.bbva.got.dto.AutorizadoDTO;
import ar.com.bbva.got.dto.CampoDetalleDTO;
import ar.com.bbva.got.dto.TramiteDTO;
import ar.com.bbva.got.model.TipoTramiteCampo;
import ar.com.bbva.got.model.TipoTramiteCampoKey;
import ar.com.bbva.got.model.Tramite;
import ar.com.bbva.got.model.TramiteAutorizado;
import ar.com.bbva.got.model.TramiteDetalle;


public class TramiteMapper {

	private TramiteMapper(){}

	public static Tramite DTOtoModel(TramiteDTO dto) {
		
		Tramite model = new Tramite();
		
		BeanUtils.copyProperties(dto, model);

		return model ;
	}
	
	public static TramiteDTO modelToDTO(Tramite model) {
		
		TramiteDTO dto = new TramiteDTO();
		
		BeanUtils.copyProperties(model, dto);
		
		dto.setTipoTramiteDesc(model.getTipoTramite().getDescripcion());
		
//		dto.setSectorActual(SectorMapper.modelToDTO(model.getSectorActual()));
//		dto.setSectorInicio(SectorMapper.modelToDTO(model.getSectorInicio()));
    	
    	List<AutorizadoDTO> listaAutorizados = new ArrayList<AutorizadoDTO>();
    	for (TramiteAutorizado autorizado : model.getAutorizado()) {
    		AutorizadoDTO autorizadoDTO = AutorizadoMapper.modelToDTO(autorizado.getAutorizado());
    		listaAutorizados.add(autorizadoDTO);
		}
    	
    	dto.setAutorizado(listaAutorizados);
    	
    	List<CampoDetalleDTO> listaDetalle = new ArrayList<CampoDetalleDTO>();
    	
    	for (TramiteDetalle detalle : model.getDetalle()) {
    		CampoDetalleDTO detalleDto = new CampoDetalleDTO();
    		
    		TipoTramiteCampo campo = getTipoTramiteCampo(detalle.getId().getTipoTramiteCampoId(), model.getTipoTramite().getCampos());
    		if (campo != null) {
    			detalleDto.setNombre(campo.getNombre());
        		detalleDto.setValor(detalle.getValor());
        		detalleDto.setDescripcion(campo.getCampoDisponible().getDescripcion());
        		detalleDto.setLeyenda(campo.getLeyenda());
        		listaDetalle.add(detalleDto);
    		}
    		
		}
    	dto.setDetalle(listaDetalle);
    	
    	return dto;
		
	}
	
	private static TipoTramiteCampo getTipoTramiteCampo (TipoTramiteCampoKey id, Set<TipoTramiteCampo> campos) {
		
		for (TipoTramiteCampo tipoTramiteCampo : campos) {
			if (tipoTramiteCampo.getId().equals(id)) {
				return tipoTramiteCampo;
			}
		}
		return null;
	}
	
	
}
