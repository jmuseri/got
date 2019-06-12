package com.sa.bbva.got.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement
@Embeddable
public class TramiteDetalleKey implements Serializable {
    private static final long serialVersionUID = 1L;

    // @Column(name = "tramite_id")
    // Long tramiteId;

    @Column(name = "tipo_tramite_campo_id")
    Long tipoTramiteCampoId;

}
