package com.sa.bbva.got.service.parametria;

import java.util.List;

import com.sa.bbva.got.model.EstadoTramite;
import com.sa.bbva.got.repository.EstadoTramiteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        logger.debug("listAllProducts called");
        return this.estadoTramiteRepository.findAll();
    }

    @Override
    public EstadoTramite getById(Integer id) {
        logger.debug("getProductById called");
        return this.estadoTramiteRepository.findById(id).orElse(null);
    }

    @Override
    public EstadoTramite save(EstadoTramite estadoTramite) {
        logger.debug("saveProduct called");
        return this.estadoTramiteRepository.save(estadoTramite);
    }

    @Override
    public void save(List<EstadoTramite> estadoTramite) {
        logger.debug("saveSector called");
        estadoTramiteRepository.saveAll(estadoTramite);
    }

    @Override
    public void delete(Integer id) {
        logger.debug("deleteProduct called");
        this.estadoTramiteRepository.deleteById(id);
    }
}
