package com.sa.bbva.got.gotApi.service.funcional;

import java.util.List;

import com.sa.bbva.got.gotApi.model.TramiteAutorizado;
import com.sa.bbva.got.gotApi.model.TramiteAutorizadoKey;
import com.sa.bbva.got.gotApi.repository.TramiteAutorizadoRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TramiteAutorizadoServiceImpl implements TramiteAutorizadoService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private TramiteAutorizadoRepository tramiteAutorizadoRepository;

    @Autowired
    public void setRepository(TramiteAutorizadoRepository tramiteAutorizadoRepository) {
        this.tramiteAutorizadoRepository = tramiteAutorizadoRepository;
    }

    @Override
    public Iterable<TramiteAutorizado> listAll() {
        logger.debug("listAll called");
        return this.tramiteAutorizadoRepository.findAll();
    }

    @Override
    public TramiteAutorizado getById(TramiteAutorizadoKey id) {
        logger.debug("getById called");
        return this.tramiteAutorizadoRepository.findById(id);
    }

    @Override
    public TramiteAutorizado save(TramiteAutorizado tramiteAutorizado) {
        logger.debug("save called");
        return this.tramiteAutorizadoRepository.save(tramiteAutorizado);
    }

    @Override
    public void save(List<TramiteAutorizado> tramiteAutorizado) {
        logger.debug("save called");
        this.tramiteAutorizadoRepository.saveAll(tramiteAutorizado);
    }

    @Override
    public void delete(TramiteAutorizadoKey id) {
        logger.debug("delete called");
        TramiteAutorizado entity = this.tramiteAutorizadoRepository.findById(id);
        this.tramiteAutorizadoRepository.delete(entity);
    }

    @Override
    public void deleteByIdAutorizadoId(Integer id) {
        logger.debug("deleteByAutorizadoId called");
        this.tramiteAutorizadoRepository.deleteByIdAutorizadoId(id);
    }
}
