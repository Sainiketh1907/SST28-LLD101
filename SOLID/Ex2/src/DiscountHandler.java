public interface DiscountHandler {
    double discountAmount(String customerType, double subtotal, int distinctLines);
}
