public class GymPricing implements AddOnPricingStrategy {
    @Override
    public Money getFee() {
        return new Money(300.0);
    }
}
