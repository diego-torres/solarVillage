//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.09.29 at 11:18:37 AM MDT 
//


package gov.services.permits;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for permitStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="permitStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="PENDING"/>
 *     &lt;enumeration value="APPROVED"/>
 *     &lt;enumeration value="DENIED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "permitStatus")
@XmlEnum
public enum PermitStatus {

    PENDING,
    APPROVED,
    DENIED;

    public String value() {
        return name();
    }

    public static PermitStatus fromValue(String v) {
        return valueOf(v);
    }

}
