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
public class TipoTramite {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated tipoTramite ID", required = true)
    private Integer id;
    @ApiModelProperty(notes = "The tipoTramite descripcion")
    private String descripcion;
    @ApiModelProperty(notes = "The tipoTramite cobraComision")
    private boolean cobraComision;
    // @ApiModelProperty(notes = "The tipoTramite comision")
    // private Comision comision;
    @ApiModelProperty(notes = "The tipoTramite requiereDocumentacion")
    private boolean requiereDocumentacion;
    @ApiModelProperty(notes = "The tipoTramite activo")
    private boolean activo;
    // @ApiModelProperty(notes = "The tipoTramite sector")
    // private Sector sectorInicial;
    // @ApiModelProperty(notes = "The tipoTramite campos")
    // private TipoTramiteCampo campos;
    @ApiModelProperty(notes = "The tipoTramite horasResolucion")
    private Long horasResolucion;
    @ApiModelProperty(notes = "The tipoTramite horasVencimiento")
    private Long horasVencimiento;
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
