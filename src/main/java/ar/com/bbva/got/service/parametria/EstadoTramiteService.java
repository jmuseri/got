package ar.com.bbva.got.service.parametria;

import java.util.List;

import ar.com.bbva.got.model.EstadoTramite;

public interface EstadoTramiteService {
    Iterable<EstadoTramite> listAll();

    EstadoTramite getById(Integer id);

    EstadoTramite save(EstadoTramite estadoTramite);

    void save(List<EstadoTramite> estadoTramite);

    void delete(Integer id);
}
