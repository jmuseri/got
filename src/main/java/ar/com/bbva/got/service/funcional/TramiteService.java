package ar.com.bbva.got.service.funcional;

import java.util.List;

import ar.com.bbva.got.model.EstadoTramite;
import ar.com.bbva.got.model.Sector;
import ar.com.bbva.got.model.Tramite;;

public interface TramiteService {
    Iterable<Tramite> listAll();

    List<Tramite> listBySectorActual(Sector sectorActual);

    List<Tramite> listByEstado(EstadoTramite estado);
    
    Tramite getById(Integer id);

    Tramite save(Tramite tramite);

    void save(List<Tramite> tramites);

    void delete(Integer id);

	List<Tramite> listByEmpresaEstadoAndTipoTramite(Integer nroClienteEmpresa, String estado,
			Integer idTipoTramite);
}
