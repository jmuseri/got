package ar.com.bbva.got.service.funcional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.stream.Collectors;

import ar.com.bbva.got.model.Autorizado;
import ar.com.bbva.got.model.EstadoTramite;
import ar.com.bbva.got.model.Sector;
import ar.com.bbva.got.model.SectorKey;
import ar.com.bbva.got.model.TipoTramite;
import ar.com.bbva.got.model.Tramite;
import ar.com.bbva.got.model.TramiteAutorizado;
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
		listaTramites.get(0).setCuitEmpresa("123");		
		listaTramites.get(0).setTipoTramite(new TipoTramite());
		listaTramites.get(0).getTipoTramite().setId(1);
		listaTramites.get(0).setSectorActual(new Sector());
		listaTramites.get(0).getSectorActual().setId(new SectorKey());
		listaTramites.get(0).getSectorActual().getId().setSector("sector1");
		listaTramites.get(0).setSectorInicio(new Sector());
		listaTramites.get(0).getSectorInicio().setId(new SectorKey());
		listaTramites.get(0).getSectorInicio().getId().setSector("sector");
		listaTramites.get(0).setAutorizado(new HashSet<TramiteAutorizado>());
		listaTramites.get(0).getAutorizado().add(new TramiteAutorizado());
		listaTramites.get(0).getAutorizado().iterator().next().setAutorizado(new Autorizado());
		listaTramites.get(0).getAutorizado().iterator().next().getAutorizado().setNroDocumento("123123");
		listaTramites.get(0).getAutorizado().iterator().next().getAutorizado().setTipoDocumento("DNI");
		
		listaTramites.add(new Tramite());
		listaTramites.get(1).setId(2);
		listaTramites.get(1).setEstado(EstadoTramite.FINALIZADO);	
		listaTramites.get(1).setNroClienteEmpresa(1234);	
		listaTramites.get(1).setCuitEmpresa("123");
		listaTramites.get(1).setTipoTramite(new TipoTramite());
		listaTramites.get(1).getTipoTramite().setId(1);
		listaTramites.get(1).setSectorActual(new Sector());		
		listaTramites.get(1).getSectorActual().setId(new SectorKey());
		listaTramites.get(1).getSectorActual().getId().setSector("sector1");
		listaTramites.get(1).setSectorInicio(new Sector());
		listaTramites.get(1).getSectorInicio().setId(new SectorKey());
		listaTramites.get(1).getSectorInicio().getId().setSector("sector");
		listaTramites.get(1).setAutorizado(new HashSet<TramiteAutorizado>());
		listaTramites.get(1).getAutorizado().add(new TramiteAutorizado());
		listaTramites.get(1).getAutorizado().iterator().next().setAutorizado(new Autorizado());
		listaTramites.get(1).getAutorizado().iterator().next().getAutorizado().setNroDocumento("123123");
		listaTramites.get(1).getAutorizado().iterator().next().getAutorizado().setTipoDocumento("DNI");
		
		listaTramites.add(new Tramite());
		listaTramites.get(2).setId(3);
		listaTramites.get(2).setEstado(EstadoTramite.PENDIENTE);	
		listaTramites.get(2).setNroClienteEmpresa(123);	
		listaTramites.get(2).setCuitEmpresa("123");
		listaTramites.get(2).setTipoTramite(new TipoTramite());
		listaTramites.get(2).getTipoTramite().setId(2);	
		listaTramites.get(2).setSectorActual(new Sector());
		listaTramites.get(2).getSectorActual().setId(new SectorKey());
		listaTramites.get(2).getSectorActual().getId().setSector("sector2");
		listaTramites.get(2).setSectorInicio(new Sector());
		listaTramites.get(2).getSectorInicio().setId(new SectorKey());
		listaTramites.get(2).getSectorInicio().getId().setSector("sector");
		listaTramites.get(2).setAutorizado(new HashSet<TramiteAutorizado>());
		listaTramites.get(2).getAutorizado().add(new TramiteAutorizado());
		listaTramites.get(2).getAutorizado().iterator().next().setAutorizado(new Autorizado());
		listaTramites.get(2).getAutorizado().iterator().next().getAutorizado().setNroDocumento("123123");
		listaTramites.get(2).getAutorizado().iterator().next().getAutorizado().setTipoDocumento("DNI");
		
		listaTramites.add(new Tramite());
		listaTramites.get(3).setId(4);
		listaTramites.get(3).setEstado(EstadoTramite.PENDIENTE);	
		listaTramites.get(3).setNroClienteEmpresa(222);	
		listaTramites.get(3).setCuitEmpresa("1233");
		listaTramites.get(3).setTipoTramite(new TipoTramite());
		listaTramites.get(3).getTipoTramite().setId(3);
		listaTramites.get(3).setSectorActual(new Sector());		
		listaTramites.get(3).getSectorActual().setId(new SectorKey());
		listaTramites.get(3).getSectorActual().getId().setSector("sector4");
		listaTramites.get(3).setSectorInicio(new Sector());
		listaTramites.get(3).getSectorInicio().setId(new SectorKey());
		listaTramites.get(3).getSectorInicio().getId().setSector("sectorotro");
		listaTramites.get(3).setAutorizado(new HashSet<TramiteAutorizado>());
		listaTramites.get(3).getAutorizado().add(new TramiteAutorizado());
		listaTramites.get(3).getAutorizado().iterator().next().setAutorizado(new Autorizado());
		listaTramites.get(3).getAutorizado().iterator().next().getAutorizado().setNroDocumento("123123");
		listaTramites.get(3).getAutorizado().iterator().next().getAutorizado().setTipoDocumento("DNI");
		
		listaTramites.add(new Tramite());
		listaTramites.get(4).setId(5);
		listaTramites.get(4).setEstado(EstadoTramite.PENDIENTE);	
		listaTramites.get(4).setNroClienteEmpresa(123);	
		listaTramites.get(4).setCuitEmpresa("123");
		listaTramites.get(4).setTipoTramite(new TipoTramite());
		listaTramites.get(4).getTipoTramite().setId(1);
		listaTramites.get(4).setSectorActual(new Sector());		
		listaTramites.get(4).getSectorActual().setId(new SectorKey());
		listaTramites.get(4).getSectorActual().getId().setSector("sector4");
		listaTramites.get(4).setSectorInicio(new Sector());
		listaTramites.get(4).getSectorInicio().setId(new SectorKey());
		listaTramites.get(4).getSectorInicio().getId().setSector("sectorotro");
		listaTramites.get(4).setAutorizado(new HashSet<TramiteAutorizado>());
		listaTramites.get(4).getAutorizado().add(new TramiteAutorizado());
		listaTramites.get(4).getAutorizado().iterator().next().setAutorizado(new Autorizado());
		listaTramites.get(4).getAutorizado().iterator().next().getAutorizado().setNroDocumento("123123");
		listaTramites.get(4).getAutorizado().iterator().next().getAutorizado().setTipoDocumento("DNI");
		
		listaTramites.add(new Tramite());
		listaTramites.get(5).setId(1);
		listaTramites.get(5).setEstado(EstadoTramite.PENDIENTE);	
		listaTramites.get(5).setNroClienteEmpresa(123);	
		listaTramites.get(5).setCuitEmpresa("123");		
		listaTramites.get(5).setTipoTramite(new TipoTramite());
		listaTramites.get(5).getTipoTramite().setId(1);
		listaTramites.get(5).setSectorActual(new Sector());
		listaTramites.get(5).getSectorActual().setId(new SectorKey());
		listaTramites.get(5).getSectorActual().getId().setSector("sector1");
		listaTramites.get(5).setSectorInicio(new Sector());
		listaTramites.get(5).getSectorInicio().setId(new SectorKey());
		listaTramites.get(5).getSectorInicio().getId().setSector("sector");
		listaTramites.get(5).setAutorizado(new HashSet<TramiteAutorizado>());
		listaTramites.get(5).getAutorizado().add(new TramiteAutorizado());
		listaTramites.get(5).getAutorizado().iterator().next().setAutorizado(new Autorizado());
		listaTramites.get(5).getAutorizado().iterator().next().getAutorizado().setNroDocumento("1231234");
		listaTramites.get(5).getAutorizado().iterator().next().getAutorizado().setTipoDocumento("DNI2");
		
		listaTramites.add(new Tramite());
		listaTramites.get(6).setId(1);
		listaTramites.get(6).setEstado(EstadoTramite.PENDIENTE);	
		listaTramites.get(6).setNroClienteEmpresa(123);	
		listaTramites.get(6).setCuitEmpresa("123");		
		listaTramites.get(6).setTipoTramite(new TipoTramite());
		listaTramites.get(6).getTipoTramite().setId(1);
		listaTramites.get(6).setSectorActual(new Sector());
		listaTramites.get(6).getSectorActual().setId(new SectorKey());
		listaTramites.get(6).getSectorActual().getId().setSector("sector1");
		listaTramites.get(6).setSectorInicio(new Sector());
		listaTramites.get(6).getSectorInicio().setId(new SectorKey());
		listaTramites.get(6).getSectorInicio().getId().setSector("sector");
		listaTramites.get(6).setAutorizado(new HashSet<TramiteAutorizado>());
		listaTramites.get(6).getAutorizado().add(new TramiteAutorizado());
		listaTramites.get(6).getAutorizado().iterator().next().setAutorizado(new Autorizado());
		listaTramites.get(6).getAutorizado().iterator().next().getAutorizado().setNroDocumento("123123");
		listaTramites.get(6).getAutorizado().iterator().next().getAutorizado().setTipoDocumento("DNI2");
		
		listaTramites.add(new Tramite());
		listaTramites.get(7).setId(1);
		listaTramites.get(7).setEstado(EstadoTramite.PENDIENTE);	
		listaTramites.get(7).setNroClienteEmpresa(123);	
		listaTramites.get(7).setCuitEmpresa("123");		
		listaTramites.get(7).setTipoTramite(new TipoTramite());
		listaTramites.get(7).getTipoTramite().setId(1);
		listaTramites.get(7).setSectorActual(new Sector());
		listaTramites.get(7).getSectorActual().setId(new SectorKey());
		listaTramites.get(7).getSectorActual().getId().setSector("sector1");
		listaTramites.get(7).setSectorInicio(new Sector());
		listaTramites.get(7).getSectorInicio().setId(new SectorKey());
		listaTramites.get(7).getSectorInicio().getId().setSector("sectorotro");
		listaTramites.get(7).setAutorizado(new HashSet<TramiteAutorizado>());
		listaTramites.get(7).getAutorizado().add(new TramiteAutorizado());
		listaTramites.get(7).getAutorizado().iterator().next().setAutorizado(new Autorizado());
		listaTramites.get(7).getAutorizado().iterator().next().getAutorizado().setNroDocumento("1231234");
		listaTramites.get(7).getAutorizado().iterator().next().getAutorizado().setTipoDocumento("DNI");
		
		listaTramites.add(new Tramite());
		listaTramites.get(8).setId(1);
		listaTramites.get(8).setEstado(EstadoTramite.PENDIENTE);	
		listaTramites.get(8).setNroClienteEmpresa(123);	
		listaTramites.get(8).setCuitEmpresa("123");		
		listaTramites.get(8).setTipoTramite(new TipoTramite());
		listaTramites.get(8).getTipoTramite().setId(1);
		listaTramites.get(8).setSectorActual(new Sector());
		listaTramites.get(8).getSectorActual().setId(new SectorKey());
		listaTramites.get(8).getSectorActual().getId().setSector("sector1");
		listaTramites.get(8).setSectorInicio(new Sector());
		listaTramites.get(8).getSectorInicio().setId(new SectorKey());
		listaTramites.get(8).getSectorInicio().getId().setSector("sectorotro");
		listaTramites.get(8).setAutorizado(new HashSet<TramiteAutorizado>());
		listaTramites.get(8).getAutorizado().add(new TramiteAutorizado());
		listaTramites.get(8).getAutorizado().iterator().next().setAutorizado(new Autorizado());
		listaTramites.get(8).getAutorizado().iterator().next().getAutorizado().setNroDocumento("1231234");
		listaTramites.get(8).getAutorizado().iterator().next().getAutorizado().setTipoDocumento("DNI");
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
		
		for(Tramite tramite : listaTramites) {
			if (tramite.getEstado() == estadoAFiltrar &&
					tramite.getNroClienteEmpresa() == nroClienteAFiltrar &&
					tramite.getTipoTramite().getId() == idTipoTramiteAFiltrar &&
					tramite.getSectorInicio().getId().getSector().equals(sectorInicioIdAFiltrar)) tramitesFiltrados.add(tramite);
		}
		
		Mockito.when(tramiteRepository.findByNroClienteEmpresaAndEstadoAndTipoTramiteIdAndSectorInicioId(nroClienteAFiltrar,estadoAFiltrar,idTipoTramiteAFiltrar,sectorInicioIdAFiltrar)).thenReturn(tramitesFiltrados);
		
		List<Tramite> tramitesRecibidos = tramiteService.listByEmpresaEstadoAndTipoTramiteAndSectorInicio(nroClienteAFiltrar, estadoAFiltrar.toString(), idTipoTramiteAFiltrar, sectorInicioIdAFiltrar);
		
		Assert.assertEquals(tramitesFiltrados, tramitesRecibidos);
	}
	
	@Test
	public void testListByEmpresaEstadoAndTipoTramiteAndSectorInicioVacio() {
		List<Tramite> tramitesFiltrados = new ArrayList<Tramite>();
		EstadoTramite estadoAFiltrar = null;
		Integer nroClienteAFiltrar = 123;
		Integer idTipoTramiteAFiltrar = 1;
		String sectorInicioIdAFiltrar = "sector";
		
		Mockito.when(tramiteRepository.findByNroClienteEmpresaAndEstadoAndTipoTramiteIdAndSectorInicioId(nroClienteAFiltrar,estadoAFiltrar,idTipoTramiteAFiltrar,sectorInicioIdAFiltrar)).thenReturn(tramitesFiltrados);
		
		List<Tramite> tramitesRecibidos = tramiteService.listByEmpresaEstadoAndTipoTramiteAndSectorInicio(nroClienteAFiltrar, null, idTipoTramiteAFiltrar, sectorInicioIdAFiltrar);
		
		Assert.assertEquals(tramitesFiltrados, tramitesRecibidos);
	}
	
	@Test
	public void testListByCuitEmpresaEstadoAndTipoTramiteAndSectorInicio() {
		List<Tramite> tramitesFiltrados = new ArrayList<Tramite>();		
		EstadoTramite estadoAFiltrar = EstadoTramite.PENDIENTE;
		String cuitEmpresaAFiltrar = "123";
		Integer idTipoTramiteAFiltrar = 1;
		String sectorInicioIdAFiltrar = "sector";
		
		for(Tramite tramite : listaTramites) {
			if (tramite.getEstado() == estadoAFiltrar &&
					tramite.getCuitEmpresa() == cuitEmpresaAFiltrar &&
					tramite.getTipoTramite().getId() == idTipoTramiteAFiltrar &&
					tramite.getSectorInicio().getId().getSector().equals(sectorInicioIdAFiltrar)) tramitesFiltrados.add(tramite);
		}
		
		Mockito.when(tramiteRepository.findByCuitEmpresaAndEstadoAndTipoTramiteIdAndSectorInicioId(cuitEmpresaAFiltrar,estadoAFiltrar,idTipoTramiteAFiltrar,sectorInicioIdAFiltrar)).thenReturn(tramitesFiltrados);
		
		List<Tramite> tramitesRecibidos = tramiteService.listByCuitEmpresaEstadoAndTipoTramiteAndSectorInicio(cuitEmpresaAFiltrar, estadoAFiltrar.toString(), idTipoTramiteAFiltrar, sectorInicioIdAFiltrar);
		
		Assert.assertEquals(tramitesFiltrados, tramitesRecibidos);
	}
	
	@Test
	public void testListByCuitEmpresaEstadoAndTipoTramiteAndSectorInicioVacio() {
		List<Tramite> tramitesFiltrados = new ArrayList<Tramite>();		
		EstadoTramite estadoAFiltrar = null;
		String cuitEmpresaAFiltrar = "123";
		Integer idTipoTramiteAFiltrar = 1;
		String sectorInicioIdAFiltrar = "sector";
		
		Mockito.when(tramiteRepository.findByCuitEmpresaAndEstadoAndTipoTramiteIdAndSectorInicioId(cuitEmpresaAFiltrar,estadoAFiltrar,idTipoTramiteAFiltrar,sectorInicioIdAFiltrar)).thenReturn(tramitesFiltrados);
		
		List<Tramite> tramitesRecibidos = tramiteService.listByCuitEmpresaEstadoAndTipoTramiteAndSectorInicio(cuitEmpresaAFiltrar, null, idTipoTramiteAFiltrar, sectorInicioIdAFiltrar);
		
		Assert.assertEquals(tramitesFiltrados, tramitesRecibidos);
	}
	
	@Test
	public void testFindById() {
		Integer idAFiltrar = 1;
		Tramite tramiteFiltrado = null;
		
		for (Tramite tramite : listaTramites) {
			if (tramite.getId() == idAFiltrar) tramiteFiltrado = tramite;
		}
		
		Mockito.when(tramiteRepository.findById(idAFiltrar)).thenReturn(Optional.of(tramiteFiltrado));
		
		Tramite tramiteRecibido = tramiteService.getById(idAFiltrar);
		
		Assert.assertEquals(tramiteFiltrado, tramiteRecibido);		
	}
	
	@Test
	public void testSave() {
		Tramite tramiteAGuardar = listaTramites.get(0);
		
		tramiteService.save(tramiteAGuardar);
		
		ArgumentCaptor<Tramite> argumentCaptor = ArgumentCaptor.forClass(Tramite.class);
		Mockito.verify(tramiteRepository).save(argumentCaptor.capture());
		Assert.assertEquals(tramiteAGuardar, argumentCaptor.getValue());
	}
	
	@Test
	public void testDelete() {
		Integer idABorrar = listaTramites.get(0).getId();
		
		tramiteService.delete(idABorrar);
		
		ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);
		Mockito.verify(tramiteRepository).deleteById(argumentCaptor.capture());
		Assert.assertEquals(idABorrar, argumentCaptor.getValue());
	}
	
	@Test
	public void testBuscarTramites() {
		List<Tramite> tramitesFiltrados = new ArrayList<Tramite>();
		String cuitAFiltrar = "123";
		EstadoTramite estadoAFiltrar = EstadoTramite.PENDIENTE;
		Integer idTipoTramiteAFiltrar = 1;
		String idSectorAFiltrar = "sector";
		String DniAutorizadoAFiltrar = "123123";
		String tipoDocAutorizadoAFiltrar = "DNI";
				
		for (Tramite tramite : listaTramites) {
			if (tramite.getEstado() == estadoAFiltrar &&
					tramite.getCuitEmpresa() == cuitAFiltrar &&
					tramite.getTipoTramite().getId() == idTipoTramiteAFiltrar &&
					tramite.getSectorInicio().getId().getSector().equals(idSectorAFiltrar) &&
					!(tramite.getAutorizado().stream().filter(autorizado -> autorizado.getAutorizado().getTipoDocumento().equals(tipoDocAutorizadoAFiltrar) && autorizado.getAutorizado().getNroDocumento().equals(DniAutorizadoAFiltrar)).collect(Collectors.toSet()).isEmpty())) tramitesFiltrados.add(tramite);
		}
		
		Mockito.when(tramiteRepository.findByCuitAndEstadoAndTipoTramiteIdAndSectorInicioId(cuitAFiltrar, estadoAFiltrar, idTipoTramiteAFiltrar, idSectorAFiltrar, DniAutorizadoAFiltrar, tipoDocAutorizadoAFiltrar))
		.thenReturn(tramitesFiltrados);
		
		List<Tramite> tramitesRecibidos = tramiteService.buscarTramites(cuitAFiltrar, estadoAFiltrar.toString(), idTipoTramiteAFiltrar, idSectorAFiltrar, DniAutorizadoAFiltrar, tipoDocAutorizadoAFiltrar);
		
		Assert.assertEquals(tramitesFiltrados, tramitesRecibidos);
	}
	
	@Test
	public void testBuscarTramitesSinEstado() {
		List<Tramite> tramitesFiltrados = new ArrayList<Tramite>();
		String cuitAFiltrar = "123";
		EstadoTramite estadoAFiltrar = null;
		Integer idTipoTramiteAFiltrar = 1;
		String idSectorAFiltrar = "sector";
		String DniAutorizadoAFiltrar = "123123";
		String tipoDocAutorizadoAFiltrar = "DNI";
		
		Mockito.when(tramiteRepository.findByCuitAndEstadoAndTipoTramiteIdAndSectorInicioId(cuitAFiltrar, estadoAFiltrar, idTipoTramiteAFiltrar, idSectorAFiltrar, DniAutorizadoAFiltrar, tipoDocAutorizadoAFiltrar))
		.thenReturn(tramitesFiltrados);
		
		List<Tramite> tramitesRecibidos = tramiteService.buscarTramites(cuitAFiltrar, null, idTipoTramiteAFiltrar, idSectorAFiltrar, DniAutorizadoAFiltrar, tipoDocAutorizadoAFiltrar);
		
		Assert.assertEquals(tramitesFiltrados, tramitesRecibidos);
	}
	
	@Test
	public void testBuscarTramitesAACC() {
		List<Tramite> tramitesFiltrados = new ArrayList<Tramite>();
		String cuitAFiltrar = "123";
		EstadoTramite estadoAFiltrar = EstadoTramite.PENDIENTE;
		Integer idTipoTramiteAFiltrar = 1;
		String idSectorAFiltrar = "sector";
		String DniAutorizadoAFiltrar = "123123";
		String tipoDocAutorizadoAFiltrar = "DNI";
		
		String usuario = "usuario1";
		
		tramitesFiltrados.add(listaTramites.get(0));
		tramitesFiltrados.add(listaTramites.get(2));
		
		Mockito.when(tramiteService.buscarTramites(cuitAFiltrar, estadoAFiltrar.toString(), idTipoTramiteAFiltrar, idSectorAFiltrar, DniAutorizadoAFiltrar, tipoDocAutorizadoAFiltrar))
		.thenReturn(tramitesFiltrados);
		
		List<Tramite> tramitesRecibidos = tramiteService.buscarTramites(usuario, cuitAFiltrar, estadoAFiltrar.toString(), idTipoTramiteAFiltrar, idSectorAFiltrar, DniAutorizadoAFiltrar, tipoDocAutorizadoAFiltrar);
				
		
	}
}
