package com.sa.bbva.got.service.funcional;

import java.util.List;

import com.sa.bbva.got.model.Tramite;

public interface TramiteService {
    Iterable<Tramite> listAll();

    Tramite getById(Integer id);

    Tramite save(Tramite tramite);

    void save(List<Tramite> tramites);

    void delete(Integer id);
}
