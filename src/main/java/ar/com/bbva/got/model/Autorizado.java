package ar.com.bbva.got.model;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"tipoDocumento", "nroDocumento"}, name = "uniqueNameConstraint")}
)
public class Autorizado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "autorizado_seq_gen")
    @ApiModelProperty(notes = "The database generated autorizado ID")
    @SequenceGenerator(name = "autorizado_seq_gen", sequenceName = "AUTORIZADO_SEQ")
    private Integer id;

    @ApiModelProperty(notes = "The autorizado nroclienteEmpresa")
    private Integer nroClienteEmpresa;
    
    @ApiModelProperty(notes = "The autorizado cuitEmpresa")
    private String cuitEmpresa;

    @ApiModelProperty(notes = "The autorizado tipoDocumento")
    private String tipoDocumento;

    @ApiModelProperty(notes = "The autorizado nroDocumento")
    private String nroDocumento;

    @ApiModelProperty(notes = "The autorizado nombre")
    private String nombre;

    @ApiModelProperty(notes = "The autorizado apellido")
    private String apellido;

    
    @ApiModelProperty(notes = "The autorizado gender")
    private String sexo;
    
    @ApiModelProperty(notes = "The creator user")
    private String usuAlta;
    
    

    @ApiModelProperty(notes = "The creation date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date fechaAlta;

    @ApiModelProperty(notes = "The update user")
    private String usuModif;

    @ApiModelProperty(notes = "The update date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date fechaModif;
    
    @ApiModelProperty(notes = "The Autorizado state")
    private boolean activo;
    
    public JSONObject toJSONObject() {
		JSONObject jo = new JSONObject();
		try {
		jo.put("id",getId());
		jo.put("nroClienteEmpresa", getNroClienteEmpresa());
		jo.put("cuitEmpresa", getCuitEmpresa());
		jo.put("tipoDocumento", getTipoDocumento());
		jo.put("nroDocumento", getNroDocumento());
		jo.put("nombre",getNombre());
		jo.put("apellido", getApellido());
		jo.put("sexo", getSexo());
		jo.put("usuAlta", getUsuAlta());
		jo.put("fechaAlta", getFechaAlta());
		jo.put("usuModif", getUsuModif());
		jo.put("fechaModif", getFechaModif());
		jo.put("activo", isActivo());
		} catch (JSONException e) {
			//DO NOTHING
		}
		
		return jo;
		
	}
}
