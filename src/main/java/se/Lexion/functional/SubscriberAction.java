package se.Lexion.functional;

import se.Lexion.model.Subscriber;

@FunctionalInterface
public interface SubscriberAction {
    void run(Subscriber subscriber);
}
