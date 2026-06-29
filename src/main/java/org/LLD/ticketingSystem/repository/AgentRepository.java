package org.LLD.ticketingSystem.repository;
import org.LLD.ticketingSystem.entity.Agent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AgentRepository {

    private final Map<String, Agent> agents = new HashMap<>();

    public void save(Agent agent) {
        agents.put(agent.getEmail(), agent);
    }

    public Agent findByEmail(String email) {
        return agents.get(email);
    }

    public List<Agent> findAll() {
        return new ArrayList<>(agents.values());
    }

    public void delete(String email) {
        agents.remove(email);
    }
}