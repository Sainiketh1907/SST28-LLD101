public class StaffDiscount implements DiscountHandler {
    @Override
    public double discountAmount(String customerType, double subtotal, int distinctLines) {
        if ("staff".equalsIgnoreCase(customerType)) {
            if (distinctLines >= 3) return 15.0;
            return 5.0;
        }
        return 0.0;
    }
}
