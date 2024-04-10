import java.util.*;
public class main {
    public static void main(String[] args) {

        Set<notebook> set = new HashSet<>();
        set.add(new notebook("Notebook 1", 8, "Windows10", 70000, "HP"));
        set.add(new notebook("Notebook 2", 16, "Windows10", 85000, "Lenovo"));
        set.add(new notebook("Notebook 3", 32, "linux", 85000, "Machenike"));
        set.add(new notebook("Notebook 4", 64, "linux", 90000, "Maibenben"));
        set.add(new notebook("Notebook 5", 16, "MacOS", 123000, "Macbook"));

        operations operation = new operations(set);
        operation.start();

    }
}
