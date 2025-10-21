import java.io.*;
import java.util.Scanner;
import model.Bill;
import model.Customer;
import util.BillCalculator;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n⚡ ELECTRICITY BILLING SYSTEM ⚡");
            System.out.println("1. Generate New Bill");
            System.out.println("2. View Saved Bills");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            // Validate input
            while (!sc.hasNextInt()) {
                System.out.print("❌ Please enter a valid number (1-3): ");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    generateNewBill(sc);
                    break;
                case 2:
                    viewSavedBills();
                    break;
                case 3:
                    System.out.println("🌼 Thank you for using the system. Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }

    // Method to create and save a new bill
    public static void generateNewBill(Scanner sc) {
        System.out.print("Enter Customer ID: ");
        String id = sc.nextLine().trim();

        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine().trim();

        System.out.print("Enter Units Consumed: ");
        while (!sc.hasNextInt()) {
            System.out.print("❌ Please enter a valid number for units: ");
            sc.next();
        }
        int units = sc.nextInt();
        sc.nextLine(); // clear buffer

        Customer customer = new Customer(id, name, units);
        double amount = BillCalculator.calculateBill(units);
        Bill bill = new Bill(customer, amount);

        System.out.println("\n" + bill.toString());
        saveBillToFile(bill);
    }

    // Method to save bill to a text file
    public static void saveBillToFile(Bill bill) {
        try (FileWriter writer = new FileWriter("bills.txt", true)) {
            writer.write(bill.toCSV() + "\n");
            System.out.println("✅ Bill saved to bills.txt");
        } catch (IOException e) {
            System.out.println("❌ Error saving bill: " + e.getMessage());
        }
    }

    // Method to view all saved bills
    public static void viewSavedBills() {
        File file = new File("bills.txt");
        if (!file.exists()) {
            System.out.println("📭 No bills saved yet.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            System.out.println("\n📂 SAVED BILLS:");
            System.out.println("-----------------------------");
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    System.out.println("ID: " + parts[0] +
                            ", Name: " + parts[1] +
                            ", Units: " + parts[2] +
                            ", Amount: ₹" + parts[3] +
                            ", Date: " + parts[4]);
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Error reading bills: " + e.getMessage());
        }
    }
}
