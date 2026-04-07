package se.Lexion;
import java.util.List;
public class Main {
    static void main(String[] args) {

        SubscriberDAO dao = new SubscriberDAO();
        SubscriberProcessor processor = new SubscriberProcessor();

        // Test data

        dao.save(new Subscriber(1, "aragorn@therealking.com", Plan.PRO, true, 4));
        dao.save(new Subscriber(2, "gimli@thebestdwarf.com", Plan.BASIC, false, 3));
        dao.save(new Subscriber(3,"legolas@thebestelf.com", Plan.BASIC, true, 1));
        dao.save(new Subscriber(4, "frodo@thehobbit.com", Plan.FREE, true, 0));

        List<Subscriber> list = dao.findAll();

        // Rules

        SubscriberFilter activeFilter = s -> s.isActive();
        SubscriberFilter expiringFilter = s -> s.getMonthsRemaining() <= 1;

        SubscriberAction extened = s ->
                s.setMonthsRemaining(s.getMonthsRemaining() + 3);

        List<Subscriber> result =
                processor.findSubscribers(list, activeFilter);

        result.forEach(System.out::println);

            }
}
