package ar.com.bbva.got.service.parametria;

import java.util.List;

import ar.com.bbva.got.model.Sector;
import ar.com.bbva.got.repository.SectorRepository;

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
        return this.sectorRepository.findAll();
    }

    @Override
    public Iterable<Sector> listActive() {
        logger.debug("listActive called");
        return this.sectorRepository.findAllByActivoIsTrue();
    }

    @Override
    public Sector getById(Integer id) {
        logger.debug("getById called");
        return this.sectorRepository.findById(id).orElse(null);
    }

    @Override
    public Sector save(Sector sector) {
        logger.debug("save called");
        return this.sectorRepository.save(sector);
    }

    @Override
    public void save(List<Sector> sectores) {
        logger.debug("save called");
        this.sectorRepository.saveAll(sectores);
    }

    @Override
    public void delete(Integer id) {
        logger.debug("delete called");
        this.sectorRepository.deleteById(id);
    }
}
