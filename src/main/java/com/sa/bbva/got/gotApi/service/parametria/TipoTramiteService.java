package com.sa.bbva.got.gotApi.service.parametria;

import java.util.List;

import com.sa.bbva.got.gotApi.model.TipoTramite;

public interface TipoTramiteService {
    Iterable<TipoTramite> listAll();

    Iterable<TipoTramite> listActive();

    TipoTramite getById(Integer id);

    TipoTramite save(TipoTramite tipoTramite);

    void save(List<TipoTramite> tipoTramites);

    void delete(Integer id);
}
