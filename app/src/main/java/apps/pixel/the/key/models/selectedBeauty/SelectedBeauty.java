package apps.pixel.the.key.models.selectedBeauty;


import java.util.List;

import apps.pixel.the.key.models.selectedRestaurant.Resturant;

public class SelectedBeauty {
    private List<News> news;

    private apps.pixel.the.key.models.selectedRestaurant.Resturant Resturant;

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

    public List<apps.pixel.the.key.models.selectedBeauty.Ourbranches> getOurbranches() {
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

    public void setAbout(apps.pixel.the.key.models.selectedBeauty.About About) {
        this.About = About;
    }

    @Override
    public String toString() {
        return "ClassPojo [news = " + news + ", Resturant = " + Resturant + ", Ourbranches = " + Ourbranches + ", Amenities = " + Amenities + ", About = " + About + "]";
    }
}

