package com.sa.bbva.got.service.parametria;

import com.sa.bbva.got.model.Comision;

public interface ComisionService {
    Iterable<Comision> listAll();

    Comision getById(Integer id);

    Comision save(Comision product);

    void delete(Integer id);
}
