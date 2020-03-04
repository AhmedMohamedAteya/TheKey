package apps.pixel.the.key.models.selectedRestaurant;

public class News {
    private String PathURL;

    private String Description;

    private String resturant_ID;

    private String Title;

    private String ID;

    private String IsVideo;

    public String getPathURL ()
    {
        return PathURL;
    }

    public void setPathURL (String PathURL)
    {
        this.PathURL = PathURL;
    }

    public String getDescription ()
    {
        return Description;
    }

    public void setDescription (String Description)
    {
        this.Description = Description;
    }

    public String getResturant_ID ()
    {
        return resturant_ID;
    }

    public void setResturant_ID (String resturant_ID)
    {
        this.resturant_ID = resturant_ID;
    }

    public String getTitle ()
    {
        return Title;
    }

    public void setTitle (String Title)
    {
        this.Title = Title;
    }

    public String getID ()
    {
        return ID;
    }

    public void setID (String ID)
    {
        this.ID = ID;
    }

    public String getStatus ()
    {
        return IsVideo;
    }

    public void setStatus (String status)
    {
        this.IsVideo = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [PathURL = "+PathURL+", Description = "+Description+", resturant_ID = "+resturant_ID+", Title = "+Title+", ID = "+ID+", status = "+IsVideo+"]";
    }
}

