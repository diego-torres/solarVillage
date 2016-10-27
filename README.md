# solarVillage
Solar Village home work assignment for advanced process development with Red Hat JBoss BPM Suite
# Instructions
## Build and deploy the web services and models
1. Clone the repository to your machine.
2. Build the Maven components by running:

  ```
  $ cd $GIT_REPOSITORY/governmentPermitServices
  $ mvn install -P war
  ```

3. Copy the target resources from the Maven projects to your JBoss Server:

  ```
  $ cp $GIT_REPOSITORY/governmentPermitServices/permitServiceTier/target/governmentPermitServices.war $JBOSS_HOME/deployments
  ```
> **Note:** the POM for OrderPermit project includes the dependency instruction for governmentPermitServices parent project.

## Build and deploy the business assets in BPM Suite
### Using Business Central
1. Clone the repository to your BPM Suite Organization.
2. Deploy the OrderPermit project.
3. Using the Deploy > Rule deployments option: Create a container based in the OrderPermit deployment.
4. Start the container.
5. If you are using the same VM for the Business Central and the kie-server: undeploy the business-central

> **Note:** This is required to avoid an exception related to the timerService-id (Issue #1).

### Using Kie-server only

Build and deploy the kjar from the OrderPermit project to your kie server.

## Execute examples
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
