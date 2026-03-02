package com.example.tickets;

/**
 * Service layer that creates tickets.
 *
 * REFACTORED:
 * - Uses Builder pattern to create immutable tickets
 * - All validation centralized in Builder.build()
 * - Update methods return NEW ticket instances instead of mutating
 */
public class TicketService {

    public IncidentTicket createTicket(String id, String reporterEmail, String title) {
        // All validation now happens in Builder.build()
        return new IncidentTicket.Builder()
                .id(id)
                .reporterEmail(reporterEmail)
                .title(title)
                .priority("MEDIUM")
                .source("CLI")
                .customerVisible(false)
                .addTag("NEW")
                .build();
    }

    public IncidentTicket escalateToCritical(IncidentTicket ticket) {
        // Return a NEW ticket with updated priority and an added tag
        return ticket.toBuilder()
                .priority("CRITICAL")
                .addTag("ESCALATED")
                .build();
    }

    public IncidentTicket assign(IncidentTicket ticket, String assigneeEmail) {
        // Return a NEW ticket with updated assignee
        // Validation happens in build()
        return ticket.toBuilder()
                .assigneeEmail(assigneeEmail)
                .build();
    }
}
