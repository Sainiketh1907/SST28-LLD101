import java.util.*;

public class DeviceRegistry {
    private final java.util.List<Object> devices = new ArrayList<>();

    public void add(Object d) { devices.add(d); }

    public Object getFirstOfType(String simpleName) {
        for (Object d : devices) {
            if (d.getClass().getSimpleName().equals(simpleName)) return d;
        }
        throw new IllegalStateException("Missing: " + simpleName);
    }

    public <T> T getFirstByCapability(Class<T> capability) {
        for (Object d : devices) {
            if (capability.isInstance(d)) {
                return capability.cast(d);
            }
        }
        throw new IllegalStateException("No device with capability: " + capability.getSimpleName());
    }
}
