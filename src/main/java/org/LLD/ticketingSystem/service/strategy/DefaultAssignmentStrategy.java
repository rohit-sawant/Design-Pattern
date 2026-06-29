package org.LLD.ticketingSystem.service.strategy;

import org.LLD.ticketingSystem.entity.Agent;
import org.LLD.ticketingSystem.entity.Issue;

import java.util.List;

public class DefaultAssignmentStrategy
        implements AssignmentStrategy {

    @Override
    public Agent assign(
            Issue issue,
            List<Agent> agents) {

        // find free agent
        for (Agent agent : agents) {

            if (agent.getCurrentIssue() == null
                    &&
                    agent.getExpertise()
                            .contains(issue.getIssueType())) {

                return agent;
            }
        }

        // fallback to any matching skilled agent
        for (Agent agent : agents) {

            if (agent.getExpertise()
                    .contains(issue.getIssueType())) {

                return agent;
            }
        }

        return null;
    }
}