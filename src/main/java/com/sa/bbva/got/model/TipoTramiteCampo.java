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
@Table(name = "tipo_tramite_campo")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TipoTramiteCampo {

    @EmbeddedId
    private TipoTramiteCampoKey id;

    @ManyToOne
    @MapsId("campo_disponible_id")
    @JoinColumn(name = "campo_disponible_id")
    @ApiModelProperty(notes = "The tipoTramiteCampo campoDisponible")
    private CampoDisponible campoDisponible;

    /*
    @ManyToOne
    @MapsId("tipo_tramite_id")
    @JoinColumn(name = "tipo_tramite_id")
    @ApiModelProperty(notes = "The tipoTramiteCampo tipoTramite")
    private TipoTramite tipoTramite;
    */

    @ApiModelProperty(notes = "The tipoTramiteCampo obligatorio")
    private boolean obligatorio;

    @ApiModelProperty(notes = "The tipoTramiteCampo activo")
    private boolean activo;

    @ApiModelProperty(notes = "The tipoTramiteCampo nombre")
    private String nombre;

    @ApiModelProperty(notes = "The tipoTramiteCampo leyenda")
    private String leyenda;

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
