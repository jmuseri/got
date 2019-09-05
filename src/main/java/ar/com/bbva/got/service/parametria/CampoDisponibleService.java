package ar.com.bbva.got.service.parametria;

import java.util.List;

import ar.com.bbva.got.model.CampoDisponible;

public interface CampoDisponibleService {
    Iterable<CampoDisponible> listAll();

    CampoDisponible getById(String id);

    CampoDisponible save(CampoDisponible campoDisponible);

    void save(List<CampoDisponible> campoDisponible);

    void delete(String id);
}
