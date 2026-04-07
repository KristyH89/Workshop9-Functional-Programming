package se.Lexion.dao;

import se.Lexion.model.Plan;
import se.Lexion.model.Subscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubscriberDAO {

    private final List<Subscriber> subscribers = new ArrayList<>();

    public SubscriberDAO() {
        // Test data

        subscribers.add(new Subscriber(1, "aragorn@therealking.com", Plan.PRO, true, 4));
        subscribers.add(new Subscriber(2, "gimli@thebestdwarf.com", Plan.BASIC, false, 3));
        subscribers.add(new Subscriber(3, "legolas@thebestelf.com", Plan.PRO, true, 7));
        subscribers.add(new Subscriber(4, "frodo@thehobbit.com", Plan.FREE, true, 0));
        subscribers.add(new Subscriber(5, "samwise@shire.com", Plan.FREE, true, 1));
        subscribers.add(new Subscriber(6, "gandalf@valinor.com", Plan.PRO, true, 6));
        subscribers.add(new Subscriber(7, "boromir@gondor.com", Plan.BASIC, false, 0));
        subscribers.add(new Subscriber(8, "faramir@gondor.com", Plan.BASIC, true, 2));
        subscribers.add(new Subscriber(9, "galadriel@lorien.com", Plan.PRO, true, 8));
        subscribers.add(new Subscriber(10, "elrond@rivendell.com", Plan.PRO, true, 5));
        subscribers.add(new Subscriber(11, "pippin@shire.com", Plan.FREE, true, 0));
        subscribers.add(new Subscriber(12, "merry@shire.com", Plan.FREE, true, 2));
        subscribers.add(new Subscriber(13, "saruman@isengard.com", Plan.PRO, false, 0));
        subscribers.add(new Subscriber(14, "sauron@mordor.com", Plan.PRO, false, 0));
        subscribers.add(new Subscriber(15, "eowyn@rohan.com", Plan.BASIC, true, 1));
        subscribers.add(new Subscriber(16, "theoden@rohan.com", Plan.BASIC, true, 3));
        subscribers.add(new Subscriber(17, "treebeard@fangorn.com", Plan.FREE, true, 4));
        subscribers.add(new Subscriber(18, "bilbo@shire.com", Plan.FREE, false, 0));
        subscribers.add(new Subscriber(19, "thranduil@mirkwood.com", Plan.PRO, true, 7));
        subscribers.add(new Subscriber(20, "denethor@gondor.com", Plan.BASIC, false, 0));
    }

    public void save(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public List<Subscriber> findAll() {
        return new ArrayList<>(subscribers);
    }

    public Optional<Subscriber> findById(int id) {
        return subscribers.stream()
                .filter(s -> s.getId() == id)
                .findFirst();
    }
}