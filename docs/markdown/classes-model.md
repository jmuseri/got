# Classes Documentation - GOT

[Back to Home](/README.md) | [Back to General Doc](/docs/readme.md) | [Go Back](/docs/markdown/classes.md)

### com.sa.bbva.got.model
* [Autorizado](#markdown-header-autorizado)
* [CampoDisponible](#markdown-header-campodisponible)
* [Comision](#markdown-header-comision)
* [EstadoTramite](#markdown-header-estadotramite)
* [Sector](#markdown-header-sector)
* [TipoTramite](#markdown-header-tipotramite)
* [TipoTramiteCampo](#markdown-header-tipotramitecampo)
* [TipoTramiteCampoKey](#markdown-header-tipotramitecampokey)
* [Tramite](#markdown-header-tramite)
* [TramiteAutorizado](#markdown-header-tramiteautorizado)
* [TramiteAutorizadoKey](#markdown-header-tramiteautorizadokey)
* [TramiteDetalle](#markdown-header-tramitedetalle)
* [TramiteDetalleKey](#markdown-header-tramitedetallekey)

## ![class](../images/class.png "class") Autorizado
---
### com/sa/bbva/got/model/
### Diagram
![class](../diagrams/classes/model/Autorizado.png "class")
### Inheritance
### Properties
* private Integer id
* private Integer clienteId
* private String tipoDocumento
* private String nroDocumento
* private String nombre
* private String apellido
* private String usuAlta
* private Date fechaAlta
* private String usuModif
* private Date fechaModif

### Methods
* public getters()
* public void setters()

## ![class](../images/class.png "class") CampoDisponible
---
### com/sa/bbva/got/model/
### Diagram
![class](../diagrams/classes/model/CampoDisponible.png "class")
### Inheritance

### Properties
* private Integer id
* private String nombre
* private String descripcion
* private String tipoDato
* private boolean activo
* private String usuAlta
* private Date fechaAlta
* private String usuModif
* private Date fechaModif

### Methods
* public getters()
* public void setters()

## ![class](../images/class.png "class") Comision
---
### com/sa/bbva/got/model/
### Diagram
![class](../diagrams/classes/model/Comision.png "class")
### Inheritance
### Properties
* private Integer id
* private String param1
* private String param2
* private String param3
* private String param4
* private String param5
* private String usuAlta
* private Date fechaAlta
* private String usuModif
* private Date fechaModif

### Methods
* public getters()
* public void setters()

## ![class](../images/class.png "class") EstadoTramite
---
### com/sa/bbva/got/model/
### Diagram
![class](../diagrams/classes/model/EstadoTramite.png "class")
### Inheritance

### Properties
* private Integer id
* private String descripcion
* private boolean cobraComision
* private Comision comision
* private boolean requiereDocumentacion
* private boolean activo
* private boolean autorizado
* private Sector sectorInicial
* private Set<TipoTramiteCampo> campos
* private Long horasResolucion
* private Long horasVencimiento
* private String usuAlta
* private Date fechaAlta
* private String usuModif
* private Date fechaModif

### Methods
* public getters()
* public void setters()

## ![class](../images/class.png "class") Sector
---
### com/sa/bbva/got/model/
### Diagram
![class](../diagrams/classes/model/Sector.png "class")
### Inheritance

### Properties
* private Integer id
* private String canal
* private String sector
* private String descripcion
* private boolean activo
* private String usuAlta
* private Date fechaAlta
* private String usuModif
* private Date fechaModif

### Methods
* public getters()
* public void setters()

## ![class](../images/class.png "class") TipoTramite
---
### com/sa/bbva/got/model/
### Diagram
![class](../diagrams/classes/model/TipoTramite.png "class")
### Inheritance
### Properties
* private Integer id
* private String descripcion
* private boolean cobraComision
* private Comision comision
* private boolean requiereDocumentacion
* private boolean activo
* private boolean autorizado
* private Sector sectorInicial
* private Set<TipoTramiteCampo> campos
* private Long horasResolucion
* private Long horasVencimiento
* private String usuAlta
* private Date fechaAlta
* private String usuModif
* private Date fechaModif

### Methods
* public getters()
* public void setters()

## ![class](../images/class.png "class") TipoTramiteCampo
---
### com/sa/bbva/got/model/
### Diagram
![class](../diagrams/classes/model/TipoTramiteCampo.png "class")
### Inheritance
### Properties
* private TipoTramiteCampoKey id
* private CampoDisponible campoDisponible
* private boolean obligatorio
* private boolean activo
* private String nombre
* private String leyenda
* private String usuAlta
* private Date fechaAlta
* private String usuModif
* private Date fechaModif

### Methods
* public getters()
* public void setters()

## ![class](../images/class.png "class") TipoTramiteCampoKey
---
### com/sa/bbva/got/model/
### Diagram
![class](../diagrams/classes/model/TipoTramiteCampoKey.png "class")
### Inheritance
### Properties
  * private static final long serialVersionUID
  * Integer tipoTramiteId
  * Integer campoDisponibleId

### Methods
* public getters()
* public void setters()
* public TipoTramiteCampoKey()
* public TipoTramiteCampoKey(Integer tipoTramiteId, Integer campoDisponibleId)

## ![class](../images/class.png "class") Tramite
---
### com/sa/bbva/got/model/
### Diagram
![class](../diagrams/classes/model/Tramite.png "class")
### Inheritance
### Properties
* private Integer id
* private TipoTramite tipoTramite
* private Integer clienteId
* private Set<TramiteAutorizado> autorizado
* private Sector sectorInicio
* private Sector sectorActual
* private Set<TramiteDetalle> detalle
* private String cuentaCobro
* private EstadoTramite estado
* private Date fechaFinalizacion
* private Date fechaInicio
* private Date fechaVencimiento
* private String usuAlta
* private Date fechaAlta
* private String usuModif
* private Date fechaModif

### Methods
* public getters()
* public void setters()

## ![class](../images/class.png "class") TramiteAutorizado
---
### com/sa/bbva/got/model/
### Diagram
![class](../diagrams/classes/model/TramiteAutorizado.png "class")
### Inheritance
* BaseEntity

### Properties
* private TramiteAutorizadoKey id
* private Autorizado autorizado
* private String usuAlta
* private Date fechaAlta

### Methods
* public getters()
* public void setters()

## ![class](../images/class.png "class") TramiteAutorizadoKey
---
### com/sa/bbva/got/model/
### Diagram
![class](../diagrams/classes/model/TramiteAutorizadoKey.png "class")
### Inheritance
* Serializable

### Properties
  * Integer tramiteId
  * Integer autorizadoId

### Methods
* public getters()
* public void setters()
* public TramiteAutorizadoKey()
* public TramiteAutorizadoKey(Integer tramiteId, Integer autorizadoId)

## ![class](../images/class.png "class") TramiteDetalle
---
### com/sa/bbva/got/model/
### Diagram
![class](../diagrams/classes/model/TramiteDetalle.png "class")
### Inheritance

### Properties
* private TramiteDetalleKey id
* private String valor
* private String usuAlta
* private Date fechaAlta
* private String usuModif
* private Date fechaModif

### Methods
* public getters()
* public void setters()

## ![class](../images/class.png "class") TramiteDetalleKey
---
### com/sa/bbva/got/model/
### Diagram
![class](../diagrams/classes/model/TramiteDetalleKey.png "class")
### Inheritance
* Serializable

### Properties
* private static final long serialVersionUID
* private Integer tramiteId
* private TipoTramiteCampoKey tipoTramiteCampoId

### Methods
* public getters()
* public void setters()
* public TramiteDetalleKey()
* public TramiteDetalleKey(Integer tramiteId, Integer tipoTramiteId, Integer campoDisponibleId)

---
[Go to Top](#markdown-header-classes-documentation-got)  
[Back to Home](/README.md) | [Back to General Doc](/docs/readme.md) | [Go Back](/docs/markdown/classes.md)