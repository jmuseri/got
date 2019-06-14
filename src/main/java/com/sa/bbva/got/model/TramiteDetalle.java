package com.sa.bbva.got.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@XmlRootElement
@Entity
@Table(name = "tramite_detalle")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TramiteDetalle {
    @EmbeddedId
    private TramiteDetalleKey id;

    /*
    @ManyToOne
    @JoinColumn(name = "tramite")
    @ApiModelProperty(notes = "The tramiteDetalle tramite")
    private Tramite tramite;
    */
    
    /*
    @OneToOne
    @MapsId("tipo_tramite_campo_id")
    @JoinColumns({
        @JoinColumn(name = "campo_disponible_id"),
        @JoinColumn(name = "tipo_tramite_id")
    })
    @ApiModelProperty(notes = "The tipoTramite campo")
    private TipoTramiteCampo campo;
    */

    @ApiModelProperty(notes = "The tramiteDetalle valor")
    private String valor;

    @ApiModelProperty(notes = "The creator user", required = true)
    private String usuAlta;

    @ApiModelProperty(notes = "The creation date", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date fechaAlta;

    @ApiModelProperty(notes = "The update user")
    private String usuModif;
    
    @ApiModelProperty(notes = "The update date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date fechaModif;
}
