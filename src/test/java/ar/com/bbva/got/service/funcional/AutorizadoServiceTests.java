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

import ar.com.bbva.got.model.Autorizado;
import ar.com.bbva.got.repository.AutorizadoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AutorizadoServiceTests {

	private static List<Autorizado> autorizadosDePrueba = new ArrayList<Autorizado>();
	private Autorizado autorizado;
	private int nroCliente;
	private int idAutorizado;
	
	@Mock
	AutorizadoRepository autorizadoRepository;
	
	@InjectMocks
	AutorizadoServiceImpl autorizadoService;
	
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
	public void testListAll() {
		Mockito.when(autorizadoRepository.findAll()).thenReturn(autorizadosDePrueba);
		
		Iterable<Autorizado> autorizadosRecibidos = autorizadoService.listAll();	
		
		Assert.assertEquals(autorizadosDePrueba, autorizadosRecibidos);
	}
	
	@Test
	public void testListByNroClienteEmpresa() {
		nroCliente = 2;
		List<Autorizado> autorizadosPorNroCliente = new ArrayList<Autorizado>();
		
		autorizadosDePrueba.forEach((x) -> {
			if(x.getNroClienteEmpresa() == nroCliente) autorizadosPorNroCliente.add(x);
		});
		Mockito.when(autorizadoRepository.findByNroClienteEmpresa(nroCliente)).thenReturn(autorizadosPorNroCliente);
		
		Iterable<Autorizado> autorizadosRecibidos = autorizadoService.listByNroClienteEmpresa(nroCliente);
		
		Assert.assertEquals(autorizadosPorNroCliente, autorizadosRecibidos);
	}
	
	@Test
	public void testGetById() {
		idAutorizado = 2;
		int nroAutorizado = idAutorizado-1;
		autorizado = autorizadosDePrueba.get(nroAutorizado);

		Mockito.when(autorizadoRepository.findById(idAutorizado)).thenReturn(Optional.of(autorizado));
		
		Autorizado autorizadoRecibido = autorizadoService.getById(idAutorizado);
		
		Assert.assertEquals(autorizado, autorizadoRecibido);
	}
	
	@Test
	public void testSave() {
		autorizado = autorizadosDePrueba.get(0);
		Mockito.when(autorizadoRepository.save(autorizado)).thenReturn(autorizado);
		
		Autorizado autorizadoRecibido = autorizadoService.save(autorizado);
		
		ArgumentCaptor<Autorizado> argumentCaptor = ArgumentCaptor.forClass(Autorizado.class);
		Mockito.verify(autorizadoRepository).save(argumentCaptor.capture());
		Assert.assertEquals(autorizado, argumentCaptor.getValue());
				
		Assert.assertEquals(autorizado, autorizadoRecibido);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testSaveAll() {
		autorizadoService.save(autorizadosDePrueba);
		
		ArgumentCaptor<List<Autorizado>> argumentCaptor = ArgumentCaptor.forClass(List.class);
		Mockito.verify(autorizadoRepository).saveAll(argumentCaptor.capture());
		Assert.assertEquals(autorizadosDePrueba, argumentCaptor.getValue());
	}
	
	@Test
	public void testDelete() {
		Integer nroAutorizado = 2;
		
		autorizadoService.delete(autorizadosDePrueba.get(nroAutorizado).getId());
		
		ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);
		Mockito.verify(autorizadoRepository).deleteById(argumentCaptor.capture());
		Assert.assertEquals(autorizadosDePrueba.get(nroAutorizado).getId(), argumentCaptor.getValue());
	}
	
	@Test
	public void testGetByTipoAndNroDocumento() {
		String tipoDoc = "DNI";
		String documento = "123";
		List<Autorizado> autorizadosPorNroDocumento = new ArrayList<Autorizado>();
		
		autorizadosDePrueba.forEach((x) -> {
			if(x.getNroDocumento() == documento) autorizadosPorNroDocumento.add(x);
		});
		
		Mockito.when(autorizadoRepository.findByTipoDocumentoAndNroDocumento(tipoDoc,documento)).thenReturn(autorizadosPorNroDocumento.get(0));
		
		Autorizado autorizadosRecibido = autorizadoService.getByTipoAndNroDocumento(tipoDoc,documento);

		Assert.assertEquals(autorizadosPorNroDocumento.get(0), autorizadosRecibido);		
	}
	
	@Test
	public void testListByNroClienteEmpresaOrCuitEmpresa() {
		Integer nroCliente = 2;
		String cuitEmpresa = "2222";

		List<Autorizado> autorizadosPorNroCliente = new ArrayList<Autorizado>();
		List<Autorizado> autorizadosPorCuitEmpresa = new ArrayList<Autorizado>();
		
		autorizadosDePrueba.forEach((x) -> {
			if (x.getNroClienteEmpresa() == nroCliente) autorizadosPorNroCliente.add(x);
			if (x.getCuitEmpresa() == cuitEmpresa) autorizadosPorCuitEmpresa.add(x);
		});
		
		Mockito.when(autorizadoRepository.findByNroClienteEmpresaAndActivoIsTrueOrCuitEmpresaAndActivoIsTrue(nroCliente, null)).thenReturn(autorizadosPorNroCliente);
		Mockito.when(autorizadoRepository.findByNroClienteEmpresaAndActivoIsTrueOrCuitEmpresaAndActivoIsTrue(null, cuitEmpresa)).thenReturn(autorizadosPorCuitEmpresa);
		
		Iterable<Autorizado> autorizadosRecibidosNroCliente = autorizadoService.listByNroClienteEmpresaOrCuitEmpresa(nroCliente, null);
		Iterable<Autorizado> autorizadosRecibidosCuit = autorizadoService.listByNroClienteEmpresaOrCuitEmpresa(null, cuitEmpresa);
		
		Assert.assertEquals(autorizadosPorNroCliente, autorizadosRecibidosNroCliente);
		Assert.assertEquals(autorizadosPorCuitEmpresa, autorizadosRecibidosCuit);		
	}
}
