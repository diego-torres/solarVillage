# solarVillage
Solar Village home work assignment for advanced process development with Red Hat JBoss BPM Suite
# Virtual Machine environment

This POC uses the gpte-bpms-advanced virtual machine from the advanced course.
You can download a copy of the VM from:

* Direct download: https://drive.google.com/open?id=0B8mmXW6hJKdiaVpndWxFV3Nmbkk.
* BitTorrent: https://github.com/gpe-mw-training/advanced-process-development-labs-etc/raw/master/etc/gpte-bpms-advanced-630.vdi.torrent.

# Instructions
## Build and deploy the web services and models
### 1. Clone the repository to your local environment.

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

### 2. Build the Maven components

The war and jar components that will serve the government permit SOAP web services are built during this step. We are using Maven, and as an standard practice, those components will be generated in their respective _target_ folders.

Using the git repository that we cloned in the previous step, execute the maven install command:

```
$ cd ~/gits/governmentPermitServices
$ mvn install -P war
```

> **Note**: the war profile is configured to generate a war component for the service tier project.

### 3. Deploy the SOAP Web Server components

The war and jar components that provide the SOAP web service finctionality are Spring-Boot components that can be deployed to any java web server environment.

Copy the target resources from the Maven projects to JBoss Server:

```
$ cp ~/gits/governmentPermitServices/permitServiceTier/target/governmentPermitServices.war \
 ~/lab/bpms/standalone/deployments
```

> **Note:** the POM for OrderPermit project includes the dependency instruction for governmentPermitServices parent project.

# Build and deploy the business assets in BPM Suite
## Using Business Central
### 1. Clone the repository to your BPM Suite Organization.

BPM Suite provides a web based graphical interface to interact with the business assets and the Kie-server.

1. Login to BPMS using jboss/bpms username and password.
2. If required add an organization from the _Authoring > Administration > Organizational Units > Manage Organizational Units_ option.
3. Use the _Authoring > Administration > Repositories > Clone Repository_ option to clone the git repository with the following details:
  * **Business Central userId / password** : jboss / bpms
  * **Organizational Unit Manager** : _[Use your organization name]_
  * **Repository Name** : solarvillage
  * **Git URL**: https://github.com/diego-torres/solarVillage.git

### 2. Build/Deploy the OrderPermit project.

By following these steps, you will be able to create the kjar component in your Busuness Central repository so you can later deploy it to a container.

1. Using BPMS, navigate to _Authoring > Project Authoring_
2. Using the Project Editor select the _Build > Build & Deploy_ option
3. Using the _Process Management > Process Definitions_, check that the different process definitions have been deployed to your business central:

  * RescindGovernmentPermit:2.0
  * GovernmentPermitMonitor:3.0
  * GovernmentOrderPermit:7.1
  * HoaPermitOrder:2.3
  * GovernmentPermitMonitorWrapper:1.4
  * ResidentialPermitOrder:1.1

### 3. Create and start a kie-server container.

By following these steps, you will enable to add a container to your kie-server instance using the integration with business-central.
Each container is related to a GAV - Group, ArtifactId and Version, of a Maven artifact. This maven artifact must be a kjar and once we create a container from it, we can execute its processes and rules.

From the previous step, we deployed our kjar for the OrderPermit project to the business-central repository, so the business-central knows about the existence of the kjar required to create a kie-server container.

1. Using BPMS, navigate to _Deploy > Rule Deployments_
2. Select a __SERVER TEMPLATE__
3. Use the _Add Container_ option
4. From the GAV options, select _org.solarVillage.OrderPermit:8.3_
5. Use **order_permit** as the container name
6. Click Next.
7. Use the following details for the _Process Configuration_:
  * **Runtime strategy**: Per Process instance
  * **Kie Base Name**: kb-order_permit
  * **Kie Session Name**: ks-order_permit
  * **Merge mode (deployment descriptor)**: Merge Collections
8. Click Finish.
9. Click the Start button of the KIE CONTAINER you have just created.

### 5. Undeploy the business-central

This step is required to avoid an exception related to the timerService-id (Issue #1).

The gpte-bpms-advanced virtual machine has the business-central web application, which interferes with some functionallity of the kie-server web application, hence we will undeploy the business-central to allow the kie-server to execute the processes without any constraints. One of the observed constraints is that the Human Task timers and the Timer Catch components throw an exception when triggered:

```
java.lang.RuntimeException: No scheduler found for order_permit-timerServiceId
 	at org.jbpm.persistence.timer.GlobalJpaTimerJobInstance.call(GlobalJpaTimerJobInstance.java:71)
```

In order to undeploy the business-central execute the following command in your VM Terminal:

```
$ mv ~/lab/bpms/standalone/deployments/business-central.war.deployed \
~/lab/bpms/standalone/deployments/business-central.war.undeploy
```

Now you are ready to [Execute the processes](#execute-the-processes).

# Use kie-server only installation
## Install kie-server
## Deploy container and kjar

# Execute the processes
1. Start a **RESIDENTIAL** permit:

  ```
  $ curl -X POST -H "Accept: application/json" -H "Content-Type: application/json" --user jboss:bpms \
  -d '{"address":"123 main street", "beneficiary":"John Doe", \
  "buildingDescription":"big house close to the park", "electricalContractNumber":"32019283749"}' \
  http://localhost:8080/kie-server/services/rest/server/containers/order_permit/processes/residential-permit-process/instances
  ```

  * watch the government processes pass through the log and finally state the approval or denial of the permits.
2. Start a **HOME OWNER ASSOCIATION** permit:

  ```
  curl -X POST -H "Accept: application/json" -H "Content-Type: application/json" --user jboss:bpms \
  -d '{"address":"123 main street", "beneficiary":"John Lee", \
  "buildingDescription":"big house close to the park", "electricalContract":"32019283749", \
  "associationMeetingDate":"01/26/2017"}' \
  http://localhost:8080/kie-server/services/rest/server/containers/order_permit/processes/hoa_permit_process/instances
  ```

  > Use a date 8 days or more in the future so the task can be assigned to the _sales_ group, otherwise it will be assigned to _executives_ automatically.

  * use _sales_ or _executives_ roles to claim tasks for Home owner association approval.
  * After Home owner association approval, watch the government permits pass through the log.

3. Claim a Home owner association approval task.
4. Approve the Home owner association permit.
5. List completed and in progress permits.
