package ar.com.bbva.got.service.parametria;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import ar.com.bbva.got.model.CampoDisponible;
import ar.com.bbva.got.repository.CampoDisponibleRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CampoDisponibleServiceTests {

	@Mock
	private CampoDisponibleRepository campoDisponibleRepository;
	
	@InjectMocks
	private CampoDisponibleServiceImpl campoDisponibleService;
	
	private static List<CampoDisponible> listaCampos = new ArrayList<CampoDisponible>();
	
	@BeforeClass
	public static void setUp() {
		listaCampos.add(new CampoDisponible());
		listaCampos.get(0).setId("1");
		
		listaCampos.add(new CampoDisponible());
		listaCampos.get(1).setId("2");
	}
	
	@Test
	public void testListAll() {
		List<CampoDisponible> camposRecibidos = new ArrayList<CampoDisponible>();
		
		Mockito.when(campoDisponibleRepository.findAll()).thenReturn(listaCampos);
		
		camposRecibidos = (List<CampoDisponible>) campoDisponibleService.listAll();
		
		Assert.assertEquals(listaCampos, camposRecibidos);
	}
	
	@Test
	public void testGetById() {
		CampoDisponible campoRecibido = new CampoDisponible();
		CampoDisponible campoRespuestaRepositorio = new CampoDisponible();
		String idParaMatchear = "1";
		
		for (CampoDisponible campo : listaCampos) {
			if (campo.getId() == idParaMatchear) campoRespuestaRepositorio = campo;
		}
		
		Mockito.when(campoDisponibleRepository.findById(idParaMatchear)).thenReturn(Optional.of(campoRespuestaRepositorio));
		
		campoRecibido = campoDisponibleService.getById(idParaMatchear);
		
		Assert.assertEquals(campoRespuestaRepositorio, campoRecibido);
	}
	
	@Test
	public void testSaveUno() {
		campoDisponibleService.save(listaCampos.get(0));
		
		ArgumentCaptor<CampoDisponible> argumentCaptor = ArgumentCaptor.forClass(CampoDisponible.class);
		Mockito.verify(campoDisponibleRepository).save(argumentCaptor.capture());
		Assert.assertEquals(listaCampos.get(0), argumentCaptor.getValue());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testSaveLista() {
		campoDisponibleService.save(listaCampos);
		
		ArgumentCaptor<List<CampoDisponible>> argumentCaptor = ArgumentCaptor.forClass(List.class);
		Mockito.verify(campoDisponibleRepository).saveAll(argumentCaptor.capture());
		Assert.assertEquals(listaCampos, argumentCaptor.getValue());
	}
	
	@Test
	public void testDelete() {
		String idParaBorrar = "1";
		
		campoDisponibleService.delete(idParaBorrar);
		
		ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
		Mockito.verify(campoDisponibleRepository).deleteById(argumentCaptor.capture());
		Assert.assertEquals(idParaBorrar, argumentCaptor.getValue());
	}
}
