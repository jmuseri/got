# Classes Documentation - GOT

[Back to Home](/README.md) | [Back to General Doc](/docs/readme.md) | [Back to Delivery](/docs/markdown/delivery.md) | [Go Back](/docs/markdown/classes.md)

### com.sa.bbva.got.repository
* [AutorizadoRepository](#markdown-header-autorizadorepository)
* [CampoDisponibleRepository](#markdown-header-campodisponiblerepository)
* [ComisionRepository](#markdown-header-comisionrepository)
* [EstadoTramiteRepository](#markdown-header-estadotramiterepository)
* [SectorRepository](#markdown-header-sector)
* [TipoTramiteCampoRepository](#markdown-header-tipotramitecamporepository)
* [TipoTramiteRepository](#markdown-header-tipotramiterepository)
* [TramiteAutorizadoRepository](#markdown-header-tramiteautorizadorepository)
* [TramiteRepository](#markdown-header-tramiterepository)

## ![class](../images/class.png "class") AutorizadoRepository
---
### com/sa/bbva/got/repository/
### Diagram
![class](../diagrams/classes/model/AutorizadoRepository.png "class")
### Inheritance
CrudRepository

### Properties

### Methods
* Iterable<Autorizado> findByClienteId(Integer clienteId)

## ![class](../images/class.png "class") CampoDisponibleRepository
---
### com/sa/bbva/got/repository/
### Diagram
![class](../diagrams/classes/model/CampoDisponibleRepository.png "class")
### Inheritance
CrudRepository

### Properties

### Methods

## ![class](../images/class.png "class") ComisionRepository
---
### com/sa/bbva/got/repository/
### Diagram
![class](../diagrams/classes/model/ComisionRepository.png "class")
### Inheritance
CrudRepository

### Properties

### Methods

## ![class](../images/class.png "class") EstadoTramiteRepository
---
### com/sa/bbva/got/repository/
### Diagram
![class](../diagrams/classes/model/EstadoTramiteRepository.png "class")
### Inheritance
CrudRepository

### Properties

### Methods

## ![class](../images/class.png "class") SectorRepository
---
### com/sa/bbva/got/repository/
### Diagram
![class](../diagrams/classes/model/SectorRepository.png "class")
### Inheritance
CrudRepository

### Properties

### Methods
* Iterable<Sector> findAllByActivoIsTrue()

## ![class](../images/class.png "class") TipoTramiteRepository
---
### com/sa/bbva/got/model/
### Diagram
![class](../diagrams/classes/model/TipoTramite.png "class")
### Inheritance
CrudRepository

### Properties

### Methods
* Iterable<TipoTramite> findAllByActivoIsTrue()

## ![class](../images/class.png "class") TipoTramiteCampoRepository
---
### com/sa/bbva/got/repository/
### Diagram
![class](../diagrams/classes/model/TipoTramiteCampoRepository.png "class")
### Inheritance
CrudRepository

### Properties

### Methods
* Iterable<TipoTramiteCampo> findAllByActivoIsTrue()
* TipoTramiteCampo findById(TipoTramiteCampoKey id)

## ![class](../images/class.png "class") TramiteRepository
---
### com/sa/bbva/got/repository/
### Diagram
![class](../diagrams/classes/model/TramiteRepository.png "class")
### Inheritance
CrudRepository

### Properties

### Methods
* List<Tramite> findBySectorActual(Sector sectorActual)
* List<Tramite> findByEstado(EstadoTramite estado)

## ![class](../images/class.png "class") TramiteAutorizadoRepository
---
### com/sa/bbva/got/repository/
### Diagram
![class](../diagrams/classes/model/TramiteAutorizadoRepository.png "class")
### Inheritance
* CrudRepository

### Properties

### Methods
* TramiteAutorizado findById(TramiteAutorizadoKey id)
* void deleteByIdAutorizadoId(Integer id)

## ![class](../images/class.png "class") TramiteDetalleRepository
---
### com/sa/bbva/got/repository/
### Diagram
![class](../diagrams/classes/model/TramiteDetalleRepository.png "class")
### Inheritance
CrudRepository

### Properties

### Methods
* TramiteDetalle findById(TramiteDetalleKey id)

---
[Go to Top](#markdown-header-classes-documentation-got)  
[Back to Home](/README.md) | [Back to General Doc](/docs/readme.md) | [Back to Delivery](/docs/markdown/delivery.md) | [Go Back](/docs/markdown/classes.md)