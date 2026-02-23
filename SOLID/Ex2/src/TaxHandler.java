public interface TaxHandler {
    double tax(String customerType, double subtotal);
    double rate(String customerType);
}