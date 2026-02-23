public class InvoiceFormatter {
    public String format(Invoice invoice, double taxRate) {
        StringBuilder sb = new StringBuilder();
        sb.append("Invoice# ").append(invoice.invoiceId).append("\n");
        
        // Add line items
        for (OrderLine line : invoice.lines) {
            MenuItem item = invoice.menu.get(line.itemId);
            double lineTotal = item.price * line.qty;
            sb.append(String.format("- %s x%d = %.2f\n", item.name, line.qty, lineTotal));
        }
        
        sb.append(String.format("Subtotal: %.2f\n", invoice.subtotal));
        sb.append(String.format("Tax(%.0f%%): %.2f\n", taxRate * 100, invoice.tax));
        sb.append(String.format("Discount: -%.2f\n", invoice.discount));
        sb.append(String.format("TOTAL: %.2f\n", invoice.total));
        return sb.toString();
    }
}
