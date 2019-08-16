package com.sa.bbva.got.gotApi.service.funcional;

import java.util.List;

import com.sa.bbva.got.gotApi.model.EstadoTramite;
import com.sa.bbva.got.gotApi.model.Sector;
import com.sa.bbva.got.gotApi.model.Tramite;
import com.sa.bbva.got.gotApi.repository.TramiteRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TramiteServiceImpl implements TramiteService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private TramiteRepository tramiteRepository;

    @Autowired
    public void setRepository(TramiteRepository tramiteRepository) {
        this.tramiteRepository = tramiteRepository;
    }

    @Override
    public Iterable<Tramite> listAll() {
        logger.debug("listAll called");
        return this.tramiteRepository.findAll();
    }

    @Override
    public List<Tramite> listBySectorActual(Sector sectorActual) {
        logger.debug("listBySector called");
        return this.tramiteRepository.findBySectorActual(sectorActual);
    }

    @Override
    public List<Tramite> listByEstado(EstadoTramite estado) {
        logger.debug("listByEstado called");
        return this.tramiteRepository.findByEstado(estado);
    }

    @Override
    public Tramite getById(Integer id) {
        logger.debug("getById called");
        return this.tramiteRepository.findById(id).orElse(null);
    }

    @Override
    public Tramite save(Tramite tramite) {
        logger.debug("save called");
        return this.tramiteRepository.save(tramite);
    }

    @Override
    public void save(List<Tramite> tramite) {
        logger.debug("save called");
        tramiteRepository.saveAll(tramite);
    }

    @Override
    public void delete(Integer id) {
        logger.debug("delete called");
        this.tramiteRepository.deleteById(id);
    }

}
