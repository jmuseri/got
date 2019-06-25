# Interfaces Documentation - GOT

[Back to Home](/README.md) | [Back to General Doc](/docs/readme.md) | [Back to Delivery](/docs/markdown/delivery.md) | [Go Back](/docs/markdown/classes.md)

### com.sa.bbva.got.service.funcional
* [AutorizadoService](#markdown-header-interface-autorizadoservice)
* [TramiteAutorizadoService](#markdown-header-interface-tramiteautorizadoservice)
* [TramiteDetalleService](#markdown-header-interface-tramitedetalleservice)
* [TramiteService](#markdown-header-interface-tramiteservice)

### com.sa.bbva.got.service.parametria
* [CampoDisponibleService](#markdown-header-interface-campodisponibleservice)
* [ComisionService](#markdown-header-interface-comisionservice)
* [EstadoTramiteService](#markdown-header-interface-estadotramiteservice)
* [SectorService](#markdown-header-interface-sectorservice)
* [TipoTramiteCampoService](#markdown-header-interface-tipotramitecamposervice)
* [TipoTramiteService](#markdown-header-interface-tipotramiteservice)

## ![interface](../images/interface.png "interface") AutorizadoService
---
### com/sa/bbva/got/service/funcional/
### Diagram
![class](../diagrams/classes/service/AutorizadoService.png "class")
### Inheritance

### Properties

### Methods
* Iterable<Autorizado> listAll()
* Iterable<Autorizado> listByClient(Integer clienteId)
* Autorizado getById(Integer id)
* Autorizado save(Autorizado autorizado)
* void save(List<Autorizado> autorizados)
* void delete(Integer id)

## ![interface](../images/interface.png "interface") TramiteAutorizadoService
---
### com/sa/bbva/got/service/funcional/
### Diagram
![class](../diagrams/classes/service/TramiteAutorizadoService.png "class")
### Inheritance

### Properties

### Methods
* Iterable<TramiteAutorizado> listAll()
* TramiteAutorizado getById(TramiteAutorizadoKey id)
* TramiteAutorizado save(TramiteAutorizado tramiteAutorizado)
* void save(List<TramiteAutorizado> tramiteAutorizados)
* void delete(TramiteAutorizadoKey id)
* void deleteByIdAutorizadoId(Integer id)

## ![interface](../images/interface.png "interface") TramiteDetalleService
---
### com/sa/bbva/got/service/funcional/
### Diagram
![class](../diagrams/classes/service/TramiteDetalleService.png "class")
### Inheritance

### Properties

### Methods
* Iterable<TramiteDetalle> listAll()
* TramiteDetalle getById(TramiteDetalleKey id)
* TramiteDetalle save(TramiteDetalle tramiteDetalle)
* void save(List<TramiteDetalle> tramiteDetalles)
* void delete(TramiteDetalleKey id)

## ![interface](../images/interface.png "interface") TramiteService
---
### com/sa/bbva/got/service/funcional/
### Diagram
![class](../diagrams/classes/service/TramiteService.png "class")
### Inheritance

### Properties

### Methods
* Iterable<Tramite> listAll()
* List<Tramite> listBySectorActual(Sector sectorActual)
* List<Tramite> listByEstado(EstadoTramite estado)
* Tramite getById(Integer id)
* Tramite save(Tramite tramite)
* void save(List<Tramite> tramites)
* void delete(Integer id)

## ![interface](../images/interface.png "interface") CampoDisponibleService
---
### com/sa/bbva/got/service/parametria/
### Diagram
![class](../diagrams/classes/service/CampoDisponibleService.png "class")
### Inheritance

### Properties

### Methods
* Iterable<CampoDisponible> listAll()
* CampoDisponible getById(Integer id)
* CampoDisponible save(CampoDisponible campoDisponible)
* void save(List<CampoDisponible> campoDisponible)
* void delete(Integer id)

## ![interface](../images/interface.png "interface") ComisionService
---
### com/sa/bbva/got/service/parametria/
### Diagram
![class](../diagrams/classes/service/ComisionService.png "class")
### Inheritance

### Properties

### Methods
* Iterable<Comision> listAll()
* Comision getById(Integer id)
* Comision save(Comision comision)
* void save(List<Comision> comisiones)
* void delete(Integer id)

## ![interface](../images/interface.png "interface") EstadoTramiteService
---
### com/sa/bbva/got/service/parametria/
### Diagram
![class](../diagrams/classes/service/EstadoTramiteService.png "class")
### Inheritance

### Properties

### Methods
* Iterable<EstadoTramite> listAll()
* EstadoTramite getById(Integer id)
* EstadoTramite save(EstadoTramite estadoTramite)
* void save(List<EstadoTramite> estadoTramite)
* void delete(Integer id)

## ![interface](../images/interface.png "interface") SectorService
---
### com/sa/bbva/got/service/parametria/
### Diagram
![class](../diagrams/classes/service/SectorService.png "class")
### Inheritance

### Properties

### Methods
* Iterable<Sector> listAll()
* Iterable<Sector> listActive()
* Sector getById(Integer id)
* Sector save(Sector sector)
* void save(List<Sector> sectores)
* void delete(Integer id)

## ![interface](../images/interface.png "interface") TipoTramiteCampoService
---
### com/sa/bbva/got/service/parametriA/
### Diagram
![class](../diagrams/classes/service/TipoTramiteCampoService.png "class")
### Inheritance

### Properties

### Methods
* Iterable<TipoTramiteCampo> listAll()
* Iterable<TipoTramiteCampo> listActive()
* TipoTramiteCampo getById(int id)
* TipoTramiteCampo getById(TipoTramiteCampoKey id)
* TipoTramiteCampo save(TipoTramiteCampo tipoTramite)
* void save(List<TipoTramiteCampo> tipoTramites)
* void delete(TipoTramiteCampo tipoTramiteCampo)

## ![interface](../images/interface.png "interface") TipoTramiteService
---
### com/sa/bbva/got/service/parametria/
### Diagram
![class](../diagrams/classes/service/PeriodoTipoTramiteServiceService.png "class")
### Inheritance

### Properties

### Methods
* Iterable<TipoTramite> listAll()
* Iterable<TipoTramite> listActive()
* TipoTramite getById(Integer id)
* TipoTramite save(TipoTramite tipoTramite)
* void save(List<TipoTramite> tipoTramites)
* void delete(Integer id)

---
[Go to Top](#markdown-header-classes-documentation-got)  
[Back to Home](/README.md) | [Back to General Doc](/docs/readme.md) | [Back to Delivery](/docs/markdown/delivery.md) | [Go Back](/docs/markdown/classes.md)