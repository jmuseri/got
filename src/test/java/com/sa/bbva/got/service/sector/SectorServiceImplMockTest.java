package com.sa.bbva.got.service.sector;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sa.bbva.got.model.Sector;
import com.sa.bbva.got.repository.SectorRepository;
import com.sa.bbva.got.service.parametria.SectorServiceImpl;

public class SectorServiceImplMockTest {

    private SectorServiceImpl sectorService;
    @Mock
    private SectorRepository sectorRepository;
    @Mock
    private Sector sector;

    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
        sectorService = new SectorServiceImpl();
        sectorService.setSectorRepository(sectorRepository);
    }

    @Test
    public void shouldReturnSector_whenGetSectorByIdIsCalled() throws Exception {
        // Arrange
        when(sectorRepository.findById(5)).thenReturn(Optional.of(sector));
        // Act
        Sector retrievedSector = sectorService.getById(5);
        // Assert
        assertThat(retrievedSector, is(equalTo(sector)));
    }

    @Test
    public void shouldReturnSector_whenSaveSectorIsCalled() throws Exception {
        // Arrange
        when(sectorRepository.save(sector)).thenReturn(sector);
        // Act
        Sector retrievedSector = sectorService.save(sector);
        // Assert
        assertThat(retrievedSector, is(equalTo(sector)));
    }

    @Test
    public void shouldCallDeleteMethodOfSectorRepository_whenDeleteSectorIsCalled() throws Exception {
        // Arrange
        doNothing().when(sectorRepository).deleteById(5);
        // Act
        sectorService.delete(5);
        // Assert
        verify(sectorRepository, times(1)).deleteById(5);
    }

    @Test
    public void shouldReturnAllSectors_whenListAllSectorsIsCalled() throws Exception {
        // Arrange
        Sector sector2 = mock(Sector.class);
        List<Sector> allSectors = new ArrayList<Sector>();
        allSectors.add(sector);
        allSectors.add(sector2);
        when(sectorRepository.findAll()).thenReturn(allSectors);
        // Act
        Iterable<Sector> returnedSectors = sectorService.listAll();
        // Assert
        assertEquals(allSectors, returnedSectors);
    }

    @Test
    public void shouldReturnAllActiveSectors_whenListActiveSectorsIsCalled() throws Exception {
        // Arrange
        List<Sector> activeSectors = new ArrayList<Sector>();
        activeSectors.add(sector);
        when(sectorRepository.findAllByActivoIsTrue()).thenReturn(activeSectors);
        // Act
        Iterable<Sector> returnedSectors = sectorService.listActive();
        // Assert
        assertEquals(activeSectors, returnedSectors);
    }
}