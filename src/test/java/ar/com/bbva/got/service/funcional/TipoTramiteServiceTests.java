package ar.com.bbva.got.service.funcional;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ar.com.bbva.got.model.TipoTramiteComision;
import ar.com.bbva.got.model.TipoTramiteComisionKey;
import ar.com.bbva.got.repository.TipoTramiteComisionRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TipoTramiteServiceTests {

	@Mock
	private TipoTramiteComisionRepository tipoTramiteComisionRepository;
	
	@InjectMocks
	private TipoTramiteComisionServiceImpl tipoTramiteService;
	
	private static List<TipoTramiteComision> listaTipoTramiteComision = new ArrayList<TipoTramiteComision>();
	
	@BeforeClass
	public static void setUp() {
		listaTipoTramiteComision.add(new TipoTramiteComision());
		listaTipoTramiteComision.get(0).setId(new TipoTramiteComisionKey());
		listaTipoTramiteComision.get(0).getId().setTipoTramiteId(1);
		
		listaTipoTramiteComision.add(new TipoTramiteComision());
		listaTipoTramiteComision.get(1).setId(new TipoTramiteComisionKey());
		listaTipoTramiteComision.get(1).getId().setTipoTramiteId(2);
	}
	
	@Test
	public void testGetById() {
		TipoTramiteComision tramiteRespuestaRepositorio = new TipoTramiteComision();
		TipoTramiteComisionKey idParaMatchear = new TipoTramiteComisionKey();
		idParaMatchear.setTipoTramiteId(1);
		TipoTramiteComision tramiteRecibido;
		
		for(TipoTramiteComision tipoTramite : listaTipoTramiteComision) {
			if (tipoTramite.getId().getTipoTramiteId() == idParaMatchear.getTipoTramiteId()) tramiteRespuestaRepositorio = tipoTramite;
		}
		
		Mockito.when(tipoTramiteComisionRepository.findById(idParaMatchear)).thenReturn(tramiteRespuestaRepositorio);
		
		tramiteRecibido = tipoTramiteService.getById(idParaMatchear);
		
		Assert.assertEquals(tramiteRespuestaRepositorio, tramiteRecibido);
	}
	
}
