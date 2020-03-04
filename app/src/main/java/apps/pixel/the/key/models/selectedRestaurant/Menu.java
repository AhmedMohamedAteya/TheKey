package apps.pixel.the.key.models.selectedRestaurant;


public class Menu {
    private String Status;

    private String Images;

    private String ID;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getImages() {
        return Images;
    }

    public void setImages(String Images) {
        this.Images = Images;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "ClassPojo [Status = " + Status + ", Images = " + Images + ", ID = " + ID + "]";
    }
}


