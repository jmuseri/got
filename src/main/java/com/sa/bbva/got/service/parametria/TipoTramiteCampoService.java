package com.sa.bbva.got.service.parametria;

import java.util.List;

import com.sa.bbva.got.model.TipoTramiteCampo;
import com.sa.bbva.got.model.TipoTramiteCampoKey;

public interface TipoTramiteCampoService {
    Iterable<TipoTramiteCampo> listAll();

    Iterable<TipoTramiteCampo> listActive();

    TipoTramiteCampo getById(int id);

    TipoTramiteCampo getById(TipoTramiteCampoKey id);

    TipoTramiteCampo save(TipoTramiteCampo tipoTramite);

    void save(List<TipoTramiteCampo> tipoTramites);

    void delete(TipoTramiteCampo tipoTramiteCampo);
}
