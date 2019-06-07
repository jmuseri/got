package com.sa.bbva.got.service.parametria;

import java.util.List;

import com.sa.bbva.got.model.TipoTramiteCampo;

public interface TipoTramiteCampoService {
    Iterable<TipoTramiteCampo> listAll();

    Iterable<TipoTramiteCampo> listActive();

    TipoTramiteCampo getById(Integer id);

    TipoTramiteCampo save(TipoTramiteCampo tipoTramite);

    void save(List<TipoTramiteCampo> tipoTramites);

    void delete(Integer id);
}
