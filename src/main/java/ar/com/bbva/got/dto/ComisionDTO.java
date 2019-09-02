package ar.com.bbva.got.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ComisionDTO {
    
    @ApiModelProperty(notes = "The database generated comision ID")
    private Integer id;
    
    @ApiModelProperty(notes = "The comision param1")
    private String param1;
    
    @ApiModelProperty(notes = "The comision param2")
    private String param2;
    
    @ApiModelProperty(notes = "The comision param3")
    private String param3;
    
    @ApiModelProperty(notes = "The comision param4")
    private String param4;
    
    @ApiModelProperty(notes = "The comision param5")
    private String param5;
    
}
