package apps.pixel.the.key.models.retaurants;

public class SelectedCat {
    private String CoverImage;

    private String Since;

    private String ID;

    private String Name;

    private String NameAR;

    private String Logo;

    public String getCoverImage() {
        return CoverImage;
    }

    public void setCoverImage(String CoverImage) {
        this.CoverImage = CoverImage;
    }

    public String getSince() {
        return Since;
    }

    public void setSince(String Since) {
        this.Since = Since;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getNameAR() {
        return NameAR;
    }

    public void setNameAR(String NameAR) {
        this.NameAR = NameAR;
    }

    public String getLogo() {
        return Logo;
    }

    public void setLogo(String Logo) {
        this.Logo = Logo;
    }

    @Override
    public String toString() {
        return "ClassPojo [CoverImage = " + CoverImage + ", Since = " + Since + ", ID = " + ID + ", Name = " + Name + ", NameAR = " + NameAR + ", Logo = " + Logo + "]";
    }
}

