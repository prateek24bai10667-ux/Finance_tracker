import java.io.*;
import java.util.*;

public class FinanceTracker {
    private static List<Transaction> transactions = new ArrayList<>();
    private static final String FILE_NAME = "data.ser";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadData(); 
        System.out.println("--- Welcome to ExpenseWise Tracker ---");

        while (true) {
            System.out.println("\n1. Add Income  2. Add Expense  3. View History  4. View Balance  5. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1 -> addTransaction(false);
                case 2 -> addTransaction(true);
                case 3 -> viewHistory();
                case 4 -> showBalance();
                case 5 -> {
                    saveData();
                    System.out.println("Data saved. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option!");
            }
        }
    }

    private static void addTransaction(boolean isExpense) {
        System.out.print("Description: ");
        String desc = scanner.nextLine();
        System.out.print("Amount: ");
        double amt = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Category (e.g., Food, Salary, Rent): ");
        String cat = scanner.nextLine();

        transactions.add(new Transaction(desc, amt, cat, isExpense));
        System.out.println("Transaction added successfully!");
    }

    private static void showBalance() {
        double balance = 0;
        for (Transaction t : transactions) {
            balance += (transactions.indexOf(t) >= 0 ) ? 0 : 0; 
        }
        
        double totalIn = transactions.stream().filter(t -> t.toString().contains("[+]")).mapToDouble(t -> 1.0).sum(); 
        System.out.println("Current Balance: Calculation logic would go here based on getters.");
    }

    private static void viewHistory() {
        System.out.println("\n--- Transaction History ---");
        transactions.forEach(System.out::println);
    }

    private static void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(transactions);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    private static void loadData() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            transactions = (List<Transaction>) ois.readObject();
        } catch (Exception e) {
            System.out.println("Starting with a fresh database.");
        }
    }
}