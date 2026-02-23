/**
 * Base contract for all exporters.
 * 
 * Preconditions (what callers can assume):
 * - export() accepts any non-null ExportRequest
 * 
 * Postconditions (what exporters guarantee):
 * - Returns a non-null ExportResult with content-type and bytes
 * - No data loss; all fields exported in format-appropriate way
 * - No unexpected exceptions for valid input
 */
public abstract class Exporter {
    public abstract ExportResult export(ExportRequest req);
}
