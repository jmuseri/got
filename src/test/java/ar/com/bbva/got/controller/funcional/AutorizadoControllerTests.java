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
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import ar.com.bbva.got.model.Autorizado;
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
		autorizadosDePrueba.get(0).setTipoDocumento("DNI");
		autorizadosDePrueba.get(0).setNroDocumento("123");
		autorizadosDePrueba.get(0).setNroClienteEmpresa(1);
		autorizadosDePrueba.get(0).setCuitEmpresa("2222");
		
		autorizadosDePrueba.add(new Autorizado());
		autorizadosDePrueba.get(1).setId(2);
		autorizadosDePrueba.get(1).setNombre("hola2");
		autorizadosDePrueba.get(1).setTipoDocumento("DNI");
		autorizadosDePrueba.get(1).setNroDocumento("124");
		autorizadosDePrueba.get(1).setNroClienteEmpresa(2);
		autorizadosDePrueba.get(1).setCuitEmpresa("2222");
		
		autorizadosDePrueba.add(new Autorizado());
		autorizadosDePrueba.get(2).setId(3);
		autorizadosDePrueba.get(2).setNombre("hola3");
		autorizadosDePrueba.get(2).setTipoDocumento("DNI");
		autorizadosDePrueba.get(2).setNroDocumento("125");
		autorizadosDePrueba.get(2).setNroClienteEmpresa(2);
		autorizadosDePrueba.get(2).setCuitEmpresa("3333");
	}
	
	@Test
	public void testListByNroClienteEmpresaOrCuitEmpresa() throws Exception {
		JSONArray autorizadosDePruebaEnJSON = new JSONArray();
		autorizadosDePrueba.forEach((x) -> autorizadosDePruebaEnJSON.put(x.toJSONObject()));
		
		Mockito.when(autorizadoService.listByNroClienteEmpresaOrCuitEmpresa(0,"a")).thenReturn(autorizadosDePrueba);
		
		
		mockMvc.perform(MockMvcRequestBuilders.get("/funcional/autorizado/list?nroClienteEmpresa=0&cuitEmpresa=a").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(autorizadosDePruebaEnJSON.toString()));
	}
	
	@Test
	public void testListByTipoYNroDocumento() throws Exception {
		JSONObject autorizadoDePruebaEnJSON = new JSONObject();
		Autorizado autorizadoFiltrado = new Autorizado();
		String tipoDocumentoFiltro = "DNI";
		String nroDocumentoFiltro = "124";
		
		for(Autorizado aut : autorizadosDePrueba) {
			if (aut.getTipoDocumento().equals(tipoDocumentoFiltro) && aut.getNroDocumento().equals(nroDocumentoFiltro)) {
				autorizadoFiltrado = aut;
				autorizadoDePruebaEnJSON = aut.toJSONObject();
			}
		}
		
		Mockito.when(autorizadoService.getByTipoAndNroDocumento(tipoDocumentoFiltro,nroDocumentoFiltro)).thenReturn(autorizadoFiltrado);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/funcional/autorizado/show/" + tipoDocumentoFiltro + "/" + nroDocumentoFiltro).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(autorizadoDePruebaEnJSON.toString()));
	}
	
	@Test
	public void testAdd() throws Exception {
		JSONArray autorizadosDePruebaEnJSON = new JSONArray();
		autorizadosDePruebaEnJSON.put(autorizadosDePrueba.get(0).toJSONObject());
		
		Mockito.when(autorizadoService.save(autorizadosDePrueba.get(0))).thenReturn(autorizadosDePrueba.get(0));
		
		mockMvc.perform(MockMvcRequestBuilders.post("/funcional/autorizado/add").contentType(MediaType.APPLICATION_JSON).content(autorizadosDePruebaEnJSON.toString()))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
		ArgumentCaptor<Autorizado> argumentCaptor = ArgumentCaptor.forClass(Autorizado.class);
		Mockito.verify(autorizadoService).save(argumentCaptor.capture());
		//Se verifican con nombre y numero de documento como ejemplo, no se puede verificar la igualdad del objeto entero debido a que se instancian nuevas fechas en el proceso de alta
		Assert.assertEquals(autorizadosDePrueba.get(0).getNombre() + autorizadosDePrueba.get(0).getNroDocumento(), argumentCaptor.getValue().getNombre() + argumentCaptor.getValue().getNroDocumento());
	}
	
	@Test
	public void testDelete() throws Exception {
		Autorizado autorizadoParaBorrar = autorizadosDePrueba.get(0);
		
		Mockito.when(autorizadoService.getById(autorizadoParaBorrar.getId())).thenReturn(autorizadoParaBorrar);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/funcional/autorizado/" + autorizadoParaBorrar.getId() + "/delete/").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
		ArgumentCaptor<Autorizado> argumentCaptor = ArgumentCaptor.forClass(Autorizado.class);
		Mockito.verify(autorizadoService).save(argumentCaptor.capture());
		Assert.assertEquals(autorizadoParaBorrar, argumentCaptor.getValue());
	}
	
	@Test
	public void testDeleteInexistente() throws Exception {
		Autorizado autorizadoParaBorrar = autorizadosDePrueba.get(0);
		JSONObject respuesta = new JSONObject();
		respuesta.put("status","error").put("message", "Autorizado not deleted").put("description", "Autorizado no encontrado.");
		
		Mockito.when(autorizadoService.getById(autorizadoParaBorrar.getId())).thenReturn(null);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/funcional/autorizado/" + autorizadoParaBorrar.getId() + "/delete/").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.content().json(respuesta.toString()));
	}
}
