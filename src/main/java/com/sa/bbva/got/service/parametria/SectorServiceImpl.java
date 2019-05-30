package com.sa.bbva.got.service.parametria;

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
    public Iterable<Sector> listAllSectors() {
        logger.debug("listAllSectors called");
        return sectorRepository.findAll();
    }

    @Override
    public Iterable<Sector> listActiveSectors() {
        logger.debug("listActiveSectors called");
        return sectorRepository.findAllByActivo();
    }

    @Override
    public Sector getSectorById(Integer id) {
        logger.debug("getSectorById called");
        return sectorRepository.findById(id).orElse(null);
    }

    @Override
    public Sector saveSector(Sector sector) {
        logger.debug("saveSector called");
        return sectorRepository.save(sector);
    }

    @Override
    public void deleteSector(Integer id) {
        logger.debug("deleteSector called");
        sectorRepository.deleteById(id);
    }
}
