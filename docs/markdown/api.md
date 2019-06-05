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
* ![get](../images/method-get.png "get")  [/](#markdown-header-api-/swagger-ui.html) *(Redirect to Documentation)*

* ![get](../images/method-get.png "get")  [/swagger-ui.html](#markdown-header-api-/swagger-ui.html) *(API Documentation)*

### Funcional
#### tramite
* ![get](../images/method-get.png "get")    [/funcional/tramite/list](/docs/markdown/api/accion-api.md) *(Listar los trámites)*
* ![get](../images/method-get.png "get")    [/funcional/tramite/list?activo={true/false}](/docs/markdown/api/accion-api.md) *(Listar los trámites activos)*
* ![get](../images/method-get.png "get")    [/funcional/tramite/list?sector=1](/docs/markdown/api/accion-api.md)  *(Listar los trámites activos por sector)*
* ![post](../images/method-post.png "post") [/funcional/tramite/add](/docs/markdown/api/accion-api.md)  *(Alta de trámite con autorizados, sector, detalle)*
* ![post](../images/method-post.png "post") [/funcional/tramite/update](/docs/markdown/api/accion-api.md) *(Modificación de un trámite)*
* ![post](../images/method-post.png "post") [/funcional/tramite/delete](/docs/markdown/api/accion-api.md) *(Baja de un trámite)*
* ![post](../images/method-post.png "post") [/funcional/tramite/cabecera/add](/docs/markdown/api/accion-api.md) *(Alta de un trámite solo cabecera)*
#### tramite-autorizado
* ![post](../images/method-post.png "post") [/funcional/tramite/autorizado/add](/docs/markdown/api/accion-api.md)  *(Alta de relación un autorizado a un trámite)*
* ![post](../images/method-post.png "post") [/funcional/tramite/autorizado/delete](/docs/markdown/api/accion-api.md)  *(Baja de relación un autorizado a un trámite)*
* ![get](../images/method-get.png "get")    [/funcional/tramite/autorizado/list/{1}](/docs/markdown/api/accion-api.md)  *(Listar autorizados por trámite)* 
#### autorizado
* ![get](../images/method-get.png "get")    [/funcional/autorizado/list?cliente=1](/docs/markdown/api/accion-api.md)  *(Listar autorizados por cliente)* 
* ![post](../images/method-post.png "post") [/funcional/autorizado/add](/docs/markdown/api/accion-api.md)  *(Alta de un autorizado)*
* ![post](../images/method-post.png "post") [/funcional/autorizado/update](/docs/markdown/api/accion-api.md)  *(Modificación de un autorizado)*
* ![post](../images/method-post.png "post") [/funcional/autorizado/delete](/docs/markdown/api/accion-api.md)  *(Baja de un autorizado)*
#### tramite-detalle
* ![get](../images/method-get.png "get")    [/funcional/tramite/detalle/list/{1}](/docs/markdown/api/accion-api.md)  *(Listar detalle de trámite)*
* ![post](../images/method-post.png "post") [/funcional/tramite/detalle/add](/docs/markdown/api/accion-api.md)  *(Alta de detalle de trámite)*
* ![post](../images/method-post.png "post") [/funcional/tramite/detalle/update](/docs/markdown/api/accion-api.md)  *(Modificación de detalle de trámite)*
* ![post](../images/method-post.png "post") [/funcional/tramite/detalle/delete](/docs/markdown/api/accion-api.md)  *(Baja de detalle de trámite)*

### Parametría
#### tipoTramite
* ![get](../images/method-get.png "get")    [/parametria/tipoTramite/list](/docs/markdown/api/accion-api.md)  *(Listar todos los tipos de trámites)*
* ![get](../images/method-get.png "get")    [/parametria/tipoTramite/list?activo=true](/docs/markdown/api/accion-api.md)  *(Listar los tipos de trámites activos)*
* ![post](../images/method-post.png "post") [/parametria/tipoTramite/add](/docs/markdown/api/accion-api.md)  *(Alta de un tipo de trámite)*
* ![post](../images/method-post.png "post") [/parametria/tipoTramite/update](/docs/markdown/api/accion-api.md)  *(Modificación de un tipo de trámite)*
* ![post](../images/method-post.png "post") [/parametria/tipoTramite/delete](/docs/markdown/api/accion-api.md)  *(Baja de un tipo de trámite)*
#### tipoTramite-campoDisponible
* ![get](../images/method-get.png "get")    [/parametria/tipoTramite/campoDisponible/list?tramite=1](/docs/markdown/api/accion-api.md)  *(Listar los campos disponibles para un tipo de trámite)*
* ![post](../images/method-post.png "post") [/parametria/tipoTramite/campoDisponible/add](/docs/markdown/api/accion-api.md)  *(Alta relación tipo de trámite con un campo disponible)*
* ![post](../images/method-post.png "post") [/parametria/tipoTramite/campoDisponible/delete](/docs/markdown/api/accion-api.md)  *(Baja de relación tipo de trámite con un campo disponible)*
* ![post](../images/method-post.png "post") [/parametria/tipoTramite/campoDisponible/update](/docs/markdown/api/accion-api.md)  *(Modificación de relación tipo de trámite con un campo disponible)*
#### tipoTramite-comision
* ![get](../images/method-get.png "get")    [/parametria/tipoTramite/comision/show/{1}](/docs/markdown/api/accion-api.md)  *(Consulta comision para un tipo de trámite)*
* ![post](../images/method-post.png "post") [/parametria/tipoTramite/comision/add](/docs/markdown/api/accion-api.md)  *(Alta de relacion comisión y tipo de trámite)*
* ![post](../images/method-post.png "post") [/parametria/tipoTramite/comision/delete](/docs/markdown/api/accion-api.md)  *(Baja de relacion comisión y tipo de trámite)*
#### campoDisponible
* ![get](../images/method-get.png "get")    [/parametria/campoDisponible/list](/docs/markdown/api/accion-api.md)  *(Listar campos disponibles)*
* ![post](../images/method-post.png "post") [/parametria/campoDisponible/add](/docs/markdown/api/accion-api.md)  *(Alta de campos disponibles)*
* ![post](../images/method-post.png "post") [/parametria/campoDisponible/update](/docs/markdown/api/accion-api.md)  *(Modificación de campos disponibles)*
* ![post](../images/method-post.png "post") [/parametria/campoDisponible/delete](/docs/markdown/api/accion-api.md)  *(Baja de campos disponibles)*
* ![get](../images/method-get.png "get")    [/parametria/campoDisponible/show/{1}](/docs/markdown/api/accion-api.md)  ***(Obtener un campoDisponible por Id)***
#### sector
* ![get](../images/method-get.png "get")    [/parametria/sector/list?activo={false/true}](/docs/markdown/api/accion-api.md)  *(Listar todos los sectores/Listar los sectores activos)*
* ![post](../images/method-post.png "post") [/parametria/sector/add](/docs/markdown/api/accion-api.md)  *(Alta de un sector)*
* ![post](../images/method-post.png "post") [/parametria/sector/update](/docs/markdown/api/accion-api.md)  *(Modificación de un sector)*
* ![post](../images/method-post.png "post") [/parametria/sector/delete](/docs/markdown/api/accion-api.md)  *(Baja de un sector)*
* ![get](../images/method-get.png "get")    [/parametria/sector/show/{1}](/docs/markdown/api/accion-api.md)  ***(Obtener un sector por Id)***
#### comision
* ![get](../images/method-get.png "get")    [/parametria/comision/list](/docs/markdown/api/accion-api.md)  *(Listar comisiones)*
* ![post](../images/method-post.png "post") [/parametria/comision/add](/docs/markdown/api/accion-api.md)  *(Alta de comisión)*
* ![post](../images/method-post.png "post") [/parametria/comision/update](/docs/markdown/api/accion-api.md)  *(Modificación de comisión)*
* ![post](../images/method-post.png "post") [/parametria/comision/delete](/docs/markdown/api/accion-api.md)  *(Baja de comisión)*
* ![get](../images/method-get.png "get")    [/parametria/comision/show/{1}](/docs/markdown/api/accion-api.md)  ***(Obtener una comision por Id)***
#### estadoTramite
* ![get](../images/method-get.png "get")    [/parametria/estadoTramite/list](/docs/markdown/api/accion-api.md) ***(Listar estadoTramite)***
* ![post](../images/method-post.png "post") [/parametria/estadoTramite/add](/docs/markdown/api/accion-api.md)  ***(Alta de estadoTramite)***
* ![post](../images/method-post.png "post") [/parametria/estadoTramite/update](/docs/markdown/api/accion-api.md) ***(Modificación de estadoTramite)***
* ![post](../images/method-post.png "post") [/parametria/estadoTramite/delete](/docs/markdown/api/accion-api.md)  ***(Baja de estadoTramite)***
* ![get](../images/method-get.png "get")    [/parametria/estadoTramite/show/{1}](/docs/markdown/api/accion-api.md)  ***(Obtener un estadoTramite por Id)***
#### tipoTramite
* ![get](../images/method-get.png "get")    [/parametria/tipoTramite/list](/docs/markdown/api/accion-api.md) ***(Listar tipoTramite)***
* ![post](../images/method-post.png "post") [/parametria/tipoTramite/add](/docs/markdown/api/accion-api.md)  ***(Alta de tipoTramite)***
* ![post](../images/method-post.png "post") [/parametria/tipoTramite/update](/docs/markdown/api/accion-api.md) ***(Modificación de tipoTramite)***
* ![post](../images/method-post.png "post") [/parametria/tipoTramite/delete](/docs/markdown/api/accion-api.md)  ***(Baja de tipoTramite)***
* ![get](../images/method-get.png "get")    [/parametria/tipoTramite/show/{1}](/docs/markdown/api/accion-api.md)  ***(Obtener un tipoTramite por Id)***
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