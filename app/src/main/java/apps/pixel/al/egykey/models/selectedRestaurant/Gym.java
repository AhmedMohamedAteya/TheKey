package apps.pixel.al.egykey.models.selectedRestaurant;


import java.util.List;

public class Gym
{
    private String Favourits;

    private String Views;

    private String Video;

    private List<Images> Images;

    private String Calls;

    private String Instagram;

    private String Twitter;

    private String ID;

    private String FaceBook;

    private List<Menu> Menu;

    private String Share;

    public String getFavourits ()
    {
        return Favourits;
    }

    public void setFavourits (String Favourits)
    {
        this.Favourits = Favourits;
    }

    public String getViews ()
    {
        return Views;
    }

    public void setViews (String Views)
    {
        this.Views = Views;
    }

    public String getVideo ()
    {
        return Video;
    }

    public void setVideo (String Video)
    {
        this.Video = Video;
    }

    public List<Images> getImages ()
    {
        return Images;
    }

    public void setImages (List<Images> Images)
    {
        this.Images = Images;
    }

    public String getCalls ()
    {
        return Calls;
    }

    public void setCalls (String Calls)
    {
        this.Calls = Calls;
    }

    public String getInstagram ()
{
    return Instagram;
}

    public void setInstagram (String Instagram)
    {
        this.Instagram = Instagram;
    }

    public String getTwitter ()
{
    return Twitter;
}

    public void setTwitter (String Twitter)
    {
        this.Twitter = Twitter;
    }

    public String getID ()
    {
        return ID;
    }

    public void setID (String ID)
    {
        this.ID = ID;
    }

    public String getFaceBook ()
    {
        return FaceBook;
    }

    public void setFaceBook (String FaceBook)
    {
        this.FaceBook = FaceBook;
    }

    public List<Menu> getMenu ()
    {
        return Menu;
    }

    public void setMenu (List<Menu> Menu)
    {
        this.Menu = Menu;
    }

    public String getShare ()
    {
        return Share;
    }

    public void setShare (String Share)
    {
        this.Share = Share;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Favourits = "+Favourits+", Views = "+Views+", Video = "+Video+", Images = "+Images+", Calls = "+Calls+", Instagram = "+Instagram+", Twitter = "+Twitter+", ID = "+ID+", FaceBook = "+FaceBook+", Menu = "+Menu+", Share = "+Share+"]";
    }
}

