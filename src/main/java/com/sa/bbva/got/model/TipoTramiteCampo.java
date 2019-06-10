package com.sa.bbva.got.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@XmlRootElement
@Entity
public class TipoTramiteCampo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated tipoTramite ID")
    private Integer id;

    @ApiModelProperty(notes = "The tipoTramite tipoTramite")
    @ManyToOne
    @JoinColumn(name = "tipoTramite")
    private TipoTramite tipoTramite;

    @ApiModelProperty(notes = "The tipoTramite campoDisponible")
    @ManyToOne
    @JoinColumn(name = "campoDisponible")
    private CampoDisponible campoDisponible;

    @ApiModelProperty(notes = "The tipoTramite obligatorio")
    private boolean obligatorio;
    @ApiModelProperty(notes = "The tipoTramite activo")
    private boolean activo;
    @ApiModelProperty(notes = "The tipoTramite nombre")
    private String nombre;
    @ApiModelProperty(notes = "The tipoTramite leyenda")
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
