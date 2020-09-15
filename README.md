# Rick and Morty API
# Content list
* [Project purpose](#purpose)
* [Project dependencies](#dependencies)
* [Startup instructions](#startup)
* [Author](#author)

# <a name="purpose"></a>Project purpose
One request randomly publishes a wiki about one character in the universe of the cartoon series Rick & Morty.
The second takes a string as an argument, and returns a list of all characters whose name contains a search string. 
Regularly, at a clear interval, web application loads data from a third-party service into an internal database.
API usage:
* http://localhost:8080/character/random: returns random character
* http://localhost:8080/character?name=<string>: returns a list of all characters whose name contains a search string
* http://localhost:8080/v2/api-docs: swagger2 automatic API documentation
<hr/>

# <a name="dependencies"></a>Project dependencies
* Java SE Development Kit: 11
* Maven: 3.6.3
* spring-boot-starter-data-jpa
* spring-boot-starter-web
* spring-boot-devtools
* postgresql
* lombok
* jackson-core: 2.11.2
* jackson-annotations: 2.11.2
* jackson-databind: 2.11.2
* springfox-swagger2: 3.0.0
* springfox-swagger-ui: 3.0.0
* springdoc-openapi-ui: 1.2.32
<hr>

# <a name="#startup"></a>Startup instructions
* prerequisites: JDK SE 11+, Spring Boot, PostgresQL; Jetbrains IDE, Git

We start the web server from the starting point - RickandmortyApplication. After that, the server sequentially
  executes requests to the external API, parses the received data and saves it 
  to a local database (the settings for which we have specified in the project configuration file).
   By default, the web server executes 20 queries on different pages, each of which contains
    a result for 20 characters. You can query the above APIs for the local server as soon as
     you deploy the web server and start synchronizing data. 

# <a name="author"></a>Author  

Oleksii Lehedza: https://github.com/olehedza                                               

