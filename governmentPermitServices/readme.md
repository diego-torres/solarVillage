# Government web services simulation
This project presents 2 web services to simulate permit processing in the government servers: ElectricalPermit and StructuralPermit
are SOAP web services that present operations for create, query status and rescind permits.
## Maven Deploy
```
mvn clean install -P war -Djavax.xml.accessExternalSchema=all
```
Creates jar and war files to deploy the web services.
