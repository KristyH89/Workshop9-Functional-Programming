public class Subscriber {
    private int id;
    private String email;
    private Plan plan;
    private boolean active;
    private int monthsRemaining;

    public Subscriber(int id, String email, Plan plan, boolean active, int monthsRemaining) {
        this.id = id;
        this.email = email;
        this.plan = plan;
        this.active = active;
        this.monthsRemaining = monthsRemaining;
    }


}
