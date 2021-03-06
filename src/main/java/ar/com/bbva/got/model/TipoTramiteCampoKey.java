package ar.com.bbva.got.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement
@Embeddable
public class TipoTramiteCampoKey implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "tipo_tramite_id")
    Integer tipoTramiteId;

    @Column(name = "campo_disponible_id")
    String campoDisponibleId;

    public TipoTramiteCampoKey() {
    }

    public TipoTramiteCampoKey(Integer tipoTramiteId, String campoDisponibleId) {
        this.tipoTramiteId = tipoTramiteId;
        this.campoDisponibleId = campoDisponibleId;
    }

}
