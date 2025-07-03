package util;

public class BillCalculator {

    public static double calculateBill(int units) {
        double fixedCharge = 50; // ₹50 fixed charge per bill
        double amount;

        if (units <= 100) {
            amount = units * 1.5;
        } else if (units <= 300) {
            amount = 100 * 1.5 + (units - 100) * 2.5;
        } else {
            amount = 100 * 1.5 + 200 * 2.5 + (units - 300) * 4.0;
        }

        return amount + fixedCharge;
    }
}
