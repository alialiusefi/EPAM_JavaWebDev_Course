package by.training.task4.builder.attribute;

public enum AbstractGemEnum {
    NAME("name"),
    PRECIOUSNESS("preciousness"),
    WEIGHT("weight"),
    GEMARRIVAL("gemArrival"),
    ORIGIN("origin"),
    ID("id");

    private String value;
    AbstractGemEnum(String str)
    {
        this.value = str;
    }

    public String getValue()
    {
        return this.value;
    }


}
