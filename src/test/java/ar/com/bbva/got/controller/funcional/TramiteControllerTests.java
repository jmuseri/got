package ar.com.bbva.got.controller.funcional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
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

import ar.com.bbva.got.dto.AltaTramiteDTO;
import ar.com.bbva.got.dto.CampoDetalleDTO;
import ar.com.bbva.got.dto.SectorDTO;
import ar.com.bbva.got.model.Autorizado;
import ar.com.bbva.got.model.Sector;
import ar.com.bbva.got.model.TipoTramite;
import ar.com.bbva.got.model.TipoTramiteCampo;
import ar.com.bbva.got.model.Tramite;
import ar.com.bbva.got.model.TramiteAutorizado;
import ar.com.bbva.got.model.TramiteDetalle;
import ar.com.bbva.got.service.funcional.AutorizadoService;
import ar.com.bbva.got.service.funcional.AutorizadoServiceImpl;
import ar.com.bbva.got.service.funcional.TipoTramiteComisionService;
import ar.com.bbva.got.service.funcional.TramiteAutorizadoService;
import ar.com.bbva.got.service.funcional.TramiteDetalleService;
import ar.com.bbva.got.service.funcional.TramiteService;
import ar.com.bbva.got.service.parametria.SectorService;
import ar.com.bbva.got.service.parametria.TipoTramiteService;
import junit.framework.Assert;

@RunWith(SpringRunner.class)
@WebMvcTest(TramiteController.class)
public class TramiteControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
    private TipoTramiteService tipoTramiteService;
    
	@MockBean
    private TipoTramiteComisionService tipoTramiteComisionService;

	@MockBean
    private TramiteService tramiteService;
	
	@MockBean
    private AutorizadoService autorizadoService;
	
	@MockBean
    private SectorService sectorService;
	
	@MockBean
    private TramiteAutorizadoService tramiteAutorizadoService;
	
	@MockBean
	private TramiteDetalleService tramiteDetalleService; 
	
	@InjectMocks
	private TramiteController tramiteController;
	
	public static AltaTramiteDTO tramiteDTO = new AltaTramiteDTO();
	public static TipoTramite tipoTramite = new TipoTramite();
	public static SectorDTO sectorDTO = new SectorDTO();
	public static Sector sector = new Sector();
	public static Autorizado autorizado;
	public static List<Autorizado> listaAutorizados = new ArrayList<Autorizado>();
	public static Tramite tramite = new Tramite();
	public static CampoDetalleDTO campoDetalleDTO;
	public static List<CampoDetalleDTO> listaCamposDetalle = new ArrayList<CampoDetalleDTO>();
	public static Set<TipoTramiteCampo> camposTramite = new HashSet<TipoTramiteCampo>();
	public static TipoTramiteCampo tipoTramiteCampo;
	
	@Before
	public void setUp() {
		tramiteDTO.setNroClienteEmpresa(123);
		tramiteDTO.setCuitEmpresa("1234");
		tramiteDTO.setCuentaCobro("1234");
		tramiteDTO.setAreaNegocio(123);
		tramiteDTO.setIdTipoTramite(1);
		
		tipoTramite.setId(1);
		
		sectorDTO.setCanal("canal");
		sectorDTO.setSector("sector");
		
		tramiteDTO.setSectorAlta(sectorDTO);
		
		sector.setDescripcion("descripcionSector");
		
		autorizado = new Autorizado();
		autorizado.setId(1);
		autorizado.setNroClienteEmpresa(123);
		listaAutorizados.add(autorizado);
		
		autorizado = new Autorizado();
		autorizado.setId(2);
		autorizado.setNroClienteEmpresa(123);
		listaAutorizados.add(autorizado);
		
		autorizado = new Autorizado();
		autorizado.setId(3);
		autorizado.setNroClienteEmpresa(123);
		listaAutorizados.add(autorizado);
		
		List<Integer> idAutorizados = new ArrayList<Integer>();
		idAutorizados.add(listaAutorizados.get(0).getId());
		idAutorizados.add(listaAutorizados.get(1).getId());
		idAutorizados.add(listaAutorizados.get(2).getId());
		
		tramiteDTO.setIdAutorizados(idAutorizados);
		
		tramite.setId(1);
		
		campoDetalleDTO = new CampoDetalleDTO();
		campoDetalleDTO.setNombre("campoPrueba");
		listaCamposDetalle.add(campoDetalleDTO);
		
		tramiteDTO.setDetalle(listaCamposDetalle);
		
		tipoTramiteCampo = new TipoTramiteCampo();
		tipoTramiteCampo.setNombre("campoPrueba");
		camposTramite.add(tipoTramiteCampo);
		
		tipoTramite.setCampos(camposTramite);
	}
	
	@Test
	public void testAdd() throws Exception {	
		
		Mockito.when(tipoTramiteService.getById(tramiteDTO.getIdTipoTramite())).thenReturn(tipoTramite);
		Mockito.when(sectorService.getById(tramiteDTO.getSectorAlta().getSector(), tramiteDTO.getSectorAlta().getCanal())).thenReturn(sector);
		Mockito.when(autorizadoService.getById(tramiteDTO.getIdAutorizados().get(0))).thenReturn(listaAutorizados.get(0));
		Mockito.when(autorizadoService.getById(tramiteDTO.getIdAutorizados().get(1))).thenReturn(listaAutorizados.get(1));
		Mockito.when(autorizadoService.getById(tramiteDTO.getIdAutorizados().get(2))).thenReturn(listaAutorizados.get(2));
		Mockito.when(tramiteService.save(Mockito.any(Tramite.class))).thenReturn(tramite);
		Mockito.when(tramiteAutorizadoService.save(Mockito.any(TramiteAutorizado.class))).thenReturn(null);
		Mockito.when(tramiteDetalleService.save(Mockito.any(TramiteDetalle.class))).thenReturn(null);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/funcional/tramite/add").contentType(MediaType.APPLICATION_JSON).content(tramiteDTO.toJSONObject().toString()))
			.andExpect(MockMvcResultMatchers.status().isOk());	
	}
	
	@Test
	public void testAddSinTipoTramite() throws Exception {
		
		Mockito.when(tipoTramiteService.getById(Mockito.any(Integer.class))).thenReturn(null);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/funcional/tramite/add").contentType(MediaType.APPLICATION_JSON).content(tramiteDTO.toJSONObject().toString()))
			.andExpect(MockMvcResultMatchers.status().is5xxServerError());
	}
	
	@Test
	public void testAddSinSector() throws Exception {
		
		Mockito.when(tipoTramiteService.getById(tramiteDTO.getIdTipoTramite())).thenReturn(tipoTramite);
		Mockito.when(sectorService.getById(tramiteDTO.getSectorAlta().getSector(), tramiteDTO.getSectorAlta().getCanal())).thenReturn(null);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/funcional/tramite/add").contentType(MediaType.APPLICATION_JSON).content(tramiteDTO.toJSONObject().toString()))
			.andExpect(MockMvcResultMatchers.status().is5xxServerError());
	}
	
	@Test
	public void testAddSinAutorizado() throws Exception {
		
		Mockito.when(tipoTramiteService.getById(tramiteDTO.getIdTipoTramite())).thenReturn(tipoTramite);
		Mockito.when(sectorService.getById(tramiteDTO.getSectorAlta().getSector(), tramiteDTO.getSectorAlta().getCanal())).thenReturn(sector);
		Mockito.when(autorizadoService.getById(tramiteDTO.getIdAutorizados().get(0))).thenReturn(null);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/funcional/tramite/add").contentType(MediaType.APPLICATION_JSON).content(tramiteDTO.toJSONObject().toString()))
			.andExpect(MockMvcResultMatchers.status().is5xxServerError());
	}
	
	@Test
	public void testAddConAutorizadosMismatched() throws Exception {
		
		listaAutorizados.get(0).setNroClienteEmpresa(1234);
		listaAutorizados.get(1).setNroClienteEmpresa(1234);
		listaAutorizados.get(2).setNroClienteEmpresa(1234);
		
		Mockito.when(tipoTramiteService.getById(tramiteDTO.getIdTipoTramite())).thenReturn(tipoTramite);
		Mockito.when(sectorService.getById(tramiteDTO.getSectorAlta().getSector(), tramiteDTO.getSectorAlta().getCanal())).thenReturn(sector);
		Mockito.when(autorizadoService.getById(tramiteDTO.getIdAutorizados().get(0))).thenReturn(listaAutorizados.get(0));
		Mockito.when(autorizadoService.getById(tramiteDTO.getIdAutorizados().get(1))).thenReturn(listaAutorizados.get(1));
		Mockito.when(autorizadoService.getById(tramiteDTO.getIdAutorizados().get(2))).thenReturn(listaAutorizados.get(2));
		
		mockMvc.perform(MockMvcRequestBuilders.post("/funcional/tramite/add").contentType(MediaType.APPLICATION_JSON).content(tramiteDTO.toJSONObject().toString()))
			.andExpect(MockMvcResultMatchers.status().is5xxServerError());
	}
	
	@Test
	public void testAddSinCamposDetalle() throws Exception {
		
		listaAutorizados.get(0).setNroClienteEmpresa(123);
		listaAutorizados.get(1).setNroClienteEmpresa(123);
		listaAutorizados.get(2).setNroClienteEmpresa(123);
		
		tipoTramiteCampo = new TipoTramiteCampo();
		tipoTramiteCampo.setNombre("adsf");
		camposTramite = new HashSet<TipoTramiteCampo>();
		camposTramite.add(tipoTramiteCampo);
		
		TipoTramite tramiteCuleado = new TipoTramite();
		tramiteCuleado.setId(1);
		tramiteCuleado.setCampos(camposTramite);
		
		Mockito.when(tipoTramiteService.getById(tramiteDTO.getIdTipoTramite())).thenReturn(tramiteCuleado);
		Mockito.when(sectorService.getById(tramiteDTO.getSectorAlta().getSector(), tramiteDTO.getSectorAlta().getCanal())).thenReturn(sector);
		Mockito.when(autorizadoService.getById(tramiteDTO.getIdAutorizados().get(0))).thenReturn(listaAutorizados.get(0));
		Mockito.when(autorizadoService.getById(tramiteDTO.getIdAutorizados().get(1))).thenReturn(listaAutorizados.get(1));
		Mockito.when(autorizadoService.getById(tramiteDTO.getIdAutorizados().get(2))).thenReturn(listaAutorizados.get(2));
		Mockito.when(tramiteService.save(Mockito.any(Tramite.class))).thenReturn(tramite);
		Mockito.when(tramiteAutorizadoService.save(Mockito.any(TramiteAutorizado.class))).thenReturn(null);
		Mockito.when(tramiteDetalleService.save(Mockito.any(TramiteDetalle.class))).thenReturn(null);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/funcional/tramite/add").contentType(MediaType.APPLICATION_JSON).content(tramiteDTO.toJSONObject().toString()))
			.andExpect(MockMvcResultMatchers.status().is5xxServerError());
	}
}
