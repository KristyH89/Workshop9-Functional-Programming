package se.Lexion;

import se.Lexion.dao.SubscriberDAO;
import se.Lexion.model.Plan;
import se.Lexion.model.Subscriber;
import se.Lexion.service.SubscriberProcessor;
import se.Lexion.rules.SubscriberActions;
import se.Lexion.rules.SubscriberFilters;

import java.util.List;

public class App {
    public static void main(String[] args) {

        SubscriberDAO dao = new SubscriberDAO();
        SubscriberProcessor processor = new SubscriberProcessor();

        // Retrieve all subscribers from the DAO
        List<Subscriber> list = dao.findAll();

        // ----------------------------
        // Scenario 1: Display all active subscribers
        System.out.println("=== Active Subscribers ===");
        processor.findSubscribers(list, SubscriberFilters.isActive())
                .forEach(System.out::println);

        // ----------------------------
        // Scenario 2: Display subscribers whose subscriptions are expiring (0 or 1 month remaining)
        System.out.println("\n=== Expiring Subscribers ===");
        processor.findSubscribers(list, SubscriberFilters.isExpiring())
                .forEach(System.out::println);

        // ----------------------------
        // Scenario 3: Display subscribers who are both active and expiring
        System.out.println("\n=== Active & Expiring Subscribers ===");
        processor.findSubscribers(list, SubscriberFilters.isActiveAndExpiring())
                .forEach(System.out::println);

        // ----------------------------
        // Scenario 4: Extend subscriptions by 3 months for active, paying, and expiring subscribers
        System.out.println("\n=== Extending Subscriptions for Active, Paying, Expiring Subscribers ===");
        processor.applyToMatching(
                list,
                s -> SubscriberFilters.isActive().matches(s)
                        && SubscriberFilters.isExpiring().matches(s)
                        && SubscriberFilters.isPaying().matches(s),
                SubscriberActions.extendSubscription(3)
        );

        // Print all subscribers after extension to observe the changes
        list.forEach(System.out::println);

        // ----------------------------
        // Scenario 5: Deactivate free subscribers whose subscriptions have expired
        System.out.println("\n=== Deactivating Expired Free Subscribers ===");
        processor.applyToMatching(
                list,
                s -> SubscriberFilters.byPlan(Plan.FREE).matches(s)
                        && SubscriberFilters.isExpired().matches(s),
                SubscriberActions.deactivate()
        );

        // Print all subscribers after deactivation to observe the changes
        list.forEach(System.out::println);

        // ----------------------------
        // Scenario 6: Display subscribers filtered by the PRO plan
        System.out.println("\n=== Subscribers with PRO Plan ===");
        processor.findSubscribers(list, SubscriberFilters.byPlan(Plan.PRO))
                .forEach(System.out::println);
    }
}