public class Expense {
    private String name;
    private long date;
    private float cost;

    public Expense(String expenseName, float amount) {
        name = expenseName;
        cost = amount;
        date = System.currentTimeMillis();
    }

    public String getName() {
        return name;
    }

    public float getCost() {
        return cost;
    }

    public long getDate() {
        return date;
    }
}