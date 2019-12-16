package ar.com.bbva.got.service.funcional;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ar.com.bbva.got.model.EstadoTramite;
import ar.com.bbva.got.model.Sector;
import ar.com.bbva.got.model.SectorKey;
import ar.com.bbva.got.model.TipoTramite;
import ar.com.bbva.got.model.Tramite;
import ar.com.bbva.got.repository.TramiteRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TramiteServiceTests {

	private static List<Tramite> listaTramites = new ArrayList<Tramite>();
	
	@Mock
	private TramiteRepository tramiteRepository;
	
	@InjectMocks
	TramiteServiceImpl tramiteService;
	
	@BeforeClass
	public static void setUp() {
		listaTramites.add(new Tramite());
		listaTramites.get(0).setId(1);
		listaTramites.get(0).setEstado(EstadoTramite.PENDIENTE);	
		listaTramites.get(0).setNroClienteEmpresa(123);
		listaTramites.get(0).setTipoTramite(new TipoTramite());
		listaTramites.get(0).getTipoTramite().setId(1);
		listaTramites.get(0).setSectorActual(new Sector());
		listaTramites.get(0).getSectorActual().setId(new SectorKey());
		listaTramites.get(0).getSectorActual().getId().setSector("sector1");
		listaTramites.get(0).setSectorInicio(new Sector());
		listaTramites.get(0).getSectorInicio().setId(new SectorKey());
		listaTramites.get(0).getSectorInicio().getId().setSector("sector");
		
		listaTramites.add(new Tramite());
		listaTramites.get(1).setId(2);
		listaTramites.get(0).setEstado(EstadoTramite.FINALIZADO);	
		listaTramites.get(0).setNroClienteEmpresa(1234);
		listaTramites.get(0).setTipoTramite(new TipoTramite());
		listaTramites.get(0).getTipoTramite().setId(1);
		listaTramites.get(1).setSectorActual(new Sector());		
		listaTramites.get(1).getSectorActual().setId(new SectorKey());
		listaTramites.get(1).getSectorActual().getId().setSector("sector1");
		listaTramites.get(0).setSectorInicio(new Sector());
		listaTramites.get(0).getSectorInicio().setId(new SectorKey());
		listaTramites.get(0).getSectorInicio().getId().setSector("sector");
		
		listaTramites.add(new Tramite());
		listaTramites.get(2).setId(3);
		listaTramites.get(0).setEstado(EstadoTramite.PENDIENTE);	
		listaTramites.get(0).setNroClienteEmpresa(123);
		listaTramites.get(0).setTipoTramite(new TipoTramite());
		listaTramites.get(0).getTipoTramite().setId(2);	
		listaTramites.get(2).setSectorActual(new Sector());
		listaTramites.get(2).getSectorActual().setId(new SectorKey());
		listaTramites.get(2).getSectorActual().getId().setSector("sector2");
		listaTramites.get(0).setSectorInicio(new Sector());
		listaTramites.get(0).getSectorInicio().setId(new SectorKey());
		listaTramites.get(0).getSectorInicio().getId().setSector("sector");
	}
	
	@Test
	public void testListAll() {
		Mockito.when(tramiteRepository.findAll()).thenReturn(listaTramites);
		
		Iterable<Tramite> tramitesRecibidos = tramiteService.listAll();
		
		Assert.assertEquals(listaTramites, tramitesRecibidos);
	}
	
	@Test
	public void testListBySector() {
		
		List<Tramite> tramitesFiltrados = new ArrayList<Tramite>();
		Sector sectorAFiltrar = new Sector();
		sectorAFiltrar.setId(new SectorKey());
		sectorAFiltrar.getId().setSector("sector1");
		
		for (Tramite tramite : listaTramites) {
			if (tramite.getSectorActual().getId().getSector() == sectorAFiltrar.getId().getSector()) tramitesFiltrados.add(tramite);
		}
		
		Mockito.when(tramiteRepository.findBySectorActual(sectorAFiltrar)).thenReturn(tramitesFiltrados);
		
		List<Tramite> tramitesRecibidos = tramiteService.listBySectorActual(sectorAFiltrar);
		
		Assert.assertEquals(tramitesFiltrados, tramitesRecibidos);
	}
	
	@Test
	public void testListByEstado() {
		List<Tramite> tramitesFiltrados = new ArrayList<Tramite>();
		EstadoTramite estadoAFiltrar = EstadoTramite.PENDIENTE;
		
		for (Tramite tramite : listaTramites) {
			if (tramite.getEstado() == estadoAFiltrar) tramitesFiltrados.add(tramite);
		}
		
		Mockito.when(tramiteRepository.findByEstado(estadoAFiltrar)).thenReturn(tramitesFiltrados);
		
		List<Tramite> tramitesRecibidos = tramiteService.listByEstado(estadoAFiltrar);
		
		Assert.assertEquals(tramitesFiltrados, tramitesRecibidos);
	}
	
	@Test
	public void testListByEmpresaEstadoAndTipoTramiteAndSectorInicio() {
		List<Tramite> tramitesFiltrados = new ArrayList<Tramite>();
		EstadoTramite estadoAFiltrar = EstadoTramite.PENDIENTE;
		Integer nroClienteAFiltrar = 123;
		Integer idTipoTramiteAFiltrar = 1;
		String sectorInicioIdAFiltrar = "sector";		
		
		for (Tramite tramite : listaTramites) {
			if (tramite.getEstado() == estadoAFiltrar &&
					tramite.getNroClienteEmpresa() == nroClienteAFiltrar &&
					tramite.getTipoTramite().getId() == idTipoTramiteAFiltrar &&
					tramite.getSectorInicio().getId().getSector().equals(sectorInicioIdAFiltrar)) tramitesFiltrados.add(tramite);
		}
		
		Mockito.when(tramiteRepository.findByNroClienteEmpresaAndEstadoAndTipoTramiteIdAndSectorInicioId(nroClienteAFiltrar,estadoAFiltrar,idTipoTramiteAFiltrar,sectorInicioIdAFiltrar)).thenReturn(tramitesFiltrados);
		
		List<Tramite> tramitesRecibidos = tramiteService.listByEmpresaEstadoAndTipoTramiteAndSectorInicio(nroClienteAFiltrar, estadoAFiltrar.toString(), idTipoTramiteAFiltrar, sectorInicioIdAFiltrar);
		
		Assert.assertEquals(tramitesFiltrados, tramitesRecibidos);
	}
}
