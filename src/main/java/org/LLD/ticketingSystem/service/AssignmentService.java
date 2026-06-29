package org.LLD.ticketingSystem.service;

import org.LLD.ticketingSystem.entity.Agent;
import org.LLD.ticketingSystem.entity.Issue;
import org.LLD.ticketingSystem.enums.IssueStatus;
import org.LLD.ticketingSystem.service.strategy.AssignmentStrategy;

import java.util.List;

public class AssignmentService {

    private final AgentService agentService;

    private final IssueService issueService;

    private final AssignmentStrategy strategy;

    public AssignmentService(
            AgentService agentService,
            IssueService issueService,
            AssignmentStrategy strategy) {

        this.agentService = agentService;
        this.issueService = issueService;
        this.strategy = strategy;
    }


    public void assignIssue(
            int issueId) throws Exception {

        Issue issue =
                issueService.getIssue(issueId);

        if (issue == null) {
            throw new Exception(
                    "Issue not found");
        }

        List<Agent> agents =
                agentService.getAllAgents();

        Agent assigned =
                strategy.assign(
                        issue,
                        agents);

        if (assigned == null) {
            throw new Exception(
                    "No suitable agent found");
        }

        issue.setAssignedTo(assigned);

        // immediately assign
        if (assigned.getCurrentIssue() == null) {

            assigned.setCurrentIssue(issue);

        }

        // always maintain queue/history
        assigned.getAssignedIssues()
                .add(issue);

        issue.setStatus(
                IssueStatus.ASSIGNED);
    }
}