package ar.com.bbva.got.service.parametria;

import java.util.List;

import ar.com.bbva.got.model.Sector;
import ar.com.bbva.got.model.SectorKey;

public interface SectorService {
    Iterable<Sector> listAll();

    Iterable<Sector> listActive();

    Sector getById(SectorKey id);

    Sector save(Sector sector);

    void save(List<Sector> sectores);

    void delete(SectorKey id);
}
