package se.Lexion.rules;

import se.Lexion.functional.SubscriberAction;

public class SubscriberActions {


    public static SubscriberAction extendSubscription(int months) {
        return s -> s.setMonthsRemaining(s.getMonthsRemaining() + months);
    }


    public static SubscriberAction deactivate() {
        return s -> s.setActive(false);
    }
}