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
* [ACCION](#markdown-header-accion)
* [ALERTA](#markdown-header-alerta)
* [CENTRO_COSTOS](#markdown-header-centro_costos)
* [DISTRIBUCION](#markdown-header-distribucion)
* [DISTRIBUCION_CENTRO](#markdown-header-distribucion_centro)
* [ESTADO](#markdown-header-estado)
* [GASTO](#markdown-header-gasto)
* [GASTO_ALERTA](#markdown-header-gasto_alerta)
* [GASTO_DISTRIBUCION](#markdown-header-gasto_distribucion)
* [GASTO_IMPUESTO](#markdown-header-gasto_impuesto)
* [IMPUESTO](#markdown-header-impuesto)
* [MONEDA](#markdown-header-moneda)
* [PAGO](#markdown-header-pago)
* [PAGO_ALERTA](#markdown-header-pago_alerta)
* [PAGO_DISTRIBUCION](#markdown-header-pago_distribucion)
* [PAGO_IMPUESTO](#markdown-header-pago_impuesto)
* [PERIODO](#markdown-header-periodo)
* [PERMISO_USUARIO](#markdown-header-permiso_usuario)
* [PROVEEDOR](#markdown-header-proveedor)
* [TIPO_COMPROBANTE](#markdown-header-tipo-compriobante)
* [TIPO_FACTURA](#markdown-header-tipo_factura)
* [TIPO_GASTO_ALERTA](#markdown-header-tipo_gasto_alerta)
* [TIPO_IMPUESTO](#markdown-header-tipo_impuesto)
* [USUARIO](#markdown-header-usuario)

---
### ![table](../images/model-table.png "table")  ACCION

|  Attr             | Type               |  Default           |   Description                 |
|-------------------|:-------------------|:-------------------|:------------------------------|
|  ID               | VARCHAR2(1 BYTE)   |                    |                               |
|  DESCRIPCION      | VARCHAR2(20 CHAR)  |                    |                               |

---
### ![table](../images/model-table.png "table")  ALERTA

|  Attr             |    Type            |  Default           |   Description                 |
|-------------------|:-------------------|:-------------------|:------------------------------|
|  ID               | NUMBER(19,0)       |                    |                               |
|  FECHA_ALTA       | TIMESTAMP (6) 	 | DEFAULT sysdate    |                               |
|  FECHA_BAJA       | TIMESTAMP (6)      |                    |                               |
|  FECHA_MOD        | TIMESTAMP (6)      |                    |                               |
|  USUARIO_ALTA     | VARCHAR2(8 CHAR)   |                    |                               |
|  USUARIO_BAJA     | VARCHAR2(8 CHAR)   |                    |                               |
|  USUARIO_MOD      | VARCHAR2(8 CHAR)   |                    |                               |
|  VALOR            | NUMBER(19,2)       |                    |                               |
|  CENTRO_COSTOS_ID | NUMBER(19,0)       |                    |                               |
|  GASTO_ID         | NUMBER(19,0)       |                    |                               |
|  PERIODO_ID       | NUMBER(19,0)       |                    |                               |
|  TIPO_ALERTA_ID   | NUMBER(19,0)       |                    |                               |

---
### ![table](../images/model-table.png "table")  CENTRO_COSTOS

|  Attr             |    Type            |  Default           |   Description                 |
|-------------------|:-------------------|:-------------------|:------------------------------|
|  ID               | NUMBER(19,0)       |                    |                               |
|  FECHA_ALTA       | TIMESTAMP (6) 	 | DEFAULT sysdate    |                               |
|  FECHA_BAJA       | TIMESTAMP (6)      |                    |                               |
|  FECHA_MOD        | TIMESTAMP (6)      |                    |                               |
|  USUARIO_ALTA     | VARCHAR2(8 CHAR)   |                    |                               |
|  USUARIO_BAJA     | VARCHAR2(8 CHAR)   |                    |                               |
|  USUARIO_MOD      | VARCHAR2(8 CHAR)   |                    |                               |
|  DESCRIPCION      | VARCHAR2(50 CHAR)  |                    |                               |
|  SUCURSAL         | NUMBER(1,0)        |                    |                               |

---
### ![table](../images/model-table.png "table")  DISTRIBUCION

|  Attr             |    Type            |  Default           |   Description                 |
|-------------------|:-------------------|:-------------------|:------------------------------|
|  ID               | NUMBER(19,0)       |                    |                               |
|  FECHA_ALTA       | TIMESTAMP (6) 	 | DEFAULT sysdate    |                               |
|  FECHA_BAJA       | TIMESTAMP (6)      |                    |                               |
|  FECHA_MOD        | TIMESTAMP (6)      |                    |                               |
|  USUARIO_ALTA     | VARCHAR2(8 CHAR)   |                    |                               |
|  USUARIO_BAJA     | VARCHAR2(8 CHAR)   |                    |                               |
|  USUARIO_MOD      | VARCHAR2(8 CHAR)   |                    |                               |
|  CENTRO_COSTOS_ID | NUMBER(19,0)       |                    |                               |
|  GASTO_ID         | NUMBER(19,0)       |                    |                

---
### ![table](../images/model-table.png "table") DISTRIBUCION_CENTRO
|  Attr             |    Type            |  Default           |   Description                 |
|-------------------|:-------------------|:-------------------|:------------------------------|
|  OBLIGATORIO		| NUMBER(1,0)		 |                    |                               |
|  PORCENTAJE		| NUMBER(5,2) 		 |                    |                               |
|  DISTRIBUCION_ID	| NUMBER(19,0)		 |                    |                               |
|  CENTRO_COSTOS_ID	| NUMBER(19,0)		 |                    |                               |
                  
---
### ![table](../images/model-table.png "table") ESTADO
|  Attr             |    Type            |  Default           |   Description                 |
|-------------------|:-------------------|:-------------------|:------------------------------|
|  ID 				| NUMBER(19,0)		 |                    |                               |
|  DESCRIPCION 		| VARCHAR2(25 CHAR)	 |                    |                               |
|  DESCRIPCION_CORTA| VARCHAR2(15 CHAR)	 |                    |                               |

---
### ![table](../images/model-table.png "table") GASTO
|  Attr             |    Type            |  Default           |   Description                 |
|-------------------|:-------------------|:-------------------|:------------------------------|
|  ID 				| NUMBER(19,0)		 |                    |                               |
|  FECHA_ALTA 		| TIMESTAMP (6) 	 | DEFAULT sysdate    |                               |
|  FECHA_BAJA 		| TIMESTAMP (6) 	 |                    |                               |
|  FECHA_MOD 		| TIMESTAMP (6) 	 |                    |                               |
|  USUARIO_ALTA 	| VARCHAR2(8 CHAR) 	 |                    |                               |
|  USUARIO_BAJA 	| VARCHAR2(8 CHAR) 	 |                    |                               |
|  USUARIO_MOD 		| VARCHAR2(8 CHAR) 	 |                    |                               |
|  DESCRIPCION 		| VARCHAR2(50 CHAR)  |                    |                               |
|  RISTRA 			| VARCHAR2(69 CHAR)  |                    |                               |
|  GLG_ID 			| NUMBER(19,0)		 |                    |                               |
     				  
---
### ![table](../images/model-table.png "table") GASTO_ALERTA
|  Attr             |    Type            |  Default           |   Description                 |
|-------------------|:-------------------|:-------------------|:------------------------------|
|  ID 				| NUMBER(19,0)		 |                    |                               |
|  VALOR 			| NUMBER(19,2)		 |                    |                               | 
|  GASTO_ID 		| NUMBER(19,0) 		 |                    |                               |
|  PERIODO_ID 		| NUMBER(19,0)		 |                    |                               | 
|TIPO_GASTO_ALERTA_I| NUMBER(19,0) 		 |                    |                               |

---
### ![table](../images/model-table.png "table") GASTO_DISTRIBUCION
|  Attr             |    Type            |  Default           |   Description                 |
|-------------------|:-------------------|:-------------------|:------------------------------|
|  OBLIGATORIO 		| NUMBER(1,0)		 |                    |                               | 
|  PORCENTAJE		| NUMBER(5,2)		 |                    |                               |
|  GASTO_ID 		| NUMBER(19,0)		 |                    |                               | 
|  CENTRO_COSTOS_ID | NUMBER(19,0)		 |                    |                               |
   
---
### ![table](../images/model-table.png "table") GASTO_IMPUESTO
|  Attr             |    Type            |  Default           |   Description                 |
|-------------------|:-------------------|:-------------------|:------------------------------|
|  OBLIGATORIO	 	| NUMBER(1,0)		 |                    |                               | 
|  IMPUESTO_ID	 	| NUMBER(19,0)		 |                    |                               |
|  GASTO_ID 		| NUMBER(19,0)		 |                    |                               |
         					   
---
### ![table](../images/model-table.png "table") IMPUESTO
|  Attr             |    Type            |  Default           |   Description                 |
|-------------------|:-------------------|:-------------------|:------------------------------|
|  ID 				| NUMBER(19,0)		 |                    |                               | 
|  FECHA_ALTA 		| TIMESTAMP (6) 	 | DEFAULT sysdate    |                               |
|  FECHA_BAJA 		| TIMESTAMP (6)		 |                    |                               | 
|  FECHA_MOD 		| TIMESTAMP (6)		 |                    |                               | 
|  USUARIO_ALTA 	| VARCHAR2(8 CHAR)	 |                    |                               | 
|  USUARIO_BAJA 	| VARCHAR2(8 CHAR)	 |                    |                               | 
|  USUARIO_MOD 		| VARCHAR2(8 CHAR)	 |                    |                               | 
|  DEBITO_CREDITO 	| VARCHAR2(1 BYTE)	 | DEFAULT 'D'		  |								  | 
|  DESCRIPCION 		| VARCHAR2(50 CHAR)  |                    |                               | 
|  DISTRIBUIR 		| NUMBER(1,0) 		 | DEFAULT 1 		  | 						      |
|  PORCENTAJE 		| NUMBER(5,2)		 |                    |                               | 
|  RISTRA 			| VARCHAR2(255 CHAR) |                    |                               | 
|  TIPO_IMPUESTO_ID | NUMBER(19,0)		 |                    |                               |
   
---
### ![table](../images/model-table.png "table") MONEDA
|  Attr             |    Type            |  Default           |   Description                 |
|-------------------|:-------------------|:-------------------|:------------------------------|
|  ID 				| NUMBER(19,0) 		 |                    |                               | 
|  FECHA_ALTA		| TIMESTAMP (6) 	 | DEFAULT sysdate    |                               |
|  FECHA_BAJA		| TIMESTAMP (6)		 |                    |                               |  
|  FECHA_MOD		| TIMESTAMP (6)		 |                    |                               |  
|  USUARIO_ALTA		| VARCHAR2(8 CHAR) 	 |                    |                               | 
|  USUARIO_BAJA		| VARCHAR2(8 CHAR)	 |                    |                               |  
|  USUARIO_MOD		| VARCHAR2(8 CHAR)	 |                    |                               |  
|  DESCRIPCION		| VARCHAR2(50 CHAR)	 |                    |                               | 

---
### ![table](../images/model-table.png "table") PAGO" 
|  Attr             |    Type            |  Default           |   Description                 |
|-------------------|:-------------------|:-------------------|:------------------------------|
|  ID				| NUMBER(19,0) 		 |                    |                               | 
|  FECHA_ALTA		| TIMESTAMP (6) 	 | DEFAULT sysdate    |                               |
|  FECHA_BAJA		| TIMESTAMP (6)		 |                    |                               |  
|  FECHA_MOD		| TIMESTAMP (6)		 |                    |                               |  
|  USUARIO_ALTA		| VARCHAR2(8 CHAR)	 |                    |                               |  
|  USUARIO_BAJA		| VARCHAR2(8 CHAR)	 |                    |                               |  
|  USUARIO_MOD		| VARCHAR2(8 CHAR) 	 |                    |                               | 	 
|  CAE_CAI			| NUMBER(19,0)		 |                    |                               |  
|  DOC_NRO_DOCUMENTO| NUMBER(8,0)		 |                    |                               |   
|  DOC_PTO_VENTA	| NUMBER(4,0)		 |                    |                               |  
|  EVIDENCIA_PAGO	| NUMBER(1,0)		 |  DEFAULT 0		  |								  | 
|  FECHA_VENCIMIENTO| DATE				 |                    |                               | 
|  IVA_EXENTO		| NUMBER(19,2)		 |                    |                               |  
|  MONTO			| NUMBER(19,2)		 |                    |                               |  
|  OBSERVACIONES	| VARCHAR2(255 CHAR) |                    |                               |  
|  TITULARIDAD		| NUMBER(1,0)		 |                    |                               |  
|  ESTADO_ID		| NUMBER(19,0) 		 |                    |                               |   
|  GASTO_ID			| NUMBER(19,0)		 |                    |                               |  
|  MONEDA_ID		| NUMBER(19,0)		 |                    |                               |  
|  PROVEEDOR_ID		| NUMBER(19,0)		 |                    |                               |  
|  SOLICITANTE_ID	| NUMBER(19,0)		 |                    |                               |  
|  SUCURSAL_PAGO_ID | NUMBER(19,0)		 |                    |                               |  
|  COMPROBANTE_ID	| NUMBER(19,0)		 |                    |                               |  
|  FACTURA_ID		| NUMBER(19,0)		 |                    |                               |
   	
---
### ![table](../images/model-table.png "table") PAGO_ALERTA" 
|  Attr             |    Type            |  Default           |   Description                 |
|-------------------|:-------------------|:-------------------|:------------------------------|
|  FECHA_ALTA		| TIMESTAMP (6) 	 | DEFAULT sysdate    |                               |
|  FECHA_BAJA		| TIMESTAMP (6)		 |                    |                               |  
|  FECHA_MOD		| TIMESTAMP (6)		 |                    |                               |  
|  USUARIO_ALTA		| VARCHAR2(8 CHAR)	 |                    |                               |  
|  USUARIO_BAJA		| VARCHAR2(8 CHAR)	 |                    |                               |  
|  USUARIO_MOD		| VARCHAR2(8 CHAR)	 |                    |                               |  
|  PAGO_ALERTA_ID	| NUMBER(19,0)		 |                    |                               |  
|  PAGO_ID			| NUMBER(19,0)		 |                    |                               |  
|  ALERTA_ID		| NUMBER(19,0)		 |                    |                               |

---
### ![table](../images/model-table.png "table") PAGO_DISTRIBUCION" 
|  Attr             |    Type            |  Default           |   Description                 |
|-------------------|:-------------------|:-------------------|:------------------------------|
|  FECHA_ALTA		| TIMESTAMP (6) 	 | DEFAULT sysdate    |                               |
|  FECHA_BAJA		| TIMESTAMP (6)		 |                    |                               |  
|  FECHA_MOD		| TIMESTAMP (6)		 |                    |                               |  
|  USUARIO_ALTA		| VARCHAR2(8 CHAR)	 |                    |                               |  
|  USUARIO_BAJA		| VARCHAR2(8 CHAR)	 |                    |                               |  
|  USUARIO_MOD		| VARCHAR2(8 CHAR)	 |                    |                               |  
|  MONTO			| NUMBER(19,2)		 |                    |                               |   
|  PORCENTAJE		| NUMBER(5,2)		 |                    |                               |  
|  PAGO_ID			| NUMBER(19,0)		 |                    |                               |  
|  CENTRO_COSTOS_ID | NUMBER(19,0)	  	 |                    |                               |
      	   
---
### ![table](../images/model-table.png "table") PAGO_IMPUESTO" 
|  Attr             |    Type            |  Default           |   Description                 |
|-------------------|:-------------------|:-------------------|:------------------------------|
|  FECHA_ALTA		| TIMESTAMP (6) 	 | DEFAULT sysdate    |                               |
|  FECHA_BAJA		| TIMESTAMP (6)		 |                    |                               |  
|  FECHA_MOD		| TIMESTAMP (6)		 |                    |                               |  
|  USUARIO_ALTA		| VARCHAR2(8 CHAR)	 |                    |                               |  
|  USUARIO_BAJA		| VARCHAR2(8 CHAR)	 |                    |                               |  
|  USUARIO_MOD		| VARCHAR2(8 CHAR)	 |                    |                               |  
|  MONTO			| NUMBER(19,2)		 |                    |                               |   
|  PAGO_ID			| NUMBER(19,0)		 |                    |                               |  
|  IMPUESTO_ID		| NUMBER(19,0)		 |                    |                               |

---
### ![table](../images/model-table.png "table") PERIODO" 
|  Attr             |    Type            |  Default           |   Description                 |
|-------------------|:-------------------|:-------------------|:------------------------------|
|  ID				| NUMBER(19,0)		 |                    |                               |  
|  DESCRIPCION		| VARCHAR2(10 CHAR)	 |                    |                               |

---
### ![table](../images/model-table.png "table") PERMISO_USUARIO" 
|  Attr             |    Type            |  Default           |   Description                 |
|-------------------|:-------------------|:-------------------|:------------------------------|
|  FECHA_ALTA		| TIMESTAMP (6) 	 | DEFAULT sysdate    |                               |
|  FECHA_BAJA		| TIMESTAMP (6)		 |                    |                               |  
|  FECHA_MOD		| TIMESTAMP (6)  	 |                    |                               |
|  USUARIO_ALTA		| VARCHAR2(8 CHAR)	 |                    |                               |  
|  USUARIO_BAJA		| VARCHAR2(8 CHAR)	 |                    |                               |  
|  USUARIO_MOD		| VARCHAR2(8 CHAR)	 |                    |                               |  
|  USUARIO			| VARCHAR2(8 CHAR)	 |                    |                               |  
|  GLG_ID			| NUMBER(19,0)		 |                    |                               |  
|  ACCION			| VARCHAR2(1 BYTE)	 |                    |                               |

---
### ![table](../images/model-table.png "table") PROVEEDOR" 
|  Attr             |    Type            |  Default           |   Description                 |
|-------------------|:-------------------|:-------------------|:------------------------------|
|  ID				| NUMBER(19,0)		 |                    |                               |  
|  FECHA_ALTA		| TIMESTAMP (6) 	 | DEFAULT sysdate    |                               |
|  FECHA_BAJA		| TIMESTAMP (6)		 |                    |                               |  
|  FECHA_MOD		| TIMESTAMP (6)		 |                    |                               |  
|  USUARIO_ALTA		| VARCHAR2(8 CHAR)	 |                    |                               |  
|  USUARIO_BAJA		| VARCHAR2(8 CHAR)	 |                    |                               |  
|  USUARIO_MOD		| VARCHAR2(8 CHAR)	 |                    |                               |  
|  CUIT				| VARCHAR2(11 CHAR)	 |                    |                               |  
|  DIRECCION		| VARCHAR2(100 CHAR) |                    |                               |  
|  HABILITADO		| NUMBER(1,0) 		 | DEFAULT 1		  |								  | 
|  LOCALIDAD		| VARCHAR2(100 CHAR) |                    |                               |  
|  PROVINCIA		| VARCHAR2(100 CHAR) |                    |                               |  
|  RAZON_SOCIAL		| VARCHAR2(100 CHAR) |                    |                               |
	
---
### ![table](../images/model-table.png "table") TIPO_COMPROBANTE" 
|  Attr             |    Type            |  Default           |   Description                 |
|-------------------|:-------------------|:-------------------|:------------------------------|
|  ID				| NUMBER(19,0)		 |                    |                               |  
|  FECHA_ALTA		| TIMESTAMP (6)		 |  DEFAULT sysdate   |							      |
|  FECHA_BAJA		| TIMESTAMP (6)		 |                    |                               |  
|  FECHA_MOD		| TIMESTAMP (6)		 |                    |                               |  
|  USUARIO_ALTA		| VARCHAR2(8 CHAR)	 |                    |                               |  
|  USUARIO_BAJA		| VARCHAR2(8 CHAR)	 |                    |                               |  
|  USUARIO_MOD		| VARCHAR2(8 CHAR)	 |                    |                               |  
|  DESCRIPCION		| VARCHAR2(50 CHAR)	 |                    |                               |   

---
### ![table](../images/model-table.png "table") TIPO_FACTURA" 
|  Attr             |    Type            |  Default           |   Description                 |
|-------------------|:-------------------|:-------------------|:------------------------------|
|  ID				| NUMBER(19,0)		 |                    |                               |  
|  FECHA_ALTA		| TIMESTAMP (6) 	 | DEFAULT sysdate    |                               |
|  FECHA_BAJA		| TIMESTAMP (6)		 |                    |                               |  
|  FECHA_MOD		| TIMESTAMP (6)		 |                    |                               |  
|  USUARIO_ALTA		| VARCHAR2(8 CHAR)	 |                    |                               |  
|  USUARIO_BAJA		| VARCHAR2(8 CHAR)	 |                    |                               |  
|  USUARIO_MOD		| VARCHAR2(8 CHAR)	 |                    |                               |  
|  DESCRIPCION		| VARCHAR2(50 CHAR)	 |                    |                               |
  
---
### ![table](../images/model-table.png "table") TIPO_GASTO_ALERTA" 
|  Attr             |    Type            |  Default           |   Description                 |
|-------------------|:-------------------|:-------------------|:------------------------------|
|  ID				| NUMBER(19,0)		 |                    |                               |  
|  FECHA_ALTA		| TIMESTAMP (6) 	 | DEFAULT sysdate    |                               |
|  FECHA_BAJA		| TIMESTAMP (6)		 |                    |                               |  
|  FECHA_MOD		| TIMESTAMP (6)		 |                    |                               |  
|  USUARIO_ALTA		| VARCHAR2(8 CHAR)	 |                    |                               |  
|  USUARIO_BAJA		| VARCHAR2(8 CHAR)	 |                    |                               |  
|  USUARIO_MOD		| VARCHAR2(8 CHAR)	 |                    |                               |  
|  DESCRIPCION		| VARCHAR2(50 CHAR)	 |                    |                               |
   	
---
### ![table](../images/model-table.png "table") TIPO_IMPUESTO" 
|  Attr             |    Type            |  Default           |   Description                 |
|-------------------|:-------------------|:-------------------|:------------------------------|
|  ID				| NUMBER(19,0)		 |                    |                               |  
|  FECHA_ALTA		| TIMESTAMP (6) 	 | DEFAULT sysdate    |                               |
|  FECHA_BAJA		| TIMESTAMP (6)		 |                    |                               |  
|  FECHA_MOD		| TIMESTAMP (6)		 |                    |                               |  
|  USUARIO_ALTA		| VARCHAR2(8 CHAR)	 |                    |                               |  
|  USUARIO_BAJA		| VARCHAR2(8 CHAR)	 |                    |                               |  
|  USUARIO_MOD		| VARCHAR2(8 CHAR)	 |                    |                               |  
|  DESCRIPCION		| VARCHAR2(50 CHAR)	 |                    |                               |

---
### ![table](../images/model-table.png "table") USUARIO" 
|  Attr             |    Type            |  Default           |   Description                 |
|-------------------|:-------------------|:-------------------|:------------------------------|
|  LEGAJO			| VARCHAR2(8 CHAR)  
|  APELLIDO			| VARCHAR2(50 CHAR)  
|  EMAIL			| VARCHAR2(50 CHAR)  
|  NOMBRE			| VARCHAR2(50 CHAR)  
|  PERFIL			| VARCHAR2(10 CHAR)  
|  CENTRO_COSTOS_ID | NUMBER(19,0)
     	   
      
---
[Go to Top](#markdown-header-database-model-pagossucursal)  
[Back to Home](/README.md) | [Back to General Doc](/docs/readme.md) | [Back to Delivery](/docs/markdown/delivery.md)