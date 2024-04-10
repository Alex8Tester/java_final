import java.util.*;

public class notebook {
    private String name;
    private int ram;
    private String os;
    private int price;
    private String model;

    public notebook(String name, int ram, String os, int price, String model) {
        this.name = name;
        this.ram = ram;
        this.os = os;
        this.price = price;
        this.model = model;
    }

    public boolean validateObject() {
        return true;
    }

    public static List<String> searchProperties() {
        List<String> list = new ArrayList<>();
        list.add("ram");
        list.add("os");
        list.add("price");
        list.add("model");

        return list;
    }

    @Override
    public String toString() {
        return "notebook: (" + name + "): " +
                "ram:" + ram +
                ", os: " + os +
                ", price: " + price +
                ", model: " + model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
