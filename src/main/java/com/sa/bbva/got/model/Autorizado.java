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
public class Autorizado {
    @EmbeddedId
    private AutorizadoKey id;

    @ApiModelProperty(notes = "The autorizado tipoDocumento")
    private String tipoDocumento;

    @ApiModelProperty(notes = "The autorizado nroDocumento")
    private String nroDocumento;

    @ApiModelProperty(notes = "The autorizado nombre")
    private String nombre;

    @ApiModelProperty(notes = "The autorizado apellido")
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
