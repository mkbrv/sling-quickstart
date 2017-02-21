# Apache Sling QuickStart Project
Sample for creating Apache Sling Projects.

## Requirements
* Java 8
* Maven 3.3.x

## Geting started
* Clone Repository
* Start Sling from sling-quickstart-server: mvn install
* Run on the project: mvn clean install -P autoInstallBundle
* For the simplicity of the setup you can give privileges to all users. (don't do this in real projects)
    * POST localhost:8080/content.modifyAce.html
    * Header: Authorization: Basic YWRtaW46YWRtaW4=
    * Param: privilege@jcr:all: granted
    * Param: principalId:everyone



## Release History

## Author
Miklós Csere