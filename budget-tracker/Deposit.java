public class Deposit {
    private float amount;
    private long date;
    
    public Deposit(float depositAmount) {
        amount = depositAmount;
        date = System.currentTimeMillis();
    }

    public float getAmount() {
        return amount;
    }

    public long getDate() {
        return date;
    }
}