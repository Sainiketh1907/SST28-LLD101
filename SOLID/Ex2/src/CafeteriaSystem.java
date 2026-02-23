import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final InvoiceRepo repo;
    private final CalculatePrice priceCalculator;
    private final TaxHandler taxHandler;
    private final DiscountHandler discountHandler;
    private int invoiceSeq = 1000;

    public CafeteriaSystem(InvoiceRepo repo, CalculatePrice priceCalculator,
                           TaxHandler taxHandler, DiscountHandler discountHandler) {
        this.repo = repo;
        this.priceCalculator = priceCalculator;
        this.taxHandler = taxHandler;
        this.discountHandler = discountHandler;
    }

    public void addToMenu(MenuItem item) {
        menu.put(item.id, item);
    }

    public void checkout(String customerType, List<OrderLine> lines) {
        String invoiceId = "INV-" + (++invoiceSeq);

        double subtotal = priceCalculator.price(lines, menu);

        double tax = taxHandler.tax(customerType, subtotal);

        double discount = discountHandler.discountAmount(customerType, subtotal, lines.size());
        double total = subtotal + tax - discount;

        Invoice invoice = new Invoice(invoiceId, lines, menu, subtotal, tax, discount, total);
        String formatted = new InvoiceFormatter().format(invoice, taxHandler.rate(customerType));
        System.out.print(formatted);

        repo.save(invoiceId, formatted);
        System.out.println("Saved invoice: " + invoiceId + " (lines=" + repo.countLines(invoiceId) + ")");
    }
}
