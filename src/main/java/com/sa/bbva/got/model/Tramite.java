package com.sa.bbva.got.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.Set;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@XmlRootElement
@Entity
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Tramite {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated tramite ID")
    private Integer id;

    @ManyToOne
    @MapsId("tipo_tramite_id")
    @JoinColumn(name = "tipo_tramite_id")
    @ApiModelProperty(notes = "The tramite tipoTramite")
    private TipoTramite tipoTramite;

    @ApiModelProperty(notes = "The tramite idCliente")
    private Integer idCliente;

    @OneToMany
    @MapsId("autorizados_id")
    @JoinColumn(name = "autorizados_id")
    @ApiModelProperty(notes = "The tramite autorizados")
    private Set<Autorizado> autorizados;

    @ManyToOne
    @ApiModelProperty(notes = "The tramite sectorInicio")
    private Sector sectorInicio;

    @ManyToOne
    @ApiModelProperty(notes = "The tramite sectorActual")
    private Sector sectorActual;

    @OneToMany
    @MapsId("tramite_detalle_id")
    @JoinColumn(name = "tramite_detalle_id")
    @ApiModelProperty(notes = "The tramite detalle")
    private Set<TramiteDetalle> detalle;

    @ApiModelProperty(notes = "The tramite cuentaCobro")
    private String cuentaCobro;

    @ManyToOne
    @MapsId("estado_id")
    @JoinColumn(name = "estado_id")
    @ApiModelProperty(notes = "The tramite estado")
    private EstadoTramite estado;

    @ApiModelProperty(notes = "The finalization date", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date fechaFinalizacion;

    @ApiModelProperty(notes = "The init date", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date fechaInicio;

    @ApiModelProperty(notes = "The expiry date", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date fechaVencimiento;

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
