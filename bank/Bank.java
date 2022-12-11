import java.util.Scanner;
import java.util.ArrayList;

public class Bank {
    private static final Scanner scanner = new Scanner(System.in);
    private ArrayList<Customer> customers = new ArrayList<Customer>();
    public static void main(String[] args) {
        new Bank();
    }

    public Bank() {
        addCustomer("John Doe");
        customers.get(0).addAccount("Saving");
        customers.get(0).addAccount("Everyday");
        startNewTransaction();
    }

    private void startNewTransaction() {
        System.out.println("Welcome to MyBank\n");
        System.out.println("Please login to get started");

        boolean loginSuccess = false;
        while (!loginSuccess) {
            loginSuccess = customers.get(0).login();
        }

        int selection;
        while (true) {
            boolean printSuccessMessage;
            selection = displayMenu();
            printSuccessMessage = processSelection(selection);
            if (printSuccessMessage) 
                System.out.println("\nSuccess\n");
        }
    }

    private int displayMenu() {
        int choice = 0;

        System.out.println("\nPlease make one of the following selections: ");
        System.out.println("1: Withdraw");
        System.out.println("2: Deposit");
        System.out.println("3: Transfer");
        System.out.println("4: Account Balance");
        System.out.println("5: Change Password");
        System.out.println("6: Exit");
        System.out.println();

        while (true) {
            System.out.print("Your selection: ");
            choice = scanner.nextInt();
            if (choice > 0 && choice < 5) break;
            else
                System.out.println("Invalid selection. Try again");
        }
        return choice;
    }

    private boolean processSelection(int selection) {
        switch(selection) {
            case 1: 
                return withdrawSelection();
            case 2: 
                return depositSelection();
            case 3:
                return transferSelection();
            case 4: 
                showAccountBalance();
                return false;
            case 5:
                return processChangePassword();
            case 6:
                startNewTransaction();
                return false;
            default:
                System.out.println("Invalid selection.");
                return false;
        }
    }

    private boolean withdrawSelection() {
        System.out.println("Please select an account to withdraw from (-1 to quit):");
        int accountIndex = customers.get(0).getAccountSelectionIndex();
        if (accountIndex == -1) return false;
        return customers.get(0).withdrawFromAccount(accountIndex);
    }

    private boolean depositSelection() {
        System.out.println("Please select an account to deposit into (-1 to quit):");
        int accountIndex = customers.get(0).getAccountSelectionIndex();
        if (accountIndex == -1) return false;
        return customers.get(0).depositIntoAccount(accountIndex);
    }

    private boolean transferSelection() {
        int transferFromAccountIndex, transferIntoAccountIndex;
        System.out.println("Please select an account to transfer from (-1 to quit):");
        transferFromAccountIndex = customers.get(0).getAccountSelectionIndex();
        if (transferFromAccountIndex == -1) return false;
        do {
            System.out.println("Please select an account to transfer into (-1 to quit):");
            transferIntoAccountIndex = customers.get(0).getAccountSelectionIndex();
            if (transferIntoAccountIndex == -1) return false;
            if (transferFromAccountIndex == transferIntoAccountIndex) 
                System.out.println("You cannot transfer to the same account. Try again");
        } while (transferIntoAccountIndex == transferFromAccountIndex);

        return customers.get(0).transfer(transferFromAccountIndex, transferIntoAccountIndex);
    }

    private boolean showAccountBalance() {
        int accountIndex = customers.get(0).getAccountSelectionIndex();
        if (accountIndex == -1) return false;
        return customers.get(0).showAccountBalance(accountIndex);
    }

    private boolean processChangePassword() {
        return customers.get(0).changePassword();
    }

    private boolean addCustomer(String customerName) {
        customers.add(new Customer(customerName));
        return true;
    }
}