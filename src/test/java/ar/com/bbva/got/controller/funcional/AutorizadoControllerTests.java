package ar.com.bbva.got.controller.funcional;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import ar.com.bbva.got.model.Autorizado;
import ar.com.bbva.got.service.funcional.AutorizadoService;
import ar.com.bbva.got.service.funcional.AutorizadoServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(AutorizadoController.class)
public class AutorizadoControllerTests {

	private static List<Autorizado> autorizadosDePrueba = new ArrayList<Autorizado>();
    
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AutorizadoServiceImpl autorizadoService;
	
	@InjectMocks
	private AutorizadoController autorizadoController;
	
	@BeforeClass
	public static void setUp() {
		autorizadosDePrueba.add(new Autorizado());
		autorizadosDePrueba.get(0).setId(1);
		autorizadosDePrueba.get(0).setNombre("hola1");
		autorizadosDePrueba.get(0).setNroDocumento("123");
		autorizadosDePrueba.get(0).setNroClienteEmpresa(1);
		autorizadosDePrueba.get(0).setCuitEmpresa("2222");
		
		autorizadosDePrueba.add(new Autorizado());
		autorizadosDePrueba.get(1).setId(2);
		autorizadosDePrueba.get(1).setNombre("hola2");
		autorizadosDePrueba.get(1).setNroDocumento("124");
		autorizadosDePrueba.get(1).setNroClienteEmpresa(2);
		autorizadosDePrueba.get(1).setCuitEmpresa("2222");
		
		autorizadosDePrueba.add(new Autorizado());
		autorizadosDePrueba.get(2).setId(3);
		autorizadosDePrueba.get(2).setNombre("hola3");
		autorizadosDePrueba.get(2).setNroDocumento("125");
		autorizadosDePrueba.get(2).setNroClienteEmpresa(2);
		autorizadosDePrueba.get(2).setCuitEmpresa("3333");
	}
	
	@Test
	public void testListAll() throws Exception {
		JSONArray autorizadosDePruebaEnJSON = new JSONArray();
		autorizadosDePrueba.forEach((x) -> autorizadosDePruebaEnJSON.put(x.toJSONObject()));
		
		Mockito.when(autorizadoService.listAll()).thenReturn(autorizadosDePrueba);
		
		
		mockMvc.perform(MockMvcRequestBuilders.get("/funcional/autorizado/list").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(autorizadosDePruebaEnJSON.toString()));
	}
	
	@Test
	public void testListByNroClienteEmpresa() throws Exception {
		JSONArray autorizadosDePruebaEnJSON = new JSONArray();
		List<Autorizado> autorizadosFiltrados = new ArrayList<Autorizado>();
		Integer nroClienteFiltro = 2;
		
		autorizadosDePrueba.forEach((x) -> {
			if (x.getNroClienteEmpresa() == nroClienteFiltro) {
				autorizadosDePruebaEnJSON.put(x.toJSONObject());
				autorizadosFiltrados.add(x);
			}
		});
		
		Mockito.when(autorizadoService.listByNroClienteEmpresa(nroClienteFiltro)).thenReturn(autorizadosFiltrados);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/funcional/autorizado/list?cliente=" + nroClienteFiltro).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(autorizadosDePruebaEnJSON.toString()));
	}
	
	@Test
	public void testListException() throws Exception {
		Mockito.when(autorizadoService.listByNroClienteEmpresa(1)).thenThrow(RuntimeException.class);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/funcional/autorizado/list?cliente=1").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is5xxServerError());
	}
	
	@Test
	public void testAdd() throws Exception {
		Autorizado autorizado = new Autorizado();
		autorizado.setNombre("pepito");
		
		Mockito.when(autorizadoService.save(autorizado)).thenReturn(autorizado);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/funcional/autorizado/add").contentType(MediaType.APPLICATION_JSON).content(autorizado.toJSONObject().toString()))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
		ArgumentCaptor<Autorizado> argumentCaptor = ArgumentCaptor.forClass(Autorizado.class);
		Mockito.verify(autorizadoService).save(argumentCaptor.capture());
		Assert.assertEquals(autorizado, argumentCaptor.getValue());
	}
	
	@Test
	public void testAddException() throws Exception {
		Autorizado autorizado = new Autorizado();
		autorizado.setNombre("pepito");
		
		Mockito.when(autorizadoService.save(autorizado)).thenThrow(RuntimeException.class);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/funcional/autorizado/add").contentType(MediaType.APPLICATION_JSON).content(autorizado.toJSONObject().toString()))
				.andExpect(MockMvcResultMatchers.status().is5xxServerError());
		
		ArgumentCaptor<Autorizado> argumentCaptor = ArgumentCaptor.forClass(Autorizado.class);
		Mockito.verify(autorizadoService).save(argumentCaptor.capture());
		Assert.assertEquals(autorizado, argumentCaptor.getValue());
	}
	
	@Test
	public void testUpdateNull() throws Exception {
		Autorizado autorizado = new Autorizado();
		
		mockMvc.perform(MockMvcRequestBuilders.post("/funcional/autorizado/add/" + autorizado.getId().toString()).contentType(MediaType.APPLICATION_JSON).content(autorizado.toJSONObject().toString()))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
