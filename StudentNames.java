
package practicequestion4;

import java.util.ArrayList;

public class StudentNames {
    public static void main(String[] args) {
        ArrayList<String> students = new ArrayList<>();

        students.add("Ravi");
        students.add("Anu");
        students.add("Kiran");
        students.add("Priya");

        System.out.println("Student Names:");

        for (String name : students) {
            System.out.println(name);
        }
    }
}
