package ar.com.bbva.got.service.funcional;

import java.util.List;

import ar.com.bbva.got.model.Autorizado;

public interface AutorizadoService {
    Iterable<Autorizado> listAll();

    Iterable<Autorizado> listByNroClienteEmpresa(Integer nroClienteEmpresa);
    
    Iterable<Autorizado> listByNroClienteEmpresaOrCuitEmpresa(Integer nroClienteEmpresa, String cuitEmpresa);

    Autorizado getById(Integer id);    
//    
//    List<Autorizado> getByNroDocumento(String nroDocumento);   

    Autorizado save(Autorizado autorizado);

    void save(List<Autorizado> autorizados);

    void delete(Integer id);

	Autorizado getByTipoAndNroDocumento(String tipoDocumento, String nroDocumento);
    
}
