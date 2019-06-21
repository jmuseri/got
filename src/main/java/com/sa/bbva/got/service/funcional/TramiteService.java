package com.sa.bbva.got.service.funcional;

import java.util.List;

import com.sa.bbva.got.model.Tramite;
import com.sa.bbva.got.model.Sector;
import com.sa.bbva.got.model.EstadoTramite;;

public interface TramiteService {
    Iterable<Tramite> listAll();

    List<Tramite> listBySectorActual(Sector sectorActual);

    List<Tramite> listByEstado(EstadoTramite estado);

    Tramite getById(Integer id);

    Tramite save(Tramite tramite);

    void save(List<Tramite> tramites);

    void delete(Integer id);
}
