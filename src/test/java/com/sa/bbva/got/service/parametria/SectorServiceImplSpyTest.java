package com.sa.bbva.got.service.parametria;

import com.sa.bbva.got.model.Sector;
import com.sa.bbva.got.repository.SectorRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.never;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

//@RunWith(MockitoJUnitRunner.class)
@RunWith(MockitoJUnitRunner.Silent.class)
public class SectorServiceImplSpyTest {
    @Spy
    private SectorServiceImpl sectorServiceSpy;
    @Mock
    private SectorRepository sectorRepository;
    @Mock
    private Sector sector;

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerException_whenGetSectorByIdIsCalledWithoutContext() throws Exception {
        // Act
        Sector retrievedSector = sectorServiceSpy.getById(5);
        // Assert
        assertThat(retrievedSector, is(equalTo(sector)));
    }

    public void shouldThrowNullPointerException_whenSaveSectorIsCalledWithoutContext() throws Exception {
        // Arrange
        Mockito.doReturn(sector).when(sectorRepository).save(sector);
        // Act
        Sector savedSector = sectorServiceSpy.save(sector);
        // Assert
        assertThat(savedSector, is(equalTo(sector)));
    }

    @Test
    public void shouldVerifyThatGetSectorByIdIsCalled() throws Exception {
        // Arrange
        // Mockito.doReturn(sector).when(sectorServiceSpy).getById(5);
        // Act
        // -- Sector retrievedSector = sectorServiceSpy.getSectorById(5);
        // Assert
        // Mockito.verify(sectorServiceSpy).getById(5);
    }

    @Test
    public void shouldVerifyThatSaveSectorIsNotCalled() throws Exception {
        // Arrange
        Mockito.doReturn(sector).when(sectorServiceSpy).getById(5);
        // Act
        // Sector retrievedSector = sectorServiceSpy.getSectorById(5);
        // Assert
        Mockito.verify(sectorServiceSpy, never()).save(sector);
    }
}