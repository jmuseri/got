package com.sa.bbva.got.service;

import com.sa.bbva.got.model.Sector;

public interface ParametriaService {
    Iterable<Sector> listAllSectors();

    Iterable<Sector> listActiveSectors();

    Sector getSectorById(Integer id);

    Sector saveSector(Sector sector);

    void deleteSector(Integer id);
}
