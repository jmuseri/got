package com.sa.bbva.got.gotApi.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement
@Embeddable
public class TramiteDetalleKey implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "tramite_id")
    private Integer tramiteId;

    @Column(name = "tipo_tramite_campo_id")
    private TipoTramiteCampoKey tipoTramiteCampoId;

    public TramiteDetalleKey() {
    }

    public TramiteDetalleKey(Integer tramiteId, Integer tipoTramiteId, Integer campoDisponibleId) {
        this.tramiteId = tramiteId;
        this.tipoTramiteCampoId = new TipoTramiteCampoKey(tipoTramiteId, campoDisponibleId);
    }

}
