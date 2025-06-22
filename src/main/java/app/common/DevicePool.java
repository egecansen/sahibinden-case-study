package app.common;

import app.config.DeviceConfig;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class DevicePool {

    private static final DevicePool INSTANCE = new DevicePool();
    private final BlockingQueue<DeviceConfig> pool = new LinkedBlockingQueue<>();

    private DevicePool() {
        pool.add(new DeviceConfig("emulator-5554", "emulator-5554", 8200));
        pool.add(new DeviceConfig("emulator-5556", "emulator-5556", 8201));
    }
    public static DevicePool getInstance() { return INSTANCE; }

    public DeviceConfig acquireDevice() {
        try {
            return pool.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void releaseDevice(DeviceConfig device) {
        pool.offer(device);
    }

}
