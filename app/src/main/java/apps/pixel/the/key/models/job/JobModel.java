package apps.pixel.the.key.models.job;


public class JobModel
{
    private double Salary;

    //private String Address;

    private String Title;

    private String ID;

    private String Image;

    private String Date;

    public double getSalary ()
    {
        return Salary;
    }

    public void setSalary (double Salary)
    {
        this.Salary = Salary;
    }

//    public String getAddress ()
//    {
//        return Address;
//    }
//
//    public void setAddress (String Address)
//    {
//        this.Address = Address;
//    }

    public String getTitle ()
    {
        return Title;
    }

    public void setTitle (String Title)
    {
        this.Title = Title;
    }

    public String getID ()
    {
        return ID;
    }

    public void setID (String ID)
    {
        this.ID = ID;
    }

    public String getImage ()
    {
        return Image;
    }

    public void setImage (String Image)
    {
        this.Image = Image;
    }

    public String getDate ()
    {
        return Date;
    }

    public void setDate (String Date)
    {
        this.Date = Date;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Salary = "+Salary+", Title = "+Title+", ID = "+ID+", Image = "+Image+", Date = "+Date+"]";
    }
}

