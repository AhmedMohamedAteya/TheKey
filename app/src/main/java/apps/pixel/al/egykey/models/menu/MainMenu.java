package apps.pixel.al.egykey.models.menu;

public class MainMenu {
    private String ID;

    private String Name;

    private String NameAR;

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
        return "ClassPojo [ID = "+ID+", Name = "+Name+", NameAR = "+NameAR+"]";
    }
}

