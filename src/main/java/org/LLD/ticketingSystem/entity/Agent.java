package org.LLD.ticketingSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.LLD.ticketingSystem.enums.IssueType;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Agent extends User {

    private Issue currentIssue;
    private List<IssueType> expertise;

    private List<Issue> assignedIssues;

    public Agent(
            String email,
            List<IssueType> expertise) {

        super(email);
        this.expertise = expertise;
        this.assignedIssues = new ArrayList<>();
    }

    public void assignIssue(Issue issue) {
        assignedIssues.add(issue);
    }

    public int getActiveIssueCount() {
        return assignedIssues.size();
    }
}