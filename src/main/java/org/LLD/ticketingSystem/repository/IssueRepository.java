package org.LLD.ticketingSystem.repository;

import org.LLD.ticketingSystem.entity.Issue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class IssueRepository {

    private final Map<Integer, Issue> issues = new HashMap<>();

    public void save(Issue issue) {
        issues.put(issue.getId(), issue);
    }

    public Issue findById(int issueId) {
        return issues.get(issueId);
    }

    public List<Issue> findAll() {
        return new ArrayList<>(issues.values());
    }

    public void delete(int issueId) {
        issues.remove(issueId);
    }
}