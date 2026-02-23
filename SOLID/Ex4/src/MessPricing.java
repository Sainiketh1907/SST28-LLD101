public class MessPricing implements AddOnPricingStrategy {
    @Override
    public Money getFee() {
        return new Money(1000.0);
    }
}
