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

The war and jar components that will serve the government permit SOAP web services are built during this step. We are using Maven, and as an standard practice, those components will be generated in their respective __target__ folders.

Using the git repository that we cloned in the previous step, execute the maven install command:

```
$ cd ~/gits/governmentPermitServices
$ mvn install -P war
```

> **Note**: the war profile is configured to generate a war component for the service tier project.

### 3. Deploy the SOAP Web Server components

The war and jar components that provide the SOAP web service finctionality are Spring-Boot components that can be deployed to any java web server environment.

1. Copy the target resources from the Maven projects to JBoss Server:

```
$ cp ~/gits/governmentPermitServices/permitServiceTier/target/governmentPermitServices.war ~/lab/bpms/standalone/deployments
```

> **Note:** the POM for OrderPermit project includes the dependency instruction for governmentPermitServices parent project.

# Build and deploy the business assets in BPM Suite
## Using Business Central
### 1. Clone the repository to your BPM Suite Organization.
### 2. Deploy the OrderPermit project.
### 3. Using the Deploy > Rule deployments option: Create a container based in the OrderPermit deployment.
### 4. Start the container.
### 5. If you are using the same VM for the Business Central and the kie-server: undeploy the business-central

> **Note:** This is required to avoid an exception related to the timerService-id (Issue #1).

# Use kie-server only installation
## Install kie-server
## Deploy container and kjar

# Execute examples
1. Start a **RESIDENTIAL** permit:

  ```
  $ curl -X POST -H "Accept: application/json" -H "Content-Type: application/json" --user jboss:bpms -d '{"address":"123 main street", "beneficiary":"John Doe", "buildingDescription":"big house close to the park", "electricalContractNumber":"32019283749"}' http://localhost:8080/kie-server/services/rest/server/containers/order_permit/processes/residential-permit-process/instances
  ```

  * watch the government processes pass through the log and finally state the approval or denial of the permits.
2. Start a **HOME OWNER ASSOCIATION** permit:

  ```
  curl -X POST -H "Accept: application/json" -H "Content-Type: application/json" --user jboss:bpms -d '{"address":"123 main street", "beneficiary":"John Lee", "buildingDescription":"big house close to the park", "electricalContract":"32019283749", "associationMeetingDate":"01/26/2017"}' http://192.168.0.7:8080/kie-server/services/rest/server/containers/order_permit/processes/hoa_permit_process/instances
  ```

  > Use a date 8 days or more in the future so the task can be assigned to the __sales__ group, otherwise it will be assigned to __executives__ automatically.

  * use __sales__ or __executives__ roles to claim tasks for Home owner association approval.
  * After Home owner association approval, watch the government permits pass through the log.

3. Claim a Home owner association approval task.
4. Approve the Home owner association permit.
5. List completed and in progress permits.
