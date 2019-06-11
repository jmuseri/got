package com.sa.bbva.got.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement
@Embeddable
public class TipoTramiteCampoKey implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "tipo_tramite_id")
    Long tipoTramiteId;

    @Column(name = "campo_disponible_id")
    Long campoDisponibleId;

}
