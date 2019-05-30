package com.sa.bbva.got.repository;

import java.util.Iterator;

import com.sa.bbva.got.model.Sector;

public interface SectorRepositoryImpl extends SectorRepository {

    @Override
    default Iterable<Sector> findAllByActivo() {
        Iterable<Sector> sectorList = this.findAll();
        Iterator<Sector> sectorIterator = sectorList.iterator();
        while (sectorIterator.hasNext()) {
            Sector element = sectorIterator.next();
            if (!element.isActivo()) {
                sectorIterator.remove();
            }
        }
        return sectorList;
    }
}
