package com.sa.bbva.got.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@XmlRootElement
@Entity
public class Comision {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @ApiModelProperty(notes = "The creator user")
    private String usuAlta;
    @ApiModelProperty(notes = "The creation date", required = true)
    private Date fechaAlta;
    @ApiModelProperty(notes = "The update user")
    private String usuModif;
    @ApiModelProperty(notes = "The update date", required = true)
    private Date fechaModif;
}
