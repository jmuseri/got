package com.sa.bbva.got.gotApi.service.parametria;

import java.util.List;

import com.sa.bbva.got.gotApi.model.EstadoTramite;

public interface EstadoTramiteService {
    Iterable<EstadoTramite> listAll();

    EstadoTramite getById(Integer id);

    EstadoTramite save(EstadoTramite estadoTramite);

    void save(List<EstadoTramite> estadoTramite);

    void delete(Integer id);
}
