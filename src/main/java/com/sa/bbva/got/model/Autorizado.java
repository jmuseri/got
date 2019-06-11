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
public class Autorizado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated comision ID")
    private Integer id;
    @ApiModelProperty(notes = "The comision idCliente")
    private Integer idCliente;
    // @ApiModelProperty(notes = "The autorizado tramites list")
    // private List<Tramite> tramites;
    @ApiModelProperty(notes = "The comision tipoDocumento")
    private String tipoDocumento;
    @ApiModelProperty(notes = "The comision nroDocumento")
    private String nroDocumento;
    @ApiModelProperty(notes = "The comision nombre")
    private String nombre;
    @ApiModelProperty(notes = "The comision apellido")
    private String apellido;
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
