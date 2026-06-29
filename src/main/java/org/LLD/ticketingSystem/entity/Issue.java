package org.LLD.ticketingSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.LLD.ticketingSystem.enums.IssueStatus;
import org.LLD.ticketingSystem.enums.IssueType;
@Data
@AllArgsConstructor
public class Issue {

    private int id;

    private IssueType issueType;

    private IssueStatus status;

    private String resolution;

    private String transactionId;

    private String subject;

    private String description;

    private Customer customer;

    private Agent assignedTo;

    public Issue(
            int id,
            IssueType issueType,
            String transactionId,
            String subject,
            String description,
            Customer customer) {

        this.id = id;
        this.issueType = issueType;
        this.transactionId = transactionId;
        this.subject = subject;
        this.description = description;
        this.customer = customer;

        this.status = IssueStatus.OPEN;
        this.resolution = null;
        this.assignedTo = null;
    }

    public void assignTo(Agent agent) {
        this.assignedTo = agent;
        this.status = IssueStatus.ASSIGNED;
        agent.assignIssue(this);
    }

    public void resolve(String resolution) {
        this.resolution = resolution;
        this.status = IssueStatus.RESOLVED;
    }

    public void close() {
        this.status = IssueStatus.CLOSED;
    }
}