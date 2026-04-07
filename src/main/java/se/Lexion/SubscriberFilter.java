package se.Lexion;

@FunctionalInterface
public interface SubscriberFilter {
boolean matches(Subscriber subscriber);
}
