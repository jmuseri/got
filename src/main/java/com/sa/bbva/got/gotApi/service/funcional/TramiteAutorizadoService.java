package com.sa.bbva.got.gotApi.service.funcional;

import java.util.List;

import com.sa.bbva.got.gotApi.model.TramiteAutorizado;
import com.sa.bbva.got.gotApi.model.TramiteAutorizadoKey;

public interface TramiteAutorizadoService {
    Iterable<TramiteAutorizado> listAll();

    TramiteAutorizado getById(TramiteAutorizadoKey id);

    TramiteAutorizado save(TramiteAutorizado tramiteAutorizado);

    void save(List<TramiteAutorizado> tramiteAutorizados);

    void delete(TramiteAutorizadoKey id);

    void deleteByIdAutorizadoId(Integer id);
}
