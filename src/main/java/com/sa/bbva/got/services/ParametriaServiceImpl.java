package com.sa.bbva.got.services;

import com.sa.bbva.got.model.Sector;
import com.sa.bbva.got.repositories.SectorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParametriaServiceImpl implements ParametriaService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private SectorRepository productRepository;

    @Autowired
    public void setProductRepository(SectorRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Iterable<Sector> listAllSectors() {
        logger.debug("listAllSectors called");
        return productRepository.findAll();
    }

    @Override
    public Sector getSectorById(Integer id) {
        logger.debug("getSectorById called");
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Sector saveSector(Sector product) {
        logger.debug("saveSector called");
        return productRepository.save(product);
    }

    @Override
    public void deleteSector(Integer id) {
        logger.debug("deleteSector called");
        productRepository.deleteById(id);
    }
}
