import java.util.List;
import java.util.Map;

public class Invoice {
    public final String invoiceId;
    public final List<OrderLine> lines;
    public final Map<String, MenuItem> menu;
    public final double subtotal;
    public final double tax;
    public final double discount;
    public final double total;

    public Invoice(String invoiceId, List<OrderLine> lines, Map<String, MenuItem> menu,
                   double subtotal, double tax, double discount, double total) {
        this.invoiceId = invoiceId;
        this.lines = lines;
        this.menu = menu;
        this.subtotal = subtotal;
        this.tax = tax;
        this.discount = discount;
        this.total = total;
    }
}
