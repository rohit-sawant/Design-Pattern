package org.LLD.ticketingSystem.service;

import org.LLD.ticketingSystem.entity.Agent;
import org.LLD.ticketingSystem.entity.Customer;
import org.LLD.ticketingSystem.entity.Issue;
import org.LLD.ticketingSystem.enums.IssueStatus;
import org.LLD.ticketingSystem.enums.IssueType;
import org.LLD.ticketingSystem.repository.CustomerRepository;
import org.LLD.ticketingSystem.repository.IssueRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class IssueService {

    private final IssueRepository issueRepository;
    private final CustomerRepository customerRepository;

    private static int issueCounter = 1;

    public IssueService(
            IssueRepository issueRepository,
            CustomerRepository customerRepository) {

        this.issueRepository = issueRepository;
        this.customerRepository = customerRepository;
    }

    public Issue createIssue(
            String transactionId,
            IssueType issueType,
            String subject,
            String description,
            String email) {

        Customer customer =
                customerRepository.findByEmail(email);

        if (customer == null) {
            customer = new Customer(email);
            customerRepository.save(customer);
        }

        Issue issue = new Issue(
                issueCounter++,
                issueType,
                transactionId,
                subject,
                description,
                customer
        );

        issueRepository.save(issue);

        return issue;
    }

    public List<Issue> getIssues(Map<String,String> filter) throws Exception {
        List<Issue> issues = issueRepository.findAll();
        if (filter.containsKey("issueType")) {
            try {
                IssueType issueType =
                        IssueType.valueOf(filter.get("issueType"));

                issues = issues.stream()
                        .filter(issue ->
                                issue.getIssueType() == issueType)
                        .toList();

            } catch (IllegalArgumentException ex) {
                throw new RuntimeException(
                        "Invalid issue type");
            }
        }
        return issues;
    }

    public Issue updateIssue(
            int issueId,
            IssueStatus status,
            String resolution) throws Exception {

        Issue issue = issueRepository.findById(issueId);

        if (issue == null) {
            throw new Exception(
                    "Issue not found");
        }

        issue.setStatus(status);

        if (resolution != null &&
                !resolution.isBlank()) {

            issue.setResolution(resolution);
        }

        issueRepository.save(issue);

        return issue;
    }

    public void resolveIssue(
            int issueId,
            String resolution) throws Exception {

        Issue issue = issueRepository.findById(issueId);

        if (issue == null) {
            throw new Exception("Issue not found");
        }

        issue.setStatus(IssueStatus.RESOLVED);
        issue.setResolution(resolution);

        Agent agent = issue.getAssignedTo();

        if (agent != null) {

            // remove resolved issue
            agent.getAssignedIssues()
                    .removeIf(i -> i.getId() == issueId);

            // assign next active issue
            if (agent.getAssignedIssues().isEmpty()) {
                agent.setCurrentIssue(null);
            } else {
                agent.setCurrentIssue(
                        agent.getAssignedIssues().get(0));
            }
        }

        issueRepository.save(issue);
    }

    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }

    public Issue getIssue(int issueId) {
        return issueRepository.findById(issueId);
    }
}