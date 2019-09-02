package ar.com.bbva.got.service.parametria;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.bbva.got.model.EstadoTramite;
import ar.com.bbva.got.repository.EstadoTramiteRepository;

@Service
public class EstadoTramiteServiceImpl implements EstadoTramiteService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private EstadoTramiteRepository estadoTramiteRepository;

    @Autowired
    public void setRepository(EstadoTramiteRepository estadoTramiteRepository) {
        this.estadoTramiteRepository = estadoTramiteRepository;
    }

    @Override
    public Iterable<EstadoTramite> listAll() {
        logger.debug("listAll called");
        return this.estadoTramiteRepository.findAll();
    }

    @Override
    public EstadoTramite getById(Integer id) {
        logger.debug("getById called");
        return this.estadoTramiteRepository.findById(id).orElse(null);
    }

    @Override
    public EstadoTramite save(EstadoTramite estadoTramite) {
        logger.debug("save called");
        return this.estadoTramiteRepository.save(estadoTramite);
    }

    @Override
    public void save(List<EstadoTramite> estadoTramite) {
        logger.debug("save called");
        estadoTramiteRepository.saveAll(estadoTramite);
    }

    @Override
    public void delete(Integer id) {
        logger.debug("delete called");
        this.estadoTramiteRepository.deleteById(id);
    }
}
