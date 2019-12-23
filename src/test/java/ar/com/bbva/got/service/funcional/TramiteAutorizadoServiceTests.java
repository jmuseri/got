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

import ar.com.bbva.got.model.TramiteAutorizado;
import ar.com.bbva.got.model.TramiteAutorizadoKey;
import ar.com.bbva.got.repository.TramiteAutorizadoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TramiteAutorizadoServiceTests {

	@Mock
	private TramiteAutorizadoRepository tramiteAutorizadoRepository;
	
	@InjectMocks
	private TramiteAutorizadoServiceImpl tramiteAutorizadoService;
	
	private static List<TramiteAutorizado> listaAutorizados = new ArrayList<TramiteAutorizado>();
	
	@BeforeClass
	public static void setUp() {
		listaAutorizados.add(new TramiteAutorizado());
		listaAutorizados.get(0).setId(new TramiteAutorizadoKey());
		listaAutorizados.get(0).getId().setAutorizadoId(1);

		listaAutorizados.add(new TramiteAutorizado());
		listaAutorizados.get(1).setId(new TramiteAutorizadoKey());
		listaAutorizados.get(1).getId().setAutorizadoId(2);
	}
	
	@Test
	public void testListAll() {
		List<TramiteAutorizado> autorizadosRecibidos = new ArrayList<TramiteAutorizado>();
		
		Mockito.when(tramiteAutorizadoRepository.findAll()).thenReturn(listaAutorizados);
		
		autorizadosRecibidos = (List<TramiteAutorizado>) tramiteAutorizadoService.listAll();
		
		Assert.assertEquals(listaAutorizados, autorizadosRecibidos);
	}
	
	@Test
	public void testGetById() {
		TramiteAutorizado autorizadoRespuestaRepositorio = new TramiteAutorizado();
		TramiteAutorizado autorizadoRecibido = new TramiteAutorizado();
		TramiteAutorizadoKey idParaMatchear = new TramiteAutorizadoKey();
		idParaMatchear.setAutorizadoId(1);
		
		for (TramiteAutorizado autorizado : listaAutorizados) {
			if (autorizado.getId().getAutorizadoId() == idParaMatchear.getAutorizadoId()) autorizadoRespuestaRepositorio = autorizado;
		}
		
		Mockito.when(tramiteAutorizadoRepository.findById(idParaMatchear)).thenReturn(autorizadoRespuestaRepositorio);
		
		autorizadoRecibido = tramiteAutorizadoService.getById(idParaMatchear);
		
		Assert.assertEquals(autorizadoRespuestaRepositorio, autorizadoRecibido);
	}
	
	@Test
	public void testSave() {
		TramiteAutorizado autorizadoParaGuardar = listaAutorizados.get(0);
		
		tramiteAutorizadoService.save(autorizadoParaGuardar);
		
		ArgumentCaptor<TramiteAutorizado> argumentCaptor = ArgumentCaptor.forClass(TramiteAutorizado.class);
		Mockito.verify(tramiteAutorizadoRepository).save(argumentCaptor.capture());
		Assert.assertEquals(autorizadoParaGuardar, argumentCaptor.getValue());
	}
	
	@Test
	public void testDelete() {
		TramiteAutorizado autorizadoRespuestaRepositorio = new TramiteAutorizado();
		TramiteAutorizadoKey idParaMatchear = new TramiteAutorizadoKey();
		idParaMatchear.setAutorizadoId(1);
		
		for (TramiteAutorizado autorizado : listaAutorizados) {
			if (autorizado.getId().getAutorizadoId() == idParaMatchear.getAutorizadoId()) autorizadoRespuestaRepositorio = autorizado;
		}
		
		Mockito.when(tramiteAutorizadoRepository.findById(idParaMatchear)).thenReturn(autorizadoRespuestaRepositorio);
		
		tramiteAutorizadoService.delete(idParaMatchear);
		
		ArgumentCaptor<TramiteAutorizado> argumentCaptor = ArgumentCaptor.forClass(TramiteAutorizado.class);
		Mockito.verify(tramiteAutorizadoRepository).delete(argumentCaptor.capture());
		Assert.assertEquals(autorizadoRespuestaRepositorio, argumentCaptor.getValue());
	}
	
	@Test
	public void testDeleteByAutorizadoId() {
		Integer idAutorizadoParaBorrar = 1;
		
		tramiteAutorizadoService.deleteByIdAutorizadoId(idAutorizadoParaBorrar);
		
		ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);
		Mockito.verify(tramiteAutorizadoRepository).deleteByIdAutorizadoId(argumentCaptor.capture());
		Assert.assertEquals(idAutorizadoParaBorrar, argumentCaptor.getValue());
	}
	
	@Test
	public void testListByAutorizadoId() {
		List<TramiteAutorizado> autorizadoRespuestaRepositorio = new ArrayList<TramiteAutorizado>();
		List<TramiteAutorizado> autorizadoRecibido = new ArrayList<TramiteAutorizado>();
		TramiteAutorizadoKey idParaMatchear = new TramiteAutorizadoKey();
		idParaMatchear.setAutorizadoId(1);
		
		for (TramiteAutorizado autorizado : listaAutorizados) {
			if (autorizado.getId().getAutorizadoId() == idParaMatchear.getAutorizadoId()) autorizadoRespuestaRepositorio.add(autorizado);
		}
		
		Mockito.when(tramiteAutorizadoRepository.findByidAutorizadoId(idParaMatchear.getAutorizadoId())).thenReturn(autorizadoRespuestaRepositorio);
		
		autorizadoRecibido = tramiteAutorizadoService.listByAutorizadoId(idParaMatchear.getAutorizadoId());
		
		Assert.assertEquals(autorizadoRespuestaRepositorio, autorizadoRecibido);
	}
}
