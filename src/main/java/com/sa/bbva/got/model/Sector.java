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
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Sector {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated sector ID")
    private Integer id;
    @ApiModelProperty(notes = "The sector canal", required = true)
    private String canal;
    @ApiModelProperty(notes = "The sector name", required = true)
    private String sector;
    @ApiModelProperty(notes = "The sector description")
    private String descripcion;
    @ApiModelProperty(notes = "The sector state")
    private boolean activo;
    @ApiModelProperty(notes = "The creator user", required = true)
    private String usuAlta;
    @ApiModelProperty(notes = "The creation date", required = true)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date fechaAlta;
    @ApiModelProperty(notes = "The update user")
    private String usuModif;
    @ApiModelProperty(notes = "The update date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date fechaModif;

}
