package com.sa.bbva.got.services;

import com.sa.bbva.got.model.Sector;

public interface ParametriaService {
    Iterable<Sector> listAllSectors();

    Sector getSectorById(Integer id);

    Sector saveSector(Sector sector);

    void deleteSector(Integer id);
}
