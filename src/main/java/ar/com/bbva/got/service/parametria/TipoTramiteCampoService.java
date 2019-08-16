package ar.com.bbva.got.service.parametria;

import java.util.List;

import ar.com.bbva.got.model.TipoTramiteCampo;
import ar.com.bbva.got.model.TipoTramiteCampoKey;

public interface TipoTramiteCampoService {
    Iterable<TipoTramiteCampo> listAll();

    Iterable<TipoTramiteCampo> listActive();

    TipoTramiteCampo getById(int id);

    TipoTramiteCampo getById(TipoTramiteCampoKey id);

    TipoTramiteCampo save(TipoTramiteCampo tipoTramite);

    void save(List<TipoTramiteCampo> tipoTramites);

    void delete(TipoTramiteCampo tipoTramiteCampo);
}
