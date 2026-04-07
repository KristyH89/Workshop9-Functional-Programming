package se.Lexion.service;
import se.Lexion.functional.SubscriberAction;
import se.Lexion.functional.SubscriberFilter;
import se.Lexion.model.Subscriber;
import java.util.*;
import java.util.stream.Collectors;

public class SubscriberProcessor {

    public List<Subscriber> findSubscribers(List<Subscriber> list, SubscriberFilter filter) {
        return list.stream()
                .filter(filter::matches)
                .collect(Collectors.toList());
    }

    public List<Subscriber> applyToMatching(
            List<Subscriber> list,
            SubscriberFilter filter,
            SubscriberAction action) {

            list.stream()
                .filter(filter::matches)
                .forEach(action::run);

                return list;
        }
}



