package ar.com.bbva.got.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@XmlRootElement
@Entity
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EstadoTramite {
    
	@Id
    @ApiModelProperty(notes = "The database estadoTramite ID", required = true)
    private Integer id;
    
	@ApiModelProperty(notes = "The estadoTramite name")
    private String nombre;
    
	@ApiModelProperty(notes = "The estadoTramite description")
    private String descripcion;
    
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
