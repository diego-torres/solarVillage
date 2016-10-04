package org.solarvillage.orderpermit;

/**
 * This class was automatically generated by the data modeler tool.
 */

@org.kie.api.definition.type.Label("Home Owner Association Meeting")
public class HoaMeeting implements java.io.Serializable
{

   static final long serialVersionUID = 1L;

   @org.kie.api.definition.type.Label(value = "Meeting Date")
   private java.util.Date meetingDate;
   @org.kie.api.definition.type.Label(value = "Association Name")
   private java.lang.String associationName;
   @org.kie.api.definition.type.Label(value = "Representative Name")
   private java.lang.String representativeName;
   @org.kie.api.definition.type.Label(value = "quorum")
   private java.util.List<java.lang.String> quorum;
   @org.kie.api.definition.type.Label(value = "Meeting Details")
   private java.lang.String meetingDetails;
   @org.kie.api.definition.type.Label(value = "Installation Approved")
   private java.lang.Boolean installationApproved;

   public HoaMeeting()
   {
   }

   public java.util.Date getMeetingDate()
   {
      return this.meetingDate;
   }

   public void setMeetingDate(java.util.Date meetingDate)
   {
      this.meetingDate = meetingDate;
   }

   public java.lang.String getAssociationName()
   {
      return this.associationName;
   }

   public void setAssociationName(java.lang.String associationName)
   {
      this.associationName = associationName;
   }

   public java.lang.String getRepresentativeName()
   {
      return this.representativeName;
   }

   public void setRepresentativeName(java.lang.String representativeName)
   {
      this.representativeName = representativeName;
   }

   public java.util.List<java.lang.String> getQuorum()
   {
      return this.quorum;
   }

   public void setQuorum(java.util.List<java.lang.String> quorum)
   {
      this.quorum = quorum;
   }

   public java.lang.String getMeetingDetails()
   {
      return this.meetingDetails;
   }

   public void setMeetingDetails(java.lang.String meetingDetails)
   {
      this.meetingDetails = meetingDetails;
   }

   public java.lang.Boolean getInstallationApproved()
   {
      return this.installationApproved;
   }

   public void setInstallationApproved(java.lang.Boolean installationApproved)
   {
      this.installationApproved = installationApproved;
   }

   public HoaMeeting(java.util.Date meetingDate,
         java.lang.String associationName,
         java.lang.String representativeName,
         java.util.List<java.lang.String> quorum,
         java.lang.String meetingDetails,
         java.lang.Boolean installationApproved)
   {
      this.meetingDate = meetingDate;
      this.associationName = associationName;
      this.representativeName = representativeName;
      this.quorum = quorum;
      this.meetingDetails = meetingDetails;
      this.installationApproved = installationApproved;
   }

}