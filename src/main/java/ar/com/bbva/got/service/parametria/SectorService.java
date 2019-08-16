package ar.com.bbva.got.service.parametria;

import java.util.List;

import ar.com.bbva.got.model.Sector;

public interface SectorService {
    Iterable<Sector> listAll();

    Iterable<Sector> listActive();

    Sector getById(Integer id);

    Sector save(Sector sector);

    void save(List<Sector> sectores);

    void delete(Integer id);
}
