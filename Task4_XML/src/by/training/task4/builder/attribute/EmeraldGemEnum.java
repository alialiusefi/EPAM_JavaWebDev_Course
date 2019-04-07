package by.training.task4.builder.attribute;

/**
 * <complexType name="AbstractGem" mixed="true">
 * <sequence>
 * <element name="name" type="string"/>
 * <element name="preciousness" type="tns:Preciousness"/>
 * <element name="weight" type="tns:Weight"/>
 * <element name="gemArrival" type="date"/>
 * </sequence>
 * <attribute name="origin" type="tns:Origin" default="OTHER"/>
 * <attribute name="id" type="integer" use="required"/>
 *
 * <element name="Emerald" substitutionGroup="tns:AbstractGem">
 * <complexType mixed="true">
 * <complexContent>
 * <extension base="tns:AbstractGem">
 * <sequence>
 * <element name="color" type="string"/>
 * <element name="transparency" type="tns:Transparency"/>
 * <element name="amountofcuts" type="tns:amountOfCuts"/>
 * </sequence>
 * </extension>
 * </complexContent>
 *
 * </complexType>
 * </element>
 */
public enum EmeraldGemEnum {


    NAME("name"),

    PRECIOUSNESS("preciousness"),

    WEIGHT("weight"),

    GEMARRIVAL("gemArrival"),

    ORIGIN("origin"),

    ID("id"),

    COLOR("color"),

    TRANSPARENCY("transperancy"),

    AMOUNTOFCUTS("amountofcuts");

    private String value;

    EmeraldGemEnum(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }

}
