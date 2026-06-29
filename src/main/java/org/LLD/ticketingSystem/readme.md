# Customer Issue Resolution System

## Problem Statement

Design and implement an in-memory Customer Issue Resolution System that allows customers to create issues, support agents to manage them, and administrators to assign and track issue resolution.

The system should be extensible, maintainable, and support future assignment and filtering strategies.

---

# Functional Requirements

The system should support the following operations.

## 1. Create Issue

Customers should be able to create a support issue.

### API

```java
createIssue(
    String transactionId,
    IssueType issueType,
    String subject,
    String description,
    String customerEmail
)
```

### Example

```java
createIssue(
    "TXN001",
    PAYMENT,
    "Money Debited",
    "Amount deducted but transaction failed",
    "user@test.com"
);
```

### Expected Behavior

- Generate a unique issue id.
- Store the issue in the system.
- Mark the issue status as `OPEN`.

---

## 2. Add Agent

Support agents can be registered into the system.

### API

```java
addAgent(
    String agentEmail,
    String agentName,
    List<IssueType> expertise
)
```

### Example

```java
addAgent(
    "john@test.com",
    "John",
    List.of(PAYMENT, INSURANCE)
);
```

### Expected Behavior

- Register the agent.
- Store agent expertise.
- Maintain agent assignment history.

---

## 3. Assign Issue

Assign an issue to a support agent.

### API

```java
assignIssue(String issueId)
```

### Expected Behavior

- Find an eligible support agent.
- Assign the issue to the selected agent.
- Update issue status to `ASSIGNED`.

### Current Assignment Strategy

For now, assign the issue to any available support agent.

### Future Assignment Strategies

- Round Robin
- Least Loaded
- Skill Based
- Weighted Assignment
- Sticky Assignment

---

## 4. Get Issues

Retrieve issues using filters.

### API

```java
getIssues(Map<String, String> filters)
```

### Example

```java
getIssues(Map.of(
    "email", "user@test.com"
));

getIssues(Map.of(
    "status", "OPEN"
));

getIssues(Map.of(
    "issueType", "PAYMENT"
));
```

### Supported Filters

| Filter | Description |
|---------|-------------|
| email | Customer email |
| issueType | Issue type |
| status | Current issue status |
| assignedTo | Assigned support agent |
| transactionId | Transaction id |
| createdBy | Issue creator |

---

## 5. Update Issue

Update issue information.

### API

```java
updateIssue(
    String issueId,
    IssueStatus status,
    String resolution
)
```

### Example

```java
updateIssue(
    "ISSUE-1",
    RESOLVED,
    "Transaction amount refunded"
);
```

### Expected Behavior

- Update issue status.
- Store issue resolution.

---

## 6. Resolve Issue

Resolve an existing issue.

### API

```java
resolveIssue(
    String issueId,
    String resolution
)
```

### Example

```java
resolveIssue(
    "ISSUE-1",
    "Amount credited back"
);
```

### Expected Behavior

- Mark issue status as `RESOLVED`.
- Save resolution details.

---

## 7. View Agent Work History

Retrieve issues handled by support agents.

### API

```java
viewAgentsWorkHistory()
```

### Expected Behavior

Return all issues worked on by each support agent.

### Example Output

```
Agent: John

ISSUE-1
ISSUE-4
ISSUE-8

Agent: Alice

ISSUE-2
ISSUE-5
```

---

# Entities

## Issue

```java
Issue
    issueId
    transactionId
    customerEmail
    issueType
    subject
    description
    status
    assignedAgent
    resolution
    createdAt
```

---

## Agent

```java
Agent
    email
    name
    expertise
    activeIssueCount
    issueHistory
```

---

# Enums

## IssueType

```java
PAYMENT
INSURANCE
MUTUAL_FUND
GOLD
```

## IssueStatus

```java
OPEN
ASSIGNED
RESOLVED
CLOSED
```

---

# Non-Functional Requirements

- The solution should be object-oriented.
- The design should follow SOLID principles.
- The system should be extensible.
- Assignment strategies should be pluggable.
- Filtering should support future extensions.
- The implementation should be in-memory.
- The code should be maintainable and testable.

---

# Design Considerations

The solution should be designed keeping the following extensibility points in mind:

- Multiple assignment strategies.
- Dynamic issue filtering.
- Issue lifecycle management.
- Agent workload balancing.
- Audit logging.
- Notifications.
- Escalation workflows.

---

# Constraints

- No database persistence is required.
- No external frameworks are required.
- Data can be stored using in-memory collections.
- Thread safety is optional unless explicitly mentioned.

---

# Bonus Features

- Agent availability tracking.
- SLA support.
- Issue priority management.
- Escalation engine.
- Retry/reassignment support.
- Event-driven notifications.
- Audit trail.
- Search optimization.