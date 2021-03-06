package ar.com.bbva.got.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@XmlRootElement
@Entity
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TipoTramite {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="tipo_tramite_seq_gen")
	@SequenceGenerator(name="tipo_tramite_seq_gen", sequenceName="TIPO_TRAMITE_SEQ")
    @ApiModelProperty(notes = "The database generated tipoTramite ID", required = true)
    private Integer id;

    @ApiModelProperty(notes = "The tipoTramite descripcion")
    private String descripcion;

    @ApiModelProperty(notes = "The tipoTramite cobraComision")
    private boolean cobraComision;

    @ManyToOne
    @JoinColumn(name = "comision")
    @ApiModelProperty(notes = "The tipoTramite comision")
    private Comision comision;

    @ApiModelProperty(notes = "The tipoTramite requiereDocumentacion")
    private boolean requiereDocumentacion;

    @ApiModelProperty(notes = "The tipoTramite activo")
    private boolean activo;

    @ApiModelProperty(notes = "The tipoTramite autorizado")
    private boolean autorizado;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumns({
    	  @JoinColumn(name = "canal", insertable = true, updatable = false),
    	  @JoinColumn(name = "sector", insertable = true, updatable = false)
    	})
    @ApiModelProperty(notes = "The tramite sectorInicial")
    private Sector sectorInicial;

    @OneToMany(mappedBy = "id.tipoTramiteId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @ApiModelProperty(notes = "The campos sector")
    private Set<TipoTramiteCampo> campos;
    
    
    @OneToMany(mappedBy = "id.tipoTramiteId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ApiModelProperty(notes = "The campos comision")
    private Set<TipoTramiteComision> tipoTramiteComisiones;
    

    @ApiModelProperty(notes = "The tipoTramite horasResolucion")
    private Long horasResolucion;

    @ApiModelProperty(notes = "The tipoTramite horasVencimiento")
    private Long horasVencimiento;

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
