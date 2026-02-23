public class AddOnPricingFactory {
    public static AddOnPricingStrategy create(AddOn addon) {
        return switch (addon) {
            case MESS -> new MessPricing();
            case LAUNDRY -> new LaundryPricing();
            case GYM -> new GymPricing();
        };
    }
}
