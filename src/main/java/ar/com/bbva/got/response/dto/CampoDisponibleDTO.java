package ar.com.bbva.got.response.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CampoDisponibleDTO implements Serializable {

	private static final long serialVersionUID = -5445678639117753177L;

    private Integer id;
    private String nombre;
    private String descripcion;
    private String tipoDato;
    private boolean activo;
    private boolean obligatorio;
    private String leyenda;

}
