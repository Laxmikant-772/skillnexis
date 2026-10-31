package miniproject4;


import java.util.ArrayList;
import java.util.Scanner;

class Booking {
    int bookingId;
    String customerName;
    int roomNumber;
    int days;
    double totalCost;

    public Booking(int bookingId, String customerName,
                   int roomNumber, int days) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.roomNumber = roomNumber;
        this.days = days;
        this.totalCost = days * 1500;
    }

    public void display() {
        System.out.println("Booking ID: " + bookingId);
        System.out.println("Customer Name: " + customerName);
        System.out.println("Room Number: " + roomNumber);
        System.out.println("Days: " + days);
        System.out.println("Total Cost: Rs. " + totalCost);
        System.out.println("-------------------------");
    }
}

public class HotelBookingSystem {
    static ArrayList<Booking> bookings = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static int nextBookingId = 1;

    static void addBooking() {
        try {
            System.out.print("Enter customer name: ");
            String name = sc.nextLine().trim();

            System.out.print("Enter room number: ");
            int room = Integer.parseInt(sc.nextLine());

            System.out.print("Enter number of days: ");
            int days = Integer.parseInt(sc.nextLine());

            if (name.isEmpty() || room <= 0 || days <= 0) {
                System.out.println("Invalid booking details.");
                return;
            }

            Booking booking = new Booking(
                    nextBookingId, name, room, days);

            bookings.add(booking);

            System.out.println("Booking added successfully!");
            System.out.println("Booking ID: " + nextBookingId);
            System.out.println("Total Cost: Rs. "
                    + booking.totalCost);

            nextBookingId++;

        } catch (NumberFormatException e) {
            System.out.println("Please enter valid numbers.");
        }
    }

    static void viewBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings available.");
            return;
        }

        System.out.println("\n--- Hotel Bookings ---");

        for (Booking booking : bookings) {
            booking.display();
        }
    }

    static void cancelBooking() {
        try {
            System.out.print("Enter booking ID to cancel: ");
            int id = Integer.parseInt(sc.nextLine());

            for (Booking booking : bookings) {
                if (booking.bookingId == id) {
                    bookings.remove(booking);
                    System.out.println(
                            "Booking cancelled successfully!");
                    return;
                }
            }

            System.out.println("Booking ID not found.");

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid booking ID.");
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Hotel Booking System =====");
            System.out.println("1. Add Booking");
            System.out.println("2. View Bookings");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            String input = sc.nextLine();

            try {
                int choice = Integer.parseInt(input);

                switch (choice) {
                    case 1:
                        addBooking();
                        break;

                    case 2:
                        viewBookings();
                        break;

                    case 3:
                        cancelBooking();
                        break;

                    case 4:
                        System.out.println("Thank you!");
                        sc.close();
                        return;

                    default:
                        System.out.println(
                                "Choose a number from 1 to 4.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number.");
            }
        }
    }
}

    

