package com.sa.bbva.got.service.funcional;

import java.util.List;

import com.sa.bbva.got.model.Autorizado;

public interface AutorizadoService {
    Iterable<Autorizado> listAll();

    Autorizado getById(Integer id);

    Autorizado save(Autorizado comision);

    void save(List<Autorizado> comisiones);

    void delete(Integer id);
}
