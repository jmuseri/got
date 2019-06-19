# Database Model
[Back to Home](/README.md) | [Back to General Doc](/docs/readme.md) | [Back to Delivery](/docs/markdown/delivery.md)

---

## Content
* [Database Scripts](#markdown-header-oracle-script)
* [DER](#markdown-header-der)
* [Tables Catalog](#markdown-header-tables-catalog)

## Database Scripts
[create_tables.sql](/db/create_tables.sql)

## Model
![der](../diagrams/database/model.png "der")

## DER
![der](../diagrams/database/der.png "der")

## Tables Catalog
* [SECTOR](#markdown-header-sector)
* [COMISION](#markdown-header-comision)
* [ESTADO_TRAMITE](#markdown-header-estado_tramite)
* [TIPO_TRAMITE](#markdown-header-tipo_tramite)
* [TIPO_TRAMITE_CAMPO](#markdown-header-tipo_tramite_campo)
* [CAMPO_DISPONIBLE](#markdown-header-campo_disponible)
* [TRAMITE](#markdown-header-tramite)
* [TRAMITE_DETALLE](#markdown-header-tramite_detalle)
* [AUTORIZADO](#markdown-header-autorizado)

---
### ![table](../images/model-table.png "table")  SECTOR

|  Attr             |    Type            |  Default           |   Description                 |
|-------------------|:-------------------|:-------------------|:------------------------------|
|  ID               | NUMBER(19,0)       |                    |                               |
|  SECTOR           | VARCHAR2(255 CHAR) |                    |                               |
|  CANAL            | VARCHAR2(255 CHAR) |                    |                               |
|  DESCRIPCION      | VARCHAR2(255 CHAR) |                    |                               |
|  ACTIVO   		| NUMBER(1,0)		 |                    |                               |
|  USU_ALTA         | VARCHAR2(8 CHAR)   |                    |                               |
|  FECHA_ALTA       | TIMESTAMP (6) 	 | DEFAULT sysdate    |                               |
|  USU_MODIF        | VARCHAR2(8 CHAR)   |                    |                               |
|  FECHA_MODIF      | TIMESTAMP (6)      |                    |                               |     	   
      
---
[Go to Top](#markdown-header-database-model-pagossucursal)  
[Back to Home](/README.md) | [Back to General Doc](/docs/readme.md) | [Back to Delivery](/docs/markdown/delivery.md)