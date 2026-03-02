public class Main {
    public static void main(String[] args) {
        System.out.println("=== Transport Booking ===");
        
        // Create concrete dependencies
        DistanceCalculable distanceCalculator = new DistanceCalculator();
        DriverAllocatable driverAllocator = new DriverAllocator();
        PaymentChargeable paymentGateway = new PaymentGateway();
        
        // Inject dependencies into the service
        TransportBookingService svc = new TransportBookingService(distanceCalculator, driverAllocator, paymentGateway);
        
        TripRequest req = new TripRequest("23BCS1010", new GeoPoint(12.97, 77.59), new GeoPoint(12.93, 77.62));
        svc.book(req);
    }
}
