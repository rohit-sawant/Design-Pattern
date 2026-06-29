package org.LLD.ticketingSystem.service.strategy;

import org.LLD.ticketingSystem.entity.Agent;
import org.LLD.ticketingSystem.entity.Issue;

import java.util.List;

public interface AssignmentStrategy {

    Agent assign(
            Issue issue,
            List<Agent> agents);
}