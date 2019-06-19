package com.sa.bbva.got.service.funcional;

import java.util.List;

import com.sa.bbva.got.model.Autorizado;

public interface AutorizadoService {
    Iterable<Autorizado> listAll();

    Iterable<Autorizado> listByClient(Integer clienteId);

    Autorizado getById(Integer id);    

    Autorizado save(Autorizado autorizado);

    void save(List<Autorizado> autorizados);

    void delete(Integer id);

}
