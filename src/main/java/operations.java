import java.util.*;
public class operations {
    private Set<notebook> notebooks = new HashSet<>();
    private List<Criteria> searchCriteria = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public void printList(){
        for (notebook notebook : notebooks){
            if (notebookIsCorrect(notebook)){
                System.out.println(notebook);
            }
        }
    }

    public boolean notebookIsCorrect(notebook notebook){

        for (Criteria criteria : searchCriteria){
            Object valueNotebook = null;

            if (criteria.property.equals("name")){
                valueNotebook = notebook.getName();
            }else if (criteria.property.equals("ram")){
                valueNotebook = notebook.getRam();
            }else if (criteria.property.equals("os")){
                valueNotebook = notebook.getOs();
            }else if (criteria.property.equals("price")){
                valueNotebook = notebook.getPrice();
            }else if (criteria.property.equals("model")){
                valueNotebook = notebook.getModel();
            }else {
                continue;
            }

            if (criteria.value != null && !criteria.value.equals(valueNotebook)){
                return false;
            }

            if (criteria.maxValue != null && criteria.maxValue < Double.parseDouble(Objects.toString(valueNotebook))){
                return false;
            }

            if (criteria.minValue != null && criteria.minValue > Double.parseDouble(Objects.toString(valueNotebook))){
                return false;
            }
        }
        return true;
    }
    public operations(Set<notebook> notebooks) {
        this.scanner = new Scanner(System.in);
        this.notebooks = notebooks;
    }

    public operations(Set<notebook> notebooks, List<Criteria> searchCriteria) {
        this.scanner = new Scanner(System.in);
        this.notebooks = notebooks;
        this.searchCriteria = searchCriteria;
    }

    public int getCriteria(){
        String text = "Enter is digit in criteria: ";

        List<String> properties = propertiesForFilter();

        for (int i = 0; i < properties.size(); i++)
        {
            text += "\n" + (i + 1) + ". " + getPropertyDescription(properties.get(i));
        }

        System.out.println(text);

        int value = scanner.nextInt();

        return value;
    }

    public String getPropertyDescription(String property){

        Map<String, String> descriptionsProperties = descriptionsProperties();

        return descriptionsProperties.get(property);

    }

    public Map<String, String> descriptionsProperties(){
        Map<String, String> map = new HashMap<>();

        map.put("name", "Name");
        map.put("ram", "Size Ram");
        map.put("os", "Operation System");
        map.put("price", "Price");
        map.put("model", "Model of notebook");

        return map;

    }

    public List<String> propertiesForFilter(){
        List<String> list = new ArrayList<>();
        list.add("name");
        list.add("ram");
        list.add("os");
        list.add("price");
        list.add("model");

        return list;
    }

    public String getOperations(){

        String text = "Enter operations: \n " +
                "1. Add criteria \n " +
                "2. Print list \n " +
                "3. End operation";

        System.out.println(text);

        String answer = scanner.next();

        return answer;
    }

    public Set<String> quantitativeSelection(){
        Set<String> set = new HashSet<>();
        set.add("amountRAM");
        set.add("price");

        return set;
    }

    public Set<String> stringSelection(){
        Set<String> set = new HashSet<>();

        set.add("name");
        set.add("operatingSystem");
        set.add("model");

        return set;
    }

    public void start(){

        boolean flag = true;
        while (flag){

            String operation = getOperations();
            if (operation.equals("3")){
                flag = false;
                scanner.close();
                continue;
            }else if(operation.equals("1")){

                int criteria = getCriteria();
                List<String> properties = propertiesForFilter();
                if (criteria - 1 < 0 || criteria - 1 > properties.size() - 1){
                    System.out.println("Warn! Your entered incorrect value params");
                    continue;
                }
                String property = properties.get(criteria - 1);
                criteria criteriaObject = null;
                try {
                    if (quantitativeSelection().contains(property)){
                        criteriaObject = criteria.startOperation(scanner, property, true);
                    }else {
                        criteriaObject = criteria.startOperation(scanner, property, false);
                    }
                }catch (Exception e){
                    System.out.println("Error in added value");
                    continue;
                }
                if (criteriaObject != null){
                    System.out.println("Criteria added");
                    searchCriteria.add(criteriaObject);
                }
            }
            else if (operation.equals("2")){
                printList();
            }
        }
    }
}


class Criteria {

    Object value;
    Double minValue;
    Double maxValue;
    boolean isQuantitative;
    String property;

    public Criteria (String property, boolean isQuantitative, Object value, Double minValue, Double maxValue) {
        this.property = property;
        this.isQuantitative = isQuantitative;
        this.value = value;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public static Criteria startOperation(Scanner scanner, String property, boolean isQuantitative) {

        if (isQuantitative) {

            String quest = "Choice criteria of search: " +
                    "\n 1. Value" +
                    "\n 2. Minimal" +
                    "\n 3. Maximal" +
                    "\n 4. Interval";
            System.out.println(quest);

            String text = scanner.next();

            Criteria criteria = null;

            if (text.equals("1")) {
                System.out.println("Enter your value criteria: ");
                int getValue = scanner.nextInt();
                criteria = new Criteria(property, isQuantitative, getValue, null, null);
            } else if (text.equals("2")) {
                System.out.println("Enter max value count: ");
                double getValue = scanner.nextDouble();
                criteria = new Criteria(property, isQuantitative, null, null, getValue);
            } else if (text.equals("3")) {
                System.out.println("Enter min value count: ");
                double getValue = scanner.nextDouble();
                criteria = new Criteria(property, isQuantitative, null, getValue, null);
            } else if (text.equals("4")) {
                System.out.println("Enter min value count: ");
                double getMin = scanner.nextDouble();
                System.out.println("Enter max value count: ");
                double getMax = scanner.nextDouble();
                criteria = new Criteria(property, isQuantitative, null, getMin, getMax);
            }

            return criteria;
        }

            System.out.println("Enter search parameter: ");
            String getValue = scanner.next();
            return new Criteria(property, isQuantitative, getValue, null, null);
        }
    }
}
