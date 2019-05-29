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
* ![post](../images/method-post.png "post") [/funcional/tramite/cabecera](/docs/markdown/api/accion-api.md)  *(Alta de un trámite solo cabecera)*
* ![post](../images/method-post.png "post") [/funcional/tramite](/docs/markdown/api/accion-api.md)  *(Alta de trámite con autorizados, sector, detalle)*
* ![post](../images/method-post.png "post") [/funcional/tramite](/docs/markdown/api/accion-api.md)  *(Baja de un trámite)*
* ![post](../images/method-post.png "post") [/funcional/tramite](/docs/markdown/api/accion-api.md)  *(Modificación de un trámite)*
* ![get](../images/method-get.png "get")    [/funcional/tramite](/docs/markdown/api/accion-api.md)  *(Listar los trámites)*
* ![get](../images/method-get.png "get")    [/funcional/tramite](/docs/markdown/api/accion-api.md)  *(Listar los trámites activos por sector)*
* ![get](../images/method-get.png "get")    [/funcional/tramite](/docs/markdown/api/accion-api.md)  *(Listar los trámites activos)*
#### autorizado
* ![post](../images/method-post.png "post") [/funcional/autorizado](/docs/markdown/api/accion-api.md)  *(Alta de un autorizado)*
* ![post](../images/method-post.png "post") [/funcional/autorizado](/docs/markdown/api/accion-api.md)  *(Modificación de un autorizado)*
* ![post](../images/method-post.png "post") [/funcional/autorizado](/docs/markdown/api/accion-api.md)  *(Baja de un autorizado)*
* ![get](../images/method-get.png "get")    [/funcional/autorizado](/docs/markdown/api/accion-api.md)  *(Listar autorizados por cliente)* 
* ![get](../images/method-get.png "get")    [/funcional/autorizado](/docs/markdown/api/accion-api.md)  *(Listar autorizados por trámite)* 
#### tramite-autorizado
* ![post](../images/method-post.png "post") [/funcional/tramiteAutorizado](/docs/markdown/api/accion-api.md)  *(Alta de relación un autorizado a un trámite)*
* ![post](../images/method-post.png "post") [/funcional/tramiteAutorizado](/docs/markdown/api/accion-api.md)  *(Baja de relación un autorizado a un trámite)*
#### tramite-detalle
* ![post](../images/method-post.png "post") [/funcional/tramite/detalle](/docs/markdown/api/accion-api.md)  *(Alta de detalle de trámite)*
* ![post](../images/method-post.png "post") [/funcional/tramite/detalle](/docs/markdown/api/accion-api.md)  *(Modificación de detalle de trámite)*
* ![post](../images/method-post.png "post") [/funcional/tramite/detalle](/docs/markdown/api/accion-api.md)  *(Baja de detalle de trámite)*
* ![get](../images/method-get.png "get")    [/funcional/tramite/detalle](/docs/markdown/api/accion-api.md)  *(Listar detalle de trámite)*


### Parametría
#### sector
* ![post](../images/method-post.png "post") [/parametria/sector](/docs/markdown/api/accion-api.md)  *(Alta de un sector)*
* ![post](../images/method-post.png "post") [/parametria/sector](/docs/markdown/api/accion-api.md)  *(Modificación de un sector)*
* ![post](../images/method-post.png "post") [/parametria/sector](/docs/markdown/api/accion-api.md)  *(Baja de un sector)*
* ![get](../images/method-get.png "get")    [/parametria/sector](/docs/markdown/api/accion-api.md)  *(Listar los sectores)*
* ![get](../images/method-get.png "get")    [/parametria/sector](/docs/markdown/api/accion-api.md)  *(Listar los sectores activos)*
#### tramite
* ![get](../images/method-get.png "get")    [/parametria/tramite](/docs/markdown/api/accion-api.md)  *(Listar los tipos de tipos de trámites activos)*
* ![get](../images/method-get.png "get")    [/parametria/tramite](/docs/markdown/api/accion-api.md)  *(Listar todos los tipos de trámites)*
#### tramiteTipo
* ![post](../images/method-post.png "post") [/parametria/tramiteTipo](/docs/markdown/api/accion-api.md)  *(Alta de un tipo de trámite)*
* ![post](../images/method-post.png "post") [/parametria/tramiteTipo](/docs/markdown/api/accion-api.md)  *(Modificación de un tipo de trámite)*
* ![post](../images/method-post.png "post") [/parametria/tramiteTipo](/docs/markdown/api/accion-api.md)  *(Baja de un tipo de trámite)*
#### campoDisponible
* ![post](../images/method-post.png "post") [/parametria/campoDisponible](/docs/markdown/api/accion-api.md)  *(Alta de campos disponibles)*
* ![post](../images/method-post.png "post") [/parametria/campoDisponible](/docs/markdown/api/accion-api.md)  *(Modificación de campos disponibles)*
* ![post](../images/method-post.png "post") [/parametria/campoDisponible](/docs/markdown/api/accion-api.md)  *(Baja de campos disponibles)*
* ![get](../images/method-get.png "get")    [/parametria/campoDisponible](/docs/markdown/api/accion-api.md)  *(Listar campos disponibles)*
#### tramiteTipo-campoDisponible
* ![post](../images/method-post.png "post") [/parametria/tramiteTipo/campoDisponible](/docs/markdown/api/accion-api.md)  *(Alta relación tipo de trámite con un campo disponible)*
* ![post](../images/method-post.png "post") [/parametria/tramiteTipo/campoDisponible](/docs/markdown/api/accion-api.md)  *(Baja de relación tipo de trámite con un campo disponible)*
* ![post](../images/method-post.png "post") [/parametria/tramiteTipo/campoDisponible](/docs/markdown/api/accion-api.md)  *(Modificación de relación tipo de trámite con un campo disponible)*
* ![get](../images/method-get.png "get")    [/parametria/tramiteTipo/campoDisponibles](/docs/markdown/api/accion-api.md)  *(Listar los campos disponibles para un tipo de trámite)*
#### comision
* ![post](../images/method-post.png "post") [/parametria/comision](/docs/markdown/api/accion-api.md)  *(Alta de comisión)*
* ![post](../images/method-post.png "post") [/parametria/comision](/docs/markdown/api/accion-api.md)  *(Modificación de comisión)*
* ![post](../images/method-post.png "post") [/parametria/comision](/docs/markdown/api/accion-api.md)  *(Baja de comisión)*
* ![get](../images/method-get.png "get")    [/parametria/comision](/docs/markdown/api/accion-api.md)  *(Listar comisiones)*
#### tramiteTipo-comision
* ![post](../images/method-post.png "post") [/parametria/tramiteTipo/comision](/docs/markdown/api/accion-api.md)  *(Alta de relacion comisión y tipo de trámite)*
* ![post](../images/method-post.png "post") [/parametria/tramiteTipo/comision](/docs/markdown/api/accion-api.md)  *(Baja de relacion comisión y tipo de trámite)*
* ![get](../images/method-get.png "get")    [/parametria/tramiteTipo/comision](/docs/markdown/api/accion-api.md)  *(Consulta comision para un tipo de trámite)*


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