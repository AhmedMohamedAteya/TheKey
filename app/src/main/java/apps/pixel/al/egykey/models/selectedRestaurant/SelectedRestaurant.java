package apps.pixel.al.egykey.models.selectedRestaurant;


import java.util.List;

public class SelectedRestaurant {
    private List<News> News;

    private Resturant Resturant;

    private List<Ourbranches> Ourbranches;

    private Amenities Amenities;

    private Beauty Beauty;

    private Gym Gym;

    private Hospital hospital;

    private Pharmacy pharmacy;


    private About About;

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public apps.pixel.al.egykey.models.selectedRestaurant.Gym getGym() {
        return Gym;
    }

    public void setGym(apps.pixel.al.egykey.models.selectedRestaurant.Gym gym) {
        Gym = gym;
    }

    public Beauty getBeauty() {
        return Beauty;
    }

    public void setBeauty(Beauty beauty) {
        this.Beauty = beauty;
    }

    public List<News> getNews() {
        return News;
    }

    public void setNews(List<News> news) {
        this.News = news;
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
        return "ClassPojo [news = " + News + ", Resturant = " + Resturant + ", Ourbranches = " + Ourbranches + ", Amenities = " + Amenities + ", About = " + About + "]";
    }
}

