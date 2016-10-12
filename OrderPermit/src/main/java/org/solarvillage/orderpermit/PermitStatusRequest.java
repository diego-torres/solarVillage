package org.solarvillage.orderpermit;

/**
 * This class was automatically generated by the data modeler tool.
 */

@org.kie.api.definition.type.Label("Permit Status Request")
public class PermitStatusRequest implements java.io.Serializable
{

   static final long serialVersionUID = 1L;

   @org.kie.api.definition.type.Label(value = "Permit Type")
   private java.lang.String permitType;
   @org.kie.api.definition.type.Label(value = "Permit Request Id")
   private java.lang.String permitRequestId;

   public PermitStatusRequest()
   {
   }

   public java.lang.String getPermitType()
   {
      return this.permitType;
   }

   public void setPermitType(java.lang.String permitType)
   {
      this.permitType = permitType;
   }

   public java.lang.String getPermitRequestId()
   {
      return this.permitRequestId;
   }

   public void setPermitRequestId(java.lang.String permitRequestId)
   {
      this.permitRequestId = permitRequestId;
   }

   public PermitStatusRequest(java.lang.String permitType,
         java.lang.String permitRequestId)
   {
      this.permitType = permitType;
      this.permitRequestId = permitRequestId;
   }

}