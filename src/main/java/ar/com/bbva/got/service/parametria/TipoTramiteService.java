package ar.com.bbva.got.service.parametria;

import java.util.List;

import ar.com.bbva.got.model.Sector;
import ar.com.bbva.got.model.TipoTramite;

public interface TipoTramiteService {
    Iterable<TipoTramite> listAll();

    Iterable<TipoTramite> listActive();

    TipoTramite getById(Integer id);

    TipoTramite save(TipoTramite tipoTramite);

    void save(List<TipoTramite> tipoTramites);

    void delete(Integer id);

	Iterable<TipoTramite> listByActiveAndSector(boolean active, Sector sector);
}
