package app.common;

public class TestStatus {

    private static final ThreadLocal<Boolean> failed = ThreadLocal.withInitial(() -> false);

    public static void markFailed() { failed.set(true); }
    public static boolean isFailed() { return failed.get(); }
    public static void clear() { failed.remove(); }

}
