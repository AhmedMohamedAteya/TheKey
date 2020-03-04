package apps.pixel.the.key.models.menu;

public class ListSizeInMenu {
    private String SizeAR;

    private String Price;

    private String Size;

    private String ID;

    public String getSizeAR ()
    {
        return SizeAR;
    }

    public void setSizeAR (String SizeAR)
    {
        this.SizeAR = SizeAR;
    }

    public String getPrice ()
    {
        return Price;
    }

    public void setPrice (String Price)
    {
        this.Price = Price;
    }

    public String getSize ()
    {
        return Size;
    }

    public void setSize (String Size)
    {
        this.Size = Size;
    }

    public String getID ()
    {
        return ID;
    }

    public void setID (String ID)
    {
        this.ID = ID;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [SizeAR = "+SizeAR+", Price = "+Price+", Size = "+Size+", ID = "+ID+"]";
    }
}

