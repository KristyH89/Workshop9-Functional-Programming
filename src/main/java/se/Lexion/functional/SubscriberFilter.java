package se.Lexion.functional;

import se.Lexion.model.Subscriber;

@FunctionalInterface
public interface SubscriberFilter {
boolean matches(Subscriber subscriber);
}
