
package practicequestion3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordCount {
    public static void main(String[] args) {
        try {
            File file = new File("words.txt");
            Scanner scanner = new Scanner(file);

            int count = 0;

            while (scanner.hasNext()) {
                scanner.next();
                count++;
            }

            scanner.close();

            System.out.println("Total words: " + count);

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
}


