package com.sa.bbva.got.gotApi.service.funcional;

import java.util.List;

import com.sa.bbva.got.gotApi.model.TramiteDetalle;
import com.sa.bbva.got.gotApi.model.TramiteDetalleKey;

public interface TramiteDetalleService {
    Iterable<TramiteDetalle> listAll();

    TramiteDetalle getById(TramiteDetalleKey id);

    TramiteDetalle save(TramiteDetalle tramiteDetalle);

    void save(List<TramiteDetalle> tramiteDetalles);

    void delete(TramiteDetalleKey id);
}
