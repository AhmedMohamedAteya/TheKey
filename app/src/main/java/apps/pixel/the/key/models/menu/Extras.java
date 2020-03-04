package apps.pixel.the.key.models.menu;

public class Extras {
    private String price;

    private String ID;

    private String Name;

    private String NameAR;

    public String getPrice ()
    {
        return price;
    }

    public void setPrice (String price)
    {
        this.price = price;
    }

    public String getID ()
    {
        return ID;
    }

    public void setID (String ID)
    {
        this.ID = ID;
    }

    public String getName ()
    {
        return Name;
    }

    public void setName (String Name)
    {
        this.Name = Name;
    }

    public String getNameAR ()
    {
        return NameAR;
    }

    public void setNameAR (String NameAR)
    {
        this.NameAR = NameAR;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [price = "+price+", ID = "+ID+", Name = "+Name+", NameAR = "+NameAR+"]";
    }
}

