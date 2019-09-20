package ar.com.bbva.got.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@XmlRootElement
@Entity
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MotivoRechazo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="motivo_rechazo_seq_gen")
	@SequenceGenerator(name="motivo_rechazo_seq_gen", sequenceName="MOTIVO_RECHAZO_SEQ")
    @ApiModelProperty(notes = "The database generated motivoRechazo ID", required = true)
    private Integer id;

    @ApiModelProperty(notes = "The motivoRechazo descripcion")
    private String descripcion;

    @ManyToOne
    @ApiModelProperty(notes = "The tramite tipoTramite")
    private TipoTramite tipoTramite;
    

}
