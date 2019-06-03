package com.sa.bbva.got.repository;

import java.util.Iterator;

import com.sa.bbva.got.model.Sector;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SectorRepository extends CrudRepository<Sector, Integer> {

    default Iterable<Sector> findAllByActivo(boolean activo) {
        Iterable<Sector> sectorList = this.findAll();
        Iterator<Sector> sectorIterator = sectorList.iterator();
        if (activo) {
            while (sectorIterator.hasNext()) {
                Sector element = sectorIterator.next();
                if (!element.isActivo()) {
                    sectorIterator.remove();
                }
            }
        } else {
            while (sectorIterator.hasNext()) {
                Sector element = sectorIterator.next();
                if (element.isActivo()) {
                    sectorIterator.remove();
                }
            }
        }
        return sectorList;
    }

}
