import java.util.*;

public class HostelFeeCalculator {
    private final FakeBookingRepo repo;

    public HostelFeeCalculator(FakeBookingRepo repo) { 
        this.repo = repo; 
    }

    public void process(BookingRequest req) {
        Money monthly = calculateMonthly(req);
        Money deposit = new Money(5000.00);

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000));
        repo.save(bookingId, req, monthly, deposit);
    }

    private Money calculateMonthly(BookingRequest req) {
        // Get room pricing strategy
        RoomPricingStrategy roomPricing = RoomPricingFactory.create(req.roomType);
        Money base = roomPricing.getMonthlyFee();

        // Add all add-ons
        Money addOnsTotal = new Money(0.0);
        for (AddOn addon : req.addOns) {
            AddOnPricingStrategy addOnPricing = AddOnPricingFactory.create(addon);
            addOnsTotal = addOnsTotal.plus(addOnPricing.getFee());
        }

        return base.plus(addOnsTotal);
    }
}
