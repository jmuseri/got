package com.sa.bbva.got.gotApi.service.funcional;

import java.util.List;

import com.sa.bbva.got.gotApi.model.EstadoTramite;
import com.sa.bbva.got.gotApi.model.Sector;
import com.sa.bbva.got.gotApi.model.Tramite;;

public interface TramiteService {
    Iterable<Tramite> listAll();

    List<Tramite> listBySectorActual(Sector sectorActual);

    List<Tramite> listByEstado(EstadoTramite estado);

    Tramite getById(Integer id);

    Tramite save(Tramite tramite);

    void save(List<Tramite> tramites);

    void delete(Integer id);
}
