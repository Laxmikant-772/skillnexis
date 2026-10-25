package assignment3;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class WordFrequency {
    public static void main(String[] args) {
        Map<String, Integer> frequency = new LinkedHashMap<>();

        try {
            File file = new File("input.txt");
            Scanner reader = new Scanner(file);

            while (reader.hasNext()) {
                String word = reader.next().toLowerCase();
                word = word.replaceAll("[^a-z0-9]", "");

                if (!word.isEmpty()) {
                    frequency.put(
                        word, frequency.getOrDefault(word, 0) + 1
                    );
                }
            }

            reader.close();

            FileWriter writer = new FileWriter("output.txt");

            for (Map.Entry<String, Integer> entry
                    : frequency.entrySet()) {
                writer.write(entry.getKey() + " : "
                        + entry.getValue() + "\n");
            }

            writer.close();

            System.out.println("Word frequency counted successfully.");
            System.out.println("Results saved to output.txt");

        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }
}

    

