
package practicequestion3;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class FileReadWrite {
    public static void main(String[] args) {
        try {
            FileWriter writer = new FileWriter("sample.txt");
            writer.write("Hello Java!\n");
            writer.write("Learning file handling.");
            writer.close();

            System.out.println("File written successfully.");

            FileReader reader = new FileReader("sample.txt");
            int character;

            System.out.println("File contents:");

            while ((character = reader.read()) != -1) {
                System.out.print((char) character);
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}

    

