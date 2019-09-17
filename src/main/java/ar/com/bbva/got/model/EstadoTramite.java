package ar.com.bbva.got.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public enum EstadoTramite {
	
	PENDIENTE_FIRMA,
	ACTIVO,
	GESTION,
	FINALIZADO,
	RECHAZADO;
    
}
