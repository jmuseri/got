package com.sa.bbva.got.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement
@Embeddable
public class AutorizadoKey implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "tramite_id")
    Integer tramiteId;

    @Column(name = "cliente_id")
    Integer clienteId;

    public AutorizadoKey() {
    }  
    
    public AutorizadoKey(Integer tramiteId, Integer clienteId) {
        this.tramiteId = tramiteId;
        this.clienteId = clienteId;
    }   
}
