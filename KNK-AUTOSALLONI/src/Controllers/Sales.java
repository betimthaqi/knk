package Controllers;

public class Sales {
    private int SID;
    private String Brand;
    private String Model;
    private String Price;
    private String Date;
    private String Time;


    public Sales(int SID, String Brand,String Model, String Price,String Date,String Time) {
        this.SID = SID;
        this.Brand = Brand;
        this.Model = Model;
        this.Price = Price;
        this.Date = Date;
        this.Time = Time;
    }

    public int getSID() {
        return SID;
    }

    public String getBrand() {
        return Brand;
    }

    public String getModel() {
        return Model;
    }

    public String getPrice() {
        return Price;
    }

    public String getDate() {
        return Date;
    }

    public String getTime() {
        return Time;
    }
}
