![Java](https://img.shields.io/badge/Java-25.0.2-blue)

# 📦Subscription Management System — Functional Programming Workshop

This Java project demonstrates the use of **functional programming principles** to build a subscription management system. The system manages subscribers, subscription plans, and applies business rules using functional interfaces and lambda expressions.

---

## 📘 Workshop Instructions

👉 [View workshop instructions](Functional_Programming_Workshop.md)

---

## 🧙 Project Structure

```
com.subscriptions
│
├── model/
│ ├── Subscriber.java # Subscriber entity
│ └── Plan.java # Subscription plan enum (FREE, BASIC, PRO)
│
├── dao/
│ └── SubscriberDAO.java # Data access object for subscribers
│
├── functional/
│ ├── SubscriberFilter.java # Functional interface for filtering
│ └── SubscriberAction.java # Functional interface for actions
│
├── service/
│ └── SubscriberProcessor.java # Processes subscribers with filters & actions
│
├── rules/
│ ├── SubscriberFilters.java # Predefined filters (active, expiring, paying, etc.)
│ └── SubscriberActions.java # Predefined actions (extend, deactivate)
│
└── App.java # Main class demonstrating the scenarios
└── SubscriberProcessorTest.java # JUnit tests for all scenarios
```
---
## ⚙️ Features

- Filter subscribers by:
  - Active status
  - Expiring subscriptions
  - Subscription plan
  - Paying subscribers
  - Expired subscriptions
- Apply actions to subscribers:
  - Extend subscription periods
  - Deactivate expired accounts
- Demonstrates **functional programming concepts**:
  - Reusable, composable filters and actions
  - Stream-based processing

---

## ⚡ Quick Start

Clone the repository:

```
git clone https://github.com/KristyH89/Workshop9-Functional-Programming.git
``` 
---

## ▶️ How to Run the Application

1. Open in an IDE with Maven support (e.g., IntelliJ IDEA).
2. Run App.java to execute core scenarios:
   - Display active subscribers
   - Show expiring subscriptions
   - Extend subscriptions for eligible users
   - Deactivate expired free subscribers
   - Filter subscribers by plan
3. Run SubscriberProcessorTest.java for JUnit 5 tests covering all core business rules.

---

🧪 Testing

JUnit 5 tests cover the following scenarios:

1. Active subscribers
2. Expiring subscriptions (0 or 1 month)
3. Active and expiring subscribers
4. Extend subscriptions for active, paying, expiring subscribers
5. Deactivate expired free subscribers
6. Filter subscribers by plan



