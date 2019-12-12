package ar.com.bbva.got.controller.funcional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
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
import ar.com.bbva.got.dto.TramiteDetalleDTO;
import ar.com.bbva.got.model.Autorizado;
import ar.com.bbva.got.model.CampoDisponible;
import ar.com.bbva.got.model.Sector;
import ar.com.bbva.got.model.TipoTramite;
import ar.com.bbva.got.model.TipoTramiteCampo;
import ar.com.bbva.got.model.TipoTramiteCampoKey;
import ar.com.bbva.got.model.TipoTramiteComisionKey;
import ar.com.bbva.got.model.Tramite;
import ar.com.bbva.got.model.TramiteAutorizado;
import ar.com.bbva.got.model.TramiteAutorizadoKey;
import ar.com.bbva.got.model.TramiteDetalle;
import ar.com.bbva.got.model.TramiteDetalleKey;
import ar.com.bbva.got.service.funcional.AutorizadoService;
import ar.com.bbva.got.service.funcional.TipoTramiteComisionService;
import ar.com.bbva.got.service.funcional.TramiteAutorizadoService;
import ar.com.bbva.got.service.funcional.TramiteDetalleService;
import ar.com.bbva.got.service.funcional.TramiteService;
import ar.com.bbva.got.service.parametria.SectorService;
import ar.com.bbva.got.service.parametria.TipoTramiteService;

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
	public static Set<TramiteAutorizado> listaTramiteAutorizados = new HashSet<TramiteAutorizado>();
	public static TramiteAutorizado tramiteAutorizado = new TramiteAutorizado();
	public static Set<TramiteDetalle> listaTramiteDetalle = new HashSet<TramiteDetalle>();
	public static TramiteDetalle tramiteDetalle = new TramiteDetalle();
	public static TramiteDetalleDTO tramiteDetalleDTO = new TramiteDetalleDTO();
	public static TramiteDetalleDTO tramiteDetalleDTOLleno = new TramiteDetalleDTO();
	
	@Before
	public void setUp() {
		tramiteDTO.setNroClienteEmpresa(123);
		tramiteDTO.setCuitEmpresa("1234");
		tramiteDTO.setCuentaCobro("1234");
		tramiteDTO.setAreaNegocio(123);
		tramiteDTO.setIdTipoTramite(1);
		
		tipoTramite.setId(1);
		tipoTramite.setDescripcion("asdf");
		
		sectorDTO.setCanal("canal");
		sectorDTO.setSector("sector");
		
		tramiteDTO.setSectorAlta(sectorDTO);
		
		sector.setDescripcion("descripcionSector");
		
		tramiteAutorizado = new TramiteAutorizado();
		tramiteAutorizado.setId(new TramiteAutorizadoKey(1,1));

		autorizado = new Autorizado();
		autorizado.setId(1);
		autorizado.setNroClienteEmpresa(123);
		listaAutorizados.add(autorizado);
		
		tramiteAutorizado.setAutorizado(autorizado);
		listaTramiteAutorizados.add(tramiteAutorizado);
		
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
		tipoTramiteCampo.setId(new TipoTramiteCampoKey(1,"1"));
		tipoTramiteCampo.setNombre("campoPrueba");
		tipoTramiteCampo.setCampoDisponible(new CampoDisponible());
		camposTramite.add(tipoTramiteCampo);
		
		tipoTramite.setCampos(camposTramite);
		
		tramiteDetalle = new TramiteDetalle();
		tramiteDetalle.setId(new TramiteDetalleKey(1,1,"1"));
		
		listaTramiteDetalle.add(tramiteDetalle);
		
		tramiteDetalleDTO.setTramiteId(1);
		tramiteDetalleDTO.setIdTipoTramite(1);
		tramiteDetalleDTO.setCampoDisponibleId("asdf");		
		tramiteDetalleDTOLleno.setTramiteId(1);
		tramiteDetalleDTOLleno.setIdTipoTramite(1);
		tramiteDetalleDTOLleno.setCampoDisponibleId("asdf");	
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
		
		TipoTramite tramiteModificado = new TipoTramite();
		tramiteModificado.setId(1);
		tramiteModificado.setCampos(camposTramite);
		
		Mockito.when(tipoTramiteService.getById(tramiteDTO.getIdTipoTramite())).thenReturn(tramiteModificado);
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
	
	@Test
	public void testAddExcepcion() throws Exception {
		
		Mockito.when(tipoTramiteService.getById(tramiteDTO.getIdTipoTramite())).thenReturn(tipoTramite);
		Mockito.when(sectorService.getById(tramiteDTO.getSectorAlta().getSector(), tramiteDTO.getSectorAlta().getCanal())).thenReturn(sector);
		Mockito.when(autorizadoService.getById(tramiteDTO.getIdAutorizados().get(0))).thenReturn(listaAutorizados.get(0));
		Mockito.when(autorizadoService.getById(tramiteDTO.getIdAutorizados().get(1))).thenReturn(listaAutorizados.get(1));
		Mockito.when(autorizadoService.getById(tramiteDTO.getIdAutorizados().get(2))).thenReturn(listaAutorizados.get(2));
		Mockito.when(tramiteService.save(Mockito.any(Tramite.class))).thenThrow(NullPointerException.class);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/funcional/tramite/add").contentType(MediaType.APPLICATION_JSON).content(tramiteDTO.toJSONObject().toString()))
		.andExpect(MockMvcResultMatchers.status().is5xxServerError());
	}
	
	@Test
	public void testGetTramite() throws Exception {
		
		BeanUtils.copyProperties(tramiteDTO, tramite);
		
		tramite.setTipoTramite(tipoTramite);
		tramite.setAutorizado(listaTramiteAutorizados);
		tramite.setDetalle(listaTramiteDetalle);
		tramite.getTipoTramite().setCampos(camposTramite);
		
		Mockito.when(tramiteService.getById(Mockito.any(Integer.class))).thenReturn(tramite);
		Mockito.when(tipoTramiteComisionService.getById(Mockito.any(TipoTramiteComisionKey.class))).thenReturn(null);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/funcional/tramite/" + tramite.getId().toString()).accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
		ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);
		Mockito.verify(tramiteService).getById(argumentCaptor.capture());
		Assert.assertEquals(tramite.getId(), argumentCaptor.getValue());
		
		ArgumentCaptor<TipoTramiteComisionKey> argumentCaptor2 = ArgumentCaptor.forClass(TipoTramiteComisionKey.class);
		Mockito.verify(tipoTramiteComisionService).getById(argumentCaptor2.capture());
		Assert.assertEquals(tramite.getAreaNegocio(), argumentCaptor2.getValue().getAreaNegocio());
	}
	
	@Test
	public void testGetTramiteException() throws Exception {
		
		Mockito.when(tramiteService.getById(Mockito.any(Integer.class))).thenThrow(NullPointerException.class);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/funcional/tramite/" + tramite.getId().toString()).accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is5xxServerError());
	}
	
	@Test
	public void testListTramitesByCliente() throws Exception {
		
		BeanUtils.copyProperties(tramiteDTO, tramite);
		
		tramite.setTipoTramite(tipoTramite);
		tramite.setAutorizado(listaTramiteAutorizados);
		tramite.setDetalle(listaTramiteDetalle);
		tramite.getTipoTramite().setCampos(camposTramite);
		
		List<Tramite> listaTramites = new ArrayList<Tramite>();
		listaTramites.add(tramite);
		
		Mockito.when(tramiteService.listByEmpresaEstadoAndTipoTramiteAndSectorInicio(Mockito.any(Integer.class),Mockito.any(String.class),Mockito.any(Integer.class),Mockito.any(String.class)))
		.thenReturn(listaTramites);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/funcional/tramite/list/" + tramite.getNroClienteEmpresa() + "?estadoTramite=a&idTipoTramite=1&sectorInicioId=a").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testListTramitesException() throws Exception {
		
		BeanUtils.copyProperties(tramiteDTO, tramite);
		
		tramite.setTipoTramite(tipoTramite);
		tramite.setAutorizado(listaTramiteAutorizados);
		tramite.setDetalle(listaTramiteDetalle);
		tramite.getTipoTramite().setCampos(camposTramite);
		
		Mockito.when(tramiteService.listByEmpresaEstadoAndTipoTramiteAndSectorInicio(Mockito.any(Integer.class),Mockito.any(String.class),Mockito.any(Integer.class),Mockito.any(String.class)))
		.thenThrow(NullPointerException.class);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/funcional/tramite/list/" + tramite.getNroClienteEmpresa() + "?estadoTramite=a&idTipoTramite=1&sectorInicioId=a").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is5xxServerError());
	}
	
	@Test
	public void testListByCuit() throws Exception {
		
		BeanUtils.copyProperties(tramiteDTO, tramite);
		
		tramite.setTipoTramite(tipoTramite);
		tramite.setAutorizado(listaTramiteAutorizados);
		tramite.setDetalle(listaTramiteDetalle);
		tramite.getTipoTramite().setCampos(camposTramite);
		
		List<Tramite> listaTramites = new ArrayList<Tramite>();
		listaTramites.add(tramite);
		
		Mockito.when(tramiteService.listByCuitEmpresaEstadoAndTipoTramiteAndSectorInicio(Mockito.any(String.class),Mockito.any(String.class),Mockito.any(Integer.class),Mockito.any(String.class)))
		.thenReturn(listaTramites);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/funcional/tramite/listByCuit/" + tramite.getCuitEmpresa() + "?estadoTramite=a&idTipoTramite=1&sectorInicioId=a").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testListByCuitException() throws Exception {
		BeanUtils.copyProperties(tramiteDTO, tramite);
		
		tramite.setTipoTramite(tipoTramite);
		tramite.setAutorizado(listaTramiteAutorizados);
		tramite.setDetalle(listaTramiteDetalle);
		tramite.getTipoTramite().setCampos(camposTramite);
		
		Mockito.when(tramiteService.listByCuitEmpresaEstadoAndTipoTramiteAndSectorInicio(Mockito.any(String.class),Mockito.any(String.class),Mockito.any(Integer.class),Mockito.any(String.class)))
		.thenThrow(NullPointerException.class);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/funcional/tramite/listByCuit/" + tramite.getCuitEmpresa() + "?estadoTramite=a&idTipoTramite=1&sectorInicioId=a").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is5xxServerError());
	}
	
	@Test
	public void testListyByAutorizado() throws Exception {
		
		BeanUtils.copyProperties(tramiteDTO, tramite);
		
		tramite.setTipoTramite(tipoTramite);
		tramite.setAutorizado(listaTramiteAutorizados);
		tramite.setDetalle(listaTramiteDetalle);
		tramite.getTipoTramite().setCampos(camposTramite);
		
		List<Tramite> listaTramites = new ArrayList<Tramite>();
		listaTramites.add(tramite);
		
		Mockito.when(tramiteService.buscarTramites(Mockito.any(String.class),Mockito.any(String.class),Mockito.any(Integer.class),Mockito.any(String.class),Mockito.any(String.class),Mockito.any(String.class)))
		.thenReturn(listaTramites);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/funcional/tramite/listByAutorizado/" + "DNI" + "/" + 123 + "?cuit=123&estadoTramite=a&idTipoTramite=1&sector=a").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testListByAutorizadoException() throws Exception {
		BeanUtils.copyProperties(tramiteDTO, tramite);
		
		tramite.setTipoTramite(tipoTramite);
		tramite.setAutorizado(listaTramiteAutorizados);
		tramite.setDetalle(listaTramiteDetalle);
		tramite.getTipoTramite().setCampos(camposTramite);
		
		Mockito.when(tramiteService.buscarTramites(Mockito.any(String.class),Mockito.any(String.class),Mockito.any(Integer.class),Mockito.any(String.class),Mockito.any(String.class),Mockito.any(String.class)))
		.thenThrow(NullPointerException.class);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/funcional/tramite/listByAutorizado/" + "DNI" + "/" + 123 + "?cuit=123&estadoTramite=a&idTipoTramite=1&sector=a").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is5xxServerError());
	}
	
	@Test
	public void testListByUsuario() throws Exception {
		
		BeanUtils.copyProperties(tramiteDTO, tramite);
		
		tramite.setTipoTramite(tipoTramite);
		tramite.setAutorizado(listaTramiteAutorizados);
		tramite.setDetalle(listaTramiteDetalle);
		tramite.getTipoTramite().setCampos(camposTramite);
		
		List<Tramite> listaTramites = new ArrayList<Tramite>();
		listaTramites.add(tramite);
		
		Mockito.when(tramiteService.buscarTramites(Mockito.any(String.class),Mockito.any(String.class),Mockito.any(String.class),Mockito.any(Integer.class),Mockito.any(String.class),Mockito.any(String.class),Mockito.any(String.class)))
		.thenReturn(listaTramites);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/funcional/tramite/listByUsuario/" + "usuario1" + "?numeroDocumentoAutorizado=123&tipoDocumentoAutorizado=DNI&cuit=123&estadoTramite=a&idTipoTramite=1&sector=a").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testListByUsuarioException() throws Exception {
		BeanUtils.copyProperties(tramiteDTO, tramite);
		
		tramite.setTipoTramite(tipoTramite);
		tramite.setAutorizado(listaTramiteAutorizados);
		tramite.setDetalle(listaTramiteDetalle);
		tramite.getTipoTramite().setCampos(camposTramite);
		
		Mockito.when(tramiteService.buscarTramites(Mockito.any(String.class),Mockito.any(String.class),Mockito.any(String.class),Mockito.any(Integer.class),Mockito.any(String.class),Mockito.any(String.class),Mockito.any(String.class)))
		.thenThrow(NullPointerException.class);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/funcional/tramite/listByUsuario/" + "usuario1" + "?numeroDocumentoAutorizado=123&tipoDocumentoAutorizado=DNI&cuit=123&estadoTramite=a&idTipoTramite=1&sector=a").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is5xxServerError());
	}
	
	@Test
	public void testAddTramiteDetalle() throws Exception {
		
		Mockito.when(tramiteDetalleService.save(Mockito.any(TramiteDetalle.class))).thenReturn(null);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/funcional/tramite/detalle/add").contentType(MediaType.APPLICATION_JSON).content(tramiteDetalleDTO.toJSONObject().toString()))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
		ArgumentCaptor<TramiteDetalle> argumentCaptor = ArgumentCaptor.forClass(TramiteDetalle.class);
		Mockito.verify(tramiteDetalleService).save(argumentCaptor.capture());
		Assert.assertEquals(tramiteDetalleDTO.getTramiteId(), argumentCaptor.getValue().getId().getTramiteId());
		Assert.assertEquals(tramiteDetalleDTO.getIdTipoTramite(), argumentCaptor.getValue().getId().getTipoTramiteCampoId().getTipoTramiteId());
		Assert.assertEquals(tramiteDetalleDTO.getCampoDisponibleId(), argumentCaptor.getValue().getId().getTipoTramiteCampoId().getCampoDisponibleId());
	}
	
	@Test
	public void testAddTramiteDetalleException() throws Exception {
		Mockito.when(tramiteDetalleService.save(Mockito.any(TramiteDetalle.class))).thenThrow(NullPointerException.class);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/funcional/tramite/detalle/add").contentType(MediaType.APPLICATION_JSON).content(tramiteDetalleDTO.toJSONObject().toString()))
		.andExpect(MockMvcResultMatchers.status().is5xxServerError());
	}
	
	@Test
	public void testUpdateTramiteDetalleVacio() throws Exception {
		
		Integer tramiteId = 1;
		Integer tipoTramiteCampoId = 1;
		String campoDisponibleId = "asdf";
		
		Mockito.when(tramiteDetalleService.getById(Mockito.any(TramiteDetalleKey.class))).thenReturn(new TramiteDetalle());
		
		mockMvc.perform(MockMvcRequestBuilders.post("/funcional/tramite/detalle/update/" + tramiteId.toString() + "/" + tipoTramiteCampoId.toString() + "/" + campoDisponibleId).contentType(MediaType.APPLICATION_JSON).content(tramiteDetalleDTO.toJSONObject().toString()))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
		ArgumentCaptor<TramiteDetalleKey> argumentCaptor = ArgumentCaptor.forClass(TramiteDetalleKey.class);
		Mockito.verify(tramiteDetalleService).getById(argumentCaptor.capture());
		Assert.assertEquals(tramiteId, argumentCaptor.getValue().getTramiteId());
		Assert.assertEquals(tipoTramiteCampoId, argumentCaptor.getValue().getTipoTramiteCampoId().getTipoTramiteId());
		Assert.assertEquals(campoDisponibleId, argumentCaptor.getValue().getTipoTramiteCampoId().getCampoDisponibleId());
		
		ArgumentCaptor<TramiteDetalle> argumentCaptor2 = ArgumentCaptor.forClass(TramiteDetalle.class);
		Mockito.verify(tramiteDetalleService).save(argumentCaptor2.capture());
		Assert.assertEquals(null, argumentCaptor2.getValue().getValor());
		Assert.assertEquals("system", argumentCaptor2.getValue().getUsuModif());
	}
	
	@Test
	public void testUpdateTramiteDetalleLleno() throws Exception {
		
		Integer tramiteId = 1;
		Integer tipoTramiteCampoId = 1;
		String campoDisponibleId = "asdf";
		
		tramiteDetalleDTOLleno.setValor("valor");
		tramiteDetalleDTOLleno.setUsuModif("usuario1");
		tramiteDetalleDTOLleno.setFechaAlta(new Date());
		tramiteDetalleDTOLleno.setFechaModif(new Date());
		
		Mockito.when(tramiteDetalleService.getById(Mockito.any(TramiteDetalleKey.class))).thenReturn(new TramiteDetalle());
		
		mockMvc.perform(MockMvcRequestBuilders.post("/funcional/tramite/detalle/update/" + tramiteId.toString() + "/" + tipoTramiteCampoId.toString() + "/" + campoDisponibleId).contentType(MediaType.APPLICATION_JSON).content(tramiteDetalleDTOLleno.toJSONObject().toString()))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
		ArgumentCaptor<TramiteDetalleKey> argumentCaptor = ArgumentCaptor.forClass(TramiteDetalleKey.class);
		Mockito.verify(tramiteDetalleService).getById(argumentCaptor.capture());
		Assert.assertEquals(tramiteId, argumentCaptor.getValue().getTramiteId());
		Assert.assertEquals(tipoTramiteCampoId, argumentCaptor.getValue().getTipoTramiteCampoId().getTipoTramiteId());
		Assert.assertEquals(campoDisponibleId, argumentCaptor.getValue().getTipoTramiteCampoId().getCampoDisponibleId());
		
		ArgumentCaptor<TramiteDetalle> argumentCaptor2 = ArgumentCaptor.forClass(TramiteDetalle.class);
		Mockito.verify(tramiteDetalleService).save(argumentCaptor2.capture());
		Assert.assertEquals("valor", argumentCaptor2.getValue().getValor());
		Assert.assertEquals("usuario1", argumentCaptor2.getValue().getUsuModif());
	}
	
	@Test
	public void testUpdateTramiteDetalleException() throws Exception {
		
		Integer tramiteId = 1;
		Integer tipoTramiteCampoId = 1;
		String campoDisponibleId = "asdf";
		
		Mockito.when(tramiteDetalleService.getById(Mockito.any(TramiteDetalleKey.class))).thenThrow(NullPointerException.class);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/funcional/tramite/detalle/update/" + tramiteId.toString() + "/" + tipoTramiteCampoId.toString() + "/" + campoDisponibleId).contentType(MediaType.APPLICATION_JSON).content(tramiteDetalleDTO.toJSONObject().toString()))
		.andExpect(MockMvcResultMatchers.status().is5xxServerError());
	}
	
	@Test
	public void testDeleteTramiteDetalle() throws Exception {
		Integer tramiteId = 1;
		Integer tipoTramiteCampoId = 1;
		String campoDisponibleId = "asdf";
		
		mockMvc.perform(MockMvcRequestBuilders.post("/funcional/tramite/detalle/delete/" + tramiteId.toString() + "/" + tipoTramiteCampoId.toString() + "/" + campoDisponibleId))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
		ArgumentCaptor<TramiteDetalleKey> argumentCaptor = ArgumentCaptor.forClass(TramiteDetalleKey.class);
		Mockito.verify(tramiteDetalleService).delete(argumentCaptor.capture());
		Assert.assertEquals(tramiteId, argumentCaptor.getValue().getTramiteId());
		Assert.assertEquals(tipoTramiteCampoId, argumentCaptor.getValue().getTipoTramiteCampoId().getTipoTramiteId());
		Assert.assertEquals(campoDisponibleId, argumentCaptor.getValue().getTipoTramiteCampoId().getCampoDisponibleId());
	}
	
	@Test
	public void testDeleteTramiteDetalleException() throws Exception {
		
		Integer tramiteId = 1;
		Integer tipoTramiteCampoId = 1;
		String campoDisponibleId = "asdf";
		
		Mockito.doThrow(NullPointerException.class).when(tramiteDetalleService).delete(Mockito.any(TramiteDetalleKey.class));
		
		mockMvc.perform(MockMvcRequestBuilders.post("/funcional/tramite/detalle/delete/" + tramiteId.toString() + "/" + tipoTramiteCampoId.toString() + "/" + campoDisponibleId))
		.andExpect(MockMvcResultMatchers.status().is5xxServerError());
	}
}
