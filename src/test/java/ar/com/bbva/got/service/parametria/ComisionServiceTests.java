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
import ar.com.bbva.got.repository.ComisionRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ComisionServiceTests {

	@Mock
	private ComisionRepository comisionRepository;
	
	@InjectMocks
	private ComisionServiceImpl comisionService;
	
	private static List<Comision> listaComision = new ArrayList<Comision>();
	
	@BeforeClass
	public static void setUp() {
		listaComision.add(new Comision());
		listaComision.get(0).setId(0);
		
		listaComision.add(new Comision());
		listaComision.get(1).setId(1);
	}
	
	@Test
	public void listAllTest() {
		Iterable<Comision> iterableComisiones = new ArrayList<Comision>();
		
		Mockito.when(comisionRepository.findAll()).thenReturn(listaComision);
		
		iterableComisiones = (List<Comision>) comisionService.listAll();
		
		Assert.assertEquals(listaComision, iterableComisiones);
	}
	
	@Test
	public void getByIdTest() {
		Integer id = 0;
		Comision comision = listaComision.get(id);
		Optional<Comision> op = Optional.of(comision);
		
		Mockito.when(comisionRepository.findById(id)).thenReturn(op);
		
		Comision comisionBuscada = (Comision) comisionService.getById(id);
		
		Assert.assertEquals(comision, comisionBuscada);
	}
	
	@Test
	public void saveOneComisionTest() {
		Integer id = 0;
		Comision comision = listaComision.get(id);
		
		Mockito.when(comisionRepository.save(comision)).thenReturn(comision);
		
		comisionService.save(comision);
		
		Mockito.verify(comisionRepository, Mockito.times(1)).save(comision);		
		
	}
	
	@Test
	public void saveManyComisionTest() {
		
		comisionService.save(listaComision);
		
		Mockito.verify(comisionRepository, Mockito.times(1)).saveAll(listaComision);	
	}
	
	@Test
	public void deleteTest() {
		Integer id = 0;
		
		comisionService.delete(id);
		
		Mockito.verify(comisionRepository, Mockito.times(1)).deleteById(id);
	}
}
