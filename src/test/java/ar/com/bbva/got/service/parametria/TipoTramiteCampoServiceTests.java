package ar.com.bbva.got.service.parametria;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ar.com.bbva.got.model.Comision;
import ar.com.bbva.got.model.TipoTramiteCampo;
import ar.com.bbva.got.model.TipoTramiteCampoKey;
import ar.com.bbva.got.repository.TipoTramiteCampoRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TipoTramiteCampoServiceTests {

	@Mock
	private TipoTramiteCampoRepository tipoTramiteCampoRepository;
	
	@InjectMocks
	private TipoTramiteCampoServiceImpl tipoTramiteCampoService;
	
	private static List<TipoTramiteCampo> listaTipoTramiteCampo = new ArrayList<TipoTramiteCampo>();
	
	private static List<TipoTramiteCampo> listaTipoTramiteCampoActivo = new ArrayList<TipoTramiteCampo>();
	
	@BeforeClass
	public static void setUp() {
		TipoTramiteCampo tipoTramiteCampo1 = new TipoTramiteCampo();
		TipoTramiteCampo tipoTramiteCampo2 = new TipoTramiteCampo();
		TipoTramiteCampo tipoTramiteCampo3 = new TipoTramiteCampo();
		tipoTramiteCampo3.setActivo(true);		
		TipoTramiteCampo tipoTramiteCampo4 = new TipoTramiteCampo();
		tipoTramiteCampo4.setActivo(true);	
		
		listaTipoTramiteCampo.add(tipoTramiteCampo1);		
		listaTipoTramiteCampo.add(tipoTramiteCampo2);
		listaTipoTramiteCampo.add(tipoTramiteCampo3);
		listaTipoTramiteCampo.add(tipoTramiteCampo4);
		
		listaTipoTramiteCampoActivo.add(tipoTramiteCampo3);		
		listaTipoTramiteCampoActivo.add(tipoTramiteCampo4);
		

	}
	
	@Test
	public void listAllTest() {
		Iterable<TipoTramiteCampo> iterableTipoTramiteCampo = new ArrayList<TipoTramiteCampo>();
		
		Mockito.when(tipoTramiteCampoRepository.findAll()).thenReturn(listaTipoTramiteCampo);
		
		iterableTipoTramiteCampo = (Iterable<TipoTramiteCampo>) tipoTramiteCampoService.listAll();
		
		Mockito.verify(tipoTramiteCampoRepository, Mockito.times(1)).findAll();
		Assert.assertEquals(listaTipoTramiteCampo, iterableTipoTramiteCampo);
		
	}
	
	@Test
	public void listActiveTest() {
		Iterable<TipoTramiteCampo> iterableTipoTramiteCampoActivo = new ArrayList<TipoTramiteCampo>();
		
		Mockito.when(tipoTramiteCampoRepository.findAllByActivoIsTrue()).thenReturn(listaTipoTramiteCampoActivo);
		
		iterableTipoTramiteCampoActivo = (Iterable<TipoTramiteCampo>) tipoTramiteCampoService.listActive();
		
		Mockito.verify(tipoTramiteCampoRepository, Mockito.times(1)).findAllByActivoIsTrue();
		Assert.assertEquals(listaTipoTramiteCampoActivo, iterableTipoTramiteCampoActivo);
		for (TipoTramiteCampo tipoTramiteCampoActivo : iterableTipoTramiteCampoActivo) {
			Assert.assertEquals(tipoTramiteCampoActivo.isActivo(), true);
		}
	}
	
	@Test
	public void getByIdIntTest() {
		int id = 0;
		TipoTramiteCampo tipoTramiteCampo = listaTipoTramiteCampo.get(id);
		Optional<TipoTramiteCampo> op = Optional.of(tipoTramiteCampo);
		
		Mockito.when(tipoTramiteCampoRepository.findById(id)).thenReturn(op);
		
		TipoTramiteCampo tipoTramiteCampoBuscado = (TipoTramiteCampo) tipoTramiteCampoService.getById(id);
		
		Mockito.verify(tipoTramiteCampoRepository, Mockito.times(1)).findById(id);
		Assert.assertEquals(tipoTramiteCampo, tipoTramiteCampoBuscado);
		
	}
	
	@Test
	public void getByIdTipoTramiteKeyTest() {
		TipoTramiteCampoKey id = new TipoTramiteCampoKey();
		id.setTipoTramiteId(0);
		TipoTramiteCampo tipoTramiteCampo = listaTipoTramiteCampo.get(id.getTipoTramiteId());
		
		Mockito.when(tipoTramiteCampoRepository.findById(id)).thenReturn(tipoTramiteCampo);
		
		TipoTramiteCampo tipoTramiteCampoBuscado = (TipoTramiteCampo) tipoTramiteCampoService.getById(id);
		
		Mockito.verify(tipoTramiteCampoRepository, Mockito.times(1)).findById(id);
		Assert.assertEquals(tipoTramiteCampo, tipoTramiteCampoBuscado);
		
	}
	
	@Test
	public void saveOneTipoTramiteCampoTest() {
		TipoTramiteCampo tipoTramiteCampo = listaTipoTramiteCampo.get(0);
		
		Mockito.when(tipoTramiteCampoRepository.save(tipoTramiteCampo)).thenReturn(tipoTramiteCampo);
		
		TipoTramiteCampo tipoTramiteCampoGuardado = (TipoTramiteCampo) tipoTramiteCampoService.save(tipoTramiteCampo);
		
		Mockito.verify(tipoTramiteCampoRepository, Mockito.times(1)).save(tipoTramiteCampo);
		Assert.assertEquals(tipoTramiteCampo, tipoTramiteCampoGuardado);
	}
	
	@Test
	public void saveManyTipoTramiteCampoTest() {
		Mockito.when(tipoTramiteCampoRepository.saveAll(listaTipoTramiteCampo)).thenReturn(listaTipoTramiteCampo);
		
		tipoTramiteCampoService.save(listaTipoTramiteCampo);
		
		Mockito.verify(tipoTramiteCampoRepository, Mockito.times(1)).saveAll(listaTipoTramiteCampo);
	}
	
	@Test
	public void deleteTest() {
		TipoTramiteCampo tipoTramiteCampo = listaTipoTramiteCampo.get(0);
		
		tipoTramiteCampoService.delete(tipoTramiteCampo);
		
		Mockito.verify(tipoTramiteCampoRepository, Mockito.times(1)).delete(tipoTramiteCampo);
	}
}
