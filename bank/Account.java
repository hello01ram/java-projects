import java.util.Scanner;

class Account {
    private static final Scanner scanner = new Scanner(System.in);
    private String name;
    private int number;
    private int bsb;
    private double balance;

    public Account(String aName) {
        name = aName;
        number = (int) (Math.random()*10000000);
        bsb = (int) (Math.random()*1000000);
        balance = 0.0;
    }

    public double getAccountBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
        balance -= amount;
        return true;
    }

    public boolean deposit(double amount) {
        balance += amount;
        return true;
    }

    public boolean startWithdraw() {
        double amount;
        boolean withdrawSuccess;
        do {
            System.out.print("Enter amount to withdraw (-1 to quit): ");
            amount = scanner.nextDouble();
            if (amount == -1) return false;
            if (amount <= 0  || amount > balance) 
                System.out.println("Amount entered invalid. Try again");
        } while (amount <= 0 || amount > balance);

        System.out.println();
        withdrawSuccess = withdraw(amount);
        return withdrawSuccess;
    }

    public boolean startDeposit() {
        double amount;
        boolean depositSuccess;
        do {
            System.out.print("Enter amount to deposit (-1 to quit, limit: 1000): ");
            amount = scanner.nextDouble();
            if (amount == -1) return false;
            if (amount <= 0  || amount > 1000) 
                System.out.println("Amount entered invalid. Try again");
        } while (amount <= 0 || amount > 1000);

        System.out.println();
        depositSuccess = deposit(amount);
        return depositSuccess;
    }

    public void display() {
        String accountDescription = "";
        accountDescription += name + "    ";
        accountDescription += "Account Number: " + number + "    ";
        accountDescription += "BSB: " + bsb + "    ";
        accountDescription += "Balance: $" + balance;
        System.out.println(accountDescription);
    }

    public void displayBalance() {
        System.out.println("Account Balance: $" + balance);
    }
}