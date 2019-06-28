package com.sa.bbva.got.service.parametria;

import java.util.List;

import com.sa.bbva.got.model.Sector;

public interface SectorService {
    Iterable<Sector> listAll();

    Iterable<Sector> listActive();

    Sector getById(Integer id);

    Sector save(Sector sector);

    void save(List<Sector> sectores);

    void delete(Integer id);
}
