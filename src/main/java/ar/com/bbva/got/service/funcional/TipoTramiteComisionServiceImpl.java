package ar.com.bbva.got.service.funcional;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.bbva.got.model.TipoTramiteComision;
import ar.com.bbva.got.model.TipoTramiteComisionKey;
import ar.com.bbva.got.repository.TipoTramiteComisionRepository;

@Service
public class TipoTramiteComisionServiceImpl implements TipoTramiteComisionService {

	 private final Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	    private TipoTramiteComisionRepository tipoTramiteComisionRepository;
	
	    @Autowired
	    public void setRepository(TipoTramiteComisionRepository tipoTramiteComisionRepository) {
	        this.tipoTramiteComisionRepository = tipoTramiteComisionRepository;
	    }
	    
	    

	@Override
	public TipoTramiteComision getById(TipoTramiteComisionKey id) {
        logger.debug("getById called");
        return this.tipoTramiteComisionRepository.findById(id);
	}


	@Override
	public void save(List<TipoTramiteComision> tipoTramiteComision) {
		tipoTramiteComisionRepository.saveAll(tipoTramiteComision);
	}

	
	
}
