package ar.com.bbva.got.dto;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TramiteDTO {

private static final long serialVersionUID = -956041686816430819L;
	
	@ApiModelProperty(notes = "The database generated autorizado ID")
    private Integer id;

}
