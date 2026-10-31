package practicequestion4;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private int id;
    private String title;
    private boolean available;

    public Book(int id, String title) {
        this.id = id;
        this.title = title;
        this.available = true;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return available;
    }

    public void borrow() {
        available = false;
    }

    public void returnBook() {
        available = true;
    }

    public String toFileString() {
        return id + "," + title + "," + available;
    }
}

class Library {
    private ArrayList<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        for (Book b : books) {
            if (b.getId() == book.getId()) {
                System.out.println("Book ID already exists.");
                return;
            }
        }

        books.add(book);
        System.out.println("Book added successfully.");
    }

    public void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        System.out.println("\n--- Book List ---");

        for (Book book : books) {
            String status = book.isAvailable()
                    ? "Available" : "Borrowed";

            System.out.println(book.getId() + " - "
                    + book.getTitle() + " - " + status);
        }
    }

    public void borrowBook(int id) {
        Book book = findBook(id);

        if (book == null) {
            System.out.println("Book not found.");
        } else if (!book.isAvailable()) {
            System.out.println("Book is already borrowed.");
        } else {
            book.borrow();
            System.out.println("Book borrowed successfully.");
        }
    }

    public void returnBook(int id) {
        Book book = findBook(id);

        if (book == null) {
            System.out.println("Book not found.");
        } else if (book.isAvailable()) {
            System.out.println("Book was not borrowed.");
        } else {
            book.returnBook();
            System.out.println("Book returned successfully.");
        }
    }

    private Book findBook(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }

        return null;
    }

    public void saveBooks() {
        try (PrintWriter writer =
                new PrintWriter(new FileWriter("books.txt"))) {

            for (Book book : books) {
                writer.println(book.toFileString());
            }

            System.out.println("Books saved to file.");

        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }

    public void loadBooks() {
        File file = new File("books.txt");

        if (!file.exists()) {
            System.out.println("No saved books found.");
            return;
        }

        ArrayList<Book> loadedBooks = new ArrayList<>();

        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] data = line.split(",", 3);

                if (data.length != 3) {
                    continue;
                }

                try {
                    int id = Integer.parseInt(data[0]);
                    String title = data[1];
                    boolean available =
                            Boolean.parseBoolean(data[2]);

                    Book book = new Book(id, title);

                    if (!available) {
                        book.borrow();
                    }

                    loadedBooks.add(book);

                } catch (NumberFormatException e) {
                    System.out.println("Skipping invalid record.");
                }
            }

            books.clear();
            books.addAll(loadedBooks);

            System.out.println("Books loaded successfully.");

        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }
}

public class  LibraryManagementSystem  {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        library.loadBooks();

        while (true) {
            System.out.println("\n===== Library Management =====");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Save Books");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            try {
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter book ID: ");
                        int id = Integer.parseInt(sc.nextLine());

                        System.out.print("Enter book title: ");
                        String title = sc.nextLine();

                        if (id <= 0 || title.trim().isEmpty()) {
                            System.out.println("Invalid book details.");
                        } else {
                            library.addBook(new Book(id, title));
                        }
                        break;

                    case 2:
                        library.viewBooks();
                        break;

                    case 3:
                        System.out.print("Enter book ID to borrow: ");
                        int borrowId =
                                Integer.parseInt(sc.nextLine());

                        library.borrowBook(borrowId);
                        break;

                    case 4:
                        System.out.print("Enter book ID to return: ");
                        int returnId =
                                Integer.parseInt(sc.nextLine());

                        library.returnBook(returnId);
                        break;

                    case 5:
                        library.saveBooks();
                        break;

                    case 6:
                        library.saveBooks();
                        System.out.println("Exiting program.");
                        sc.close();
                        return;

                    default:
                        System.out.println("Choose from 1 to 6.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number.");
            }
        }
    }
}

    

