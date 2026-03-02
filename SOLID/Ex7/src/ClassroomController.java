public class ClassroomController {
    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) { this.reg = reg; }

    public void startClass() {
        // Get projector by type and use its specific capabilities
        Object pjObj = reg.getFirstOfType("Projector");
        if (pjObj instanceof PowerControllable) {
            ((PowerControllable) pjObj).powerOn();
        }
        if (pjObj instanceof InputConnectable) {
            ((InputConnectable) pjObj).connectInput("HDMI-1");
        }

        // Get lights by type and use its specific capabilities
        Object lightsObj = reg.getFirstOfType("LightsPanel");
        if (lightsObj instanceof BrightnessControllable) {
            ((BrightnessControllable) lightsObj).setBrightness(60);
        }

        // Get AC by type and use its specific capabilities
        Object acObj = reg.getFirstOfType("AirConditioner");
        if (acObj instanceof TemperatureControllable) {
            ((TemperatureControllable) acObj).setTemperatureC(24);
        }

        // Get scanner by type and use its specific capabilities
        Object scanObj = reg.getFirstOfType("AttendanceScanner");
        if (scanObj instanceof ScanningCapable) {
            System.out.println("Attendance scanned: present=" + ((ScanningCapable) scanObj).scanAttendance());
        }
    }

    public void endClass() {
        System.out.println("Shutdown sequence:");
        Object pjObj = reg.getFirstOfType("Projector");
        if (pjObj instanceof PowerControllable) {
            ((PowerControllable) pjObj).powerOff();
        }
        
        Object lightsObj = reg.getFirstOfType("LightsPanel");
        if (lightsObj instanceof PowerControllable) {
            ((PowerControllable) lightsObj).powerOff();
        }
        
        Object acObj = reg.getFirstOfType("AirConditioner");
        if (acObj instanceof PowerControllable) {
            ((PowerControllable) acObj).powerOff();
        }
    }
}
