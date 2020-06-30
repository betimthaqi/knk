package Controllers;

public class Cars {
    private int Id;
    private String Brand;
    private String Modeli;
    private String Engine;
    private String Type;
    private String Color;
    private String Year;
    private int Doors;
    private String Fuel;
    private String Gear;
    private String  Price;


    public Cars(int id, String brand,String modeli, String engine, String type, String color, String year, int doors, String fuel, String gear, String price) {
        this.Id = id;
        this.Brand = brand;
        this.Modeli = modeli;
        this.Engine = engine;
        this.Type = type;
        this.Color = color;
        this.Year = year;
        this.Doors = doors;
        this.Fuel = fuel;
        this.Gear = gear;
        this.Price = price;
    }

    public String getType() {
        return Type;
    }

    public int getId() {
        return Id;
    }

    public String getBrand() {
        return Brand;
    }

    public String getEngine() {
        return Engine;
    }

    public String getColor() {
        return Color;
    }

    public String getYear() {
        return Year;
    }

    public int getDoors() {
        return Doors;
    }

    public String getFuel() {
        return Fuel;
    }

    public String getGear() {
        return Gear;
    }

    public String  getPrice() {
        return Price;
    }

    public String getModeli() {
        return Modeli;
    }
}
