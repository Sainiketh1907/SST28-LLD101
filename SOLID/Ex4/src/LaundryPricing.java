public class LaundryPricing implements AddOnPricingStrategy {
    @Override
    public Money getFee() {
        return new Money(500.0);
    }
}
