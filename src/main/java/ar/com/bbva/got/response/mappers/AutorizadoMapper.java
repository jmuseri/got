package ar.com.bbva.got.response.mappers;


import ar.com.bbva.got.model.Autorizado;
import ar.com.bbva.got.response.dto.AutorizadoDTO;


public class AutorizadoMapper {

	private AutorizadoMapper(){}

	
	public static AutorizadoDTO modelToDTO(Autorizado model) {
		
		AutorizadoDTO dto = new AutorizadoDTO();
		
		dto.setApellido(model.getApellido());
		dto.setNombre(model.getNombre());
		dto.setNroClienteEmpresa(model.getNroClienteEmpresa());
		dto.setNroDocumento(model.getNroDocumento());
		dto.setTipoDoc(model.getTipoDocumento());	

		return dto ;
	}
	
}
