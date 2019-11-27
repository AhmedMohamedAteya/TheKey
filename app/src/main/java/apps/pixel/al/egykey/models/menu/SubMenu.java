package apps.pixel.al.egykey.models.menu;

public class SubMenu {
    private String Description;

    private String ID;

    private String Image;

    private String Name;

    public String getDescription ()
    {
        return Description;
    }

    public void setDescription (String Description)
    {
        this.Description = Description;
    }

    public String getID ()
    {
        return ID;
    }

    public void setID (String ID)
    {
        this.ID = ID;
    }

    public String getImage ()
    {
        return Image;
    }

    public void setImage (String Image)
    {
        this.Image = Image;
    }

    public String getName ()
    {
        return Name;
    }

    public void setName (String Name)
    {
        this.Name = Name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Description = "+Description+", ID = "+ID+", Image = "+Image+", Name = "+Name+"]";
    }
}

