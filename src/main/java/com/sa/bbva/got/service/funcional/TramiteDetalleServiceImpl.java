package com.sa.bbva.got.service.funcional;

import java.util.List;

import com.sa.bbva.got.model.TramiteDetalle;
import com.sa.bbva.got.repository.TramiteDetalleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TramiteDetalleServiceImpl implements TramiteDetalleService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private TramiteDetalleRepository tramiteDetalleRepository;

    @Autowired
    public void setRepository(TramiteDetalleRepository tramiteDetalleRepository) {
        this.tramiteDetalleRepository = tramiteDetalleRepository;
    }

    @Override
    public Iterable<TramiteDetalle> listAll() {
        logger.debug("listAll called");
        return this.tramiteDetalleRepository.findAll();
    }

    @Override
    public TramiteDetalle getById(Integer id) {
        logger.debug("getById called");
        return this.tramiteDetalleRepository.findById(id).orElse(null);
    }

    @Override
    public TramiteDetalle save(TramiteDetalle tramiteDetalle) {
        logger.debug("save called");
        return this.tramiteDetalleRepository.save(tramiteDetalle);
    }

    @Override
    public void save(List<TramiteDetalle> tramiteDetalle) {
        logger.debug("save called");
        tramiteDetalleRepository.saveAll(tramiteDetalle);
    }

    @Override
    public void delete(Integer id) {
        logger.debug("delete called");
        this.tramiteDetalleRepository.deleteById(id);
    }
}
