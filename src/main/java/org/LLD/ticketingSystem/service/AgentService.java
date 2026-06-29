package org.LLD.ticketingSystem.service;

import org.LLD.ticketingSystem.entity.Agent;
import org.LLD.ticketingSystem.entity.Issue;
import org.LLD.ticketingSystem.enums.IssueStatus;
import org.LLD.ticketingSystem.enums.IssueType;
import org.LLD.ticketingSystem.repository.AgentRepository;

import java.util.List;

public class AgentService {

    private final AgentRepository agentRepository;
    private final IssueService issueService;

    public AgentService(
            AgentRepository agentRepository,
            IssueService issueService) {

        this.agentRepository = agentRepository;
        this.issueService = issueService;
    }
    public Agent addAgent(
            String email,
            String name,
            List<IssueType> expertise) {

        Agent existing =
                agentRepository.findByEmail(email);

        if (existing != null) {
            throw new RuntimeException(
                    "Agent already exists");
        }

        Agent agent = new Agent(
                email,
                expertise);

        agentRepository.save(agent);

        return agent;
    }
    public List<Issue> viewAgentHistory(
            String agentEmail) throws Exception {

        Agent agent =
                agentRepository.findByEmail(agentEmail);

        if (agent == null) {
            throw new Exception(
                    "Agent not found");
        }

        return issueService.getAllIssues()
                .stream()
                .filter(issue ->
                        issue.getStatus() == IssueStatus.RESOLVED
                                &&
                                issue.getAssignedTo() != null
                                &&
                                issue.getAssignedTo()
                                        .equals(agent))
                .toList();
    }

    public List<Agent> getAllAgents() {
        agentRepository.findAll();
    }
}