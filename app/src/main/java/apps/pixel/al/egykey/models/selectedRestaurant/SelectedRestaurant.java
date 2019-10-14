package apps.pixel.al.egykey.models.selectedRestaurant;


import java.util.List;

public class SelectedRestaurant {
    private List<News> news;

    private Resturant Resturant;

    private List<Ourbranches> Ourbranches;

    private Amenities Amenities;

    private About About;

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    public Resturant getResturant() {
        return Resturant;
    }

    public void setResturant(Resturant Resturant) {
        this.Resturant = Resturant;
    }

    public List<Ourbranches> getOurbranches() {
        return Ourbranches;
    }

    public void setOurbranches(List<Ourbranches> Ourbranches) {
        this.Ourbranches = Ourbranches;
    }

    public Amenities getAmenities() {
        return Amenities;
    }

    public void setAmenities(Amenities Amenities) {
        this.Amenities = Amenities;
    }

    public About getAbout() {
        return About;
    }

    public void setAbout(About About) {
        this.About = About;
    }

    @Override
    public String toString() {
        return "ClassPojo [news = " + news + ", Resturant = " + Resturant + ", Ourbranches = " + Ourbranches + ", Amenities = " + Amenities + ", About = " + About + "]";
    }
}

