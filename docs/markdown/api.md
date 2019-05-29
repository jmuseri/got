# API Services Documentation 

[Back to Home](/README.md) | [Back to General Doc](/docs/readme.md) | [Back to Delivery](/docs/markdown/delivery.md)

---
## Content

- [General Description](#markdown-header-general-description)
- [General Version](#markdown-header-general-version)
- [API Catalog](#markdown-header-api-catalog)

## General Description
GOT APIs are based on RESTFul Services Protocol.

## General Version
| Version  |    Date    | Description                    |  Author     |   Revision   |
|----------|:-----------|:-------------------------------|:------------|--------------|
| v0.0.1   | 2019/05/29 | Initial version - Definitions  |    SA       |              |

--- 
## API Catalog

### General
* ![get](../images/method-get.png "get")  [/](#markdown-header-api-/)

* ![get](../images/method-get.png "get")  [/swagger-ui.html](#markdown-header-api-/swagger-ui.html)

### Funcional
#### tramite
* ![get](../images/method-get.png "get")    [/funcional/tramite](/docs/markdown/api/accion-api.md)  *(Listar los trámites)*
* ![get](../images/method-get.png "get")    [/funcional/tramite?sector=1](/docs/markdown/api/accion-api.md)  *(Listar los trámites activos por sector)*
* ![get](../images/method-get.png "get")    [/funcional/tramite?activo=true](/docs/markdown/api/accion-api.md)  *(Listar los trámites activos)*
* ![post](../images/method-post.png "post") [/funcional/tramite/new](/docs/markdown/api/accion-api.md)  *(Alta de trámite con autorizados, sector, detalle)*
* ![post](../images/method-post.png "post") [/funcional/tramite/update](/docs/markdown/api/accion-api.md)  *(Modificación de un trámite)*
* ![post](../images/method-post.png "post") [/funcional/tramite/delete](/docs/markdown/api/accion-api.md)  *(Baja de un trámite)*
* ![post](../images/method-post.png "post") [/funcional/tramite/cabecera/new](/docs/markdown/api/accion-api.md)  *(Alta de un trámite solo cabecera)*
#### autorizado
* ![get](../images/method-get.png "get")    [/funcional/autorizado?cliente=1](/docs/markdown/api/accion-api.md)  *(Listar autorizados por cliente)* 
* ![get](../images/method-get.png "get")    [/funcional/autorizado?tramite=1](/docs/markdown/api/accion-api.md)  *(Listar autorizados por trámite)* 
* ![post](../images/method-post.png "post") [/funcional/autorizado/new](/docs/markdown/api/accion-api.md)  *(Alta de un autorizado)*
* ![post](../images/method-post.png "post") [/funcional/autorizado/update](/docs/markdown/api/accion-api.md)  *(Modificación de un autorizado)*
* ![post](../images/method-post.png "post") [/funcional/autorizado/delete](/docs/markdown/api/accion-api.md)  *(Baja de un autorizado)*
#### tramite-autorizado
* ![post](../images/method-post.png "post") [/funcional/tramite/autorizado/new](/docs/markdown/api/accion-api.md)  *(Alta de relación un autorizado a un trámite)*
* ![post](../images/method-post.png "post") [/funcional/tramite/autorizado/delete](/docs/markdown/api/accion-api.md)  *(Baja de relación un autorizado a un trámite)*
#### tramite-detalle
* ![get](../images/method-get.png "get")    [/funcional/tramite/detalle?tramite=1](/docs/markdown/api/accion-api.md)  *(Listar detalle de trámite)*
* ![post](../images/method-post.png "post") [/funcional/tramite/detalle/new](/docs/markdown/api/accion-api.md)  *(Alta de detalle de trámite)*
* ![post](../images/method-post.png "post") [/funcional/tramite/detalle/update](/docs/markdown/api/accion-api.md)  *(Modificación de detalle de trámite)*
* ![post](../images/method-post.png "post") [/funcional/tramite/detalle/delete](/docs/markdown/api/accion-api.md)  *(Baja de detalle de trámite)*

### Parametría
#### sector
* ![get](../images/method-get.png "get")    [/parametria/sector](/docs/markdown/api/accion-api.md)  *(Listar los sectores)*
* ![get](../images/method-get.png "get")    [/parametria/sector?activo=true](/docs/markdown/api/accion-api.md)  *(Listar los sectores activos)*
* ![post](../images/method-post.png "post") [/parametria/sector/new](/docs/markdown/api/accion-api.md)  *(Alta de un sector)*
* ![post](../images/method-post.png "post") [/parametria/sector/update](/docs/markdown/api/accion-api.md)  *(Modificación de un sector)*
* ![post](../images/method-post.png "post") [/parametria/sector/delete](/docs/markdown/api/accion-api.md)  *(Baja de un sector)*
#### tramiteTipo
* ![get](../images/method-get.png "get")    [/parametria/tramiteTipo](/docs/markdown/api/accion-api.md)  *(Listar todos los tipos de trámites)*
* ![get](../images/method-get.png "get")    [/parametria/tramiteTipo?activo=true](/docs/markdown/api/accion-api.md)  *(Listar los tipos de trámites activos)*
* ![post](../images/method-post.png "post") [/parametria/tramiteTipo/new](/docs/markdown/api/accion-api.md)  *(Alta de un tipo de trámite)*
* ![post](../images/method-post.png "post") [/parametria/tramiteTipo/update](/docs/markdown/api/accion-api.md)  *(Modificación de un tipo de trámite)*
* ![post](../images/method-post.png "post") [/parametria/tramiteTipo/delete](/docs/markdown/api/accion-api.md)  *(Baja de un tipo de trámite)*
#### campoDisponible
* ![get](../images/method-get.png "get")    [/parametria/campoDisponible](/docs/markdown/api/accion-api.md)  *(Listar campos disponibles)*
* ![post](../images/method-post.png "post") [/parametria/campoDisponible/new](/docs/markdown/api/accion-api.md)  *(Alta de campos disponibles)*
* ![post](../images/method-post.png "post") [/parametria/campoDisponible/update](/docs/markdown/api/accion-api.md)  *(Modificación de campos disponibles)*
* ![post](../images/method-post.png "post") [/parametria/campoDisponible/delete](/docs/markdown/api/accion-api.md)  *(Baja de campos disponibles)*
#### tramiteTipo-campoDisponible
* ![get](../images/method-get.png "get")    [/parametria/tramiteTipo/campoDisponibles?tramite=1](/docs/markdown/api/accion-api.md)  *(Listar los campos disponibles para un tipo de trámite)*
* ![post](../images/method-post.png "post") [/parametria/tramiteTipo/campoDisponible/new](/docs/markdown/api/accion-api.md)  *(Alta relación tipo de trámite con un campo disponible)*
* ![post](../images/method-post.png "post") [/parametria/tramiteTipo/campoDisponible/delete](/docs/markdown/api/accion-api.md)  *(Baja de relación tipo de trámite con un campo disponible)*
* ![post](../images/method-post.png "post") [/parametria/tramiteTipo/campoDisponible/update](/docs/markdown/api/accion-api.md)  *(Modificación de relación tipo de trámite con un campo disponible)*
#### comision
* ![get](../images/method-get.png "get")    [/parametria/comision](/docs/markdown/api/accion-api.md)  *(Listar comisiones)*
* ![post](../images/method-post.png "post") [/parametria/comision/new](/docs/markdown/api/accion-api.md)  *(Alta de comisión)*
* ![post](../images/method-post.png "post") [/parametria/comision/update](/docs/markdown/api/accion-api.md)  *(Modificación de comisión)*
* ![post](../images/method-post.png "post") [/parametria/comision/delete](/docs/markdown/api/accion-api.md)  *(Baja de comisión)*
#### tramiteTipo-comision
* ![get](../images/method-get.png "get")    [/parametria/tramiteTipo/comision?tramite=1](/docs/markdown/api/accion-api.md)  *(Consulta comision para un tipo de trámite)*
* ![post](../images/method-post.png "post") [/parametria/tramiteTipo/comision/new](/docs/markdown/api/accion-api.md)  *(Alta de relacion comisión y tipo de trámite)*
* ![post](../images/method-post.png "post") [/parametria/tramiteTipo/comision/delete](/docs/markdown/api/accion-api.md)  *(Baja de relacion comisión y tipo de trámite)*

---
## /
## /swagger-ui.html
---
### Description
Service to see api swagger documentation and test all application methods.
 
Return Swagger UI.

### Method
![get](../images/method-get.png "get")
### URL
    http://localhost:8080/
### Parameters
- void

### Response
- 200 
         
---
[Go to Top](#markdown-header-api-services-documentation-pagossucursal)  
[Back to Home](/README.md) | [Back to General Doc](/docs/readme.md) | [Back to Delivery](/docs/markdown/delivery.md)