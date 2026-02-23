import java.util.List;
import java.util.Map;

public class CalculatePrice {
    public double price(List<OrderLine> lines, Map<String, MenuItem> menu) {
        double subtotal = 0.0;
        for (OrderLine line : lines) {
            MenuItem item = menu.get(line.itemId);
            double lineTotal = item.price * line.qty;
            subtotal += lineTotal;
        }
        return subtotal;
    }
}
