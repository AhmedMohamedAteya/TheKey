package apps.pixel.al.egykey.models.selectedRestaurant;

public class Ourbranches
{
    private String Address;

    private String ID;

    public String getAddress ()
    {
        return Address;
    }

    public void setAddress (String Address)
    {
        this.Address = Address;
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
        return "ClassPojo [Address = "+Address+", ID = "+ID+"]";
    }
}

