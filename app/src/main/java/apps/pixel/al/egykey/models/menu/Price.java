package apps.pixel.al.egykey.models.menu;

import java.util.List;

public class Price {

    private String Price;

    private String ID;

    private List<ListSizeInMenu> ListSizeInMenu;

    private String Name;

    public String getPrice ()
    {
        return Price;
    }

    public void setPrice (String Price)
    {
        this.Price = Price;
    }

    public String getID ()
    {
        return ID;
    }

    public void setID (String ID)
    {
        this.ID = ID;
    }

    public List<ListSizeInMenu> getListSizeInMenu ()
    {
        return ListSizeInMenu;
    }

    public void setListSizeInMenu (List<ListSizeInMenu> ListSizeInMenu)
    {
        this.ListSizeInMenu = ListSizeInMenu;
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
        return "ClassPojo [Price = "+Price+", ID = "+ID+", ListSizeInMenu = "+ListSizeInMenu+", Name = "+Name+"]";
    }
}

