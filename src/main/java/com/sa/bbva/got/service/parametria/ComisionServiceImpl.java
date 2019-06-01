package com.sa.bbva.got.service.parametria;

import java.util.List;

import com.sa.bbva.got.model.Comision;
import com.sa.bbva.got.repository.ComisionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComisionServiceImpl implements ComisionService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private ComisionRepository comisionRepository;

    @Autowired
    public void setRepository(ComisionRepository productRepository) {
        this.comisionRepository = productRepository;
    }

    @Override
    public Iterable<Comision> listAll() {
        logger.debug("listAllProducts called");
        return this.comisionRepository.findAll();
    }

    @Override
    public Comision getById(Integer id) {
        logger.debug("getProductById called");
        return this.comisionRepository.findById(id).orElse(null);
    }

    @Override
    public Comision save(Comision comision) {
        logger.debug("saveProduct called");
        return this.comisionRepository.save(comision);
    }

    @Override
    public void save(List<Comision> comision) {
        logger.debug("saveSector called");
        comisionRepository.saveAll(comision);
    }

    @Override
    public void delete(Integer id) {
        logger.debug("deleteProduct called");
        this.comisionRepository.deleteById(id);
    }
}
