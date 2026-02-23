public class StudentDiscount implements DiscountHandler {
    @Override
    public double discountAmount(String customerType, double subtotal, int distinctLines) {
        if ("student".equalsIgnoreCase(customerType)) {
            if (subtotal >= 180.0) return 10.0;
        }
        return 0.0;
    }
}
