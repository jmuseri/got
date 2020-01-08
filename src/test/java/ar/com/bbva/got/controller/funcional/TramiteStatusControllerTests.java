package ar.com.bbva.got.controller.funcional;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
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

import ar.com.bbva.got.model.MotivoRechazo;
import ar.com.bbva.got.model.Tramite;
import ar.com.bbva.got.model.TramiteAutorizado;
import ar.com.bbva.got.model.TramiteAutorizadoKey;
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
	
	public static MotivoRechazo motivoRechazo = new MotivoRechazo();
	
	@Before
	public void setUp() {	
		
		Set<TramiteAutorizado> autorizados = new HashSet<TramiteAutorizado>();
		
		TramiteAutorizado autorizado1 = new TramiteAutorizado();
		TramiteAutorizadoKey id1 = new TramiteAutorizadoKey();
		id1.setAutorizadoId(1);
		autorizado1.setId(id1);
		
		TramiteAutorizado autorizado2 = new TramiteAutorizado();
		TramiteAutorizadoKey id2 = new TramiteAutorizadoKey();
		id2.setAutorizadoId(2);
		autorizado2.setId(id2);
		
		TramiteAutorizado autorizado3 = new TramiteAutorizado();
		TramiteAutorizadoKey id3 = new TramiteAutorizadoKey();
		id3.setAutorizadoId(3);
		autorizado3.setId(id3);
				
		autorizados.add(autorizado1);
		autorizados.add(autorizado2);
		autorizados.add(autorizado3);		
		
		tramite.setId(0);
		tramite.setUsuModif("Ana");
		tramite.setAutorizado(autorizados);
				
	}
	
	@Test
	public void gestionarTramiteTest() throws Exception {
		Integer id = 0;
		
		Mockito.when(tramiteService.getById(id)).thenReturn(tramite);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/funcional/tramite/" + id.toString() + "/gestionar").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
		ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);
		ArgumentCaptor<Tramite> argumentCaptor2 = ArgumentCaptor.forClass(Tramite.class);
		
		Mockito.verify(tramiteService).getById(argumentCaptor.capture());
		Assert.assertEquals(id, argumentCaptor.getValue());
		Mockito.verify(tramiteService).save(argumentCaptor2.capture());	
		
	}
	
	@Test
	public void gestionarTramiteExceptionTest() throws Exception {
		
		Integer id = 0;
		
		Mockito.when(tramiteService.getById(Mockito.any(Integer.class))).thenThrow(NullPointerException.class);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/funcional/tramite/" + id.toString() + "/gestionar").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is5xxServerError());

	}
	
	@Test
	public void finalizarTramiteTest() throws Exception {
		Integer id = 0;
		
		Mockito.when(tramiteService.getById(id)).thenReturn(tramite);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/funcional/tramite/" + id.toString() + "/finalizar?usuario=Ana").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
		ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);
		ArgumentCaptor<Tramite> argumentCaptor2 = ArgumentCaptor.forClass(Tramite.class);
		
		Mockito.verify(tramiteService).getById(argumentCaptor.capture());
		Assert.assertEquals(id, argumentCaptor.getValue());
		Mockito.verify(tramiteService).save(argumentCaptor2.capture());	
	}
	
	@Test
	public void finalizarTramiteUsuarioNullTest() throws Exception {
		Integer id = 0;
		
		Mockito.when(tramiteService.getById(id)).thenReturn(tramite);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/funcional/tramite/" + id.toString() + "/finalizar").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}
	
	@Test
	public void finalizarTramiteUsuarioInvalidoTest() throws Exception {
		Integer id = 0;
		
		Mockito.when(tramiteService.getById(id)).thenReturn(tramite);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/funcional/tramite/" + id.toString() + "/finalizar?usuario=Pepe").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}
	
	@Test
	public void finalizarTramiteExceptionTest() throws Exception {
		Integer id = 0;
		
		Mockito.when(tramiteService.getById(id)).thenReturn(tramite);
		
		Mockito.when(tramiteService.save(Mockito.any(Tramite.class))).thenThrow(NullPointerException.class);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/funcional/tramite/" + id.toString() + "/finalizar?usuario=Ana").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is5xxServerError());
	}
	
	@Test
	public void finalizarTramiteConAutorizadoTest() throws Exception {
		Integer id = 0;
		
		Mockito.when(tramiteService.getById(id)).thenReturn(tramite);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/funcional/tramite/" + id.toString() + "/finalizar?usuario=Ana&idAutorizado=1").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
		ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);
		ArgumentCaptor<Tramite> argumentCaptor2 = ArgumentCaptor.forClass(Tramite.class);
		
		Mockito.verify(tramiteService).getById(argumentCaptor.capture());
		Assert.assertEquals(id, argumentCaptor.getValue());
		Mockito.verify(tramiteService).save(argumentCaptor2.capture());	
	}
	
	@Test
	public void cancelarTramiteTest() throws Exception {
		Integer id = 0;
		String usuario = "Ana";
		
		Mockito.when(tramiteService.getById(id)).thenReturn(tramite);
		
		ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);
		ArgumentCaptor<Tramite> argumentCaptor2 = ArgumentCaptor.forClass(Tramite.class);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/funcional/tramite/" + id.toString() + "/cancelar?usuario=" + usuario).accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
		Mockito.verify(tramiteService).getById(argumentCaptor.capture());
		Assert.assertEquals(id, argumentCaptor.getValue());
		Mockito.verify(tramiteService).save(argumentCaptor2.capture());
		Assert.assertEquals(usuario, argumentCaptor2.getValue().getUsuModif());
	}
	
	@Test
	public void cancelarTramiteExceptionTest() throws Exception {
		Integer id = 0;
		String usuario = "Ana";
		
		Mockito.when(tramiteService.getById(id)).thenReturn(null);
		
		Mockito.when(tramiteService.save(Mockito.any(Tramite.class))).thenThrow(NullPointerException.class);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/funcional/tramite/" + id.toString() + "/cancelar?usuario=" + usuario).accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is5xxServerError());
	}
	
	@Test
	public void rechazarTramiteTest() throws Exception {
		Integer id = 0;
		Integer motivoRechazoId = 0;
		String usuario = "Ana";
		
		Mockito.when(tramiteService.getById(id)).thenReturn(tramite);
		Mockito.when(motivoRechazoService.getById(motivoRechazoId)).thenReturn(motivoRechazo);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/funcional/tramite/" + id.toString() + "/rechazar?motivoRechazoId=" + motivoRechazoId.toString() + "&usuario=" + usuario).accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
		ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);
		ArgumentCaptor<Integer> argumentCaptor2 = ArgumentCaptor.forClass(Integer.class);		
		ArgumentCaptor<Tramite> argumentCaptor3 = ArgumentCaptor.forClass(Tramite.class);
		
		
		Mockito.verify(tramiteService).getById(argumentCaptor.capture());
		Assert.assertEquals(id, argumentCaptor.getValue());
		Mockito.verify(motivoRechazoService).getById(argumentCaptor2.capture());
		Assert.assertEquals(motivoRechazoId, argumentCaptor.getValue());
		Mockito.verify(tramiteService).save(argumentCaptor3.capture());
		Assert.assertEquals(usuario, argumentCaptor3.getValue().getUsuModif());
		Assert.assertEquals(motivoRechazo, argumentCaptor3.getValue().getMotivoRechazo());
	}
	
	@Test
	public void rechazarTramiteSinMotivoRechazoTest() throws Exception {
		Integer id = 0;
		String usuario = "Ana";
		
		Mockito.when(tramiteService.getById(id)).thenReturn(tramite);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/funcional/tramite/" + id.toString() + "/rechazar?usuario=" + usuario).accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
		ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);	
		ArgumentCaptor<Tramite> argumentCaptor2 = ArgumentCaptor.forClass(Tramite.class);
		
		
		Mockito.verify(tramiteService).getById(argumentCaptor.capture());
		Assert.assertEquals(id, argumentCaptor.getValue());
		Mockito.verify(tramiteService).save(argumentCaptor2.capture());
		Assert.assertEquals(usuario, argumentCaptor2.getValue().getUsuModif());
		Assert.assertEquals(null, argumentCaptor2.getValue().getMotivoRechazo());
		Mockito.verify(motivoRechazoService, Mockito.times(0)).getById(Mockito.any());
	}
	
	@Test
	public void rechazarTramiteExceptionTest() throws Exception {
		Integer id = 0;
		String usuario = "Ana";
		
		Mockito.when(tramiteService.getById(id)).thenReturn(null);
		
		Mockito.when(tramiteService.save(Mockito.any(Tramite.class))).thenThrow(NullPointerException.class);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/funcional/tramite/" + id.toString() + "/rechazar?usuario=" + usuario).accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is5xxServerError());
	}
	
	@Test
	public void activarTramiteTest() throws Exception {
		Integer id = 0;
		String usuario = "Ana";
		
		Mockito.when(tramiteService.getById(id)).thenReturn(tramite);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/funcional/tramite/" + id.toString() + "/activar?usuario=" + usuario).accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
		ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);	
		ArgumentCaptor<Tramite> argumentCaptor2 = ArgumentCaptor.forClass(Tramite.class);
		
		
		Mockito.verify(tramiteService).getById(argumentCaptor.capture());
		Assert.assertEquals(id, argumentCaptor.getValue());
		Mockito.verify(tramiteService).save(argumentCaptor2.capture());
		Assert.assertEquals(usuario, argumentCaptor2.getValue().getUsuModif());
	}
	
	@Test
	public void activarTramiteExceptionTest() throws Exception {
		Integer id = 0;
		String usuario = "Ana";
		
		Mockito.when(tramiteService.getById(id)).thenReturn(null);
		
		Mockito.when(tramiteService.save(Mockito.any(Tramite.class))).thenThrow(NullPointerException.class);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/funcional/tramite/" + id.toString() + "/activar?usuario=" + usuario).accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is5xxServerError());
	}
	
	@Test
	public void deleteTramiteTest() throws Exception {
		Integer id = 0;
		
		mockMvc.perform(MockMvcRequestBuilders.post("/funcional/tramite/" + id.toString() + "/eliminar").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
		ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);	
				
		Mockito.verify(tramiteService).delete(argumentCaptor.capture());
		Assert.assertEquals(id, argumentCaptor.getValue());

	}
	
	@Test
	public void deleteTramiteExceptionTest() throws Exception {
		Integer id = 0;
		
		Mockito.doThrow(NullPointerException.class).when(tramiteService).delete(id);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/funcional/tramite/" + id.toString() + "/eliminar").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is5xxServerError());
	}
}
