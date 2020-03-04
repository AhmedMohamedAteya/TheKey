package apps.pixel.the.key.models.jobDetails;

public class JobDetailsModel {
    private double Salary;

    private String Description;

    private String Title;

    private String ID;

    private String Education_Level;

    private String Image;

    private String Experience_Level;

    private String Employment_Type;

    public double getSalary() {
        return Salary;
    }

    public void setSalary(double Salary) {
        this.Salary = Salary;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getEducation_Level() {
        return Education_Level;
    }

    public void setEducation_Level(String Education_Level) {
        this.Education_Level = Education_Level;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getExperience_Level() {
        return Experience_Level;
    }

    public void setExperience_Level(String Experience_Level) {
        this.Experience_Level = Experience_Level;
    }

    public String getEmployment_Type() {
        return Employment_Type;
    }

    public void setEmployment_Type(String Employment_Type) {
        this.Employment_Type = Employment_Type;
    }

    @Override
    public String toString() {
        return "ClassPojo [Salary = " + Salary + ", Description = " + Description + ", Title = " + Title + ", ID = " + ID + ", Education_Level = " + Education_Level + ", Image = " + Image + ", Experience_Level = " + Experience_Level + ", Employment_Type = " + Employment_Type + "]";
    }
}

