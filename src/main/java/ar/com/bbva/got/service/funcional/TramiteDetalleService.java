package ar.com.bbva.got.service.funcional;

import java.util.List;

import ar.com.bbva.got.model.TramiteDetalle;
import ar.com.bbva.got.model.TramiteDetalleKey;

public interface TramiteDetalleService {
    Iterable<TramiteDetalle> listAll();

    TramiteDetalle getById(TramiteDetalleKey id);

    TramiteDetalle save(TramiteDetalle tramiteDetalle);

    void save(List<TramiteDetalle> tramiteDetalles);

    void delete(TramiteDetalleKey id);
}
