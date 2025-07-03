import model.Customer;
import model.Bill;
import util.BillCalculator;

import java.io.*;
import java.util.Scanner;

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
            choice = sc.nextInt();
            sc.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    generateNewBill(sc);
                    break;
                case 2:
                    viewSavedBills();
                    break;
                case 3:
                    System.out.println("🌼 Thank you for using the system. Exiting...");
                    return;
                default:
                    System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }

    public static void generateNewBill(Scanner sc) {
        System.out.print("Enter Customer ID: ");
        String id = sc.nextLine();

        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Units Consumed: ");
        int units = sc.nextInt();
        sc.nextLine(); // Clear buffer

        Customer customer = new Customer(id, name, units);
        double amount = BillCalculator.calculateBill(units);
        Bill bill = new Bill(customer, amount);

        System.out.println("\n" + bill.toString());
        saveBillToFile(bill);
    }

    public static void saveBillToFile(Bill bill) {
        try {
            FileWriter writer = new FileWriter("bills.txt", true);
            writer.write(bill.toCSV() + "\n");
            writer.close();
            System.out.println("✅ Bill saved to bills.txt");
        } catch (IOException e) {
            System.out.println("❌ Error saving bill: " + e.getMessage());
        }
    }

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
                System.out.println("ID: " + parts[0] + ", Name: " + parts[1] +
                        ", Units: " + parts[2] + ", Amount: ₹" + parts[3] + ", Date: " + parts[4]);
            }
        } catch (IOException e) {
            System.out.println("❌ Error reading bills: " + e.getMessage());
        }
    }
}
