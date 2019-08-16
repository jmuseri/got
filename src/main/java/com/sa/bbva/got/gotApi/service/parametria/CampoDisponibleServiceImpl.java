package com.sa.bbva.got.gotApi.service.parametria;

import java.util.List;

import com.sa.bbva.got.gotApi.model.CampoDisponible;
import com.sa.bbva.got.gotApi.repository.CampoDisponibleRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CampoDisponibleServiceImpl implements CampoDisponibleService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private CampoDisponibleRepository campoDisponibleRepository;

    @Autowired
    public void setRepository(CampoDisponibleRepository campoDisponibleRepository) {
        this.campoDisponibleRepository = campoDisponibleRepository;
    }

    @Override
    public Iterable<CampoDisponible> listAll() {
        logger.debug("listAll called");
        return this.campoDisponibleRepository.findAll();
    }

    @Override
    public CampoDisponible getById(Integer id) {
        logger.debug("getById called");
        return this.campoDisponibleRepository.findById(id).orElse(null);
    }

    @Override
    public CampoDisponible save(CampoDisponible campoDisponible) {
        logger.debug("save called");
        return this.campoDisponibleRepository.save(campoDisponible);
    }

    @Override
    public void save(List<CampoDisponible> campoDisponible) {
        logger.debug("savecalled");
        campoDisponibleRepository.saveAll(campoDisponible);
    }

    @Override
    public void delete(Integer id) {
        logger.debug("delete called");
        this.campoDisponibleRepository.deleteById(id);
    }
}
