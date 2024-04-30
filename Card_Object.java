public class Card_Object
{
    String type;
    String color;
    Integer value;

    Card_Object(String type,String color, Integer value)
    {
        //unique data for each instance
        this.type = type;
        this.color = color;
        this.value = value;
    }

    public String getType()
    {
        return type;
    }

    public String getColor()
    {
        return color;
    }

    public Integer getValue()
    {
        return value;
    }
}

