package se.Lexion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.Lexion.dao.SubscriberDAO;
import se.Lexion.model.Plan;
import se.Lexion.model.Subscriber;
import se.Lexion.rules.SubscriberActions;
import se.Lexion.rules.SubscriberFilters;
import se.Lexion.service.SubscriberProcessor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class SubscriberProcessorTest {

    private SubscriberDAO dao;
    private SubscriberProcessor processor;
    private List<Subscriber> subscribers;

    @BeforeEach
    void setUp() {
        // Initialize DAO and processor
        dao = new SubscriberDAO();
        processor = new SubscriberProcessor();
        subscribers = dao.findAll();
    }

    // ----------------------------
    // Scenario 1: Show Active Subscribers
    @Test
    void testActiveSubscribers() {
        // Filter all active subscribers
        List<Subscriber> active = processor.findSubscribers(subscribers, SubscriberFilters.isActive());

        // Verify all are active
        assertTrue(active.stream().allMatch(Subscriber::isActive));
    }

    // ----------------------------
    // Scenario 2: Show Expiring Subscriptions (0 or 1 month)
    @Test
    void testExpiringSubscribers() {
        List<Subscriber> expiring = processor.findSubscribers(subscribers, SubscriberFilters.isExpiring());

        // Verify all have 0 or 1 month remaining
        assertTrue(expiring.stream().allMatch(s -> s.getMonthsRemaining() <= 1));
    }

    // ----------------------------
    // Scenario 3: Show Active and Expiring Subscribers
    @Test
    void testActiveAndExpiringSubscribers() {
        List<Subscriber> result = processor.findSubscribers(subscribers, SubscriberFilters.isActiveAndExpiring());

        // Verify all are active AND expiring
        assertTrue(result.stream().allMatch(s -> s.isActive() && s.getMonthsRemaining() <= 1));
    }

    // ----------------------------
    // Scenario 4: Extend Subscriptions for Active, Paying, Expiring Subscribers
    @Test
    void testExtendSubscriptions() {
        // Store original monthsRemaining per subscriber
        Map<Integer, Integer> original = subscribers.stream()
                .collect(Collectors.toMap(Subscriber::getId, Subscriber::getMonthsRemaining));

        // Apply +3 months to active, paying subscribers with <=1 month remaining
        processor.applyToMatching(
                subscribers,
                s -> SubscriberFilters.isActive().matches(s)
                        && SubscriberFilters.isExpiring().matches(s)
                        && SubscriberFilters.isPaying().matches(s),
                SubscriberActions.extendSubscription(3)
        );

        // Verify only correct subscribers were extended
        subscribers.forEach(s -> {
            int before = original.get(s.getId());
            if (s.isActive() && (s.getPlan() == Plan.BASIC || s.getPlan() == Plan.PRO) && before <= 1) {
                // Should have +3 months
                assertEquals(before + 3, s.getMonthsRemaining());
            } else {
                // Others should be unchanged
                assertEquals(before, s.getMonthsRemaining());
            }
        });
    }

    // ----------------------------
    // Scenario 5: Deactivate Expired Free Subscribers
    @Test
    void testDeactivateExpiredFreeSubscribers() {
        // Apply deactivate action to expired free subscribers
        processor.applyToMatching(
                subscribers,
                s -> SubscriberFilters.byPlan(Plan.FREE).matches(s)
                        && SubscriberFilters.isExpired().matches(s),
                SubscriberActions.deactivate()
        );

        // Verify only expired free subscribers are inactive
        subscribers.stream()
                .filter(s -> s.getPlan() == Plan.FREE && s.getMonthsRemaining() <= 0)
                .forEach(s -> assertFalse(s.isActive(), "Expired free subscriber should be inactive"));
    }

    // ----------------------------
    // Scenario 6: Filter Subscribers by PRO plan
    @Test
    void testFilterByProPlan() {
        List<Subscriber> pros = processor.findSubscribers(subscribers, SubscriberFilters.byPlan(Plan.PRO));

        // Verify all subscribers are PRO plan
        assertTrue(pros.stream().allMatch(s -> s.getPlan() == Plan.PRO));
    }
}