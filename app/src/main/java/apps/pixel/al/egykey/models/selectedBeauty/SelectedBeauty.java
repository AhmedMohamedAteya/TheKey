package apps.pixel.al.egykey.models.selectedBeauty;


import java.util.List;

import apps.pixel.al.egykey.models.selectedRestaurant.Resturant;

public class SelectedBeauty {
    private List<News> news;

    private Resturant Resturant;

    private List<apps.pixel.al.egykey.models.selectedBeauty.Ourbranches> Ourbranches;

    private apps.pixel.al.egykey.models.selectedBeauty.Amenities Amenities;

    private apps.pixel.al.egykey.models.selectedBeauty.About About;

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

    public List<apps.pixel.al.egykey.models.selectedBeauty.Ourbranches> getOurbranches() {
        return Ourbranches;
    }

    public void setOurbranches(List<apps.pixel.al.egykey.models.selectedBeauty.Ourbranches> Ourbranches) {
        this.Ourbranches = Ourbranches;
    }

    public apps.pixel.al.egykey.models.selectedBeauty.Amenities getAmenities() {
        return Amenities;
    }

    public void setAmenities(apps.pixel.al.egykey.models.selectedBeauty.Amenities Amenities) {
        this.Amenities = Amenities;
    }

    public apps.pixel.al.egykey.models.selectedBeauty.About getAbout() {
        return About;
    }

    public void setAbout(apps.pixel.al.egykey.models.selectedBeauty.About About) {
        this.About = About;
    }

    @Override
    public String toString() {
        return "ClassPojo [news = " + news + ", Resturant = " + Resturant + ", Ourbranches = " + Ourbranches + ", Amenities = " + Amenities + ", About = " + About + "]";
    }
}

