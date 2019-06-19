package com.sa.bbva.got.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@XmlRootElement
@Entity
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Comision {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="comision_seq_gen")
	@SequenceGenerator(name="comision_seq_gen", sequenceName="COMISION_SEQ")
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
