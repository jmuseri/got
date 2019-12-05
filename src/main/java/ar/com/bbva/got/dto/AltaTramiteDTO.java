package ar.com.bbva.got.dto;

import java.io.Serializable;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AltaTramiteDTO implements Serializable {
	 
	private static final long serialVersionUID = 2602223305984673722L;

	private Integer idTipoTramite;

    private Integer nroClienteEmpresa;
    
    private String cuitEmpresa;

    private List<Integer> idAutorizados;
    
    private Integer areaNegocio;

    private SectorDTO sectorAlta;

    private List<CampoDetalleDTO> detalle;
    private String cuentaCobro;
    
    private String UsuarioAlta;

//    private EstadoTramite estado;
//    private Date fechaFinalizacion;
//    private Date fechaInicio;
//    private Date fechaVencimiento;
    
    public JSONObject toJSONObject() {
    	
    	JSONObject jo = new JSONObject();
    	
    	try {
        	jo.put("idTipoTramite", getIdTipoTramite())
        	.put("nroClienteEmpresa", getNroClienteEmpresa())
        	.put("cuitEmpresa", getCuitEmpresa())
        	.put("idAutorizados", getIdAutorizados())
        	.put("areaNegocio", getAreaNegocio())
        	.put("sectorAlta", getSectorAlta())
        	.put("detalle", getDetalle())
        	.put("cuentaCobro", getCuentaCobro())
        	.put("UsuarioAlta", getUsuarioAlta());
        	
    	} catch (JSONException e) {
			//DO NOTHING
		}
    	
    	return jo;
    }

}
