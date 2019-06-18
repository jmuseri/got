package com.sa.bbva.got.service.funcional;

import java.util.List;

import com.sa.bbva.got.model.Autorizado;
import com.sa.bbva.got.model.AutorizadoKey;

public interface AutorizadoService {
    Iterable<Autorizado> listAll();

    Iterable<Autorizado> listByClient(Integer clienteId);

    Autorizado getById(AutorizadoKey id);

    Autorizado save(Autorizado autorizado);

    void save(List<Autorizado> autorizados);

    void delete(AutorizadoKey id);
}
