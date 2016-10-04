# solarVillage
Solar Village home work assignment for advanced process development with Red Hat JBoss BPM Suite
# Instructions
## Build and deploy the web services and models
1. Clone the repository to your machine.
2. Build the Maven components by running: 

  ```
  $ cd $GIT_REPOSITORY/governmentPermitServices
  $ mvn install -P war -Djavax.xml.access=all
  ```
  
3. Copy the target resources from the Maven projects to your JBoss Server: 
  
  ```
  $ cp $GIT_REPOSITORY/governmentPermitServices/permitDomainModel/target/permitDomainModel-1.0.jar $JBOSS_HOME/deployments/business-central.war/WEB-INF/lib
  $ cp $GIT_REPOSITORY/governmentPermitServices/permitServiceTier/target/governmentPermitServices.war $JBOSS_HOME/deployments
  ```
  
4. Bounce your JBoss Server

## Build and deploy the business assets in BPM Suite
1. Clone the repository to your BPM Suite organization.
2. Deploy the OrderPermit project
3. go to process definitions and start either the GovernmentOrderPermit for Residential or HoaPermitOrder for Home Owner Association permits.
