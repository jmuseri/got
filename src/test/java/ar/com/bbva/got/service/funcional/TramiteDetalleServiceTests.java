package ar.com.bbva.got.service.funcional;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ar.com.bbva.got.model.TipoTramiteCampoKey;
import ar.com.bbva.got.model.Tramite;
import ar.com.bbva.got.model.TramiteDetalle;
import ar.com.bbva.got.model.TramiteDetalleKey;
import ar.com.bbva.got.repository.TramiteDetalleRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TramiteDetalleServiceTests {
	
	private static List<TramiteDetalle> listaTramites = new ArrayList<TramiteDetalle>();

	@Mock
    private TramiteDetalleRepository tramiteDetalleRepository;

	@InjectMocks
	private TramiteDetalleServiceImpl tramiteDetalleService;
	
	@BeforeClass
	public static void setUp() {
		listaTramites.add(new TramiteDetalle());
		listaTramites.add(new TramiteDetalle());
		
		TramiteDetalleKey tramiteIdKey = null;
		TipoTramiteCampoKey tipoTramiteCampoKey = null; 
		
		tramiteIdKey = new TramiteDetalleKey();
		tipoTramiteCampoKey = new TipoTramiteCampoKey();
		
		tipoTramiteCampoKey.setTipoTramiteId(1);
		
		tramiteIdKey.setTipoTramiteCampoId(tipoTramiteCampoKey);
		tramiteIdKey.setTramiteId(1);
		
		listaTramites.get(0).setId(tramiteIdKey);
		
		tramiteIdKey = new TramiteDetalleKey();
		tipoTramiteCampoKey = new TipoTramiteCampoKey();
		
		tipoTramiteCampoKey.setTipoTramiteId(2);
		
		tramiteIdKey.setTipoTramiteCampoId(tipoTramiteCampoKey);
		tramiteIdKey.setTramiteId(2);
		
		listaTramites.get(1).setId(tramiteIdKey);

	}
	
	@Test
	public void testListAll() {
		Mockito.when(tramiteDetalleRepository.findAll()).thenReturn(listaTramites);
		
		Iterable<TramiteDetalle> tramitesRecibidos = tramiteDetalleService.listAll();
		
		Mockito.verify(tramiteDetalleRepository).findAll();
		Assert.assertEquals(listaTramites, tramitesRecibidos);
	}
	
	@Test
	public void testGetById() {
		TramiteDetalleKey id = new TramiteDetalleKey();
		TramiteDetalle tramiteDetalleMock = new TramiteDetalle();
		
		tramiteDetalleMock.setId(id);
		id.setTramiteId(3);
		
		Mockito.when(tramiteDetalleRepository.findById(id)).thenReturn(tramiteDetalleMock);
		
		TramiteDetalle tramiteDetalle = tramiteDetalleService.getById(id);
		
		Mockito.verify(tramiteDetalleRepository).findById(id);
		Assert.assertEquals(tramiteDetalleMock, tramiteDetalle);
		
	}
	
	@Test
	public void testSave() {
		TramiteDetalle tramiteAGuardar = listaTramites.get(0);
		
		tramiteDetalleService.save(tramiteAGuardar);
		
		ArgumentCaptor<TramiteDetalle> argumentCaptor = ArgumentCaptor.forClass(TramiteDetalle.class);
		
		Mockito.verify(tramiteDetalleRepository).save(argumentCaptor.capture());
		Assert.assertEquals(tramiteAGuardar, argumentCaptor.getValue());
		Assert.assertNotEquals(listaTramites.get(1), argumentCaptor.getValue());
	}
	
	@Test
	public void testSaveList() {
		List<TramiteDetalle> tramitesAGuardar = listaTramites;
		
		tramiteDetalleService.save(tramitesAGuardar);
		
		@SuppressWarnings("unchecked")
		ArgumentCaptor<List<TramiteDetalle>> argumentCaptor = ArgumentCaptor.forClass(List.class);
		
		Mockito.verify(tramiteDetalleRepository).saveAll(argumentCaptor.capture());
		Assert.assertEquals(tramitesAGuardar, argumentCaptor.getValue());
	}
	
	@Test
	public void testDelete() {
		TramiteDetalle tramiteABorrar = listaTramites.get(0);
		TramiteDetalleKey idABorrar = tramiteABorrar.getId();
		
		Mockito.when(tramiteDetalleRepository.findById(idABorrar)).thenReturn(tramiteABorrar);
		
		tramiteDetalleService.delete(idABorrar);
		
		ArgumentCaptor<TramiteDetalle> argumentCaptor = ArgumentCaptor.forClass(TramiteDetalle.class);
		
		
		Mockito.verify(tramiteDetalleRepository).delete(argumentCaptor.capture());
		Mockito.verify(tramiteDetalleRepository).findById(idABorrar);
		Assert.assertEquals(tramiteABorrar, argumentCaptor.getValue());
	}
}
