package com.sa.bbva.got.repository;

import com.sa.bbva.got.configuration.RepositoryConfiguration;
import com.sa.bbva.got.model.Sector;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { RepositoryConfiguration.class })
public class SectorRepositoryTest {
    private SectorRepository sectorRepository;

    @Autowired
    public void setSectorRepository(SectorRepository sectortRepository) {
        this.sectorRepository = sectortRepository;
    }

    @Test
    public void testSaveSector() {
        // setup sector
        Sector sector = new Sector();
        // sector.setId(1);
        sector.setDescripcion("Sector1");
        sector.setCanal("Web");
        sector.setActivo(true);
        // save sector, verify has ID value after save
        assertNull(sector.getId()); // null before save
        sectorRepository.save(sector);
        assertNotNull(sector.getId()); // not null after save
        // fetch from DB
        Sector fetchedSector = sectorRepository.findById(sector.getId()).orElse(null);
        // should not be null
        assertNotNull(fetchedSector);
        // should equal
        assertEquals(sector.getId(), fetchedSector.getId());
        assertEquals(sector.getDescripcion(), fetchedSector.getDescripcion());
        // update description and save
        fetchedSector.setDescripcion("New Description");
        sectorRepository.save(fetchedSector);
        // get from DB, should be updated
        Sector fetchedUpdatedSector = sectorRepository.findById(fetchedSector.getId()).orElse(null);
        assertEquals(fetchedSector.getDescripcion(), fetchedUpdatedSector.getDescripcion());
        // verify count of sectors in DB
        long sectorCount = sectorRepository.count();
        assertEquals(sectorCount, 1);
        // get all sectors, list should only have one
        Iterable<Sector> sectors = sectorRepository.findAll();
        int count = 0;
        for (Sector p : sectors) {
            p.getDescripcion();
            count++;
        }
        assertEquals(count, 1);
    }
}
