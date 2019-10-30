package apps.pixel.al.egykey.models.selectedBeauty;

public class Images
{
    private String ID;

    private String Image;

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

    @Override
    public String toString()
    {
        return "ClassPojo [ID = "+ID+", Image = "+Image+"]";
    }
}

