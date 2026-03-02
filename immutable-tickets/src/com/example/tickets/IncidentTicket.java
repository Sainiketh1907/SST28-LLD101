package com.example.tickets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * REFACTORED: An immutable ticket object.
 *
 * - All fields are private final
 * - No setters
 * - Defensive copying for collections
 * - Created via Builder pattern
 * - Validation centralized in Builder.build()
 */
public class IncidentTicket {

    private final String id;
    private final String reporterEmail;
    private final String title;

    private final String description;
    private final String priority;       // LOW, MEDIUM, HIGH, CRITICAL
    private final List<String> tags;     // immutable
    private final String assigneeEmail;
    private final boolean customerVisible;
    private final Integer slaMinutes;    // optional
    private final String source;         // e.g. "CLI", "WEBHOOK", "EMAIL"

    // Private constructor - only Builder can create instances
    private IncidentTicket(Builder builder) {
        this.id = builder.id;
        this.reporterEmail = builder.reporterEmail;
        this.title = builder.title;
        this.description = builder.description;
        this.priority = builder.priority;
        // Defensive copy of tags
        this.tags = builder.tags != null ? new ArrayList<>(builder.tags) : new ArrayList<>();
        this.assigneeEmail = builder.assigneeEmail;
        this.customerVisible = builder.customerVisible;
        this.slaMinutes = builder.slaMinutes;
        this.source = builder.source;
    }

    // Getters - no setters
    public String getId() { return id; }
    public String getReporterEmail() { return reporterEmail; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getPriority() { return priority; }
    // Return unmodifiable list to prevent external mutation
    public List<String> getTags() { return Collections.unmodifiableList(tags); }
    public String getAssigneeEmail() { return assigneeEmail; }
    public boolean isCustomerVisible() { return customerVisible; }
    public Integer getSlaMinutes() { return slaMinutes; }
    public String getSource() { return source; }

    // Create a builder from this instance for updates
    public Builder toBuilder() {
        return new Builder()
                .id(this.id)
                .reporterEmail(this.reporterEmail)
                .title(this.title)
                .description(this.description)
                .priority(this.priority)
                .tags(this.tags)
                .assigneeEmail(this.assigneeEmail)
                .customerVisible(this.customerVisible)
                .slaMinutes(this.slaMinutes)
                .source(this.source);
    }

    @Override
    public String toString() {
        return "IncidentTicket{" +
                "id='" + id + '\'' +
                ", reporterEmail='" + reporterEmail + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", priority='" + priority + '\'' +
                ", tags=" + tags +
                ", assigneeEmail='" + assigneeEmail + '\'' +
                ", customerVisible=" + customerVisible +
                ", slaMinutes=" + slaMinutes +
                ", source='" + source + '\'' +
                '}';
    }

    // Builder Pattern
    public static class Builder {
        // Required fields
        private String id;
        private String reporterEmail;
        private String title;

        // Optional fields with defaults
        private String description;
        private String priority;
        private List<String> tags;
        private String assigneeEmail;
        private boolean customerVisible;
        private Integer slaMinutes;
        private String source;

        public Builder() {}

        // Static method to create builder from existing ticket
        public static Builder from(IncidentTicket ticket) {
            return ticket.toBuilder();
        }

        // Fluent setter methods
        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder reporterEmail(String reporterEmail) {
            this.reporterEmail = reporterEmail;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder priority(String priority) {
            this.priority = priority;
            return this;
        }

        public Builder tags(List<String> tags) {
            // Defensive copy
            this.tags = tags != null ? new ArrayList<>(tags) : null;
            return this;
        }

        public Builder addTag(String tag) {
            if (this.tags == null) {
                this.tags = new ArrayList<>();
            }
            this.tags.add(tag);
            return this;
        }

        public Builder assigneeEmail(String assigneeEmail) {
            this.assigneeEmail = assigneeEmail;
            return this;
        }

        public Builder customerVisible(boolean customerVisible) {
            this.customerVisible = customerVisible;
            return this;
        }

        public Builder slaMinutes(Integer slaMinutes) {
            this.slaMinutes = slaMinutes;
            return this;
        }

        public Builder source(String source) {
            this.source = source;
            return this;
        }

        // Build method with centralized validation
        public IncidentTicket build() {
            // Validate required fields
            Validation.requireTicketId(id);
            Validation.requireEmail(reporterEmail, "reporterEmail");
            Validation.requireNonBlank(title, "title");
            Validation.requireMaxLen(title, 80, "title");

            // Validate optional fields if provided
            if (assigneeEmail != null && !assigneeEmail.trim().isEmpty()) {
                Validation.requireEmail(assigneeEmail, "assigneeEmail");
            }

            if (priority != null) {
                Validation.requireOneOf(priority, "priority", "LOW", "MEDIUM", "HIGH", "CRITICAL");
            }

            if (slaMinutes != null) {
                Validation.requireRange(slaMinutes, 5, 7200, "slaMinutes");
            }

            return new IncidentTicket(this);
        }
    }
}
