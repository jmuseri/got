package ar.com.bbva.got.dto;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import ar.com.bbva.got.model.SectorKey;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AltaTramiteDTO implements Serializable {
	 
	private static final long serialVersionUID = 2602223305984673722L;

	private Integer idTipoTramite;

    private Integer nroClienteEmpresa;

    private Set<Integer> idAutorizados;

    private SectorKey sectorAlta;

    //private Set<TramiteDetalle> detalle;
    private String cuentaCobro;
    
    private String UsuarioAlta;

//    private EstadoTramite estado;
//    private Date fechaFinalizacion;
//    private Date fechaInicio;
//    private Date fechaVencimiento;

}
