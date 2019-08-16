package ar.com.bbva.got.service.parametria;

import java.util.List;

import ar.com.bbva.got.model.TipoTramiteCampo;
import ar.com.bbva.got.model.TipoTramiteCampoKey;
import ar.com.bbva.got.repository.TipoTramiteCampoRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoTramiteCampoServiceImpl implements TipoTramiteCampoService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private TipoTramiteCampoRepository tipoTramiteCampoRepository;

    @Autowired
    public void setRepository(TipoTramiteCampoRepository tipoTramiteCampoRepository) {
        this.tipoTramiteCampoRepository = tipoTramiteCampoRepository;
    }

    @Override
    public Iterable<TipoTramiteCampo> listAll() {
        logger.debug("listAll called");
        return this.tipoTramiteCampoRepository.findAll();
    }

    @Override
    public Iterable<TipoTramiteCampo> listActive() {
        logger.debug("listActive called");
        return tipoTramiteCampoRepository.findAllByActivoIsTrue();
    }

    @Override
    public TipoTramiteCampo getById(int id) {
        logger.debug("getById called");
        return this.tipoTramiteCampoRepository.findById(id).orElse(null);
    }

    @Override
    public TipoTramiteCampo getById(TipoTramiteCampoKey id) {
        logger.debug("getById called");
        return this.tipoTramiteCampoRepository.findById(id);
    }

    @Override
    public TipoTramiteCampo save(TipoTramiteCampo tipoTramiteCampo) {
        logger.debug("save called");
        return this.tipoTramiteCampoRepository.save(tipoTramiteCampo);
    }

    @Override
    public void save(List<TipoTramiteCampo> tipoTramiteCampo) {
        logger.debug("save called");
        tipoTramiteCampoRepository.saveAll(tipoTramiteCampo);
    }

    @Override
    public void delete(TipoTramiteCampo tipoTramiteCampo) {
        logger.debug("delete called");
        // this.tipoTramiteCampoRepository.deleteById(id);
        this.tipoTramiteCampoRepository.delete(tipoTramiteCampo);
    }
}
