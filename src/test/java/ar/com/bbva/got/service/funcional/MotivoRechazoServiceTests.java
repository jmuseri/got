package ar.com.bbva.got.service.funcional;

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

import ar.com.bbva.got.model.MotivoRechazo;
import ar.com.bbva.got.model.TipoTramite;
import ar.com.bbva.got.model.TramiteAutorizado;
import ar.com.bbva.got.repository.MotivoRechazoRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MotivoRechazoServiceTests {

	@Mock
	private MotivoRechazoRepository motivoRechazoRepository;
	
	@InjectMocks
	private MotivoRechazoServiceImpl motivoRechazoService;
	
	private static List<MotivoRechazo> listaMotivos = new ArrayList<MotivoRechazo>();
	
	@BeforeClass
	public static void setUp() {
		listaMotivos.add(new MotivoRechazo());
		listaMotivos.get(0).setId(1);
		listaMotivos.get(0).setTipoTramite(new TipoTramite());
		listaMotivos.get(0).getTipoTramite().setId(1);
		
		listaMotivos.add(new MotivoRechazo());
		listaMotivos.get(1).setId(2);
		listaMotivos.get(1).setTipoTramite(new TipoTramite());
		listaMotivos.get(1).getTipoTramite().setId(2);
		
		listaMotivos.add(new MotivoRechazo());
		listaMotivos.get(2).setId(3);
		listaMotivos.get(2).setTipoTramite(new TipoTramite());
		listaMotivos.get(2).getTipoTramite().setId(1);
	}
	
	@Test
	public void testListAll() {
		List<MotivoRechazo> motivosRecibidos = new ArrayList<MotivoRechazo>();
		
		Mockito.when(motivoRechazoRepository.findAll()).thenReturn(listaMotivos);
		
		motivosRecibidos = (List<MotivoRechazo>) motivoRechazoService.listAll();
		
		Assert.assertEquals(listaMotivos, motivosRecibidos);
	}
	
	@Test
	public void testListByTipoTramiteId() {
		List<MotivoRechazo> motivosRecibidos = new ArrayList<MotivoRechazo>();
		List<MotivoRechazo> motivosRespuestaRepositorio = new ArrayList<MotivoRechazo>();
		Integer idParaMatchear = 1;
		
		for (MotivoRechazo motivo : listaMotivos) {
			if (motivo.getTipoTramite().getId() == idParaMatchear) motivosRespuestaRepositorio.add(motivo);
		}
		
		Mockito.when(motivoRechazoRepository.findAllByTipoTramiteId(idParaMatchear)).thenReturn(motivosRespuestaRepositorio);
		
		motivosRecibidos = (List<MotivoRechazo>) motivoRechazoService.listByTipoTramiteId(idParaMatchear);
		
		Assert.assertEquals(motivosRespuestaRepositorio, motivosRecibidos);
	}
	
	@Test
	public void testGetById() {
		MotivoRechazo motivoRecibido, motivoFiltrado = new MotivoRechazo();
		Integer idParaMatchear = 1;
		
		for (MotivoRechazo motivo : listaMotivos) {
			if (motivo.getTipoTramite().getId() == idParaMatchear) motivoFiltrado = motivo;
		}
		
		Mockito.when(motivoRechazoRepository.findById(idParaMatchear)).thenReturn(Optional.of(motivoFiltrado));
		
		motivoRecibido = motivoRechazoService.getById(idParaMatchear);
		
		Assert.assertEquals(motivoFiltrado, motivoRecibido);
	}
	
	@Test
	public void testSaveUno() {
		motivoRechazoService.save(listaMotivos.get(0));
		
		ArgumentCaptor<MotivoRechazo> argumentCaptor = ArgumentCaptor.forClass(MotivoRechazo.class);
		Mockito.verify(motivoRechazoRepository).save(argumentCaptor.capture());
		Assert.assertEquals(listaMotivos.get(0), argumentCaptor.getValue());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testSaveLista() {
		motivoRechazoService.save(listaMotivos);
		
		ArgumentCaptor<List<MotivoRechazo>> argumentCaptor = ArgumentCaptor.forClass(List.class);
		Mockito.verify(motivoRechazoRepository).saveAll(argumentCaptor.capture());
		Assert.assertEquals(listaMotivos, argumentCaptor.getValue());
	}
	
	@Test
	public void testDelete() {
		Integer idParaBorrar = 1;
		
		motivoRechazoService.delete(idParaBorrar);
		
		ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);
		Mockito.verify(motivoRechazoRepository).deleteById(argumentCaptor.capture());
		Assert.assertEquals(idParaBorrar, argumentCaptor.getValue());
	}
}
