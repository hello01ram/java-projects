import java.util.Scanner;
import java.util.ArrayList;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private float saving;
    private ArrayList<Deposit> deposits = new ArrayList<Deposit>();
    private ArrayList<Expense> expenses = new ArrayList<Expense>();
    
    public static void main(String[] args) {
        System.out.println("Hello, App!");
        new App();
    }

    public App() {
        saving = 0.0f;
        while (true) {
            getOption();
        }
    }

    private boolean getOption() {
        int option;
        System.out.println("Please select one of the following options: ");
        System.out.println(" 1: Deposit");
        System.out.println(" 2: Expense");
        System.out.println(" 3: Check saving");
        do {
            System.out.print("Your selection: ");
            option = scanner.nextInt();
            if (option <= 0 || option > 3) 
                System.out.println("Invalid option!");
        } while (option <= 0 || option > 3);
        
        switch (option) {
            case 1:
                return addDeposit();
            case 2:
                return addExpense();
            case 3:
                return showSaving();
        }

        return true;
    }

    public boolean addDeposit() {
        float depositAmount = getDepositAmount();
        if (depositAmount == 0.0) return false;
        deposits.add(new Deposit(depositAmount));
        addMoney(depositAmount);
        return true;
    }

    public boolean addExpense() {
        float expenseAmount = getExpenseAmount();
        if (expenseAmount == 0.0) return false;
        String expenseName = getExpenseName();
        if (expenseName.equals("")) return false;
        expenses.add(new Expense(expenseName, expenseAmount));
        takeMoney(expenseAmount);
        return true;
    }

    public boolean showSaving() {
        System.out.println("\nSaving amount: " + saving + "\n");
        return true;
    }

    private float getDepositAmount() {
        System.out.print("Please enter deposit amount (Max 1000): ");
        float depositAmount = scanner.nextFloat();
        if (depositAmount <= 0.0 || depositAmount > 1000) {
            System.out.println("Amount is invalid!");
            depositAmount = 0.0f;
        }
        return depositAmount;
    }

    private float getExpenseAmount() {
        System.out.print("Please enter expense cost: ");
        float expenseAmount = scanner.nextFloat();
        if (expenseAmount <= 0 || expenseAmount > saving) {
            System.out.println("Amount is invalid");
            expenseAmount = 0.0f;
        }
        return expenseAmount;
    }

    private String getExpenseName() {
        System.out.print("Enter expense name: ");
        String expenseName = scanner.nextLine();
        if (expenseName.equals("quit") || expenseName.equals("exit")) 
            expenseName = "";
        return expenseName;
    }

    private void addMoney(float amount) {
        saving += amount;
    }

    private void takeMoney(float amount) {
        saving -= amount;
    }
}