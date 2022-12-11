import java.util.Scanner;
import java.util.ArrayList;

class Customer {
    private static final Scanner scanner = new Scanner(System.in);
    private boolean loggedIn = false;
    private String name;
    private String id;
    private String password;
    public ArrayList<Account> accounts = new ArrayList<Account>();

    public Customer(String cName) {
        name = cName;
        id = "12345";
        password = "ABC123";
    }

    private void setPassword(String newPassword) {
        password = newPassword;
    }

    private boolean checkPassword(String pass) {
        return password.equals(pass);
    }

    private boolean checkId(String cId) {
        return id.equals(cId);
    }

    private boolean passwordRulesValidation(String pass) {
        if (pass.length() < 6) {
            return false;
        }
        return true;
    }

    public boolean changePassword() {
        String cPass, newPass;
        System.out.print("Input your current password ('0' or 'exit' to exit): ");
        do {
            cPass = scanner.nextLine();
            if (cPass.equals("0") || cPass.equals("exit")) {
                return false;
            }
            if (!checkPassword(cPass)) {
                System.out.println("Entered password is invalid, please try again.\n");
            }
        } while (!checkPassword(cPass));

        do {
            System.out.print("\nEnter new password (min 6 characters): ");
            newPass = scanner.nextLine();
            if (!passwordRulesValidation(newPass)) {
                System.out.print("Invalid password, please try again.\n");
            }
        } while (!passwordRulesValidation(newPass));

        setPassword(newPass);
        
        return true;
    }

    public boolean login() {
        String cId, cPass;
        do {
            System.out.print("Please enter your customer ID ('0' or 'exit' to exit): ");
            cId = scanner.nextLine();
            if (cId.equals("0") || cId.equals("exit")) {
                return false;
            }
            if (!checkId(cId)) {
                System.out.println("Entered ID is invalid, please try again.\n");
            }
        } while (!checkId(cId));

        do {
            System.out.print("Please enter your customer password ('0' or 'exit' to exit): ");
            cPass = scanner.nextLine();
            if (cPass.equals("0") || cPass.equals("exit")) {
                return false;
            }
            if (!checkPassword(cPass)) {
                System.out.println("Entered password is invalid, please try again.\n");
            }
        } while (!checkPassword(cPass));

        loggedIn = true;
        return loggedIn;
    }

    public boolean addAccount(String accountName) {
        accounts.add(new Account(accountName));
        return true;
    }

    public void display() {
        String customerDescription = "";
        customerDescription += "Hello " + name + "    ";
        customerDescription += id + "\n";
        System.out.println(customerDescription);
    }

    public void displayAccounts() {
        for (int i = 0; i < accounts.size(); i++) {
            System.out.print(i+1 + ": ");
            accounts.get(i).display();
        }
    }

    public boolean withdrawFromAccount(int accountIndex) {
        return accounts.get(accountIndex).startWithdraw();
    }

    public boolean depositIntoAccount(int accountIndex) {
        return accounts.get(accountIndex).startDeposit();
    }

    public boolean transfer(int fromAccountIndex, int toAccountIndex) {
        boolean success;
        double amount, fromAccountBalance;
        fromAccountBalance = accounts.get(fromAccountIndex).getAccountBalance();
        System.out.print("Enter amount to transfer (-1 to quit): ");
        do {
            amount = scanner.nextDouble();
            if (amount == -1) return false;
            if (amount <= 0 || amount > fromAccountBalance) 
                System.out.println("Amount is invalid. Try again");
        } while (amount <= 0 || amount > fromAccountBalance);
        
        success = accounts.get(fromAccountIndex).withdraw(amount);
        if (!success) return success;
        success = accounts.get(toAccountIndex).deposit(amount);
        if (!success) {
            accounts.get(fromAccountIndex).deposit(amount);
            return false;
        }
        return success;
    }

    public boolean showAccountBalance(int accountIndex) {
        accounts.get(accountIndex).displayBalance();
        return true;
    }

    public int getAccountSelectionIndex() {
        int accountIndex;
        do {
            displayAccounts();
            System.out.print("Your selection: ");
            accountIndex = scanner.nextInt();
            if (accountIndex == -1) {
                return accountIndex;
            }
            if (accountIndex < 1 && accountIndex > accounts.size()) {
                System.out.println("Invalid account selection. Try again");
            }
            System.out.println();
        } while (accountIndex < 1 && accountIndex > accounts.size());
        return accountIndex - 1;
    }
}