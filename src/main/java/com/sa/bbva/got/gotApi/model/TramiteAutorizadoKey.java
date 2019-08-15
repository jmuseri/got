package com.sa.bbva.got.gotApi.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement
@Embeddable
public class TramiteAutorizadoKey implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "tramite_id")
    Integer tramiteId;

    @Column(name = "autorizado_id")
    Integer autorizadoId;

    public TramiteAutorizadoKey() {
    }

    public TramiteAutorizadoKey(Integer tramiteId, Integer autorizadoId) {
        this.tramiteId = tramiteId;
        this.autorizadoId = autorizadoId;
    }
}
