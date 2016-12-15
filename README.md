# Apache Sling QuickStart Project
Sample for creating Apache Sling Projects.

## Requirements
* Java 8
* Maven 3.3.x

## Geting started
* Download Sling Launchpad 8: http://apache.uniminuto.edu/sling/org.apache.sling.launchpad-8.jar
* Start LaunchPad from console: java -jar org.apache.sling.launchpad-8.jar
* Clone Repository
* Run on the project: mvn clean install -P autoInstallBundle
* For the simplicity of the setup you can give privileges to all users. (don't do this in real projects)
    * POST localhost:8080/content.modifyAce.html
    * Header: Authorization: Basic YWRtaW46YWRtaW4=
    * Param: privilege@jcr:all: granted
    * Param: principalId:everyone



## Release History

## Author
Miklós Csere