package ar.com.bbva.got.model;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@XmlRootElement
@Entity
@Table(name = "tramite_autorizado")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TramiteAutorizado {
    
	@EmbeddedId
    private TramiteAutorizadoKey id;

    @ManyToOne
    @JoinColumn(insertable = false, updatable = false)
    private Autorizado autorizado;

    @ApiModelProperty(notes = "The creator user", required = true)
    private String usuAlta;

    @ApiModelProperty(notes = "The creation date", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date fechaAlta;
    
    @ApiModelProperty(notes = "Indicates if this Autorizado finalize the Tramite", required = false)
	private boolean finalizoTramite;

}
