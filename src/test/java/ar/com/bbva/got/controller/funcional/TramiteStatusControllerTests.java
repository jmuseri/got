package ar.com.bbva.got.controller.funcional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import ar.com.bbva.got.model.Tramite;
import ar.com.bbva.got.model.TramiteDetalle;
import ar.com.bbva.got.service.funcional.MotivoRechazoService;
import ar.com.bbva.got.service.funcional.TramiteService;
@RunWith(SpringRunner.class)
@WebMvcTest(TramiteStatusController.class)
public class TramiteStatusControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
    private TramiteService tramiteService;
	
	@MockBean
	private MotivoRechazoService motivoRechazoService;
	
	@InjectMocks
	private TramiteStatusController tramiteStatusController;
	
	public static Tramite tramite = new Tramite();
	
	@Before
	public void setUp() {
		tramite.setId(0);
	}
	
	@Test
	public void gestionarTramiteTest() throws Exception {
		Integer id = 0;
		
		Mockito.when(tramiteService.getById(id)).thenReturn(tramite);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/funcional/tramite/" + id.toString() + "/gestionar").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
		ArgumentCaptor<Tramite> argumentCaptor = ArgumentCaptor.forClass(Tramite.class);
		
		Mockito.verify(tramiteService).getById(id);
		Mockito.verify(tramiteService).save(argumentCaptor.capture());
		
		
	}
}
