//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.09.29 at 09:29:49 PM MDT 
//


package gov.services.permits.structural;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import gov.services.permits.PermitRequest;


/**
 * <p>Java class for structuralPermitRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="structuralPermitRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://services.gov/permits}permitRequest">
 *       &lt;sequence>
 *         &lt;element name="buildingDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "structuralPermitRequest", propOrder = {
    "buildingDescription"
})
public class StructuralPermitRequest
    extends PermitRequest
{

    @XmlElement(required = true)
    protected String buildingDescription;

    /**
     * Gets the value of the buildingDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuildingDescription() {
        return buildingDescription;
    }

    /**
     * Sets the value of the buildingDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuildingDescription(String value) {
        this.buildingDescription = value;
    }

}