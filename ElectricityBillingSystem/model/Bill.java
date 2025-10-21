package model;

import java.time.LocalDate;

public class Bill {
    private Customer customer;
    private double amount;
    private LocalDate date;

    public Bill(Customer customer, double amount) {
        this.customer = customer;
        this.amount = amount;
        this.date = LocalDate.now();
    }

    public String toString() {
        return "📄 Electricity Bill\n" +
            "--------------------------\n" +
            "Customer ID: " + customer.getId() + "\n" +
            "Name       : " + customer.getName() + "\n" +
            "Units Used : " + customer.getUnits() + "\n" +
            "Amount Due : ₹" + String.format("%.2f", amount) + "\n" +
            "Date       : " + date + "\n";
    }

    public String toCSV() {
        return customer.getId() + "," + customer.getName() + "," +
            customer.getUnits() + "," + amount + "," + date;
    }
}
