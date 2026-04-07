package se.Lexion;

import java.util.*;

public class SubscriberDAO {
private List<Subscriber> subscribers = new ArrayList<>();

public void save(Subscriber subscriber) {
    subscribers.add(subscriber);
}

public List<Subscriber> findAll() {
    return subscribers;
}

public Optional<Subscriber> findById(int id) {
    return subscribers.stream()
            .filter(s -> s.getId() ==id)
            .findFirst();
    }
}
