
package game.answer.place.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for elementTypeDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="elementTypeDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="intLabel1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="intLabel2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nameLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="powerLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "elementTypeDto", propOrder = {
    "intLabel1",
    "intLabel2",
    "nameLabel",
    "powerLabel"
})
public class ElementTypeDto {

    protected String intLabel1;
    protected String intLabel2;
    protected String nameLabel;
    protected String powerLabel;

    /**
     * Gets the value of the intLabel1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntLabel1() {
        return intLabel1;
    }

    /**
     * Sets the value of the intLabel1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntLabel1(String value) {
        this.intLabel1 = value;
    }

    /**
     * Gets the value of the intLabel2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntLabel2() {
        return intLabel2;
    }

    /**
     * Sets the value of the intLabel2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntLabel2(String value) {
        this.intLabel2 = value;
    }

    /**
     * Gets the value of the nameLabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameLabel() {
        return nameLabel;
    }

    /**
     * Sets the value of the nameLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameLabel(String value) {
        this.nameLabel = value;
    }

    /**
     * Gets the value of the powerLabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPowerLabel() {
        return powerLabel;
    }

    /**
     * Sets the value of the powerLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPowerLabel(String value) {
        this.powerLabel = value;
    }

}
