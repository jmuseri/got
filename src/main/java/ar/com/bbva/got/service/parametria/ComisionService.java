package ar.com.bbva.got.service.parametria;

import java.util.List;

import ar.com.bbva.got.model.Comision;

public interface ComisionService {
    Iterable<Comision> listAll();

    Comision getById(Integer id);

    Comision save(Comision comision);

    void save(List<Comision> comisiones);

    void delete(Integer id);
}
