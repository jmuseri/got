package com.sa.bbva.got.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@XmlRootElement
@Entity
public class Sector {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated sector ID")
    private Integer id;
    @ApiModelProperty(notes = "The sector canal", required = true)
    private String canal;
    @ApiModelProperty(notes = "The sector name", required = true)
    private String sector;
    @ApiModelProperty(notes = "The sector description")
    private String description;
    @ApiModelProperty(notes = "The creator user")
    private String usuAlta;
    @ApiModelProperty(notes = "The creation date", required = true)
    private Date fechaAlta;
    @ApiModelProperty(notes = "The update user")
    private String usuModif;
    @ApiModelProperty(notes = "The update date", required = true)
    private Date fechaModif;

}
