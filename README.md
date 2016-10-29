# 1. Solar Village
Solar Village home work assignment for advanced process development with Red Hat JBoss BPM Suite
# 2. Virtual Machine environment

This POC uses the gpte-bpms-advanced virtual machine from the advanced course.
You can download a copy of the VM from:

* Direct download: https://drive.google.com/open?id=0B8mmXW6hJKdiaVpndWxFV3Nmbkk.
* BitTorrent: https://github.com/gpe-mw-training/advanced-process-development-labs-etc/raw/master/etc/gpte-bpms-advanced-630.vdi.torrent.

# 3. Instructions
## 3.1. Build and deploy the web services and models
### 3.1.1. Clone the repository to your local environment.

It is required to clone the remote maven repository in order to build and deploy the jars and wars that will serve the government permit services:

* Add electrical/structural permit requests
* Rescind electrical/structural permit requests
* Query the government permit requests status

1. Create a folder to organize your git repositories.

  ```
  $ mkdir ~/gits
  $ cd ~/gits
  ```

2. Execute the git clone command:  

  ```
  $ git clone https://github.com/diego-torres/solarVillage.git
  ```

### 3.1.2. Build the Maven components

The war and jar components that will serve the government permit SOAP web services are built during this step. We are using Maven, and as an standard practice, those components will be generated in their respective `target` folders.

Using the git repository that we cloned in the previous step, execute the maven install command:

```
$ cd ~/gits/governmentPermitServices
$ mvn install -P war
```

> **Note**: the war profile is configured to generate a war component for the service tier project.

### 3.1.3. Deploy the SOAP Web Server components

The war and jar components that provide the SOAP web service finctionality are `Spring-Boot` components that can be deployed to any java web server environment.

Copy the target resources from the Maven projects to JBoss Server:

```
$ cp ~/gits/governmentPermitServices/permitServiceTier/target/governmentPermitServices.war \
 ~/lab/bpms/standalone/deployments
```

> **Note:** the POM for OrderPermit project includes the dependency instruction for governmentPermitServices parent project.

## 3.2. Build and deploy the business assets in Kie Server
### 3.2.1. JBoss EAP environment preparation

In this section we will deploy the _Solar Village_ project kjar to our _Kie Server_.

Just to refresh our memory: The _Kie Server_ is a Java web application that allow us to expose rules and business process to be executed remotely using REST and JMS interfaces. The difference between the _Kie Server_ and the _business-central_ is that _Kie Server_ is focused in remote execution, while _business-central_ offers a complete authoring evironment, including process execution features and a remote API.

The _Kie Server_ is a web application that can be deployed in _JBoss EAP_, _Wildfly_, _Tomcat_ or any other Java application server or web container. It works by accessing kjars from a Maven repository and exposing its rules and processes throught HTTP or JMS.

When a _Kie Server_ uses the _Business-Central_ as its _Controller_, it happens to be a _Managed Kie Server_.

1. In some cases, deploying kjar projects to a _Managed Kie Server_, the process behavior is constrained by some overlapping properties and resources, hence we will undeploy the _business_central_ from our JBoss EAP installation by executing the following command:

  ```
  $ mv ~/lab/bpms/standalone/deployments/dashbuilder.war.deployed ~/lab/bpms/standalone/deployments/dashbuilder.war.undeploy
  $ mv ~/lab/bpms/standalone/deployments/business-central.war.deployed ~/lab/bpms/standalone/deployments/business-central.war.undeploy
  ```
2. Modify the startup parameters that register the _business-central_ as the _Kie Server_ controller by editing the file `~/lab/bpms/bin/standalone.conf`:

  ```
  JAVA_OPTS="$JAVA_OPTS -Dorg.kie.server.id=kie-server-advanced-vm "
  JAVA_OPTS="$JAVA_OPTS -Dorg.kie.server.location=http://localhost:8080/kie-server/services/rest/server "
  # JAVA_OPTS="$JAVA_OPTS -Dorg.kie.server.controller=http://localhost:8080/business-central/rest/controller "
  JAVA_OPTS="$JAVA_OPTS -Dorg.kie.server.controller.user=kieserver "
  ```
  > **Note**: We are commenting the line that specifies the kie server controller (-Dorg.kie.server.controller)

3. Restart the JBoss EAP instance.
4. Test the _Kie Server_ availabillity by curl:

  ```
  $ curl -u 'kieserver:kieserver1!' http://localhost:8080/kie-server/services/rest/server
  ```
  Should return the main information about the _Kie Server_ installation:

  ```
  <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
  <response type="SUCCESS" msg="Kie Server info">
      <kie-server-info>
          <capabilities>BRM</capabilities>
          <capabilities>BPM-UI</capabilities>
          <capabilities>BPM</capabilities>
          <capabilities>KieServer</capabilities>
          <location>http://localhost:8080/kie-server/services/rest/server</location>
          <messages>
              <content>Server KieServerInfo{serverId='kie-server-advanced-vm', version='6.4.0.Final-redhat-3', location='http://localhost:8080/kie-server/services/rest/server'}started successfully at Sat Oct 29 00:40:44 CEST 2016</content>
              <severity>INFO</severity>
              <timestamp>2016-10-29T00:40:44.121+02:00</timestamp>
          </messages>
          <name>kie-server-advanced-vm</name>
          <id>kie-server-advanced-vm</id>
          <version>6.4.0.Final-redhat-3</version>
      </kie-server-info>
  </response>
  ```

### 3.2.2. Create and start a kie-server container.

To create the container we use the REST API by sending a PUT HTTP request to the endpoint http://localhost:8080/kie-server/services/rest/server/containers/order_permit, where `order_permit` is the name and the ID of the container. This request uses authentication and we must send the kjar artifact maven information (GAV).

1. Execute the curl PUT request command:

  ```
  $ curl -X PUT -H "Accept:application/json" -H "Content-Type:application/json" --user jboss:bpms \
  -d '{"release-id":{"group-id":"org.solarVillage","artifact-id":"OrderPermit","version":"8.3"}}' \
  "http://localhost:8080/kie-server/services/rest/server/containers/order_permit"
  ```

2. You should receive a response like the following:

  ```
  {
    "type" : "SUCCESS",
    "msg" : "Container order_permit successfully deployed with module org.solarVillage:OrderPermit:8.3.",
    "result" : {
      "kie-container" : {
        "status" : "STARTED",
        "messages" : [ ],
        "container-id" : "order_permit",
        "release-id" : {
          "version" : "8.3",
          "group-id" : "org.solarVillage",
          "artifact-id" : "OrderPermit"
        },
        "resolved-release-id" : {
          "version" : "8.3",
          "group-id" : "org.solarVillage",
          "artifact-id" : "OrderPermit"
        },
        "config-items" : [ ]
      }
    }
  }
  ```


# 4. Execute the processes
1. Start a **RESIDENTIAL** permit request:

  ```
  $ curl -X POST -H "Accept: application/json" -H "Content-Type: application/json" --user jboss:bpms \
  -d '{"address":"123 main street", "beneficiary":"John Doe", \
  "buildingDescription":"big house close to the park", "electricalContractNumber":"32019283749"}' \
  http://localhost:8080/kie-server/services/rest/server/containers/order_permit/processes/residential-permit-process/instances
  ```

  * watch the government processes pass through the log and finally state the approval or denial of the permits.
2. Start a **HOME OWNER ASSOCIATION** permit:

  ```
  $ curl -X POST -H "Accept: application/json" -H "Content-Type: application/json" --user jboss:bpms \
  -d '{"address":"123 main street", "beneficiary":"John Lee", \
  "buildingDescription":"big house close to the park", "electricalContract":"32019283749", \
  "associationMeetingDate":"01/26/2017"}' \
  http://localhost:8080/kie-server/services/rest/server/containers/order_permit/processes/hoa_permit_process/instances
  ```

  > Use a date 8 days or more in the future so the task can be assigned to the _sales_ group, otherwise it will be assigned to _executives_ automatically.

  * use _sales_ or _executives_ roles to claim tasks for Home owner association approval.
  * After Home owner association approval, watch the government permits pass through the log.

3. Claim a Home owner association approval task.

  1. Add a _sales_ user by executing the following command:

    ```
    $ ~/lab/bpms/bin/add-user.sh -u sales_user -p secret -g sales -a -s -sc ~/lab/bpms/standalone/configuration
    ```

  2. Add an _executive_ user:

    ```
    $ ~/lab/bpms/bin/add-user.sh -u executive_user -p secret -g executives -a -s -sc ~/lab/bpms/standalone/configuration
    ```

  2. List the available tasks to be claimed by the _sales_ group:

    ```
    $ curl -X GET -H "Accept: application/json" --user jboss:bpms "http://localhost:8080/kie-server/services/rest/server/queries/tasks/instances/pot-owners?groups=sales"
    ```

  3. Claim a task:

    ```
    $ curl -X PUT -H "Accept: application/json" --user sales_user:secret "http://localhost:8080/kie-server/services/rest/server/containers/order_permit/tasks/1/states/claimed"
    ```

    > **NOTE**: This can only be done when authenticating as a user who is a potential owner of the task. If the user is not a potential owner, an exception message is returned.

  4. List claimed tasks (owned by a user):

    ```
    $ curl -X GET -H "Accept: application/json" --user sales_user:secret "http://localhost:8080/kie-server/services/rest/server/queries/tasks/instances/owners"
    ```

4. Approve the Home owner association permit.

  Complete the claimed task with approval result:

  ```
  $ curl -X PUT -H "Accept: application/json" --user sales_user:secret "http://localhost:8080/kie-server/services/rest/server/containers/order_permit/tasks/1/states/completed"
  ```

# 5. Conclusion
  JBPM Business Suite provides an adequate tool for development of the Solar Village Business Processes. One of the main factors considered in this evaluation were the options for scalability and out-of-the-box tools like its powerful REST API available throught the kie-server.
  JBPM Business Suite may not be an ideal development tool for integration services, it is better suitable for business process shaping, mapping and monitoring through its wait states.
