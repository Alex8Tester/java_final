import java.util.ArrayList;
import java.util.List;

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
    public boolean validateObject(){
        return true;
    }

    public static List<String> searchProperties(){
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

}
