package ar.com.bbva.got.dto;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CampoDetalleDTO implements Serializable {

	private static final long serialVersionUID = -5445678639117753177L;

	@ApiModelProperty(notes = "The nombre")
    private String nombre;
	
	@ApiModelProperty(notes = "The valor")
    private String valor;
   
	
	@ApiModelProperty(notes = "The Descripcion")
    private String descripcion;
	
	@ApiModelProperty(notes = "The Leyenda")
    private String leyenda;
	
	public JSONObject toJSONObject() {
		JSONObject jo = new JSONObject();
		try {
			jo.put("nombre", getNombre());
			jo.put("valor", getValor());
			jo.put("descripcion", getDescripcion());
			jo.put("leyenda", getLeyenda());
		}
		catch (JSONException e) {
		}
		return jo;
	}	
}
