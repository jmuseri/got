package ar.com.bbva.got.service.funcional;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.bbva.got.model.TramiteDetalle;
import ar.com.bbva.got.model.TramiteDetalleKey;
import ar.com.bbva.got.repository.TramiteDetalleRepository;

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
    public TramiteDetalle getById(TramiteDetalleKey id) {
        logger.debug("getById called");
        return this.tramiteDetalleRepository.findById(id);
    }

    @Override
    public TramiteDetalle save(TramiteDetalle tramiteDetalle) {
        logger.debug("save called");
        return this.tramiteDetalleRepository.save(tramiteDetalle);
    }

    @Override
    public void save(List<TramiteDetalle> tramiteDetalle) {
        logger.debug("save called");
        this.tramiteDetalleRepository.saveAll(tramiteDetalle);
    }

    @Override
    public void delete(TramiteDetalleKey id) {
        logger.debug("delete called");
        TramiteDetalle entity = this.tramiteDetalleRepository.findById(id);
        this.tramiteDetalleRepository.delete(entity);
    }
}
