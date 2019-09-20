package ar.com.bbva.got.service.funcional;

import java.util.List;


import ar.com.bbva.got.model.MotivoRechazo;

public interface MotivoRechazoService {
    Iterable<MotivoRechazo> listAll();

    Iterable<MotivoRechazo> listByTipoTramiteId(Integer tipoTramiteId);
    

    MotivoRechazo getById(Integer id);    

    MotivoRechazo save(MotivoRechazo motivoRechazo);

    void save(List<MotivoRechazo> motivosRechazo);

    void delete(Integer id);
    
}
