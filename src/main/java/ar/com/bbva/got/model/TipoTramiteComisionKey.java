package ar.com.bbva.got.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement
@Embeddable
public class TipoTramiteComisionKey implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "tipo_tramite_id")
    Integer tipoTramiteId;

    @Column(name = "area_negocio")
    Integer areaNegocio;

    public TipoTramiteComisionKey() {
    }

    public TipoTramiteComisionKey(Integer tipoTramiteId, Integer areaNegocio) {
        this.tipoTramiteId = tipoTramiteId;
        this.areaNegocio = areaNegocio;
    }

}
