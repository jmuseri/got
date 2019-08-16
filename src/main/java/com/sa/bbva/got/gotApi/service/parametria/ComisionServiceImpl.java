package com.sa.bbva.got.gotApi.service.parametria;

import java.util.List;

import com.sa.bbva.got.gotApi.model.Comision;
import com.sa.bbva.got.gotApi.repository.ComisionRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComisionServiceImpl implements ComisionService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private ComisionRepository comisionRepository;

    @Autowired
    public void setRepository(ComisionRepository comisionRepository) {
        this.comisionRepository = comisionRepository;
    }

    @Override
    public Iterable<Comision> listAll() {
        logger.debug("listAll called");
        return this.comisionRepository.findAll();
    }

    @Override
    public Comision getById(Integer id) {
        logger.debug("getById called");
        return this.comisionRepository.findById(id).orElse(null);
    }

    @Override
    public Comision save(Comision comision) {
        logger.debug("save called");
        return this.comisionRepository.save(comision);
    }

    @Override
    public void save(List<Comision> comision) {
        logger.debug("save called");
        comisionRepository.saveAll(comision);
    }

    @Override
    public void delete(Integer id) {
        logger.debug("delete called");
        this.comisionRepository.deleteById(id);
    }
}
