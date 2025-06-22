package app.common;

import app.config.DeviceConfig;
import context.ContextStore;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class DevicePool {

    private static final DevicePool INSTANCE = new DevicePool();
    private final BlockingQueue<DeviceConfig> pool = new LinkedBlockingQueue<>();

    private DevicePool() {
        pool.add(new DeviceConfig("emulator-5554", "emulator-5554", 8203));
        pool.add(new DeviceConfig("emulator-5556", "emulator-5556", 8204));
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

    public DeviceConfig getDefaultDevice() {
        return new DeviceConfig(ContextStore.get("default-device-name"), ContextStore.get("default-udid"), Integer.parseInt(ContextStore.get("default-port")));
    }

}
