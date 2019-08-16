package com.sa.bbva.got.gotApi.service.parametria;

import java.util.List;

import com.sa.bbva.got.gotApi.model.CampoDisponible;

public interface CampoDisponibleService {
    Iterable<CampoDisponible> listAll();

    CampoDisponible getById(Integer id);

    CampoDisponible save(CampoDisponible campoDisponible);

    void save(List<CampoDisponible> campoDisponible);

    void delete(Integer id);
}
