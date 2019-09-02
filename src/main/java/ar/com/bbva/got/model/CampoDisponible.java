package ar.com.bbva.got.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@XmlRootElement
@Entity
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CampoDisponible {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="campo_disponible_seq_gen")
	@SequenceGenerator(name="campo_disponible_seq_gen", sequenceName="CAMPO_DISPONIBLE_SEQ")
    @ApiModelProperty(notes = "The database campoDisponible ID", required = true)
    private Integer id;
    @ApiModelProperty(notes = "The campoDisponible name", required = true)
    private String nombre;
    @ApiModelProperty(notes = "The campoDisponible description")
    private String descripcion;
    @ApiModelProperty(notes = "The campoDisponible tipoDato", required = true)
    private String tipoDato;
    @ApiModelProperty(notes = "The campoDisponible activo")
    private boolean activo;
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
