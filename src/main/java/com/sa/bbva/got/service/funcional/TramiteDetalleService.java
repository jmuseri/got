package com.sa.bbva.got.service.funcional;

import java.util.List;

import com.sa.bbva.got.model.TramiteDetalle;

public interface TramiteDetalleService {
    Iterable<TramiteDetalle> listAll();

    TramiteDetalle getById(Integer id);

    TramiteDetalle save(TramiteDetalle tramiteDetalle);

    void save(List<TramiteDetalle> tramiteDetalles);

    void delete(Integer id);
}
