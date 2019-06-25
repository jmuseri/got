# Classes Documentation - GOT

[Back to Home](/README.md) | [Back to General Doc](/docs/readme.md) | [Back to Delivery](/docs/markdown/delivery.md) | [Go Back](/docs/markdown/classes.md)

## Class Catalog
### com.sa.bbva.got.configuration
* [CRUDStatus](#markdown-header-crudstatus)
* [Page](#markdown-header-page)
* [StatusBase](#markdown-header-statusbase)

## ![class](../images/class.png "class") SpringSecConfig
---
### com/sa/bbva/got/configuration/
### Diagram
![class](../diagrams/bean/SpringSecConfig.png "class")

### Inheritance
  * WebSecurityConfigurerAdapter.java

### Properties

### Methods
  * protected void configure(HttpSecurity httpSecurity)

## ![class](../images/class.png "class") SwaggerConfig
---
### com/sa/bbva/got/configuration/
### Diagram
![class](../diagrams/bean/SwaggerConfig.png "class")

### Inheritance
  * WebMvcConfigurationSupport.java

### Properties

### Methods
  * public Docket parametriaApi()
  * public Docket funcionalApi()
  * private ApiInfo metaData()
  * protected void addResourceHandlers(ResourceHandlerRegistry registry)

## ![class](../images/class.png "class") WebConfig
---
### com/sa/bbva/got/configuration/
### Diagram
![class](../diagrams/bean/WebConfig.png "class")

### Inheritance

### Properties

### Methods
  * ServletRegistrationBean<?> h2servletRegistration()

---
[Go to Top](#markdown-header-classes-documentation-got)  
[Back to Home](/README.md) | [Back to General Doc](/docs/readme.md) | [Back to Delivery](/docs/markdown/delivery.md) | [Go Back](/docs/markdown/classes.md)
