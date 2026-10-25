package assignment3;




import java.util.ArrayList;
import java.util.Collections;

public class StudentMarksRecord {
    public static void main(String[] args) {
        ArrayList<Integer> marks = new ArrayList<>();

        marks.add(85);
        marks.add(72);
        marks.add(95);
        marks.add(60);
        marks.add(78);

        System.out.println("Student Marks: " + marks);

        int highest = Collections.max(marks);
        int lowest = Collections.min(marks);

        System.out.println("Highest Marks: " + highest);
        System.out.println("Lowest Marks: " + lowest);
    }
}

    

