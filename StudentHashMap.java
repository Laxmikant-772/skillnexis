package practicequestion4;
import java.util.HashMap;
import java.util.Map;


public class StudentHashMap {
    public static void main(String[] args) {
        HashMap<Integer, String> students = new HashMap<>();

        students.put(101, "Ravi");
        students.put(102, "Anu");
        students.put(103, "Kiran");

        System.out.println("Student Records:");

        for (Map.Entry<Integer, String> entry
                : students.entrySet()) {
            System.out.println(
                    entry.getKey() + " : " + entry.getValue());
        }

        System.out.println("Name for roll number 102: "
                + students.get(102));
    }
}

    

