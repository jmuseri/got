package com.sa.bbva.got.service.parametria;

import java.util.List;

import com.sa.bbva.got.model.Sector;
import com.sa.bbva.got.repository.SectorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectorServiceImpl implements SectorService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private SectorRepository sectorRepository;

    @Autowired
    public void setSectorRepository(SectorRepository sectorRepository) {
        this.sectorRepository = sectorRepository;
    }

    @Override
    public Iterable<Sector> listAll() {
        logger.debug("listAll called");
        return sectorRepository.findAll();
    }

    @Override
    public Iterable<Sector> listActive() {
        logger.debug("listActive called");
        return sectorRepository.findAllByActivo();
    }

    @Override
    public Sector getById(Integer id) {
        logger.debug("getById called");
        return sectorRepository.findById(id).orElse(null);
    }

    @Override
    public Sector save(Sector sector) {
        logger.debug("save called");
        return sectorRepository.save(sector);
    }

    @Override
    public void save(List<Sector> sectors) {
        logger.debug("save called");
        sectorRepository.saveAll(sectors);
    }

    @Override
    public void delete(Integer id) {
        logger.debug("delete called");
        sectorRepository.deleteById(id);
    }
}
