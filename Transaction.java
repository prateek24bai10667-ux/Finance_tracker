import java.io.Serializable;

// Using Serializable to allow saving objects to a file
public class Transaction implements Serializable {
    private String description;
    private double amount;
    private String category;
    private boolean isExpense;

    public Transaction(String description, double amount, String category, boolean isExpense) {
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.isExpense = isExpense;
    }

    @Override
    public String toString() {
        String type = isExpense ? "[-] Expense" : "[+] Income";
        return String.format("%-12s | %-15s | $%-10.2f | %s", type, category, amount, description);
    }
}