package apps.pixel.al.egykey.models.menu;

import java.util.List;

public class SubMenuDetails {
    private Price Price;

    private List<Extras> Extras;

    public Price getPrice() {
        return Price;
    }

    public void setPrice(Price Price) {
        this.Price = Price;
    }

    public List<Extras> getExtras() {
        return Extras;
    }

    public void setExtras(List<Extras> Extras) {
        this.Extras = Extras;
    }

    @Override
    public String toString() {
        return "ClassPojo [Price = " + Price + ", Extras = " + Extras + "]";
    }
}



