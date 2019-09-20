package ar.com.bbva.got.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
public class Tramite {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "tramite_seq_gen")
    @SequenceGenerator(name = "tramite_seq_gen", sequenceName = "TRAMITE_SEQ")
    @ApiModelProperty(notes = "The database generated tramite ID")
    private Integer id;

    @ManyToOne
    @ApiModelProperty(notes = "The tramite tipoTramite")
    private TipoTramite tipoTramite;

    @ApiModelProperty(notes = "The tramite clienteId")
    private Integer nroClienteEmpresa;
    
    @ApiModelProperty(notes = "The tramite cuitEmpresa")
    private String cuitEmpresa;

    @OneToMany(mappedBy = "id.tramiteId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<TramiteAutorizado> autorizado;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumns({
    	  @JoinColumn(name = "canalInicio", insertable = true, updatable = false),
    	  @JoinColumn(name = "sectorInicio", insertable = true, updatable = false)
    	})
    @ApiModelProperty(notes = "The tramite sectorInicio")
    private Sector sectorInicio;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumns({
    	  @JoinColumn(name = "canalActual", insertable = true, updatable = false),
    	  @JoinColumn(name = "sectorActual", insertable = true, updatable = false)
    	})
    @ApiModelProperty(notes = "The tramite sectorActual")
    private Sector sectorActual;

    @OneToMany(mappedBy = "id.tramiteId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @ApiModelProperty(notes = "The tramite detalle")
    private Set<TramiteDetalle> detalle;

    @ApiModelProperty(notes = "The tramite cuentaCobro")
    private String cuentaCobro;
    
    @ApiModelProperty(notes = "The tramite estado")
    @Enumerated(EnumType.STRING)
    private EstadoTramite estado;
    
    @ApiModelProperty(notes = "The finalization date", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date fechaFinalizacion;

    @ApiModelProperty(notes = "The init date", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date fechaInicio;

    @ApiModelProperty(notes = "The expiry date", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date fechaVencimiento;

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
    
    @ManyToOne
    @ApiModelProperty(notes = "The Motivo Rechazo")
    private MotivoRechazo motivoRechazo;
    
}
