package ar.com.bbva.got.service.parametria;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.bbva.got.model.Sector;
import ar.com.bbva.got.model.TipoTramite;
import ar.com.bbva.got.repository.TipoTramiteRepository;

@Service
public class TipoTramiteServiceImpl implements TipoTramiteService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private TipoTramiteRepository tipoTramiteRepository;

    @Autowired
    public void setRepository(TipoTramiteRepository tipoTramiteRepository) {
        this.tipoTramiteRepository = tipoTramiteRepository;
    }

    @Override
    public Iterable<TipoTramite> listAll() {
        logger.debug("listAll called");
        return this.tipoTramiteRepository.findAll();
    }

    @Override
    public Iterable<TipoTramite> listActive() {
        logger.debug("listActive called");
        return tipoTramiteRepository.findAllByActivoIsTrue();
    }
    
    @Override
    public Iterable<TipoTramite> listByActiveAndSector(boolean activo, Sector sector) {
        logger.debug("listByActiveAndSector called");
        return tipoTramiteRepository.findAllByActivoAndSectorInicial(activo, sector);
    }
    
    

    @Override
    public TipoTramite getById(Integer id) {
        logger.debug("getById called");
        return this.tipoTramiteRepository.findById(id).orElse(null);
    }

    @Override
    public TipoTramite save(TipoTramite tipoTramite) {
        logger.debug("save called");
        return this.tipoTramiteRepository.save(tipoTramite);
    }

    @Override
    public void save(List<TipoTramite> tipoTramite) {
        logger.debug("save called");
        tipoTramiteRepository.saveAll(tipoTramite);
    }

    @Override
    public void delete(Integer id) {
        logger.debug("delete called");
        this.tipoTramiteRepository.deleteById(id);
    }
}
