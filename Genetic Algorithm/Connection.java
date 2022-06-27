public class Connection
{
    private final long id_m1;
    private final long id_m2;
    private final long cost;
    private long amount;

    public long getId_m1() {
        return id_m1;
    }

    public long getId_m2() {
        return id_m2;
    }

    public long getCost() {
        return cost;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Connection(long id_m1, long id_m2, long cost, long amount) {
        this.id_m1 = id_m1;
        this.id_m2 = id_m2;
        this.cost = cost;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "id_m1=" + id_m1 +
                ", id_m2=" + id_m2 +
                ", cost=" + cost +
                ", amount=" + amount;
    }
}
