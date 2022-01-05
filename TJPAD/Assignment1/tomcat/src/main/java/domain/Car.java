package domain;

public class Car {
    private int id = 0;
    private String code;
    private String color;
    private String brand;
    private int year;
//    public static final AtomicInteger count = new AtomicInteger(0);

    public Car( String code,String color, String brand, int year){
//        this.id = count.incrementAndGet();
        this.code = code;
        this.color = color;
        this.brand = brand;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getColor() {
        return color;
    }

    public String getBrand() {
        return brand;
    }

    public int getYear() {
        return year;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", color='" + color + '\'' +
                ", brand='" + brand + '\'' +
                ", year=" + year +
                '}';
    }
}
