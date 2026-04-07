package se.Lexion.rules;

import se.Lexion.functional.SubscriberFilter;
import se.Lexion.model.Plan;

public class SubscriberFilters {

    // Active subscribers
    public static SubscriberFilter isActive() {
        return s -> s.isActive();
    }

    // Expiring (0 or 1 month)
    public static SubscriberFilter isExpiring() {
        return s -> s.getMonthsRemaining() <= 1;
    }

    // Active + Expiring
    public static SubscriberFilter isActiveAndExpiring() {
        return s -> s.isActive() && s.getMonthsRemaining() <= 1;
    }

    // Filter on plan (FREE, BASIC, PRO)
    public static SubscriberFilter byPlan(Plan plan) {
        return s -> s.getPlan() == plan;
    }

    // Paying users (BASIC + PRO)
    public static SubscriberFilter isPaying() {
        return s -> s.getPlan() == Plan.BASIC || s.getPlan() == Plan.PRO;
    }

    // EXTRA: matches subscribers with an expired subscription
    public static SubscriberFilter isExpired() {
        return s -> s.getMonthsRemaining() <= 0;
    }
}