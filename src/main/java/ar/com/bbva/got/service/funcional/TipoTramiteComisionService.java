package ar.com.bbva.got.service.funcional;

import java.util.List;

import ar.com.bbva.got.model.TipoTramiteComision;
import ar.com.bbva.got.model.TipoTramiteComisionKey;;

public interface TipoTramiteComisionService {
    
    TipoTramiteComision getById(TipoTramiteComisionKey id);

	void save(List<TipoTramiteComision> tipoTramiteComision);
	
}
