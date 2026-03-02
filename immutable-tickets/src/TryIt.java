import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.List;

/**
 * REFACTORED demo that shows immutability in action.
 *
 * After refactor:
 * - Tickets are created using Builder
 * - No setters exist (mutations don't compile)
 * - Tags list is unmodifiable from outside
 * - Service "updates" return NEW ticket instances
 */
public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        // Create ticket using service (which uses Builder internally)
        IncidentTicket t = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("Created: " + t);

        // Demonstrate immutability: service methods return NEW instances
        IncidentTicket t2 = service.assign(t, "agent@example.com");
        System.out.println("\nOriginal ticket after assign (unchanged): " + t);
        System.out.println("New ticket with assignee: " + t2);

        IncidentTicket t3 = service.escalateToCritical(t2);
        System.out.println("\nPrevious ticket after escalate (unchanged): " + t2);
        System.out.println("New escalated ticket: " + t3);

        // Demonstrate tags are unmodifiable
        System.out.println("\n--- Attempting to modify tags externally ---");
        try {
            List<String> tags = t3.getTags();
            tags.add("SHOULD_FAIL");
            System.out.println("ERROR: Tags were modified (this should not happen!)");
        } catch (UnsupportedOperationException e) {
            System.out.println("SUCCESS: Tags list is unmodifiable - " + e.getClass().getSimpleName());
        }

        // Direct Builder usage example
        System.out.println("\n--- Creating ticket directly with Builder ---");
        IncidentTicket customTicket = new IncidentTicket.Builder()
                .id("TCK-2002")
                .reporterEmail("user@example.com")
                .title("Feature request: Dark mode")
                .description("Please add dark mode support")
                .priority("LOW")
                .addTag("FEATURE")
                .addTag("UI")
                .customerVisible(true)
                .slaMinutes(1440)
                .source("EMAIL")
                .build();
        System.out.println("Custom ticket: " + customTicket);

        // Using toBuilder() for updates
        System.out.println("\n--- Using toBuilder() for updates ---");
        IncidentTicket updated = customTicket.toBuilder()
                .priority("HIGH")
                .assigneeEmail("dev@example.com")
                .build();
        System.out.println("Original (unchanged): " + customTicket);
        System.out.println("Updated copy: " + updated);
    }
}
