# GOT - Gestion OnLine de Tramites - Rest Services
Gestion OnLine de Tramites - Services Application.  
© 2019. Sistemas Activos. All rights reserved.

[![Latest Stable Version](docs/images/stable-version.png)](#)  [![Latest Unstable Version](docs/images/unstable-version.png)](#)

## Description
Servicios para la gestion de tramites online en Web Empresas BBVA.

## Versions
| Version  |    Date    | Description                    |  Author     |   Revision   |
|----------|:-----------|:-------------------------------|:------------|--------------|
| 0.0.1    | 2019/05/15 | Initial Application Template   |    SA       |      0       |
| 0.0.2    | 2019/05/22 | Services Template              |    SA       |      0       |
| 0.0.3    | 2019/05/27 | Response Services              |    SA       |      0       |
| 0.1.0    | 2019/05/29 | Groups & Sector api added      |    SA       |      0       |
| 0.1.1    | 2019/06/03 | Parameters Api added           |    SA       |      0       |

## Content

### General
* [Customer](#markdown-header-customer)
* [Key Features](#markdown-header-key-features)
* [Requirements](#markdown-header-requirements)
* [Getting Start and Installation](#markdown-header-getting-start-and-installation)
* [How to run tests](#markdown-header-how-to-run-tests)
* [Deployment instructions](#markdown-header-deployment-instructions)
* [Quick Links](#markdown-header-quick-links)
* [Get Help](#markdown-header-get-help)
* [Additional Information](#markdown-header-additional-information)

### Documentation
* [Development Requirements](docs/markdown/requirements.md)
* [Architecture](docs/markdown/architecture.md)
* [DataBase](docs/markdown/database.md)
* [API Services Documentation](docs/markdown/api.md)
* [Technical Documentation](docs/markdown/technical.md)
* [Delivery Documentation](docs/markdown/delivery.md)
* [Documentation Guide](docs/markdown/documentation-guide.md)

## Customer
**BBVA** Banco Frances   

Manager Reference: 

* Daniel Hernandez:      [daniel.hernandez1@bbva.com]()

Technical Reference: 

* Javier da Silva:       [javier.dasilva@bbva.com]()
* Matias Hernandez:      [matias.hernandez@bbva.com]()
* Juan Pablo Garcia:     [juanp.garcia@bbva.com]()

## Key Features

* Maven
* Spring Boot Framework
* Swagger UI Documentation
* JPA Persistence
* Lonbok library to reduce code
* Tests

## Requirements

* Use just GET & POST request methods (Webseal limit)
* Label text Configuration

## Getting Start and Installation

* Download project from:
    
    git clone https://sistemas_activos@bitbucket.org/sa_bbva_got/got.git

* mvn clean
* mvn spring-boot:run
* http://localhost:8080 in browser
* or mvn clean package -Dmaven.test.skip=true
* or mvn clean package -DskipTests
* java -jar target/got-services-0.1.0.jar  and
* http://localhost:8080/swagger-ui.html shows API documentation


## How to run tests

## Deployment instructions

## Get Help

Sistemas Activos srl

* German Gambera  german.gambera@sistemasactivos.com
* Eduardo Gauna   eduardo.gauna@sistemasactivos.com

## Contributing

## Additional Information

---
[Go to Top](#markdown-header-got-gestion-online-de-tramites-rest-services)  