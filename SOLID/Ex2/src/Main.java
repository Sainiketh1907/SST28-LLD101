import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Cafeteria Billing ===");

        InvoiceRepo repo = new FileStore();
        CalculatePrice priceCalculator = new CalculatePrice();
        TaxHandler taxHandler = new StandardTaxHandler();
        DiscountHandler discountHandler = new StudentDiscount();

        CafeteriaSystem sys = new CafeteriaSystem(repo, priceCalculator, taxHandler, discountHandler);

        sys.addToMenu(new MenuItem("M1", "Veg Thali", 80.00));
        sys.addToMenu(new MenuItem("C1", "Coffee", 30.00));
        sys.addToMenu(new MenuItem("S1", "Sandwich", 60.00));

        List<OrderLine> order = List.of(
                new OrderLine("M1", 2),
                new OrderLine("C1", 1)
        );

        sys.checkout("student", order);
    }
}
