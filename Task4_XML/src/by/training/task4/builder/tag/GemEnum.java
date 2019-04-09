package by.training.task4.builder.tag;

public enum GemEnum {
    GEMS("Gems"),
    DIAMOND("Diamond"),
    EMERALD("Emerald"),
    PEARL("Pearl"),
    NAME("name"),
    PRECIOUSNESS("preciousness"),
    UNIT("unit"),
    VALUE("value"),
    WEIGHT("weight"),
    GEMARRIVAL("gemArrival"),
    ORIGIN("origin"),
    ID("id"),
    TRANSPARENCY("transparency"),
    AMOUNTOFCUTS("amountofcuts"),
    COLOR("color"),
    LUSTER("luster");


    private String value;
    GemEnum(String str)
    {
        this.value = str;
    }

    public String getValue()
    {
        return this.value;
    }


}
