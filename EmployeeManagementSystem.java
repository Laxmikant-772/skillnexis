
package miniproject3;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;

    public Employee(int id, String name, String department,
                    double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void display() {
        System.out.println("ID: " + id
                + ", Name: " + name
                + ", Department: " + department
                + ", Salary: " + salary);
    }

    public String toFileString() {
        return id + "," + name + "," + department + "," + salary;
    }
}

public class EmployeeManagementSystem {
    static ArrayList<Employee> employees = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static final String FILE_NAME = "employees.txt";

    static void addEmployee() {
        try {
            System.out.print("Enter employee ID: ");
            int id = Integer.parseInt(sc.nextLine().trim());

            for (Employee e : employees) {
                if (e.getId() == id) {
                    System.out.println("Employee ID already exists.");
                    return;
                }
            }

            System.out.print("Enter employee name: ");
            String name = sc.nextLine().trim();

            System.out.print("Enter department: ");
            String department = sc.nextLine().trim();

            System.out.print("Enter salary: ");
            double salary = Double.parseDouble(sc.nextLine().trim());

            if (id <= 0 || name.isEmpty()
                    || department.isEmpty() || salary < 0
                    || !Double.isFinite(salary)) {
                throw new IllegalArgumentException(
                        "Enter valid employee details.");
            }

            employees.add(new Employee(id, name, department, salary));
            System.out.println("Employee added successfully.");

        } catch (NumberFormatException e) {
            System.out.println("Error: ID and salary must be numbers.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void viewEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employee records found.");
            return;
        }

        System.out.println("\n--- Employee Records ---");
        for (Employee e : employees) {
            e.display();
        }
    }

    static void saveEmployees() {
        try (PrintWriter writer = new PrintWriter(
                new FileWriter(FILE_NAME))) {
            for (Employee e : employees) {
                writer.println(e.toFileString());
            }
            System.out.println("Employee data saved to " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    static void loadEmployees() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("No saved employee file found yet.");
            return;
        }

        ArrayList<Employee> loaded = new ArrayList<>();

        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] data = line.split(",", -1);

                if (data.length != 4) {
                    System.out.println("Skipping invalid record.");
                    continue;
                }

                try {
                    int id = Integer.parseInt(data[0].trim());
                    String name = data[1].trim();
                    String department = data[2].trim();
                    double salary = Double.parseDouble(data[3].trim());

                    if (id <= 0 || name.isEmpty()
                            || department.isEmpty() || salary < 0
                            || !Double.isFinite(salary)) {
                        System.out.println("Skipping invalid record.");
                        continue;
                    }

                    boolean duplicate = false;
                    for (Employee e : loaded) {
                        if (e.getId() == id) {
                            duplicate = true;
                            break;
                        }
                    }

                    if (!duplicate) {
                        loaded.add(new Employee(
                                id, name, department, salary));
                    } else {
                        System.out.println("Skipping duplicate ID: " + id);
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Skipping invalid record.");
                }
            }

            employees.clear();
            employees.addAll(loaded);
            System.out.println("Employee data loaded successfully.");

        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        loadEmployees();

        while (true) {
            System.out.println("\n===== Employee Management System =====");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Save Employees");
            System.out.println("4. Load Employees");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            String input = sc.nextLine();

            try {
                int choice = Integer.parseInt(input.trim());

                switch (choice) {
                    case 1:
                        addEmployee();
                        break;
                    case 2:
                        viewEmployees();
                        break;
                    case 3:
                        saveEmployees();
                        break;
                    case 4:
                        loadEmployees();
                        break;
                    case 5:
                        System.out.println("Thank you!");
                        sc.close();
                        return;
                    default:
                        System.out.println("Choose a number from 1 to 5.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number.");
            }
        }
    }
}
