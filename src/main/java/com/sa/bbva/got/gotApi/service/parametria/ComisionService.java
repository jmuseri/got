package com.sa.bbva.got.gotApi.service.parametria;

import java.util.List;

import com.sa.bbva.got.gotApi.model.Comision;

public interface ComisionService {
    Iterable<Comision> listAll();

    Comision getById(Integer id);

    Comision save(Comision comision);

    void save(List<Comision> comisiones);

    void delete(Integer id);
}
