public class StandardTaxHandler implements TaxHandler {
    private static final double STUDENT_TAX_RATE = 0.05;
    private static final double STAFF_TAX_RATE = 0.08;
    private static final double DEFAULT_TAX_RATE = 0.10;

    @Override
    public double tax(String customerType, double subtotal) {
        return subtotal * rate(customerType);
    }

    @Override
    public double rate(String customerType) {
        if ("student".equalsIgnoreCase(customerType)) return STUDENT_TAX_RATE;
        if ("staff".equalsIgnoreCase(customerType)) return STAFF_TAX_RATE;
        return DEFAULT_TAX_RATE;
    }
}
